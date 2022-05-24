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
	 * ControllerV1 에서는 ServiceV1 을 사용할 때
	 * 단순히 기본 생성자를 호출하여 ServiceV1 에서 stdList 와 fileName 을
	 * 직접 관리하도록 하였다.
	 * 만약 list 변수와 file 을 다른 것으로 바꾸려면 Service class를 변경해야하지만,
	 * 
	 * V2 에서는 list 변수와 file 을 다른 것으로 바꾸려면 Controller 에서 바꾸면 된다.
	 * 	 */
	public static void main(String[] args) throws FileNotFoundException {
		List<StudentVO> stdList = new ArrayList<>();
		String fileName = "src/com/callor/student/student.txt";
		
		// java 1.5 이상에서 text 파일에 내용을 기록하기 위하여
		// 단독으로 사용할 수 있는 클래스
		String printFile = "src/com/callor/student/print.txt";
		PrintStream ps = new PrintStream(printFile);
		PrintStream psConsole = System.out;
			
		// stdList 와 원본데이터(student.txt) 파일을 생성자에 주입하여
		// student.txt 파일에서 데이터를 읽어 stdList 를 준비하라
		StudentService stService = new StudentServiceImplV2(stdList, fileName, ps);
		
		stService.printStudent();
		
		StudentService stService1 = new StudentServiceImplV2( stdList, fileName, psConsole);
		stService1.printStudent();
	}
}
