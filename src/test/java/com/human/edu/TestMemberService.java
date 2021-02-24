package com.human.edu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.human.service.MemberService;
import com.human.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class TestMemberService {
	
	@Inject
	private DataSource dataSource;//객체, 오브젝트
	
	@Inject
	private MemberService memberService;
	
	/* 멤버 삭제 */
	public void memberDelete() throws Exception {
		List<MemberVO> memberList = memberService.memberSelect();
		System.out.println("디버그 top1구하기"+memberList.get(0).getUserid());
		if(memberList.size()>1) {
			memberService.memberDelete(memberList.get(0).getUserid());
		}else {
			System.out.println("삭제할 사용자가 1명일때입니다");
		}
	}
	/* 멤버 수정*/
	@Test
	public void memberUpdate() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setUserid("user2");
		memberVO.setUsername("수정사용자");
		memberVO.setUserpw("4321");
		memberVO.setEmail("abc@abc.com");
		memberService.memberUpdate(memberVO);
	}
	/* 멤버 추가 */
	@Test
	public void memberInsert() throws Exception {
		List<MemberVO> memberList = memberService.memberSelect();
		MemberVO memberVO = new MemberVO();
		memberVO.setUserid("user_"+memberList.size());
		memberVO.setUsername("사용자_"+memberList.size());
		memberVO.setUserpw("1234");
		memberVO.setEmail("user_"+memberList.size()+"@.abc.com");
		memberService.memberInsert(memberVO);
	}
	/* 멤버 상세보기*/
	@Test
	public void memberSelect() throws Exception {
		//JUnit으로 멤버서비스 Read 테스트
		System.out.println("디버그 1개의 레코드:"+memberService.memberView("user2"));
		System.out.println("디버그 다중 레코드:"+memberService.memberSelect());
	}
	/* 현재 접속된 DB 확인*/
	@Test
	public void oracle_connect() throws SQLException {
		Connection connect = dataSource.getConnection();
		//DB커넥트를 new 키워드로 만들지않고 get방식을 사용하는 이유는
		//new키워드 객체를 만들게 허용하면, 객체를 계속 생성가능
		//한번 커넥션이 되면 커넥션을 2번이상 호출하지 않음
		System.out.println("현재 접속된 DB커넥션은 "+connect);
	}
	/*테스트*/
	@Test
	public void junit_test() {
		System.out.println("JUnit실행확인 메서드");
	}
}
