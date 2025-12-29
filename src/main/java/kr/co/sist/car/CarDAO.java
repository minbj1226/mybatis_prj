package kr.co.sist.car;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import day1226.CarModelDomain;
import kr.co.sist.dao.MyBatisHandler;

public class CarDAO {
	
	private static CarDAO cDAO;
	
	private CarDAO() {
		
	}
	
	public static CarDAO getInstance() {
		if(cDAO==null) {
			cDAO=new CarDAO();
		}//end if
		return cDAO;
	}//getInstance

	/**
	 * 제조국에 따른 제조사 검색
	 * @param country
	 * @return
	 * @throws PersistenceException
	 */
	public List<String> selectMaker(String country) throws PersistenceException{
		List<String> makerList=null;
		
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		makerList=ss.selectList("kr.co.sist.car.selectMaker", country);
		
		if(ss!=null) {
			ss.close();
		}//end if
		
		return makerList;
	}//selectMaker
	
	public List<String> selectModel(String maker) throws PersistenceException{
		List<String> modelList=null;
		
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		modelList=ss.selectList("kr.co.sist.car.selectModel", maker);
		
		if(ss!=null) {
			ss.close();
		}//end if
		
		return modelList;
	}//selectModel
	
	public List<CarModelDomain> selectCar(String model) throws PersistenceException{
		List<CarModelDomain> carList=null;
		
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		carList=ss.selectList("kr.co.sist.car.selectCar", model);
		
		if(ss!=null) {
			ss.close();
		}//end if
		
		return carList;
	}//selectMaker
	
	
}//class