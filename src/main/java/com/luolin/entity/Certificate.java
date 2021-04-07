package com.luolin.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.luolin.utils.Entity;
import java.util.Date;

@Data
public class Certificate extends Entity{

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
	private String file;
	/**
	 *
	 */
	private Date obtainDate;
	/**
	 *
	 */
	private Integer resumeId;

	private Resume resume;

}
