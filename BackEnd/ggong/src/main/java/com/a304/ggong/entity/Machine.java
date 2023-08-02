package com.a304.ggong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "machine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Machine {

	@Id
	@GeneratedValue
	@Column(name = "machine_no")
	private Long machineNo;

	@Column(name = "latitude")
	private double latitude;

	@Column(name = "longitude")
	private double longitude;

	@Column(name = "name")
	private String name;

	@Column(name = "SN")
	private String SN;

	@Column(name = "area_gu")
	private String areaGu;
}
