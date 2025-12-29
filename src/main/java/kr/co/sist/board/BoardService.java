package kr.co.sist.board;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import oracle.security.o3logon.a;

public class BoardService {
	private static BoardService bs;
	
	private BoardService() {
		
	}  
	 
	public static BoardService getInstance() {
		if(bs==null) {
			bs=new BoardService();
		}//end if  
		  
		return bs;
	}//getInstance

	public int totalCnt(RangeDTO rDTO) { 
		int totalCnt=0;
		BoardDAO bDAO=BoardDAO.getInstance();
		
		try {
			totalCnt=bDAO.selectBoardTotalCnt(rDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return totalCnt;
	}//totalCnt
	
	/**
	 * @return
	 */
	public int pageScale() {
		return 10;
	}
	
	public int totalPage(int totalCount, int pageScale) {
		return (int)Math.ceil((double)totalCount/pageScale);
	}
	
	/**
	 * 페이지의 시작번호 구하기
	 * @param currentPage - 현재 페이지
	 * @param pageScale - 한 화면에 보여줄 게시물의 수
	 * @return
	 */
	public int startNum(int currentPage, int pageScale) {
		return currentPage * pageScale-pageScale+1;
	}
	
	public int endNum(int startNum, int pageScale) {
		return startNum+pageScale-1;
	}
	
	
	
	public boolean addBoard(BoardDomain bDTO) throws PersistenceException{
		boolean flag=false;
		
		BoardDAO bDAO=BoardDAO.getInstance();
		try {
			bDAO.insertBoard(bDTO);
			flag=true;
		} catch(PersistenceException pe){
			pe.printStackTrace();
		}//end catch
		
		return flag;
	}//addBoard
	
	/**
	 * 시작번호, 끝번호, 검색 필드, 검색 키워드를 사용한 게시글 검색
	 * @param rDto
	 * @return
	 */
	public List<BoardDomain> searchBoardList(RangeDTO rDTO) {
		List<BoardDomain> list=null;
		BoardDAO bDAO=BoardDAO.getInstance();
		
		try {
			list=bDAO.selectRangeBoard(rDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 제목이 20자를 초과하면 19자까지 보여주고 뒤에 ...을 붙이는 일
	 * @param list
	 */
	public void titleSubStr(List<BoardDomain> boardList) {
		String title="";
		for(BoardDomain bDTO:boardList) {
			title=bDTO.getTitle();
			if(title.length()>19) {
				bDTO.setTitle(title.substring(0, 19)+ "...");
			}//end if
		}//end for
	}//titleSubStr
	
	/**
	 * 페이지네이션 [<<] ... [1][2][3]... [>>]
	 * @param rDTO
	 * @return
	 */
	public String pagination(RangeDTO rDTO) {
		StringBuilder pagination=new StringBuilder();
		//1. 한 화면에 보여줄 pagination의 수
		int pageNumber=3;
		//2. 화면에 보여줄 시작 페이지 번호 1,2,3 => 1로 시작 4,5,6 => 2로 시작
		int startPage=((rDTO.getCurrentPage()-1)/pageNumber)*pageNumber+1;
		//3. 화면에 보여줄 마지막 페이지 번호 1,2,3 => 3
		int endPage=(((startPage-1)+pageNumber)/pageNumber)*pageNumber;
		//4. 총 페이지 수가 연산된 마지막 페이지 수보다 작다면 총 페이지 수가 마지막 페이지 수로 설정
		//456인 경우 > 4로 설정
		int totalPage=rDTO.getTotalPage();
		if(totalPage<endPage) {
			endPage=totalPage;
		}//end if
		//5. 첫 페이지가 인덱스 화면이 아닌 경우 ([<<]눌렀을 때 이전페이지로 이동)
		int movePage=0;
		StringBuilder prevMark=new StringBuilder();
		prevMark.append("[&lt;&lt;]");
		/*prevMark.append("<li class=\"page-item\"><a class=\"page-link\" href=\"#\">Previous</a></li>"); */
		if(rDTO.getCurrentPage()>pageNumber) { //시작 페이지 번호가 3보다 크면
			movePage=startPage-1; //4,5,6->3 , 7,8,9->6 -> 4
			prevMark.delete(0, prevMark.length()); //이전에 링크가 없는 [<<] 삭제
			prevMark.append("[ <a href='").append(rDTO.getUrl()).append("?currentPage=")
			.append(movePage);
			if(rDTO.getKeyword() != null && !rDTO.getKeyword().isEmpty()) { //검색 키워드가 있다면 검색 키워드를 붙인다.
				prevMark.append("&field=").append(rDTO.getField())
				.append("&keyword=").append(rDTO.getKeyword());
			}//end if
			prevMark.append("' class='prevMark'>&lt;&lt;</a>");
			
		}//end if
		
		
		//6. 시작 페이지 번호부터 끝 번호까지 화면에 출력
		StringBuilder pageLink=new StringBuilder();
		movePage=startPage;
		
		while(movePage <= endPage) {
			if(movePage==rDTO.getCurrentPage()) { //현재 페이지: 링크 x
				pageLink.append("[ <span class='currentPage'>")
				.append(movePage).append("</span>]");
			} else { //현재 페이지가 아닌 다른 페이지: 링크 O
				pageLink.append("[<a class='notCurrentPage' href='")
				.append(rDTO.getUrl()).append("?currentPage=").append(movePage);
			
				if(rDTO.getKeyword() != null && !rDTO.getKeyword().isEmpty()) { //검색 키워드가 있다면 검색 키워드를 붙인다.
					prevMark.append("&field=").append(rDTO.getField())
					.append("&keyword=").append(rDTO.getKeyword());
				}//end if
				pageLink.append("'>").append(movePage).append("</a> ] ");
			}//else
			movePage++;
		}//end while
		
		//7. 뒤에 페이지가 있는 경우
		StringBuilder nextMark=new StringBuilder("[ &gt;&gt; ]");
		
		if(rDTO.getTotalPage() > endPage) { //뒤에 페이지가 더 있음
			movePage=endPage+1;
			nextMark.delete(0, nextMark.length());
			nextMark.append("[ <a class='nextMark' href='")
			.append(rDTO.getUrl()).append("?currentPage=").append(movePage);
			if(rDTO.getKeyword() != null && !rDTO.getKeyword().isEmpty()) { //검색 키워드가 있다면 검색 키워드를 붙인다.
				nextMark.append("&field=").append(rDTO.getField())
				.append("&keyword=").append(rDTO.getKeyword());
			}//end if
			
			nextMark.append("'>&gt;&gt;</a> ] ");
		}
		
		pagination.append(prevMark).append("...").append(pageLink).append("...").append(nextMark);
		
		return pagination.toString();
	}//pagination
	/**
	 * 상세보기
	 * @param num
	 * @return
	 */
	public BoardDomain searchOneBoard(int num) {
		BoardDomain bDTO=null;
		BoardDAO bDAO=BoardDAO.getInstance();
		try {
			bDTO=bDAO.selectBoardDetail(num);
		} catch(SQLException se){
			se.printStackTrace();
		}//end catch
		
		return bDTO;
	}
	
	public void modifyBoardCnt(int num) {
		BoardDAO bDAO=BoardDAO.getInstance();
		try {
			bDAO.updateBoardCnt(num);
		} catch(SQLException se){
			se.printStackTrace();
		}//end catch
	}//modifyBoardCnt
	
	public boolean modifyBoard(BoardDomain bDTO) {
		boolean flag=false;
		
		BoardDAO bDAO=BoardDAO.getInstance();
		
		try {
			flag=bDAO.updateBoard(bDTO)==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean removeBoard(BoardDomain bDTO) {
		boolean flag=false;
		
		BoardDAO bDAO=BoardDAO.getInstance();
		
		try {
			flag=bDAO.deleteBoard(bDTO)==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(flag);
		return flag;
	}
}//class
