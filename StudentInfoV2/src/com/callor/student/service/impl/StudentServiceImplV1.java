package com.callor.student.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.student.model.StudentVO;
import com.callor.student.service.StudentService;
import com.cho.utils.Line;

/*
 * Service Ŭ���������� �����͸� ����(�Է�, ���, ��������) �� ���ٵ�
 * ������������ �����ϴ� ����� �ְ�,
 * �ܺο���(controller) �����Ͽ� �Ű������� �����ϴ� ����� �ִ�.
 * 
 * V1 ������ ������������ �����ϴ� ����� ����ϰڴ�.
 */
public class StudentServiceImplV1 implements StudentService {

	/*
	 * Ŭ��������(�ʵ念��) ���� ��ü�� �����ϰ� �⺻ �����ڿ��� ��ü�� �ʱ�ȭ(����) �Ͽ� ����ϱ�.
	 */
	protected final List<StudentVO> stdList;
	protected final String fileName;

	public StudentServiceImplV1() {
		this(new ArrayList<>(),  "src/com/callor/student/student.txt");
	}
	
	public StudentServiceImplV1(List<StudentVO> stdList, String fileName) {
		this.stdList = stdList;
		this.fileName = fileName;
		loadStudent();
	}

	/*
	 * Controller ���� ServiceV1 Ŭ������ �ʱ�ȭ �� �� student.txt ���Ͽ��� �л� ������ �о� stdList ��
	 * ��Ƶδ� �ڵ带 �ۼ��Ѵ�.
	 */
	protected void loadStudent() {

		// is ��ü��, file ��ü�� ����
		InputStream is = null;
		Scanner file = null;

		try {
			// ���� �̸��� �����Ͽ� is ��ü�� ����
			// student.txt ������ ��� ���� �غ� �϶�
			is = new FileInputStream(fileName);

			// FileInputStream �� Scanner �� �����ϴ� ����
			// �̹� �ü���� student.txt ������ ��� �о
			// ����� �غ� �����ش�.
			file = new Scanner(is);

			while (file.hasNext()) {
				String[] stInfo = file.nextLine().split(":");

				StudentVO stVO = StudentVO.builder().num(stInfo[0]).name(stInfo[1]).dept(stInfo[2])
						.grade(Integer.valueOf(stInfo[3])).tel(stInfo[4]).build();
				stdList.add(stVO);
			}

			file.close();
			try {
				is.close();

				/*
				 * Exception �� �������� Exception �� �� ��޺��� ���� ������ ���´�. ���� ����� Exception �� ������
				 * Exception Ű����� ó���� �Ѵ�. ���� catch �� Exception ó���� ���� ��޺��� ������� ó���ϸ�, ����� ���� ����
				 * ���� Exception �� �������� catch �� �����Ѵ�.
				 */
			} catch (FileNotFoundException e) {
				System.out.println(fileName + "������ ã�� �� �����ϴ�.");

				/*
				 * return �� ����ϴ� ������ try-catch {} ���Ŀ� �����ؾ� �� �ڵ尡 ���� �� �����Ƿ� Exception �� �߻��ϸ� �� �̻�
				 * �������� �ʵ��� �ϱ� �����̴�.
				 */
				return;
			}
		} catch (IOException e) {
			System.out.println("IO ����");
			return;
		} catch (Exception e) {
			System.out.println("������ �� �� ���� Execption");
			return;
		}
		// � �ڵ带 �����ϰڴ�.
	}

	@Override
	public void inputStudent() {
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println(Line.dLine(50));
			System.out.println("�л����� �Է�");
			System.out.println(Line.sLine(50));

			System.out.println("�й�(QUIT:����)");
			String num = scan.nextLine();
			if (num.equals("QUIT"))
				break;
			System.out.println("�̸�(QUIT:����)");
			String name = scan.nextLine();
			if (name.equals("QUIT"))
				break;
			System.out.println("�а�(QUIT:����)");
			String dept = scan.nextLine();
			if (dept.equals("QUIT"))
				break;
			System.out.println("�г�(QUIT:����)");
			String strGrade = scan.nextLine();
			if (strGrade.equals("QUIT"))
				break;
			int intGrade = 0;
			try {
				intGrade = Integer.valueOf(strGrade);
			} catch (Exception e) {
				System.out.println("�г��� ���ڸ� �Է�");
				continue;
			}
			System.out.println("��ȭ��ȣ(QUIT:����)");
			String tel = scan.nextLine();
			if (tel.equals("QUIT"))
				break;
			StudentVO stVO = StudentVO.builder().num(num).name(name).grade(intGrade).dept(dept).tel(tel).build();
			stdList.add(stVO);
		} //end while
		saveStudent();
		printStudent();

	}
	protected void saveStudent() {
		OutputStream os = null;
		PrintWriter out = null;
		
		try {
			os = new FileOutputStream(fileName);
			out = new PrintWriter(os);
			
			for( StudentVO stVO : stdList) {
				out.printf("%s:",stVO.getNum());
				out.printf("%s:",stVO.getName());
				out.printf("%s:",stVO.getDept());
				out.printf("%d:",stVO.getGrade());
				out.printf("%s:\n",stVO.getTel());
			}
			out.flush();
			out.close();
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
		}
	}

	@Override
	public void printStudent() {
		System.out.println(Line.dLine(50));
		System.out.println("���Ѱ� �л�����");
		System.out.println(Line.dLine(50));

		System.out.println("�й�\t�̸�\t�а�\t�г�\t��ȭ��ȣ");
		System.out.println(Line.sLine(50));

		for (StudentVO vo : stdList) {
			System.out.print(vo.getNum() + "\t");
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getDept() + "\t");
			System.out.print(vo.getGrade() + "\t");
			System.out.println(vo.getTel());
		}
		System.out.println(Line.dLine(50));
	}
}
