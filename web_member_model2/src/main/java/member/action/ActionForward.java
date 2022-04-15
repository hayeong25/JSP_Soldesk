package member.action;

import lombok.*;

// path, 이동방식

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ActionForward {
	private String path;
	private boolean redirect; // true(sendRedirect), false(forward)
}