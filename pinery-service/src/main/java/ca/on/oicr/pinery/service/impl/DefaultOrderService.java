package ca.on.oicr.pinery.service.impl;

import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.on.oicr.pinery.api.Lims;
import ca.on.oicr.pinery.api.Order;
import ca.on.oicr.pinery.api.User;
import ca.on.oicr.pinery.service.OrderService;

@Service
public class DefaultOrderService implements OrderService {

   @Autowired
   private Lims lims;

   @Override
   public List<Order> getOrders(Set<String> users, DateTime modified_before, DateTime modified_after, DateTime created_before, DateTime created_after) {
      return lims.getOrders(users,modified_before, modified_after, created_before, created_after);
   }

	@Override
	public List<User> getUsers() {
		return lims.getUsers();
	}
   @Override
   public Order getOrder(Integer id) {
      return lims.getOrder(id);
   }

}
