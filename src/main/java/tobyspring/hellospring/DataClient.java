package tobyspring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.hellospring.data.OrderRepository;
import tobyspring.hellospring.order.Order;

import java.math.BigDecimal;

public class DataClient {
	public static void main(String[] args) {
		BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
		OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);
		JpaTransactionManager jtm = beanFactory.getBean(JpaTransactionManager.class);

		try {
			new TransactionTemplate(jtm).execute(status -> {
				Order order = new Order("100", BigDecimal.TEN);
				orderRepository.save(order);

				Order order2 = new Order("100", BigDecimal.ONE);
				orderRepository.save(order2);

				return null;
			});
		} catch (DataIntegrityViolationException e) {
			System.out.println("주민번호 중복 복구 작업");
		}
	}
}
