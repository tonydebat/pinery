package ca.on.oicr.pinery.service;

import java.util.List;

import ca.on.oicr.pinery.api.Order;

import org.joda.time.DateTime;
			
public interface OrderService {

   public List<Order> getOrders(DateTime after);

   public Order getOrder(Integer id);
}
