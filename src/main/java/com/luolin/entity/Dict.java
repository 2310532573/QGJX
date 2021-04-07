package com.luolin.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.luolin.utils.Entity;

@Data
public class Dict extends Entity{

	/**
	 *
	 */
	private Integer id;
	/**
	 *
	 */
	@Length(max = 100)
	private String dictKey;
	/**
	 *
	 */
	@Length(max = 100)
	private String dictVal;
	/**
	 *
	 */
	private Integer sort;
	/**
	 * 0正常|1删除
	 */
	private Integer status;
	/**
	 *
	 */
	private Integer typeId;

}
