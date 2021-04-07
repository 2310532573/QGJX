package com.luolin.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.luolin.utils.Entity;

@Data
public class Company extends Entity{

	/**
	 *
	 */
	private Integer id;
	/**
	 *
	 */
	@Length(max = 100)
	private String name;
	/**
	 *
	 */
	@Length(max = 100)
	private String account;
	/**
	 *
	 */
	@Length(max = 100)
	private String password;
	/**
	 *
	 */
	@Length(max = 100)
	private String contact;
	/**
	 *
	 */
	@Length(max = 100)
	private String telephone;
	/**
	 *
	 */
	@Length(max = 100)
	private String email;
	/**
	 *
	 */
	@Length(max = 200)
	private String addr;
	/**
	 *
	 */
	@Length(max = 100)
	private String url;
	/**
	 *
	 */
	@Length(max = 100)
	private String size;
	/**
	 *
	 */
	@Length(max = 100)
	private String type;
	/**
	 *
	 */
	@Length(max = 100)
	private String logo;
	/**
	 *
	 */
	@Length(max = 0)
	private String description;

}
