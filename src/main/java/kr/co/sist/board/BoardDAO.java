package kr.co.sist.board;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.co.sist.dao.MyBatisHandler;

public class BoardDAO {
	private static BoardDAO bDAO;

	private BoardDAO() {

	}
 
	public static BoardDAO getInstance() {
		if (bDAO == null) {
			bDAO = new BoardDAO();
		} // end if
		return bDAO;
	}// getInstance

	/**
	 * @return 총 게시글의 수를 int로 반환하는 method
	 * @throws SQLException
	 */
	public int selectBoardTotalCnt(RangeDTO rDTO) throws SQLException {
		int totalCnt = 0;
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		totalCnt=ss.selectOne("kr.co.sist.board.selectBoardTotalCnt", rDTO);
		if(ss!=null) {ss.close();}
		return totalCnt;
		/*
		 * DbConn dbCon = DbConn.getInstance("jdbc/dbcp");
		 * 
		 * Connection con = null; PreparedStatement pstmt = null; ResultSet rs = null;
		 * 
		 * try { con = dbCon.getConn(); //검색 키워드가 없다면 모든 글을 총 개수 검색 StringBuilder
		 * selectTotal = new StringBuilder();
		 * selectTotal.append("select count(*) cnt from board"); //dynamic query: 검색키워드가
		 * 있다면 검색 키워드에 해당하는 글의 개수 검색 if(rDTO.getKeyword()!= null &&
		 * !rDTO.getKeyword().isEmpty()) { selectTotal.append(" where instr(")
		 * .append(rDTO.getFieldStr()).append(",?) != 0"); }//end if pstmt =
		 * con.prepareStatement(selectTotal.toString());
		 * 
		 * if (rDTO.getKeyword()!= null && !rDTO.getKeyword().isEmpty()) {
		 * pstmt.setString(1, rDTO.getKeyword()); } // end if
		 * 
		 * rs=pstmt.executeQuery(); if( rs.next()) { totalCnt=rs.getInt("cnt"); }
		 * 
		 * } finally { dbCon.dbClose(rs, pstmt, null); } // end finally
		 */
	}// selectBoardTotalCnt

	public List<BoardDomain> selectRangeBoard(RangeDTO rDTO) throws SQLException {
		List<BoardDomain> list = null;
		
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		list=ss.selectList("kr.co.sist.board.selectRangeBoard", rDTO);
		if(ss!=null) {ss.close();}
		
		return list;
	}

	public void insertBoard(BoardDTO bDTO) throws PersistenceException {
		//1.MyBatis Handler얻기
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(true);
		//2.쿼리문 수행 후 결과 얻기 
		ss.insert("kr.co.sist.board.insertBoard",bDTO);
		//3.결과 작업
		if(ss!=null) {ss.close();}
		//4.MyBatis Handler닫기 
	}// insertBoard 
 
	public BoardDomain selectBoardDetail(int num) throws SQLException {
		BoardDomain bDomain = null;
		//1.MyBatis Handler얻기
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(true);
		//2.쿼리문 수행 후 결과 얻기 
		bDomain=ss.selectOne("kr.co.sist.board.selectBoardDetail", num);
		//3.결과 작업
		if(ss!=null) {ss.close();}
		//4.MyBatis Handler닫기 

		return bDomain;
	} // insertBoardDetail
	
	public void updateBoardCnt(int num) throws SQLException {
		//1.MyBatis Handler얻기
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(true);
		//2.쿼리문 수행 후 결과 얻기 
		ss.update("kr.co.sist.board.updateBoardCnt", num);
		//3.결과 작업
		if(ss!=null) {ss.close();}
		//4.MyBatis Handler닫기 
		
	} // updateBoardDetail

	public int updateBoard(BoardDTO bDTO) throws SQLException {
		int cnt=0;
		
		//1.MyBatis Handler얻기
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(true);
		//2.쿼리문 수행 후 결과 얻기 
		cnt=ss.update("kr.co.sist.board.updateBoard", bDTO);
		//3.결과 작업
		if(ss!=null) {ss.close();}
		//4.MyBatis Handler닫기 
		return cnt;
		 
	}// updateBoard

	public int deleteBoard(BoardDTO bDTO) throws SQLException {
		int cnt=0;
		//1.MyBatis Handler얻기
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(true);
		//2.쿼리문 수행 후 결과 얻기 
		cnt=ss.delete("kr.co.sist.board.deleteBoard", bDTO);
		//3.결과 작업
		if(ss!=null) {ss.close();}
		//4.MyBatis Handler닫기 
		return cnt;
	}// updateBoard
	
}// class