package tobyspring.hellospring.order;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.hellospring.data.OrderRepository;

import java.math.BigDecimal;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final JpaTransactionManager jtm;

	public OrderService(OrderRepository orderRepository, JpaTransactionManager jtm) {
		this.orderRepository = orderRepository;
		this.jtm = jtm;
	}

	public Order createOrder(String no, BigDecimal total) {
		Order order = new Order(no, total);

		return new TransactionTemplate(jtm).execute(status -> {
			this.orderRepository.save(order);
			return order;
		});
	}
}
