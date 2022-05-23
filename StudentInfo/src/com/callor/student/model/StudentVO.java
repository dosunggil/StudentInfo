package com.callor.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StudentVO {

	private String stNum;
	private String stName;
	private String stDept;
	private String stGrade;
	private String stTel;
}
