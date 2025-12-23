package kr.co.sist.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisHandler {
	private static MyBatisHandler mbh;
	private static SqlSessionFactory ssf;
	
	private MyBatisHandler() {
		org.apache.ibatis.logging.LogFactory.useLog4J2Logging();
	}
	
	public static MyBatisHandler getInstance() {
		if(mbh==null) {
			mbh=new MyBatisHandler();
		}//end if
		return mbh;
	}//getInstance
	
	private static SqlSessionFactory getSessFactory() throws IOException {
		if(ssf==null) {
			//1. 설정용 XML과 연결
			Reader reader=Resources.getResourceAsReader("kr/co/sist/dao/mybatis-config.xml");
			//2. SqlSessionFactoryBuild 생성
			SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
			//3. SqlSessionFactory를 building 한다.
			ssf=ssfb.build(reader);
			if(reader!=null) {
				reader.close();
			}//end if
		}//end if
		return ssf;
	}//getSessFactory
	
	/**
	 * MyBatisHandler 얻기
	 * @param autoCommitFlag true=autoCommit, false=non autoCommit
	 * @return
	 */
	public SqlSession getMyBatisHandler(boolean autoCommitFlag) {
		SqlSession ss=null;
		
		try {
			ss=getSessFactory().openSession(autoCommitFlag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ss;
	}
}
