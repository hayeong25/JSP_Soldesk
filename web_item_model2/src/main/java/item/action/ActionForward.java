package item.action;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ActionForward {
	private String path;
	private boolean redirect; //true(sendRedirect), false(forward)
}