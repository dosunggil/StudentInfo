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
 * Service 클래스에서는 데이터를 관리(입력, 출력, 파일저장) 을 할텐데
 * 내부전용으로 선언하는 방법이 있고,
 * 외부에서(controller) 선언하여 매개변수로 주입하는 방법이 있다.
 * 
 * V1 에서는 내부전용으로 선언하는 방법을 사용하겠다.
 */
public class StudentServiceImplV1 implements StudentService {

	/*
	 * 클래스영역(필드영역) 에서 객체를 선언하고 기본 생성자에서 객체를 초기화(생성) 하여 사용하기.
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
	 * Controller 에서 ServiceV1 클래스를 초기화 할 때 student.txt 파일에서 학생 정보를 읽어 stdList 에
	 * 담아두는 코드를 작성한다.
	 */
	protected void loadStudent() {

		// is 객체와, file 객체를 선언만
		InputStream is = null;
		Scanner file = null;

		try {
			// 파일 이름을 전달하여 is 객체를 생성
			// student.txt 파일을 열어서 읽을 준비를 하라
			is = new FileInputStream(fileName);

			// FileInputStream 을 Scanner 와 연결하는 순간
			// 이미 운영체제는 student.txt 파일을 모두 읽어서
			// 사용할 준비를 마쳐준다.
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
				 * Exception 의 계층구조 Exception 은 각 등급별로 계층 구조를 갖는다. 가장 상급의 Exception 은 순수한
				 * Exception 키워드로 처리를 한다. 보통 catch 의 Exception 처리는 낮은 등급부터 순서대로 처리하며, 등급이 가장 높은
				 * 순수 Exception 은 마지막에 catch 를 실행한다.
				 */
			} catch (FileNotFoundException e) {
				System.out.println(fileName + "파일을 찾을 수 없습니다.");

				/*
				 * return 을 사용하는 이유는 try-catch {} 이후에 실행해야 할 코드가 있을 수 있으므로 Exception 이 발생하면 더 이상
				 * 진행하지 않도록 하기 위함이다.
				 */
				return;
			}
		} catch (IOException e) {
			System.out.println("IO 오류");
			return;
		} catch (Exception e) {
			System.out.println("원인을 알 수 없는 Execption");
			return;
		}
		// 어떤 코드를 실행하겠다.
	}

	@Override
	public void inputStudent() {
		while (true) {
			Scanner scan = new Scanner(System.in);
			System.out.println(Line.dLine(50));
			System.out.println("학생정보 입력");
			System.out.println(Line.sLine(50));

			System.out.println("학번(QUIT:종료)");
			String num = scan.nextLine();
			if (num.equals("QUIT"))
				break;
			System.out.println("이름(QUIT:종료)");
			String name = scan.nextLine();
			if (name.equals("QUIT"))
				break;
			System.out.println("학과(QUIT:종료)");
			String dept = scan.nextLine();
			if (dept.equals("QUIT"))
				break;
			System.out.println("학년(QUIT:종료)");
			String strGrade = scan.nextLine();
			if (strGrade.equals("QUIT"))
				break;
			int intGrade = 0;
			try {
				intGrade = Integer.valueOf(strGrade);
			} catch (Exception e) {
				System.out.println("학년은 숫자만 입력");
				continue;
			}
			System.out.println("전화번호(QUIT:종료)");
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
		System.out.println("대한고교 학생정보");
		System.out.println(Line.dLine(50));

		System.out.println("학번\t이름\t학과\t학년\t전화번호");
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
