package com.callor.student.controller;

import com.callor.student.service.StudentService;
import com.callor.student.service.impl.StudentServiceImplV1;

public class StudentControllerV1 {

	public static void main(String[] args) {
		
		// stService ��ü�� �����ϴ� ����,
		// student.txt ���Ͽ��� �����͸� �о� stdList �� �غ�ȴ� (loadStudent() ������)
		StudentService stService = new StudentServiceImplV1();
		stService.inputStudent();
	}
}
