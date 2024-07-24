package tobyspring.hellospring.order;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.hellospring.data.JpaOrderRepository;

import java.math.BigDecimal;

@Service
public class OrderService {
	private final OrderRepository jpaOrderRepository;
	private final JpaTransactionManager jtm;

	public OrderService(OrderRepository jpaOrderRepository, JpaTransactionManager jtm) {
		this.jpaOrderRepository = jpaOrderRepository;
		this.jtm = jtm;
	}

	public Order createOrder(String no, BigDecimal total) {
		Order order = new Order(no, total);

		return new TransactionTemplate(jtm).execute(status -> {
			this.jpaOrderRepository.save(order);
			return order;
		});
	}
}
