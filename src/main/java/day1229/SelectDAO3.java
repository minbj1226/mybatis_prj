package day1229;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import day1226.CarModelDomain;
import day1226.EmpAllDomain;
import kr.co.sist.dao.MyBatisHandler;

public class SelectDAO3 {
	private static SelectDAO3 sDAO;
	
	private SelectDAO3() {
		
	}//selectDAO
	
	public static SelectDAO3 getInstacne() {
		if(sDAO==null) {
			sDAO=new SelectDAO3();
		}//end if
		return sDAO;
	}//getInstance
	
	public List<CarModelDomain> subQueryNjoin() throws PersistenceException{
		List<CarModelDomain> carList=null;
		
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		carList=ss.selectList("day1229.subNjoin");
		
		if(ss!=null) {ss.close();}
		return carList;
	};//subQueryNjoin
	
	public List<EmpAllDomain> dollar(String tableName) throws PersistenceException{
		List<EmpAllDomain> empList=null;
		
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		empList=ss.selectList("day1229.dollar", tableName);
		
		if(ss!=null) {ss.close();}
		return empList;
	};//dollar
	
	public List<EmpAllDomain> dynamicIf(int deptno) throws PersistenceException{
		List<EmpAllDomain> empList=null;
		
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		empList=ss.selectList("day1229.if", deptno);
		
		if(ss!=null) {ss.close();}
		return empList;
	};//dollar
	
}//class