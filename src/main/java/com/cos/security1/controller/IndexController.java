package com.cos.security1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Controller//뷰를 리턴하겠다=>index는 뷰
public class IndexController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
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
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/joinForm") //회원가입 페이지가 나오고
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("/join") //실제 회원가입
	public @ResponseBody String join(User user) {
		System.out.println("User:"+user);
		user.setRole("ROLE_USER"); //role을 강제로 넣어주자/id랑 크리에이트데이트는 자동으로 생성
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		
		userRepository.save(user); //회원가입 잘 된다 -> 비밀번호가 그냥 1234 => 패스워드 암호화가 안돼서 시큐리티로 로그인 할 수 없음
		return "redirect:/loginForm"; //redirect: /loginForm(이거 왜 안되지)    redirect를 붙이면 /loginForm이 붙은 함수를 호출해준다 =>loginForm 페이지로 이동!
	}
}
