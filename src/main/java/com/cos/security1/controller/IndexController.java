package com.cos.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
