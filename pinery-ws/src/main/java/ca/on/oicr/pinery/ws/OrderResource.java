package ca.on.oicr.pinery.ws;

import java.net.URI;
import java.util.Set;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.on.oicr.pinery.api.Order;
import ca.on.oicr.pinery.service.OrderService;
import ca.on.oicr.ws.dto.Dtos;
import ca.on.oicr.ws.dto.OrderDto;
import ca.on.oicr.ws.dto.OrderDtoSample;

import com.google.common.collect.Lists;

import org.joda.time.DateTime;

@Component
@Path("/")
public class OrderResource {

   private static final Logger log = LoggerFactory.getLogger(OrderResource.class);

   @Context
   private UriInfo uriInfo;

   @Autowired
   private OrderService orderService;

   void setUriInfo(UriInfo uriInfo) {
      this.uriInfo = uriInfo;
   }

   void setOrderService(OrderService orderService) {
      this.orderService = orderService;
   }

   @GET
   @Produces({ "application/json" })
   @Path("/orders")
   public List<OrderDto> getOrders(@QueryParam("user") Set<String> users,@QueryParam("modified_before") String modified_before, 
											  @QueryParam("modified_after") String modified_after,@QueryParam("created_before") String created_before, 
											  @QueryParam("created_after") String created_after) {

		DateTime mod_before = null;
		DateTime mod_after = null;
		DateTime crt_before = null;
		DateTime crt_after = null;

      for (String user : users) {
         log.debug("user={}", user);
      }
	try{
		//TODO: DRY code
		if(modified_before != null && !modified_before.equals("")) {
			mod_before = DateTime.parse(modified_before);			
		}
		if(modified_after != null && !modified_after.equals("")) {
			mod_after = DateTime.parse(modified_after);			
		}
		if(created_before != null && !created_before.equals("")) {
			crt_before = DateTime.parse(created_before);			
		}
		if(created_after != null && !created_after.equals("")) {
			crt_after = DateTime.parse(created_after);			
		}

	}
	catch (IllegalArgumentException e) {
		throw new BadRequestException("Invalid date format in parameter(s) [modified/created before/after]. Use IS08601 formatting. " + e.getMessage(), e);
	}
      List<Order> orders = orderService.getOrders(users, mod_before, mod_after, crt_before, crt_after);
      if (orders.isEmpty()) {
         throw new NotFoundException("", Response.noContent().status(Status.NOT_FOUND).build());
      }
      List<OrderDto> result = Lists.newArrayList();
      final URI baseUri = uriInfo.getBaseUriBuilder().path("order").build();
      for (Order order : orders) {
         OrderDto dto = Dtos.asDto(order);
         dto.setUrl(baseUri + "/" + dto.getId().toString());
         addSampleUrl(dto);
         addOrders(order, dto);
         result.add(dto);
      }
      return result;
   }

   @GET
   @Produces({ "application/json" })
   @Path("/order/{id}")
   public OrderDto getOrder(@PathParam("id") Integer id) {

      Order order = orderService.getOrder(id);
      OrderDto dto = Dtos.asDto(order);
      final URI uri = uriInfo.getAbsolutePathBuilder().build();
      dto.setUrl(uri.toString());
      addSampleUrl(dto);
      addOrders(order, dto);
      return dto;
   }

   private OrderDto addSampleUrl(OrderDto dto) {
      final URI baseUriSample = uriInfo.getBaseUriBuilder().path("sample/").build();

      if (dto != null && dto.getSamples() != null) {
         for (OrderDtoSample orderDtoSample : dto.getSamples()) {
            orderDtoSample.setUrl(baseUriSample + orderDtoSample.getId().toString());
         }
      }
      return dto;
   }

   private void addOrders(Order order, OrderDto dto) {

      final URI baseUri = uriInfo.getBaseUriBuilder().path("user/").build();

      if (order.getCreatedById() != null) {
         dto.setCreatedByUrl(baseUri + order.getCreatedById().toString());
      }

      if (order.getModifiedById() != null) {
         dto.setModifiedByUrl(baseUri + order.getModifiedById().toString());
      }

   }
}
