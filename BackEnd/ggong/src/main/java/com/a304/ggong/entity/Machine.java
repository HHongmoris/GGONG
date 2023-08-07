package com.a304.ggong.entity;

import javax.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "machine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

	@OneToMany(mappedBy = "machine")
	@Builder.Default
	private List<Vote> votes = new ArrayList<>();
}
