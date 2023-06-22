package com.cos.security1.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override //컨트롤+스페이스로 configureViewResolvers 찾아
	  public void configureViewResolvers(ViewResolverRegistry registry) {
	      MustacheViewResolver resolver = new MustacheViewResolver(); //오버라이딩해서 해당 뷰 리졸브머스테치를 설정할 수 있다

	      resolver.setCharset("UTF-8"); //인코딩은 UTF-8이고
	      resolver.setContentType("text/html;charset=UTF-8"); //내가 너한테 던지는 데이터는 html 파일이고 UTF-8이야
	      resolver.setPrefix("classpath:/templates/"); //classpath:까지가 내 프로젝트 경로
	      resolver.setSuffix(".html"); //.html로 만들어도 머스테치로 인식하게 된다

	      registry.viewResolver(resolver); //마지막으로 레지스트리에 뷰리졸브를 등록해주면 된다
	}
	
}
