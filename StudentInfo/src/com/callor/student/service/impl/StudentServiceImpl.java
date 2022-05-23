package com.callor.student.service.impl;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.student.model.StudentVO;
import com.callor.student.service.StudentService;
import com.cho.utils.Line;

public class StudentServiceImpl implements StudentService {

	private final List<StudentVO> stList;
	private final String fileName;
	private final Scanner scan;

	public StudentServiceImpl() {
		this.stList = new ArrayList<>();
		this.fileName = "src/com/callor/student/model/student.txt";
		scan = new Scanner(System.in);
	}

	@Override
	public void inputStudent() {
		// 학번:이름:학과:학년:전화번호
		StudentVO stVO = null;
		System.out.println(Line.dLine(50));
		System.out.println("학생 정보를 입력하여 저장합니다.");
		System.out.println(Line.sLine(50));

		while (true) {

			stVO = new StudentVO();
			System.out.print("학번을 입력하세요 >> ");
			String read = scan.nextLine();
			if (read.equals("QUIT"))
				break;
			stVO.setStNum(read);
			System.out.print("이름을 입력하세요 >> ");
			read = scan.nextLine();
			if (read.equals("QUIT"))
				break;
			stVO.setStName(read);
			System.out.print("학과를 입력하세요 >> ");
			read = scan.nextLine();
			if (read.equals("QUIT"))
				break;
			stVO.setStDept(read);
			System.out.print("학년을 입력하세요 >> ");
			read = scan.nextLine();
			if (read.equals("QUIT"))
				break;
			stVO.setStGrade(read);
			System.out.print("전화번호를 입력하세요 >> ");
			read = scan.nextLine();
			if (read.equals("QUIT"))
				break;
			stVO.setStTel(read);
			stList.add(stVO);
			System.out.println("학생 저장 완료");
			System.out.println(Line.sLine(50));

		}

		FileWriter writer = null;
		PrintWriter out = null;
		try {
			writer = new FileWriter(fileName, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out = new PrintWriter(writer);
		// 학번:이름:학과:학년:전화번호
		for (StudentVO vo : stList) {
			out.printf("%s:", vo.getStNum());
			out.printf("%s:", vo.getStName());
			out.printf("%s:", vo.getStDept());
			out.printf("%s:", vo.getStGrade());
			out.printf("%s\n", vo.getStTel());
		}
		out.flush();
		out.close();
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void printStudent() {

		FileInputStream is = null;
		Scanner fileReader = null;
		List<StudentVO> stList1 = new ArrayList<>();

		try {
			is = new FileInputStream(fileName);
		} catch (Exception e) {
			System.out.println(fileName + "파일을 찾을 수 없습니다.");
			return;
		}

		fileReader = new Scanner(is);

		
		while (fileReader.hasNext()) {

			String stLine = fileReader.nextLine();
			String[] stInfo = stLine.split(":");

			/*
			 * if (stInfo.length < 4) continue;
			 */

			// 1,조도성,수학과,4,010,

			String num = stInfo[0];
			String name = stInfo[1];
			String dept = stInfo[2];
			String grade = stInfo[3];
			String tel = stInfo[4];

			StudentVO stVO1 = StudentVO.builder().stName(name).stNum(num).stDept(dept).stGrade(grade).stTel(tel).build();
			stList1.add(stVO1);
			try {
				is.close();
			} catch (IOException e) {
			}
		}
		System.out.println(Line.dLine(50));
		System.out.print("학번\t");
		System.out.print("이름\t");
		System.out.print("학과\t");
		System.out.print("학년\t");
		System.out.println("전화번호");
		System.out.println(Line.sLine(50));
		for (StudentVO vo : stList1) {
			System.out.print(vo.getStNum() + "\t");
			System.out.print(vo.getStName() + "\t");
			System.out.print(vo.getStDept() + "\t");
			System.out.print(vo.getStGrade() + "\t");
			System.out.println(vo.getStTel());
		}
		System.out.println(Line.dLine(50));
	}
}
