package day1226;

/**
 * insert에 추가되는 값, update의 변경값, where절 사용하는 값, delete에 where절에서 사용하는 값
 * select where절에 사용하는 값
 * 
 * <jsp:usebean>: 기본생성자를 사용하여 객체화를 하고
 * <jsp:setProperty>: setter method를 통해 입력값이 저장
 * MyBatis에서는 parameterType="EmpDTO" 입력받아서 #{ getter명 }으로 사용
 */
public class EmpDTO {
	private int deptno, empno;

	public EmpDTO() {
		System.out.println("<jsp:usebean>에 의해서 객체화");
	}

	public EmpDTO(int deptno, int empno) {
		this.deptno = deptno;
		this.empno = empno;
	}

	public int getDeptno() {
		System.out.println("MyBatis에서 #{deptno}로 사용");
		return deptno;
	}

	public int getEmpno() {
		System.out.println("MyBatis에서 #{empno}로 사용");
		return empno;
	}

	public void setDeptno(int deptno) {
		System.out.println("<jsp:setProperty>에 의해서 setDeptno 사용");
		this.deptno = deptno;
	}

	public void setEmpno(int empno) {
		System.out.println("<jsp:setProperty>에 의해서 setEmpno 사용");
		this.empno = empno;
	}

	@Override
	public String toString() {
		return "EmpDTO [deptno=" + deptno + ", empno=" + empno + "]";
	}
	
}