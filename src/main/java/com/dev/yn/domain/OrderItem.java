package com.dev.yn.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.dev.yn.domain.item.Item;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderItem {
	
	@Id @GeneratedValue
	@Column(name = "order_item_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	private int  orderPrice;
	
	private int count;
	
	//==생성 메서드==//
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
		OrderItem orderitem = new OrderItem();
		orderitem.setItem(item);
		orderitem.setOrderPrice(orderPrice);
		orderitem.setCount(count);
		
		item.removeStock(count);
		return orderitem;
	}
	
	//==비지니스 로직==//
	public void cancel() {
		getItem().addStock(count);
	}

	//==조회 로직==//
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}
	
}
