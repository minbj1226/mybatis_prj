package day1224;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

public class SelectService {
	private static SelectService ss;
	
	private SelectService() {
		
	}
	
	public static SelectService getInstance() {
		if(ss==null) {
			ss=new SelectService();
		}//end if
		return ss;
	}//getInstance
	
	/**
	 * 부서번호를 입력하면 부서명을 검색
	 * @param deptno
	 * @return
	 */
	public String scsr(int deptno) {
		String dname="";
		SelectDAO sDAO=SelectDAO.getInstacne();
		try {
			dname=sDAO.scsr(deptno);
		} catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return dname;
	}//scsr
	
	/**
	 * 부서번호를 입력하면 사원명을 검색
	 * @param deptno
	 * @return
	 */
	public List<String> scmr(int deptno) {
		List<String> list=null;
		SelectDAO sDAO=SelectDAO.getInstacne();
		
		try {
			list=sDAO.scmr(deptno);
		} catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return list;
	}//scmr
	
	public List<EmpDTO> mcmr(int empno) {
		List<EmpDTO> list=null;
		SelectDAO sDAO=SelectDAO.getInstacne();
		
		try {
			list=sDAO.mcmr(empno);
		} catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return list;
	}//scmr
}//class