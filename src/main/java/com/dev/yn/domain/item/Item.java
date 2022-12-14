package com.dev.yn.domain.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import com.dev.yn.domain.Category;
import com.dev.yn.exception.NotEnoughStockException;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //한테이블에 전부 넣겠다.
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
	
	@Id @GeneratedValue
	@Column(name = "item_id")
	private Long id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();
	
	//==비지니스 로직==//
	/**
	 * stock증가
	 */
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
	
	/**
	 * stock 감소 
	 * @throws NotEnoughStockException 
	 */
	public void removeStock(int quantity) throws NotEnoughStockException {
		int restStock = this.stockQuantity - quantity;
		if(restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = restStock;
	}

    public void change(String name, int price, int stockQuantity){
		setName(name);
		setPrice(price);
		setStockQuantity(stockQuantity);
//		this.name = name;
//		this.price = price;
//		this.stockQuantity = stockQuantity;
	};
}
