package ca.on.oicr.pinery.ws;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.on.oicr.pinery.api.Order;
import ca.on.oicr.pinery.api.OrderSample;
import ca.on.oicr.pinery.service.OrderService;
import ca.on.oicr.ws.dto.Dtos;
import ca.on.oicr.ws.dto.OrderDto;
import ca.on.oicr.ws.dto.OrderDtoSample;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

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
   public List<OrderDto> getOrders() {
      List<Order> orders = orderService.getOrder();
      if (orders.isEmpty()) {
         throw new NotFoundException("", Response.noContent().status(Status.NOT_FOUND).build());
      }
      List<OrderDto> result = Lists.newArrayList();
      final URI baseUri = uriInfo.getBaseUriBuilder().path("order").build();
      for (Order order : orders) {
         OrderDto dto = Dtos.asDto(order);
         dto.setUrl(baseUri + "/" + dto.getId().toString());
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
      addOrders(order, dto);
      return dto;
   }

   private void addOrders(Order order, OrderDto dto) {

      final URI baseUri = uriInfo.getBaseUriBuilder().path("user/").build();
      final URI baseUriSample = uriInfo.getBaseUriBuilder().path("sample/").build();

      if (order.getCreatedById() != null) {
         dto.setCreatedByUrl(baseUri + order.getCreatedById().toString());
      }

      if (order.getModifiedById() != null) {
         dto.setModifiedByUrl(baseUri + order.getModifiedById().toString());
      }

      if (order.getCreatedById() != null) {

         Set<OrderSample> tempSet = Sets.newHashSet();
         tempSet = order.getSamples();

         if (dto.getSamples() != null) {
            for (OrderDtoSample orderDtoSample : dto.getSamples()) {
               for (OrderSample orderSample : tempSet) {
                  orderDtoSample.setUrl(baseUriSample + orderSample.getId().toString());
               }
            }
         }

      }

   }
}