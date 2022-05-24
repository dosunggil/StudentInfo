package com.callor.student.controller;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.callor.student.model.StudentVO;
import com.callor.student.service.StudentService;
import com.callor.student.service.impl.StudentServiceImplV2;

public class StudentControllerV2 {

	/*
	 * ControllerV1 ������ ServiceV1 �� ����� ��
	 * �ܼ��� �⺻ �����ڸ� ȣ���Ͽ� ServiceV1 ���� stdList �� fileName ��
	 * ���� �����ϵ��� �Ͽ���.
	 * ���� list ������ file �� �ٸ� ������ �ٲٷ��� Service class�� �����ؾ�������,
	 * 
	 * V2 ������ list ������ file �� �ٸ� ������ �ٲٷ��� Controller ���� �ٲٸ� �ȴ�.
	 * 	 */
	public static void main(String[] args) throws FileNotFoundException {
		List<StudentVO> stdList = new ArrayList<>();
		String fileName = "src/com/callor/student/student.txt";
		
		// java 1.5 �̻󿡼� text ���Ͽ� ������ ����ϱ� ���Ͽ�
		// �ܵ����� ����� �� �ִ� Ŭ����
		String printFile = "src/com/callor/student/print.txt";
		PrintStream ps = new PrintStream(printFile);
		PrintStream psConsole = System.out;
			
		// stdList �� ����������(student.txt) ������ �����ڿ� �����Ͽ�
		// student.txt ���Ͽ��� �����͸� �о� stdList �� �غ��϶�
		StudentService stService = new StudentServiceImplV2(stdList, fileName, ps);
		
		stService.printStudent();
		
		StudentService stService1 = new StudentServiceImplV2( stdList, fileName, psConsole);
		stService1.printStudent();
	}
}
