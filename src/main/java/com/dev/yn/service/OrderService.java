package com.dev.yn.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.yn.domain.Delivery;
import com.dev.yn.domain.Member;
import com.dev.yn.domain.Order;
import com.dev.yn.domain.OrderItem;
import com.dev.yn.domain.item.Item;
import com.dev.yn.repository.ItemRepository;
import com.dev.yn.repository.MemberRepository;
import com.dev.yn.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	/**
	 *주문 
	 */
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		
		//엔티티 조회
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);
		
		//배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAdress());
		
		//주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		
		//주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);
		
		//주문 저장
		orderRepository.save(order);
		
		return order.getId();
	}
	
	//취소
	@Transactional
	public void cancelOrder(Long orderId) {
		//주문 엔티티 조회
		Order order = orderRepository.findOne(orderId);
		
		//주문 취소
		order.cancel();
	}
	
	//검색
//	public List<Order> findOrders(OrderSearch orderSearch){
//		return orderRepository.findAll(orderSearch);
//	}
}
