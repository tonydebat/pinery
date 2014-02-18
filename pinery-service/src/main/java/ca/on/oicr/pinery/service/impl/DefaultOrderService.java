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
   public List<Order> getOrders(Set<String> users, DateTime before, DateTime after) {
      return lims.getOrders(users,before, after);
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
