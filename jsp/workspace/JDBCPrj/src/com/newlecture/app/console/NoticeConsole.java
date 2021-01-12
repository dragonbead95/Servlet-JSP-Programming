package com.newlecture.app.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;



public class NoticeConsole {

	private NoticeService service;
	public NoticeConsole()
	{
		service = new NoticeService();
	}
	public void printNoticeList() {
		// TODO Auto-generated method stub
		List<Notice> list = service.getList(1);
		
		System.out.println("-----------------------------------------");
		System.out.printf("<공지사항> 총 %d 게시글\n", 12);
		System.out.println("-----------------------------------------");
		
		for(Notice n : list)
		{
			System.out.printf("%d. %s / %s / %s\n",n.getId(),
													n.getTitle(),
													n.getWriterId(),
													n.getRegDate());
		}
		
		System.out.println("-----------------------------------------");
		System.out.printf("				%d/%d pages\n", 1,2);
	}

	public int inputNoticeMenu() throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.printf("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.종료 >");
		
		String menu_ = br.readLine();
		int menu = Integer.parseInt(menu_);
		
		
		
		return menu;
	}

}
