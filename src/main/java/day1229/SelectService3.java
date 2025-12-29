package day1229;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import day1226.CarModelDomain;
import day1226.EmpAllDomain;
import kr.co.sist.dao.MyBatisHandler;

public class SelectService3 {
	private static SelectService3 ss;
	
	private SelectService3() {
		
	}
	
	public static SelectService3 getInstance() {
		if(ss==null) {
			ss=new SelectService3();
		}//end if
		return ss;
	}//getInstance
	
	public List<CarModelDomain> subNjoin() {
		List<CarModelDomain> carList=null;
		SelectDAO3 sDAO=SelectDAO3.getInstacne();
		try {
			carList=sDAO.subQueryNjoin();
		} catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return carList;
	}//subNjoin
	
	public List<EmpAllDomain> dollar(String tableName) throws PersistenceException{
		List<EmpAllDomain> empList=null;
		
		SelectDAO3 sDAO=SelectDAO3.getInstacne();
		try {
			empList=sDAO.dollar(tableName);
		} catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return empList;
	};//dollar
	
	public List<EmpAllDomain> dynamicIf(int deptno) throws PersistenceException{
		List<EmpAllDomain> empList=null;
		
		if (!(deptno==10 || deptno==20 || deptno==30 || deptno==40)) {
			deptno=0;
		}//end if
		
		SelectDAO3 sDAO=SelectDAO3.getInstacne();
		try {
			empList=sDAO.dynamicIf(deptno);
		} catch(PersistenceException pe) {
			pe.printStackTrace();
		}//end catch
		
		return empList;
	};//dynamicIf
}//class