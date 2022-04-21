package board.dto;

import lombok.*;

// 페이지 나누기와 관련된 정보를 담을 객체

@AllArgsConstructor
@Setter
@Getter
public class PageDTO {
	// 화면에 보여줄 첫 번째 페이지 번호
	private int startPage;
	
	// 화면에 보여줄 마지막 페이지 번호
	private int endPage;
	
	// 이전 부분 보여줄 것인지 여부
	private boolean prev;
	
	// 다음 부분 보여줄 것인지 여부
	private boolean next;
	
	// 전체 페이지 수
	private int totalPage;
	
	// 전체 게시물 수
	private int total;
	
	// 검색어, 검색 기준, 현재 페이지 번호, 현재 페이지에 보여줄 게시물 개수
	private SearchDTO searchDTO;
	
	public PageDTO(int total, SearchDTO searchDTO) {
		this.total = total;
		this.searchDTO = searchDTO;
		
		// 현재 화면의 마지막 페이지 수
		endPage = (int)(Math.ceil(searchDTO.getPage() / 10.0)) * 10;
		
		// 현재 화면 시작 페이지 수 1 2 3 4 ....
		startPage = endPage - 9;
		
		// 끝나는 페이지 다시 계산
		int realEnd = (int)(Math.ceil((total / 1.0) / searchDTO.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		// 이전 & 다음 버튼 활성화
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		
		// 전체 페이지 수
		totalPage = realEnd;
	}
}