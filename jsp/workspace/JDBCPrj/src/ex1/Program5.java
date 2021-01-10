package ex1;

import com.newlecture.app.console.NoticeConsole;

public class Program5 {

	public static void main(String[] args) {
		NoticeConsole console = new NoticeConsole();
		console.printNoticeList();
		int menu = console.inputNoticeMenu();
		
		switch(menu)
		{
		case 1: // 상세 조회
			break;
		case 2: // 이전
			break;
		case 3: // 다음
			break;
		case 4: // 글쓰기
			break;
		}
		
	}

}
