package board.action;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ActionForward {
	private String path;
	private boolean redirect;
}