package web2.dto;

import lombok.*;

// Member 테이블과 동일하게 작성

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class MemberDTO {

	private int id;
	private String name;
	private String addr;
	private String email;
	private int age;
	
	public MemberDTO(String name, String addr, String email, int age) {
		super();
		this.name = name;
		this.addr = addr;
		this.email = email;
		this.age = age;
	}
	
}