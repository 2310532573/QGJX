package com.luolin.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.luolin.utils.Entity;
import java.util.Date;
@Data
public class Channel extends Entity{

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
	private Integer parentId;
	/**
	 *
	 */
	@Length(max = 100)
	private String channelImg;
	/**
	 *
	 */
	@Length(max = 200)
	private String summary;
	/**
	 * Y单页|其他非单页
	 */
	@Length(max = 1)
	private String single;
	/**
	 *
	 */
	@Length(max = 100)
	private String url;
	/**
	 *
	 */
	@Length(max = 0)
	private String content;
	/**
	 *
	 */
	private Integer sort;
	/**
	 *
	 */
	private Date createDate;
	/**
	 *
	 */
	private Integer createUser;
	/**
	 * D删除
	 */
	@Length(max = 1)
	private String deletedFlag;
	/**
	 *
	 */
	private Integer postion;

}
