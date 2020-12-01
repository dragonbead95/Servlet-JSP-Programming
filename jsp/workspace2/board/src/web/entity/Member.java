package web.entity;

public class Member {
	private String email;
	private String pwd;
	
	public Member(String email, String pwd) {
		this.email = email;
		this.pwd = pwd;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	@Override
	public String toString() {
		return "Member [email=" + email + ", pwd=" + pwd + "]";
	}
	

}
