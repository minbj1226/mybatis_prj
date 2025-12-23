package kr.co.sist.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
 
import kr.co.sist.dao.MyBatisHandler;

public class BoardDAO2 {
	private static BoardDAO2 bDAO;

	private BoardDAO2() {

	}
 
	public static BoardDAO2 getInstance() {
		if (bDAO == null) {
			bDAO = new BoardDAO2();
		} // end if
		return bDAO;
	}// getInstance

	/**
	 * @return 총 게시글의 수를 int로 반환하는 method
	 * @throws SQLException
	 */
	public int selectBoardTotalCnt(RangeDTO rDTO) throws SQLException {
		int totalCnt = 0;
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
		return totalCnt;
	}// selectBoardTotalCnt

	public List<BoardDTO> selectRangeBoard(RangeDTO rDTO) throws SQLException {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		/*
		 * DbConn dbCon = DbConn.getInstance("jdbc/dbcp");
		 * 
		 * Connection con = null; PreparedStatement pstmt = null; ResultSet rs = null;
		 * 
		 * try { // 1.JNDI 사용 객체 생성 // 2.DataSource 얻기 // 3.Connection 얻기 con =
		 * dbCon.getConn(); // 4.쿼리문생성 객체 얻기 StringBuilder selectBoard = new
		 * StringBuilder();
		 * selectBoard.append("	select num, title, input_date, ip, cnt, id	")
		 * .append("	from(select num, title, input_date, ip, cnt, id,	")
		 * .append("	row_number() over(order by input_date desc) rnum	").
		 * append("	from board	"); // dynamic query //dynamic query: 검색키워드가 있다면 검색 키워드에
		 * 해당하는 글의 개수 검색 if(rDTO.getKeyword()!= null && !rDTO.getKeyword().isEmpty()) {
		 * selectBoard.append(" where instr(")
		 * .append(rDTO.getFieldStr()).append(",?) != 0"); }//end if
		 * selectBoard.append(") where rnum between ? and ?	");
		 * 
		 * pstmt = con.prepareStatement(selectBoard.toString()); // 5.바인드 변수 값 설정 int
		 * pstmtIdx=0; if(rDTO.getKeyword()!= null && !rDTO.getKeyword().isEmpty()) {
		 * pstmt.setString(++pstmtIdx, rDTO.getKeyword()); }//end if
		 * pstmt.setInt(++pstmtIdx, rDTO.getStartNum()); pstmt.setInt(++pstmtIdx,
		 * rDTO.getEndNum()); // 6.조회 결과 얻기 BoardDTO bDTO = null;
		 * 
		 * rs = pstmt.executeQuery(); while (rs.next()) { bDTO = new BoardDTO();
		 * bDTO.setNum(rs.getInt("num")); bDTO.setTitle(rs.getString("title"));
		 * bDTO.setInput_date(rs.getDate("input_date")); bDTO.setIp(rs.getString("ip"));
		 * bDTO.setCnt(rs.getInt("cnt")); bDTO.setId(rs.getString("id"));
		 * 
		 * list.add(bDTO); } // end while
		 *  
		 * } finally { dbCon.dbClose(rs, pstmt, con); } // end finally
		 */		return list;
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
 
	public BoardDTO selectBoardDetail(int num) throws SQLException {
		BoardDTO bDTO = null;

		/*
		 * DbConn dbCon = DbConn.getInstance("jdbc/dbcp");
		 * 
		 * Connection con = null; PreparedStatement pstmt = null; ResultSet rs = null;
		 * 
		 * try { con = dbCon.getConn();
		 * 
		 * StringBuilder selectDetail = new StringBuilder();
		 * selectDetail.append(" select title, content, input_date, ip, cnt, id ").
		 * append(" from board ") .append(" where num=? ");
		 * 
		 * pstmt = con.prepareStatement(selectDetail.toString()); pstmt.setInt(1, num);
		 * rs = pstmt.executeQuery();
		 * 
		 * if (rs.next()) { bDTO = new BoardDTO(); bDTO.setTitle(rs.getString("title"));
		 * 
		 * // --- CLOB → String 변환 --- StringBuilder content = new StringBuilder();
		 * 
		 * try (BufferedReader br = new
		 * BufferedReader(rs.getClob("content").getCharacterStream())) {
		 * 
		 * String line; while ((line = br.readLine()) != null) { content.append(line); }
		 * 
		 * } catch (IOException | NullPointerException e) { e.printStackTrace(); }
		 * 
		 * bDTO.setContent(content.toString());
		 * bDTO.setInput_date(rs.getDate("input_date")); bDTO.setIp(rs.getString("ip"));
		 * bDTO.setCnt(rs.getInt("cnt")); bDTO.setId(rs.getString("id")); }
		 * 
		 * } finally { dbCon.dbClose(rs, pstmt, con); }
		 */
		return bDTO;
	} // insertBoardDetail
	
	public void updateBoardCnt(int num) throws SQLException {
		//DbConn dbCon = DbConn.getInstance("jdbc/dbcp");
		/*
		 * Connection con = null; PreparedStatement pstmt = null;
		 * 
		 * try { con = dbCon.getConn();
		 * 
		 * StringBuilder updateCnt = new StringBuilder(); updateCnt
		 * .append(" update board ") .append(" set cnt=cnt+1 ")
		 * .append(" where num=? ");
		 * 
		 * pstmt = con.prepareStatement(updateCnt.toString());
		 * 
		 * pstmt.setInt(1, num); pstmt.executeUpdate(); } finally { dbCon.dbClose(null,
		 * pstmt, con); }
		 */
		
	} // updateBoardDetail

	public int updateBoard(BoardDTO bDTO) throws SQLException {
		int cnt=0;
		
		/*
		 * DbConn dbCon = DbConn.getInstance("jdbc/dbcp");
		 * 
		 * Connection con = null; PreparedStatement pstmt = null; ResultSet rs = null;
		 * 
		 * try { con = dbCon.getConn(); StringBuilder updateBoard = new StringBuilder();
		 * updateBoard .append("	update board	")
		 * .append("	set content=?, ip=?	") .append("	where num=?	and id=?");
		 * 
		 * pstmt=con.prepareStatement(updateBoard.toString());
		 * 
		 * pstmt.setString(1, bDTO.getContent()); pstmt.setString(2, bDTO.getIp());
		 * pstmt.setInt(3, bDTO.getNum()); pstmt.setString(4, bDTO.getId());
		 * 
		 * cnt=pstmt.executeUpdate(); } finally { dbCon.dbClose(rs, pstmt, null); } //
		 * end finally
		 */		return cnt;
	}// updateBoard

	public int deleteBoard(BoardDTO bDTO) throws SQLException {
		int cnt=0;
		
		/*
		 * DbConn dbCon = DbConn.getInstance("jdbc/dbcp");
		 * 
		 * Connection con = null; PreparedStatement pstmt = null; ResultSet rs = null;
		 * 
		 * try { con = dbCon.getConn(); StringBuilder deleteBoard = new StringBuilder();
		 * deleteBoard .append("	delete from board	")
		 * .append("	where num=?	and id=?");
		 * 
		 * pstmt=con.prepareStatement(deleteBoard.toString());
		 * 
		 * pstmt.setInt(1, bDTO.getNum()); pstmt.setString(2, bDTO.getId());
		 * 
		 * cnt=pstmt.executeUpdate(); } finally { dbCon.dbClose(rs, pstmt, null); } //
		 * end finally
		 */		return cnt;
	}// updateBoard
	
}// class