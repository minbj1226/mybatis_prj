package test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;

import day1224.EmpDTO;
import day1224.SelectDAO;
import day1224.SelectService;

public class Test {

	@org.junit.Test
	@DisplayName("select 테스트")
	public void test() {
		SelectService ss=SelectService.getInstance();
//		assertNotNull(ss.scsr(10));
//		assertEquals(ss.scsr(10), "ACCOUNTING");
//		assertNotNull(ss.scmr(10));
		SelectDAO sDAO=SelectDAO.getInstacne();
//		EmpDTO eDTO=sDAO.mcsr(7788);
		List<EmpDTO> list=sDAO.mcmr(10);
		System.out.println(list);
//		assertNotNull(list);
		assertEquals(list.size(), 3);
	}//test

	
}//class
