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
		String sql = "select * from (" + 
				"    select rownum num, N.* " + 
				"    from (select * from notice order by regdate desc) N " + 
				") " + 
				"where num between 6 and 10";
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
		String sql = "select * from notice where id = ?";
		return null;
	}
	public Notice getNextNotice(int id)
	{
		String sql = "select * from notice " + 
				"	where id = ( " + 
				"   select id from notice " + 
				"   where regdate > (select regdate from notice where id=3) " + 
				"   and rownum=1 " + 
				") ";
		return null;
	}
	public Notice getPrevNotice(int id)
	{
		String sql = "select id from (select * from notice order by regdate desc) " + 
				"    where regdate < (select regdate from notice where id=3) " + 
				"    and rownum=1";
		return null;
	}
}
