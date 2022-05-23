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
		// �й�:�̸�:�а�:�г�:��ȭ��ȣ
		StudentVO stVO = null;
		System.out.println(Line.dLine(50));
		System.out.println("�л� ������ �Է��Ͽ� �����մϴ�.");
		System.out.println(Line.sLine(50));

		while (true) {

			stVO = new StudentVO();
			System.out.print("�й��� �Է��ϼ��� >> ");
			String read = scan.nextLine();
			if (read.equals("QUIT"))
				break;
			stVO.setStNum(read);
			System.out.print("�̸��� �Է��ϼ��� >> ");
			read = scan.nextLine();
			if (read.equals("QUIT"))
				break;
			stVO.setStName(read);
			System.out.print("�а��� �Է��ϼ��� >> ");
			read = scan.nextLine();
			if (read.equals("QUIT"))
				break;
			stVO.setStDept(read);
			System.out.print("�г��� �Է��ϼ��� >> ");
			read = scan.nextLine();
			if (read.equals("QUIT"))
				break;
			stVO.setStGrade(read);
			System.out.print("��ȭ��ȣ�� �Է��ϼ��� >> ");
			read = scan.nextLine();
			if (read.equals("QUIT"))
				break;
			stVO.setStTel(read);
			stList.add(stVO);
			System.out.println("�л� ���� �Ϸ�");
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
		// �й�:�̸�:�а�:�г�:��ȭ��ȣ
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
			System.out.println(fileName + "������ ã�� �� �����ϴ�.");
			return;
		}

		fileReader = new Scanner(is);

		
		while (fileReader.hasNext()) {

			String stLine = fileReader.nextLine();
			String[] stInfo = stLine.split(":");

			/*
			 * if (stInfo.length < 4) continue;
			 */

			// 1,������,���а�,4,010,

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
		System.out.print("�й�\t");
		System.out.print("�̸�\t");
		System.out.print("�а�\t");
		System.out.print("�г�\t");
		System.out.println("��ȭ��ȣ");
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
