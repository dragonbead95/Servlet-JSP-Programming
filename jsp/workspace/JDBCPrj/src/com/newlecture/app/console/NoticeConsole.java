package com.newlecture.app.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;



public class NoticeConsole {

	private NoticeService service;
	private int page;
	private int count;
	
	public NoticeConsole()
	{
		service = new NoticeService();
		page = 1;
		count = 0;
	}
	public void printNoticeList() {
		// TODO Auto-generated method stub
		
		List<Notice> list = service.getList(page);
		count = service.getCount();
				
		System.out.println("-----------------------------------------");
		System.out.printf("<��������> �� %d �Խñ�\n", count);
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
		System.out.printf("1.����ȸ/ 2.����/ 3.����/ 4.�۾���/ 5.���� >");
		
		String menu_ = br.readLine();
		int menu = Integer.parseInt(menu_);
		
		
		
		return menu;
	}
	public void movePrevList() {
		// TODO Auto-generated method stub
		if(page==1)
		{
			System.out.println("���� �������� �����ϴ�.");
			return;
		}
		page--;
	}
	public void moveNextList() {
		// TODO Auto-generated method stub
//		if(page==?)
//		{
//			System.out.println("���� �������� �����ϴ�.");
//			return;
//		}
		page++;
	}

}
