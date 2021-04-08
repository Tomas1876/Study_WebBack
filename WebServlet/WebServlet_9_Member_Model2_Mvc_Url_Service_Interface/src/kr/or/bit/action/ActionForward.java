package kr.or.bit.action;

//servlet 요청 받아서
//1. 화면 출력
//2. 서비스 실행(로직처리)

//화면이냐 로직이냐
//뷰의 경로 
public class ActionForward {
	private boolean isRedirect =false; //화면 전환 여부
	private String path=null;  //이동경로(뷰의 주소)
	
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
