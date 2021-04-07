package com.luolin.entity;

import org.hibernate.validator.constraints.Length;
import com.luolin.utils.Entity;


public class Type extends Entity{

	/**
	 *
	 */
	private Integer id;
	/**
	 *
	 */
	@Length(max = 100)
	private String name;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
