package com.cos.security1.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

//시큐리티 설정(시큐리티컨피그 파일)에서 .loginProcessingUrl("/login")으로 걸어나ㅗㅆ어
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어있는 loadUserByUsername 함수가 실행(규칙 꼭 이렇게 해야해)
@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	//시큐리티 session(내부 Authentication(내부=>UserDetails=PrincipalDetails)) loadUserByUsername 이 메소드가 알아서 리턴될때 다 넣어준다
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //여기서 username은 loginForm.html에서 파라미터로 넘어가는 name="username"을 정확하게 적어줘야해
		System.out.println("username:" + username);																					//name="username2"라고하면 안 받아져 잘 적자
		User userEntity = userRepository.findByUsername(username); //위에 넘겨받은 username 이름으로 유저가 있는지 확인을 해야해
		
		if(userEntity != null) { //유저엔티티 찾았으면 유저엔티티 넣어서 리턴해줘
			return new PrincipalDetails(userEntity);
		}
		return null; //없으면 null리턴
	}

}
