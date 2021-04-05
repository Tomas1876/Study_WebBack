package kr.or.bit.dto;

/*

	create table memo(
	id varchar2(15) not null,
	email varchar2(20) not null,
	content varchar2(100)
	);
	
	select id, email, content from memo where id=? - 데이터 한 건을 담을 수 있는 클래스
	>> hong, hong@naver.com, 방가방가
	
	DTO는 DB(Table)구조와 동일하게 만든다(컬럼명부터) - 자동화를 위한 작업이기도 함

 */
public class memo {
	
	private String id;
	private String email;
	private String content;
	
	//디폴트 생성자는 필수는 아니다
	public memo() {
		
	}

	public memo(String id, String email, String content) {
		super();
		this.id = id;
		this.email = email;
		this.content = content;
	}
	
	// getter와 setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	//toString 오버라이드
	@Override
	public String toString() {
		return "memo [id=" + id + ", email=" + email + ", content=" + content + "]";
	}
	

	// 이렇게 만든 클래스가 DTO다 바로 데이터를 담을 수 있는 클래스
	// 테이블과 1대 1 대응(맵핑될 수 있게 클래스명과 테이블명을 일치시킨다)
	// 예외도 있지만 통상적으로 이런 식
	
	// DTO를 만들었다면 이제 활용할 DAO를 만든다
	
	

}
