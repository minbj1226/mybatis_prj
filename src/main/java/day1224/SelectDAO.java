package day1224;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.co.sist.dao.MyBatisHandler;

public class SelectDAO {

	private static SelectDAO sDAO;
	
	private SelectDAO() {
		
	}//selectDAO
	
	public static SelectDAO getInstacne() {
		if(sDAO==null) {
			sDAO=new SelectDAO();
		}//end if
		return sDAO;
	}//getInstance
	
	/**
	 * 컬럼 하나에 한행 조회
	 * @param deptno
	 * @return 부서명
	 */
	public String scsr(int deptno)throws PersistenceException{
		String dname="";
		
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		dname=ss.selectOne("day1224.scsr", deptno);
		
		if(ss!=null) {ss.close();}
		return dname;
	};//scsr
	
	public List<String> scmr(int deptno) throws PersistenceException{
		List<String> list=null;
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		list=ss.selectList("day1224.scmr", deptno);
		
		if(ss!=null) {ss.close();}
		return list;
	}
	
	public EmpDTO mcsr(int empno)throws PersistenceException{
		EmpDTO eDTO=null;
		
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		eDTO=ss.selectOne("day1224.mcsr", empno);
		
		if(ss!=null) {ss.close();}
		return eDTO;
	};//mcsr
	
	public List<EmpDTO> mcmr(int deptno) throws PersistenceException{
		List<EmpDTO> list=null;
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		list=ss.selectList("day1224.mcmr", deptno);
		
		if(ss!=null) {ss.close();}
		return list;
	}
}//class