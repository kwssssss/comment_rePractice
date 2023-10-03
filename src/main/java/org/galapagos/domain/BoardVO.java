package org.galapagos.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BoardVO {
	
	private Long bno;
	
	@NotBlank(message="필수 항목입니다.")
	private String title;
	
	@NotBlank(message="필수 항목입니다.")
	private String content;
	
	@NotBlank(message="필수 항목입니다.")
	private String writer;
	
	private Date regDate;
	private Date updateDate;
}
