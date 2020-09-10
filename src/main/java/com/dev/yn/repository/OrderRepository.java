package com.dev.yn.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.dev.yn.domain.Order;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
	
	private final EntityManager em;
	
	public void save(Order order) {
		em.persist(order);
	}
	
	//검색
//	public List<Order> findAll(OrderSearch orderSearch){
//		return em.createQuery("select o from Order o", Order.class).getResultList();
//	}
	
	public Order findOne(Long orderId) {
		return em.find(Order.class, orderId);
	}
}
