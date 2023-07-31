package com.a304.ggong.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "machine")
@Getter
@Setter
public class Machine {

	@Id
	@GeneratedValue
	@Column(name = "machine_no")
	private int machineNo;

	@Column(name = "latitude")
	private int latitude;

	@Column(name = "longitude")
	private int longitude;

	@Column(name = "name")
	private int name;

	@Column(name = "SN")
	private int SN;

	@Column(name = "area_gu")
	private int areaGu;
}
