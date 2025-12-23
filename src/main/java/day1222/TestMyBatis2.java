package day1222;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import day1219.DeptDTO;
import kr.co.sist.dao.MyBatisHandler;

public class TestMyBatis2 {

	public static void main(String[] args) {
		
		//1. MyBatisHandler 얻기
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(true);
		//2. 사용할 쿼리문을(mapper.xml)을 찾아서 실행
		List<DeptDTO> list=ss.selectList("day1219.selectDept");
		//3. 결과얻기
		for(DeptDTO dd:list) {
			System.out.println(dd.getDeptno()+"/"+dd.getDname()+"/"+dd.getLoc());
		}
		//4. MyBatisHandler 닫기
		if(ss!=null) {ss.close();}
	}//main

}//class