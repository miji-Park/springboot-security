package com.cos.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller//뷰를 리턴하겠다=>index는 뷰
public class IndexController {

	//localhost: 8080/ 혹은 8080
	@GetMapping({"","/"})
	public String index() {
		
		//머스테치 =>스프링이 권장하는 간단한 템플릿 엔진 / 기본폴더 src/main/resourses/
		//뷰리졸버 설정 : templates(prefix), .mustache(suffix)
		//pom.xml에 머스테치 사용하겠다고 의존성 등록해두면 자동으로 기본경로로 잡힌다 => application.yml에서 생략해도 돼
		return "index"; // src/main/resourses/templates/index.mustache로 경로 잡힘=>WebMvcConfig 파일을 만들자
	}
	
	@GetMapping("/user")
	public @ResponseBody String user() { //페이지 만들기 귀찮으니까 일단 @ResponseBody 다 붙여놔
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	//스프링 시큐리티가 해당 주소를 낚아채버리넹 =>SecurityConfig 파일 생성 후 작동 안 함
	@GetMapping("/login")
	public @ResponseBody String login() {
		return "login";
	}
	
	@GetMapping("/join")
	public @ResponseBody String join() {
		return "join";
	}
	
	@GetMapping("/joinProc")
	public @ResponseBody String joinProc() {
		return "회원가입 완료됨";
	}
	
}
