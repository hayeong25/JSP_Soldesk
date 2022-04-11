package member.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class LoginDto {
	private String userid;
	private String password;
}