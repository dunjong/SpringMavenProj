package com.kosmo.onememo.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosmo.onememo.service.OneMemoService;

@Controller
@RequestMapping("/OneMemo")
public class AuthController {
	
	//서비스 주입]
	@Resource(name="memoService")
	private OneMemoService memoService;
	
	//로그인 폼으로 이동]
	@RequestMapping("/Auth/Login.bbs")
	public String login() {
		return "member/Login.tiles";
	}///////////login
	//스프링씨큐리티 적용시 로그인처리 /로그아웃처리 주석처리
	/*
	//로그인 처리]
	@RequestMapping("/Auth/LoginProcess.bbs")
	public String process(HttpSession session,@RequestParam Map map,Model model) {
		//서비스 호출]
		boolean flag=memoService.isLogin(map);
		if(flag)//회원
			//세션 영역에 데이타 저장
			session.setAttribute("id",map.get("id"));
		else//비회원이거나 아이디가 틀린경우
			model.addAttribute("NotMember", "아뒤와 비번이 틀려요");
		return "member/Login.tiles";	
	}////////process
	
	//로그아웃 처리]
	@RequestMapping("/Auth/Logout.bbs")
	public String logout(HttpSession session) {
		
		//로그아웃 처리-세션영역 데이타 삭제
		session.invalidate();
		return "member/Login.tiles";	
	}//////////logout
	*/
	
	
	//로그인 여부 판단]
	@RequestMapping(value="/Auth/IsLogin.bbs",produces = "text/html; charset=UTF-8")
	@ResponseBody
	//public String isLogin(HttpSession session) {//Security 미 사용시
	public String isLogin(Authentication auth) {//Security 사용시
		//스프링 씨큐리티 적용시 인증(로그인)되었다면
		//Authentication객체에 로그인과 관련된 정보가 전달됨
		//로그인이 안되어 있으면 auth는 null
		JSONObject json = new JSONObject();
		/*
		//Security 미 사용시
		if(session.getAttribute("id")==null) {
			json.put("IsLogin", "NO");
			return json.toJSONString();
		}*/
		if(auth == null) {//로그인이 안된 경우
			json.put("IsLogin", "NO");
			return json.toJSONString();
		}
		json.put("IsLogin", "YES");
		return json.toJSONString();
	}
	
	
}
