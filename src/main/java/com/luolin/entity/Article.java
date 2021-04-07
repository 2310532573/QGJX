package com.luolin.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.luolin.utils.Entity;
import java.util.Date;

@Data
public class Article extends Entity{

	/**
	 *
	 */
	private Integer id;
	/**
	 *
	 */
	private Integer channelId;
	/**
	 *
	 */
	@Length(max = 100)
	private String title;
	/**
	 *
	 */
	@Length(max = 100)
	private String titleImg;
	/**
	 *
	 */
	@Length(max = 200)
	private String summary;
	/**
	 *
	 */
	@Length(max = 100)
	private String author;
	/**
	 *
	 */
	@Length(max = 100)
	private String url;
	/**
	 *
	 */
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
	 *
	 */
	private Date updateDate;

	private Integer views;

}
