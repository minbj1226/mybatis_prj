package day1226;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

public class SelectService2 {
	private static SelectService2 ss;
	
	private SelectService2() {
		
	}
	
	public static SelectService2 getInstance() {
		if(ss==null) {
			ss=new SelectService2();
		}//end if
		return ss;
	}//getInstance
	
	
	public EmpDomain useDomain(EmpDTO empDTO) {
		EmpDomain empDomain=null;
		SelectDAO2 sDAO=SelectDAO2.getInstacne();
		try {
			empDomain=sDAO.useDomain(empDTO);
		} catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return empDomain;
	}//useDomain
	
	public List<ZipcodeDomain> useLike(String dong) {
		List<ZipcodeDomain> zipList=null;
		SelectDAO2 sDAO=SelectDAO2.getInstacne();
		try {
			zipList=sDAO.useLike(dong);
		} catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return zipList;
	}//useLike
	
	public List<EmpDomain> useLessThan(int sal) {
		List<EmpDomain> empList=null;
		SelectDAO2 sDAO=SelectDAO2.getInstacne();
		try {
			empList=sDAO.lessThan(sal);
		} catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return empList;
	}//useLessThan
	
	public List<EmpDomain> useGreaterThan(int sal) {
		List<EmpDomain> empList=null;
		SelectDAO2 sDAO=SelectDAO2.getInstacne();
		try {
			empList=sDAO.greaterThan(sal);
		} catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return empList;
	}//useGreaterThan
	
	public List<EmpAllDomain> useSubquery() {
		List<EmpAllDomain> empList=null;
		SelectDAO2 sDAO=SelectDAO2.getInstacne();
		try {
			empList=sDAO.subquery();
		} catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return empList;
	}//useSubquery
	
	public List<EmpAllDomain> useUnion() {
		List<EmpAllDomain> empList=null;
		SelectDAO2 sDAO=SelectDAO2.getInstacne();
		try {
			empList=sDAO.union();
		} catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return empList;
	}//useSubquery
	
	public List<CarModelDomain> useJoin() {
		List<CarModelDomain> carList=null;
		SelectDAO2 sDAO=SelectDAO2.getInstacne();
		try {
			carList=sDAO.join();
		} catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return carList;
	}//useSubquery
}//class