package day1231;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Setter;

public class SelectService5 {
	private static SelectService5 ss;
	
	private SelectService5() {
		
	}
	
	public static SelectService5 getInstance() {
		if(ss==null) {
			ss=new SelectService5();
		}//end if
		return ss;
	}//getInstance
	
	public void addMember(MemberDTO mDTO) {
		SelectDAO5 sd5=SelectDAO5.getInstacne();
		sd5.insertMember(mDTO);
	}
	
	public MemberDomain searchOneMember(int num) {
		MemberDomain md=null;
		
		SelectDAO5 sd5=SelectDAO5.getInstacne();
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("num", num);
		
		sd5.selectOneMember(map);
		//숫자: BigDecimal, 문자열: String, 날짜: Timestamp
		
		List<Map<String, Object>> list=(List<Map<String,Object>>)map.get("searchMember");
		//검색결과가 없다면 list는 생성되지만 방에 값이 들어가지는 않는다.
		if(!list.isEmpty()) {
			Map<String, Object> resultMap=list.get(0);
			md=new MemberDomain();
			md.setName((String)resultMap.get("NAME"));
			BigDecimal bdAge=(BigDecimal)resultMap.get("AGE");
			int age=0;
			if(bdAge!=null) {
				age=bdAge.intValue();
			}//end if
			md.setAge(age);
			md.setGender((String)resultMap.get("GENDER"));
			md.setTel((String)resultMap.get("TEL"));
			md.setInputDate((Timestamp)resultMap.get("INPUT_DATE"));
		}//end if
		
		return md;
	}//searchOneMember
	
	public List<MemberDomain> searchAllMember(){
		List<MemberDomain> list=new ArrayList<MemberDomain>();
		SelectDAO5 sd5=SelectDAO5.getInstacne();
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		sd5.selectAllMember(map);
		
		List<Map<String, Object>> dataList=(List<Map<String,Object>>)map.get("searchAllMember");
		
		BigDecimal bdAge=null;
		MemberDomain md=null;
		Timestamp ts=null;
		for(Map<String, Object> dataMap:dataList) {
			md=new MemberDomain();
			md.setNum(((BigDecimal)dataMap.get("NUM")).intValue());
			md.setName((String)dataMap.get("NAME"));
			bdAge=(BigDecimal)dataMap.get("AGE");
			
			int age=0;
			if(bdAge!=null) {
				age=bdAge.intValue();
			}//end if
			md.setAge(age);
			md.setGender((String)dataMap.get("GENDER"));
			md.setTel((String)dataMap.get("TEL"));
			
			ts=(Timestamp)dataMap.get("INPUT_DATE");
			md.setInputDate(ts);
			if( ts != null) {
				md.setInput_date(new Date(ts.getTime()));
			}//end if
			list.add(md);
		}//end for
		return list;
	}//searchAllMember
	
	public static void main(String[] args) {
		SelectService5 ss5=SelectService5.getInstance();
		System.out.println(ss5.searchAllMember());
	}
	
}//class