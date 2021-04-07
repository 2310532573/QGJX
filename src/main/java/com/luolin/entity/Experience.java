package com.luolin.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.luolin.utils.Entity;
import java.util.Date;

@Data
public class Experience extends Entity{

	/**
	 *
	 */
	private Integer id;
	/**
	 *
	 */
	@Length(max = 100)
	private String company;
	/**
	 *
	 */
	@Length(max = 100)
	private String post;
	/**
	 *
	 */
	@Length(max = 100)
	private String salary;
	/**
	 *
	 */
	@Length(max = 0)
	private String description;
	/**
	 *
	 */
	private Date joinDate;
	/**
	 *
	 */
	private Date leaveDate;
	/**
	 *
	 */
	private Integer resumeId;

	private Resume resume;

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

}
