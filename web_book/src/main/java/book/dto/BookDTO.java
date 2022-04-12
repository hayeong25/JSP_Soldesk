package book.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BookDTO {
	private int code;
	private String title;
	private String writer;
	private int price;
}