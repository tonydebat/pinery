package ca.on.oicr.pinery.ws;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.junit.Ignore;
import org.junit.Test;

import ca.on.oicr.pinery.api.Order;
import ca.on.oicr.pinery.api.OrderSample;
import ca.on.oicr.pinery.api.Run;
import ca.on.oicr.pinery.api.RunPosition;
import ca.on.oicr.pinery.api.RunSample;
import ca.on.oicr.pinery.lims.DefaultOrder;
import ca.on.oicr.pinery.lims.DefaultOrderSample;
import ca.on.oicr.pinery.lims.DefaultRun;
import ca.on.oicr.pinery.lims.DefaultRunPosition;
import ca.on.oicr.pinery.lims.DefaultRunSample;
import ca.on.oicr.pinery.service.OrderService;
import ca.on.oicr.pinery.service.RunService;
import ca.on.oicr.ws.dto.AttributeDto;
import ca.on.oicr.ws.dto.OrderDto;
import ca.on.oicr.ws.dto.OrderDtoSample;
import ca.on.oicr.ws.dto.RunDto;
import ca.on.oicr.ws.dto.RunDtoPosition;
import ca.on.oicr.ws.dto.RunDtoSample;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ResourceTest {

   @Test
   public void test_Resource_1() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("order/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/order/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("order/").build()).thenReturn(new URI("http://test/order/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));

      OrderService orderService = mock(OrderService.class);
      when(orderService.getOrder(1)).thenReturn(getOrder());
      OrderResource orderResource = new OrderResource();
      orderResource.setOrderService(orderService);
      orderResource.setUriInfo(uriInfoMock);
      OrderDto orderDto = orderResource.getOrder(1);

      assertThat(orderDto, is(notNullValue()));
   }

   @Test
   public void test_Resource_2() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("order/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/order/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("order/").build()).thenReturn(new URI("http://test/order/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));

      OrderService orderService = mock(OrderService.class);
      when(orderService.getOrder(1)).thenReturn(getOrder());
      OrderResource orderResource = new OrderResource();
      orderResource.setOrderService(orderService);
      orderResource.setUriInfo(uriInfoMock);
      OrderDto orderDto = orderResource.getOrder(1);
      Date date = new Date();
      SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

      assertThat(orderDto.getCreatedDate(), is(sf.format(date)));
   }

   @Test
   public void test_Resource_3() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("order/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/order/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("order/").build()).thenReturn(new URI("http://test/order/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));

      OrderService orderService = mock(OrderService.class);
      when(orderService.getOrder(1)).thenReturn(getOrder());
      OrderResource orderResource = new OrderResource();
      orderResource.setOrderService(orderService);
      orderResource.setUriInfo(uriInfoMock);
      OrderDto orderDto = orderResource.getOrder(1);

      assertThat(orderDto.getId(), is(2));
   }

   @Test
   public void test_Resource_4() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("order/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/order/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("order/").build()).thenReturn(new URI("http://test/order/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));

      OrderService orderService = mock(OrderService.class);
      when(orderService.getOrder(1)).thenReturn(getOrder());
      OrderResource orderResource = new OrderResource();
      orderResource.setOrderService(orderService);
      orderResource.setUriInfo(uriInfoMock);
      OrderDto orderDto = orderResource.getOrder(1);
      Date date = new Date();
      SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

      assertThat(orderDto.getModifiedDate(), is(sf.format(date)));
   }

   @Test
   public void test_Resource_5() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("order/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/order/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("order/").build()).thenReturn(new URI("http://test/order/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));

      OrderService orderService = mock(OrderService.class);
      when(orderService.getOrder(1)).thenReturn(getOrder());
      OrderResource orderResource = new OrderResource();
      orderResource.setOrderService(orderService);
      orderResource.setUriInfo(uriInfoMock);
      OrderDto orderDto = orderResource.getOrder(1);

      assertThat(orderDto.getPlatform(), is("Illumina HiSeq 2000"));
   }

   @Test
   public void test_Resource_6() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("order/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/order/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("order/").build()).thenReturn(new URI("http://test/order/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));

      OrderService orderService = mock(OrderService.class);
      when(orderService.getOrder(1)).thenReturn(getOrder());
      OrderResource orderResource = new OrderResource();
      orderResource.setOrderService(orderService);
      orderResource.setUriInfo(uriInfoMock);
      OrderDto orderDto = orderResource.getOrder(1);

      assertThat(orderDto.getProject(), is("HALT"));
   }

   @Test
   public void test_Resource_7() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("order/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/order/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("order/").build()).thenReturn(new URI("http://test/order/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));

      OrderService orderService = mock(OrderService.class);
      when(orderService.getOrder(1)).thenReturn(getOrder());
      OrderResource orderResource = new OrderResource();
      orderResource.setOrderService(orderService);
      orderResource.setUriInfo(uriInfoMock);
      OrderDto orderDto = orderResource.getOrder(1);

      assertThat(orderDto.getStatus(), is("Complete"));
   }

   @Test
   public void test_Resource_8() throws Exception {

      boolean status;
      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("order/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/order/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("order/").build()).thenReturn(new URI("http://test/order/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));

      OrderService orderService = mock(OrderService.class);
      when(orderService.getOrder(1)).thenReturn(getOrder());
      OrderResource orderResource = new OrderResource();
      orderResource.setOrderService(orderService);
      orderResource.setUriInfo(uriInfoMock);

      AttributeDto attributeDto = new AttributeDto();
      Set<AttributeDto> attributeDtoSet = Sets.newHashSet();
      OrderDtoSample orderDtoSample = new OrderDtoSample();
      attributeDto.setName("read length");
      attributeDtoSet.add(attributeDto);
      orderDtoSample.setAttributes(attributeDtoSet);
      status = attributeContainsName(orderDtoSample.getAttributes(), attributeDto.getName());

      assertThat(status, is(true));
   }

   @Test
   public void test_Resource_9() throws Exception {

      boolean status;
      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("order/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/order/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("order/").build()).thenReturn(new URI("http://test/order/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));

      OrderService orderService = mock(OrderService.class);
      when(orderService.getOrder(1)).thenReturn(getOrder());
      OrderResource orderResource = new OrderResource();
      orderResource.setOrderService(orderService);
      orderResource.setUriInfo(uriInfoMock);

      AttributeDto attributeDto = new AttributeDto();
      Set<AttributeDto> attributeDtoSet = Sets.newHashSet();
      OrderDtoSample orderDtoSample = new OrderDtoSample();
      attributeDto.setValue("2x101");
      attributeDtoSet.add(attributeDto);
      orderDtoSample.setAttributes(attributeDtoSet);
      status = attributeContainsValue(orderDtoSample.getAttributes(), attributeDto.getValue());

      assertThat(status, is(true));
   }

   @Test
   public void test_Resource_10() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("order")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("order/")).thenReturn(uriBuilderMock);

      when(uriInfoMock.getBaseUriBuilder().build()).thenReturn(new URI("http://test/order"));
      when(uriInfoMock.getBaseUriBuilder().path("order").build()).thenReturn(new URI("http://test/order"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));

      OrderService orderService = mock(OrderService.class);
      when(orderService.getOrder()).thenReturn(getListOrder());
      OrderResource orderResource = new OrderResource();
      orderResource.setOrderService(orderService);
      orderResource.setUriInfo(uriInfoMock);
      List<OrderDto> orderDto = orderResource.getOrders();

      assertThat(orderDto, is(notNullValue()));
   }

   @Test
   public void test_Resource_11() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("order")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("order/")).thenReturn(uriBuilderMock);

      when(uriInfoMock.getBaseUriBuilder().build()).thenReturn(new URI("http://test/order"));
      when(uriInfoMock.getBaseUriBuilder().path("order").build()).thenReturn(new URI("http://test/order"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample"));

      OrderService orderService = mock(OrderService.class);
      when(orderService.getOrder()).thenReturn(getListOrder());
      OrderResource orderResource = new OrderResource();
      orderResource.setOrderService(orderService);
      orderResource.setUriInfo(uriInfoMock);

      List<OrderDto> originalListOrderDto = orderResource.getOrders();
      OrderDto orderDto = new OrderDto();
      List<OrderDto> listOrderDto = Lists.newArrayList();
      Date date = new Date();
      SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

      Set<OrderDtoSample> orderDtoSampleSet = Sets.newHashSet();
      OrderDtoSample orderDtoSampleObj = new OrderDtoSample();
      orderDtoSampleSet.add(orderDtoSampleObj);

      orderDto.setCreatedDate(sf.format(date));
      orderDto.setId(2);
      orderDto.setModifiedDate(sf.format(date));
      orderDto.setPlatform("Illumina HiSeq 2000");
      orderDto.setProject("HALT");
      orderDto.setStatus("Complete");
      orderDto.setUrl("http://test/sample/2");

      for (OrderDtoSample orderDtoSample : orderDtoSampleSet) {
         orderDtoSample.setId(2);
      }

      orderDto.setSamples(orderDtoSampleSet);

      listOrderDto.add(orderDto);

      assertThat(originalListOrderDto, containsInAnyOrder(listOrderDto.toArray()));
   }

   private Order getOrder() {
      Order order = new DefaultOrder();
      Date date = new Date();

      Set<OrderSample> orderSampleSet = Sets.newHashSet();
      OrderSample orderSampleObj = new DefaultOrderSample();
      orderSampleSet.add(orderSampleObj);

      order.setCreatedDate(date);
      order.setId(2);
      order.setModifiedDate(date);
      order.setPlatform("Illumina HiSeq 2000");
      order.setProject("HALT");
      order.setStatus("Complete");
      for (OrderSample orderSample : orderSampleSet) {
         orderSample.setId(45);
      }
      order.setSample(orderSampleSet);
      return order;
   }

   public boolean attributeContainsName(Set<AttributeDto> attribute, String attributeName) {

      for (AttributeDto dto : attribute) {
         if (dto.getName().equals(attributeName)) {
            return true;
         }
      }
      return false;
   }

   public boolean attributeContainsValue(Set<AttributeDto> attribute, String attributeValue) {

      for (AttributeDto dto : attribute) {
         if (dto.getValue().equals(attributeValue)) {
            return true;
         }
      }
      return false;
   }

   private List<Order> getListOrder() {
      Order order = new DefaultOrder();
      List<Order> list = Lists.newArrayList();
      Date date = new Date();

      Set<OrderSample> orderSampleSet = Sets.newHashSet();
      OrderSample orderSampleObj = new DefaultOrderSample();
      orderSampleSet.add(orderSampleObj);

      order.setCreatedDate(date);
      order.setId(2);
      order.setModifiedDate(date);
      order.setPlatform("Illumina HiSeq 2000");
      order.setProject("HALT");
      order.setStatus("Complete");

      for (OrderSample orderSample : orderSampleSet) {
         orderSample.setId(2);
      }
      order.setSample(orderSampleSet);

      list.add(order);

      return list;
   }

   @Test
   public void test_Resource__Run_1() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/sequencerrun/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/").build()).thenReturn(new URI("http://test/sequencerrun/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));
      when(uriInfoMock.getBaseUriBuilder().path("instrument/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("instrument/").build()).thenReturn(new URI("http://test/instrument/1"));

      RunService runService = mock(RunService.class);
      when(runService.getRun(1)).thenReturn(getRun());
      RunResource runResource = new RunResource();
      runResource.setRunService(runService);
      runResource.setUriInfo(uriInfoMock);
      RunDto runDto = runResource.getRun(1);

      assertThat(runDto, is(notNullValue()));
   }

   @Test
   public void test_Resource__Run_2() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/sequencerrun/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/").build()).thenReturn(new URI("http://test/sequencerrun/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));
      when(uriInfoMock.getBaseUriBuilder().path("instrument/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("instrument/").build()).thenReturn(new URI("http://test/instrument/1"));

      RunService runService = mock(RunService.class);
      when(runService.getRun(1)).thenReturn(getRun());
      RunResource runResource = new RunResource();
      runResource.setRunService(runService);
      runResource.setUriInfo(uriInfoMock);
      RunDto runDto = runResource.getRun(1);
      Date date = new Date();
      SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

      assertThat(runDto.getCreatedDate(), is(sf.format(date)));
   }

   @Test
   public void test_Resource_Run_3() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/sequencerrun/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/").build()).thenReturn(new URI("http://test/sequencerrun/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));
      when(uriInfoMock.getBaseUriBuilder().path("instrument/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("instrument/").build()).thenReturn(new URI("http://test/instrument/1"));

      RunService runService = mock(RunService.class);
      when(runService.getRun(1)).thenReturn(getRun());
      RunResource runResource = new RunResource();
      runResource.setRunService(runService);
      runResource.setUriInfo(uriInfoMock);
      RunDto runDto = runResource.getRun(1);

      assertThat(runDto.getId(), is(2));
   }

   @Test
   public void test_Resource_Run_4() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/sequencerrun/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/").build()).thenReturn(new URI("http://test/sequencerrun/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));
      when(uriInfoMock.getBaseUriBuilder().path("instrument/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("instrument/").build()).thenReturn(new URI("http://test/instrument/1"));

      RunService runService = mock(RunService.class);
      when(runService.getRun(1)).thenReturn(getRun());
      RunResource runResource = new RunResource();
      runResource.setRunService(runService);
      runResource.setUriInfo(uriInfoMock);
      RunDto runDto = runResource.getRun(1);

      assertThat(runDto.getState(), is("Complete"));
   }

   @Test
   public void test_Resource_Run_5() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/sequencerrun/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/").build()).thenReturn(new URI("http://test/sequencerrun/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));
      when(uriInfoMock.getBaseUriBuilder().path("instrument/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("instrument/").build()).thenReturn(new URI("http://test/instrument/1"));

      RunService runService = mock(RunService.class);
      when(runService.getRun(1)).thenReturn(getRun());
      RunResource runResource = new RunResource();
      runResource.setRunService(runService);
      runResource.setUriInfo(uriInfoMock);
      RunDto runDto = runResource.getRun(1);

      assertThat(runDto.getName(), is("130906_SN203_0196_AC2D4DACXX"));
   }

   @Test
   public void test_Resource_Run_6() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/sequencerrun/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/").build()).thenReturn(new URI("http://test/sequencerrun/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));
      when(uriInfoMock.getBaseUriBuilder().path("instrument/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("instrument/").build()).thenReturn(new URI("http://test/instrument/1"));

      RunService runService = mock(RunService.class);
      when(runService.getRun(1)).thenReturn(getRun());
      RunResource runResource = new RunResource();
      runResource.setRunService(runService);
      runResource.setUriInfo(uriInfoMock);
      RunDto runDto = runResource.getRun(1);

      assertThat(runDto.getBarcode(), is("C2D8J"));
   }

   @Test
   public void test_Resource_Run_7() throws Exception {

      boolean status;
      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/sequencerrun/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/").build()).thenReturn(new URI("http://test/sequencerrun/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));

      RunService runService = mock(RunService.class);
      when(runService.getRun(1)).thenReturn(getRun());
      RunResource runResource = new RunResource();
      runResource.setRunService(runService);
      runResource.setUriInfo(uriInfoMock);

      RunDtoSample runDtoSample = new RunDtoSample();
      Set<RunDtoSample> runDtoSampleSet = Sets.newHashSet();
      RunDtoPosition runDtoPosition = new RunDtoPosition();
      runDtoSample.setBarcode("C2D8J");
      runDtoSampleSet.add(runDtoSample);
      runDtoPosition.setSamples(runDtoSampleSet);
      status = runContainsBarcode(runDtoPosition.getSamples(), runDtoSample.getBarcode());

      assertThat(status, is(true));
   }

   @Test
   public void test_Resource_Run_8() throws Exception {

      boolean status;
      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/sequencerrun/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/").build()).thenReturn(new URI("http://test/sequencerrun/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));

      RunService runService = mock(RunService.class);
      when(runService.getRun(1)).thenReturn(getRun());
      RunResource runResource = new RunResource();
      runResource.setRunService(runService);
      runResource.setUriInfo(uriInfoMock);

      RunDtoSample runDtoSample = new RunDtoSample();
      Set<RunDtoSample> runDtoSampleSet = Sets.newHashSet();
      RunDtoPosition runDtoPosition = new RunDtoPosition();
      runDtoSample.setUrl("https://pinery.res.oicr.on.ca:8443/pinery/sample/45");
      runDtoSampleSet.add(runDtoSample);
      runDtoPosition.setSamples(runDtoSampleSet);
      status = runContainsUrl(runDtoPosition.getSamples(), runDtoSample.getUrl());

      assertThat(status, is(true));
   }

   @Test
   public void test_Resource_Run_9() throws Exception {

      boolean status;
      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getAbsolutePathBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);

      when(uriInfoMock.getAbsolutePathBuilder().build()).thenReturn(new URI("http://test/sequencerrun/1"));
      when(uriInfoMock.getAbsolutePathBuilder().path("sequencerrun/").build()).thenReturn(new URI("http://test/sequencerrun/1"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));

      RunService runService = mock(RunService.class);
      when(runService.getRun(1)).thenReturn(getRun());
      RunResource runResource = new RunResource();
      runResource.setRunService(runService);
      runResource.setUriInfo(uriInfoMock);

      RunDtoSample runDtoSample = new RunDtoSample();
      Set<RunDtoSample> runDtoSampleSet = Sets.newHashSet();
      RunDtoPosition runDtoPosition = new RunDtoPosition();
      runDtoSample.setId(12);
      runDtoSampleSet.add(runDtoSample);
      runDtoPosition.setSamples(runDtoSampleSet);
      status = runContainsId(runDtoPosition.getSamples(), runDtoSample.getId());

      assertThat(status, is(true));
   }

   @Test
   public void test_Resource_Run_10() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sequencerrun")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sequencerrun/")).thenReturn(uriBuilderMock);

      when(uriInfoMock.getBaseUriBuilder().build()).thenReturn(new URI("http://test/sequencerrun"));
      when(uriInfoMock.getBaseUriBuilder().path("sequencerrun").build()).thenReturn(new URI("http://test/sequencerruns"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sequenncerrunrun")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sequenncerrunrun").build()).thenReturn(new URI("http://test/sequenncerrunrun/1"));
      when(uriInfoMock.getBaseUriBuilder().path("instrument/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("instrument/").build()).thenReturn(new URI("http://test/instrument/1"));

      RunService runService = mock(RunService.class);
      when(runService.getRun()).thenReturn(getListRun());
      RunResource runResource = new RunResource();
      runResource.setRunService(runService);
      runResource.setUriInfo(uriInfoMock);
      List<RunDto> runDto = runResource.getRuns();

      assertThat(runDto, is(notNullValue()));
   }

   @Ignore
   public void test_Resource_Run_11() throws Exception {

      UriInfo uriInfoMock = mock(UriInfo.class);
      UriBuilder uriBuilderMock = mock(UriBuilder.class);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sequencerrun")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder()).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sequencerrun/")).thenReturn(uriBuilderMock);

      when(uriInfoMock.getBaseUriBuilder().build()).thenReturn(new URI("http://test/sequencerrun"));
      when(uriInfoMock.getBaseUriBuilder().path("sequencerrun").build()).thenReturn(new URI("http://test/sequencerrun"));

      when(uriInfoMock.getBaseUriBuilder().path("user/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("user/").build()).thenReturn(new URI("http://test/user/1"));
      when(uriInfoMock.getBaseUriBuilder().path("sample/")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("sample/").build()).thenReturn(new URI("http://test/sample"));
      when(uriInfoMock.getBaseUriBuilder().path("run")).thenReturn(uriBuilderMock);
      when(uriInfoMock.getBaseUriBuilder().path("run").build()).thenReturn(new URI("http://test/run/"));

      RunService runService = mock(RunService.class);
      when(runService.getRun()).thenReturn(getListRun());
      RunResource runResource = new RunResource();
      runResource.setRunService(runService);
      runResource.setUriInfo(uriInfoMock);

      List<RunDto> originalListRunDto = runResource.getRuns();
      RunDto runDto = new RunDto();
      List<RunDto> listRunDto = Lists.newArrayList();
      Date date = new Date();
      SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

      runDto.setCreatedDate(sf.format(date));
      runDto.setId(2);
      runDto.setName("130906_SN804_0130_AC2D8JACXX");
      runDto.setBarcode("C2D8J");
      runDto.setState("Complete");
      runDto.setUrl("http://test/run//2");

      RunDtoPosition runPosition = new RunDtoPosition();
      Set<RunDtoPosition> positions = Sets.newHashSet();
      positions.add(runPosition);

      RunDtoSample sampleObj = new RunDtoSample();
      Set<RunDtoSample> runSample = Sets.newHashSet();
      runSample.add(sampleObj);

      for (RunDtoPosition position : positions) {
         position.setPosition(54);
         for (RunDtoSample sample : runSample) {
            sample.setBarcode("ABC");
            sample.setId(45);
            sample.setUrl("http://test/run/45");
         }
         position.setSamples(runSample);
      }

      runDto.setPositions(positions);

      listRunDto.add(runDto);

      assertThat(originalListRunDto, containsInAnyOrder(listRunDto.toArray()));
   }

   private Run getRun() {
      Run run = new DefaultRun();
      Date date = new Date();
      run.setCreatedDate(date);
      run.setId(2);
      run.setBarcode("C2D8J");
      run.setName("130906_SN203_0196_AC2D4DACXX");
      run.setState("Complete");

      RunPosition runPosition = new DefaultRunPosition();
      Set<RunPosition> positions = Sets.newHashSet();
      positions.add(runPosition);

      RunSample sampleObj = new DefaultRunSample();
      Set<RunSample> runSample = Sets.newHashSet();
      runSample.add(sampleObj);

      for (RunPosition position : positions) {
         position.setPosition(54);
         for (RunSample sample : runSample) {
            sample.setBarcode("ABC");
            sample.setId(45);
         }
         position.setRunSample(runSample);
      }

      run.setSample(positions);
      return run;
   }

   public boolean runContainsBarcode(Set<RunDtoSample> run, String runBarcode) {

      for (RunDtoSample dto : run) {
         if (dto.getBarcode().equals(runBarcode)) {
            return true;
         }
      }
      return false;
   }

   public boolean runContainsUrl(Set<RunDtoSample> run, String runUrl) {

      for (RunDtoSample dto : run) {
         if (dto.getUrl().equals(runUrl)) {
            return true;
         }
      }
      return false;
   }

   public boolean runContainsId(Set<RunDtoSample> run, Integer runId) {

      for (RunDtoSample dto : run) {
         if (dto.getId() == (runId)) {
            return true;
         }
      }
      return false;
   }

   private List<Run> getListRun() {
      Run run = new DefaultRun();
      List<Run> list = Lists.newArrayList();
      Date date = new Date();

      run.setCreatedDate(date);
      run.setId(2);
      run.setName("130906_SN804_0130_AC2D8JACXX");
      run.setBarcode("C2D8J");
      run.setState("Complete");

      RunPosition runPosition = new DefaultRunPosition();
      Set<RunPosition> positions = Sets.newHashSet();
      positions.add(runPosition);

      RunSample sampleObj = new DefaultRunSample();
      Set<RunSample> runSample = Sets.newHashSet();
      runSample.add(sampleObj);

      for (RunPosition position : positions) {
         position.setPosition(54);
         for (RunSample sample : runSample) {
            sample.setBarcode("ABC");
            sample.setId(45);
         }
         position.setRunSample(runSample);
      }

      run.setSample(positions);

      list.add(run);

      return list;
   }
}
