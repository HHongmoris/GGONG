package com.a304.ggong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
	//상품 id번호
	@Id
	@GeneratedValue
	@Column(name = "product_no")
	private Long productNo;

	//상품 pin 번호
	@Column(name = "pin")
	private String pin;

	//상품 가격정보
	@Column(name = "price", nullable = true)
	private int price;

	@Builder
	public Product(Long productNo, String pin, int price) {
		this.productNo = productNo;
		this.pin = pin;
		this.price = price;
	}
}
