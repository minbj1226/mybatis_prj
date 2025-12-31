package day1231;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
	private int num, age, cnt;
	private String name, gender, tel, errMsg;
	private Date input_date;
}
