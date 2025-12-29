package test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;

import day1224.EmpDTO;
import day1224.SelectDAO;
import day1224.SelectService;
import day1226.EmpDomain;
import day1226.SelectDAO2;
import day1229.SelectDAO3;
import kr.co.sist.board.BoardDAO;
import kr.co.sist.board.RangeDTO;
import kr.co.sist.car.CarDAO;
import kr.co.sist.car.CarService;

public class Test {

	@org.junit.Test
	@DisplayName("select 테스트")
	public void test() {
//		SelectService ss=SelectService.getInstance();
//		assertNotNull(ss.scsr(10));
//		assertEquals(ss.scsr(10), "ACCOUNTING");
//		assertNotNull(ss.scmr(10));
//		SelectDAO sDAO=SelectDAO.getInstacne();
//		EmpDTO eDTO=sDAO.mcsr(7788);
//		List<EmpDTO> list=sDAO.mcmr(10);
//		System.out.println(list);
//		assertNotNull(list);
//		assertEquals(list.size(), 3);
//		day1226.EmpDTO eDTO=new day1226.EmpDTO();
//		eDTO.setEmpno(7521);
//		eDTO.setDeptno(30);
		
//		SelectDAO2 sDAO=SelectDAO2.getInstacne();
//		CarDAO cDAO=CarDAO.getInstance();
//		CarService cs=CarService.getInstance();
//		EmpDomain ed=sDAO.useDomain(eDTO);
//		assertNotNull(ed);
		SelectDAO3 sd3=SelectDAO3.getInstacne();
		BoardDAO bDAO=BoardDAO.getInstance();
		try { 
//			assertNotNull(sDAO.useLike("신곡동"));
//			assertNotNull(sDAO.lessThan(3000));
//			assertNotNull(sDAO.subquery());
//			assertNotNull(sDAO.union());
//			assertNotNull(sDAO.join());
//			assertNotNull(cDAO.selectMaker("국산"));
//			assertNotNull(cDAO.selectModel("현대"));
//			assertNotNull(cs.searchModel("현대"));
//			assertNotNull(cDAO.selectCar("K5"));
//			assertNotNull(cs.searchCar("K5"));
//			assertNotNull(sd3.subQueryNjoin());
//			assertNotNull(sd3.dollar("cp_emp5"));
//			assertNotNull(sd3.dynamicIf(0));
			RangeDTO rDTO=new RangeDTO();
//			rDTO.setField("1");
//			rDTO.setKeyword("오늘은");
			rDTO.setStartNum(1);
			rDTO.setEndNum(10);
			
//			assertNotNull(bDAO.selectBoardTotalCnt(rDTO));
			assertNotNull(bDAO.selectRangeBoard(rDTO));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}//test
	
}//class