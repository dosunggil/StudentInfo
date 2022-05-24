package com.callor.student.service.impl;

import java.io.PrintStream;
import java.util.List;

import com.callor.student.model.StudentVO;
import com.cho.utils.Line;

public class StudentServiceImplV2 extends StudentServiceImplV1 {
	
	private PrintStream ps;

	/*
	 * �Ű������� stdList �� fileName �� ���Թ޾� V1 Ŭ������ �����ڸ� ���ؼ� stdList �� fileName �� �ʱ�ȭ����.
	 */
	public StudentServiceImplV2(List<StudentVO> stdList, String fileName, PrintStream ps) {
		super(stdList, fileName);
		this.ps = ps;
		
	}

	@Override
	public void printStudent() {
		//PrintStream ps = System.out;
		printAndFileSaveStudent(ps);
	}

	protected void printAndFileSaveStudent(PrintStream ps) {
		PrintStream out = ps;
		out.println(Line.dLine(50));
		out.println("�л�����");
		out.println(Line.sLine(50));
		out.println("�й�\t�̸�\t�а�\t�г�\t��ȭ��ȣ");
		for (StudentVO vo : stdList) {
			out.printf("%s\t",vo.getNum());
			out.printf("%s\t",vo.getName());
			out.printf("%s\t",vo.getDept());
			out.printf("%s\t",vo.getGrade());
			out.printf("%s\n",vo.getTel());
		}
		out.close();
		ps.close();
	}
}
