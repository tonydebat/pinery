package ca.on.oicr.pinery.service;

import java.util.Set;
import java.util.List;

import org.joda.time.DateTime;

import ca.on.oicr.pinery.api.Order;
import ca.on.oicr.pinery.api.User;


			
public interface OrderService {

   public List<Order> getOrders(Set<String> users, DateTime before, DateTime after);
	
	public List<User> getUsers();

   public Order getOrder(Integer id);

}
