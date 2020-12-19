package web.service;

import java.util.List;

import web.entity.Notice;

public class NoticeService {
	public List<Notice> getNoticeList()
	{
		return getNoticeList("title", "", 1); // 기본값 page 1
	}
	public List<Notice> getNoticeList(int page)
	{
		//3개의 getNoticeList를 각각 구현하면 관리가 힘드므로 매개변수가 가장 많은 3번째 getNoticeList를
		//하는 방향으로 구현한다.
		return getNoticeList("title", "", page);
	}
	public List<Notice> getNoticeList(String field, String query, int page)
	{
		return null;
	}
	public int getNoticeCount()
	{
		return getNoticeCount("title", "");
	}
	public int getNoticeCount(String field, String query)
	{
		return 0;
	}
	public Notice getNotice(int id)
	{
		return null;
	}
	public Notice getNextNotice(int id)
	{
		return null;
	}
	public Notice getPrevNotice(int id)
	{
		return null;
	}
}
