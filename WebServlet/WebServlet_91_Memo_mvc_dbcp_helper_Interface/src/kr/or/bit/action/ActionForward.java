package kr.or.bit.action;

//servlet 요청 받아서
//1. 화면 출력
//2. 서비스 실행(로직처리)

//화면이냐 로직이냐
//뷰의 경로
public class ActionForward {
	
	private boolean isRedirect = false; 
	private String path = null;
	
	
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
