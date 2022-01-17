package com.dev.yn.repository.order.simplequery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {
	
	private final EntityManager em;
	public List<OrderSimpleQueryDto> findOrderDto() {
		return em.createQuery(
				"select new com.dev.yn.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
						" from Order o" +
						" join o.member m" +
						" join o.delivery d", OrderSimpleQueryDto.class)
				.getResultList();
	}
}
