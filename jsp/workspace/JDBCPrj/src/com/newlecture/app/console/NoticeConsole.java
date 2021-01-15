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
	private String searchField;
	private String searchWord;
	
	public NoticeConsole()
	{
		service = new NoticeService();
		page = 1;
		searchField = "title";
		searchWord = "";
		
	}
	public void printNoticeList() {
		// TODO Auto-generated method stub
		
		List<Notice> list = service.getList(page,searchField,searchWord);
		int count = service.getCount();
		int lastPage = count/10;
		lastPage = lastPage%10>0 ? lastPage+1:lastPage;
		System.out.println("-----------------------------------------");
		System.out.printf("<공지사항> 총 %d 게시글중 %d 게시글\n", count,list.size());
		System.out.println("-----------------------------------------");
		
		for(Notice n : list)
		{
			System.out.printf("%d. %s / %s / %s\n",n.getId(),
													n.getTitle(),
													n.getWriterId(),
													n.getRegDate());
		}
		
		System.out.println("-----------------------------------------");
		System.out.printf("				%d/%d pages\n", page,lastPage);
	}

	public int inputNoticeMenu() throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.printf("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.검색/ 6.종료 >");
		
		String menu_ = br.readLine();
		int menu = Integer.parseInt(menu_);

		return menu;
	}
	public void movePrevList() {
		// TODO Auto-generated method stub
		if(page==1)
		{
			System.out.println("===================");
			System.out.println("[이전 페이지가 없습니다.]");
			System.out.println("===================");
			return;
		}
		page--;
	}
	public void moveNextList() {
		// TODO Auto-generated method stub
		int count = service.getCount();
		int lastPage = count/10;
		lastPage = lastPage%10>0 ? lastPage+1:lastPage;
		
		if(page==lastPage)
		{
			System.out.println("===================");
			System.out.println("[다음 페이지가 없습니다.]");
			System.out.println("===================");
			return;
		}
		page++;
	}
	public void inputSearchWord() {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("검색 범주(title/content/writerId)중에 하나를 입력하세요.");
			System.out.print(">");
			searchField = br.readLine();
			
			System.out.print("검색어 > ");
			searchWord = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
