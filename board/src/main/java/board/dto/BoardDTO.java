package board.dto;

import java.util.Date;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class BoardDTO {
	private int bno;
	private String name;
	private String password;
	private String title;
	private String content;
	private String attach;
	private int reRef;
	private int reLev;
	private int reSeq;
	private int readCount;
	private Date regDate;
}