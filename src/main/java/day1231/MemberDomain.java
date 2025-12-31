package day1231;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDomain {
	private int num, age;
	private String name, gender, tel;
	private Date input_date;
	private Timestamp inputDate;
}
