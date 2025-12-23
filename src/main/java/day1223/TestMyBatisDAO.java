package day1223;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import day1219.DeptDTO;
import kr.co.sist.dao.MyBatisHandler;

public class TestMyBatisDAO {

	public void insertBoard() throws PersistenceException{
		//1.MyBatis Handler 얻기
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(true);
		//2.method 호출
		int cnt=ss.insert("day1223.nonParameter");
		//3.결과 받기
		System.out.println(cnt+"건 추가");
		//if(cnt==1) {
		//	ss.commit();
		//}
		//4.MyBatis Handler 닫기
		if(ss!=null) {ss.close();}//end if
	}//insertBoard
	
	public void insertCpDept(DeptDTO dDTO) throws PersistenceException{
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(true);
		int cnt=ss.insert("day1223.insertDept", dDTO);
		System.out.println(cnt+"건 추가되었습니다.");
		if(ss!=null) {ss.close();}
	}
	
	public void insertCpDept2(DeptDTO dDTO) throws PersistenceException{
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(true);
		int cnt=ss.insert("day1223.insertDept2", dDTO);
		System.out.println(cnt+"건 추가되었습니다.");
		if(ss!=null) {ss.close();}
	}
	
	public static void main(String[] args) {
		try {
		DeptDTO dDTO=new DeptDTO(50, "QA", "경기");
		//new TestMyBatisDAO().insertBoard();
		new TestMyBatisDAO().insertCpDept2(dDTO);
		} catch(PersistenceException pe) {
			System.err.println("문제발생");
			pe.printStackTrace();
		}//end catch
	}//main

}//class