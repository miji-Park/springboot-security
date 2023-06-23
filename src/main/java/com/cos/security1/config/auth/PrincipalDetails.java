package com.cos.security1.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.security1.model.User;

//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
//로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다(같은 세션 공간인데 시큐리티 자신만의 세션 공간을 갖는다)(Security ContextHolder 이 키 값에 세션정보를 저장)
//오브젝트=>Authentication 타입 객체만 가능
//Authentication 안에 User 정보가 있어야됨
//User 오브젝트 타입=>UserDetails 타입 객체

//Security Session 영역 => Authentication 타입 객체 =>저장할 User 정보는 UserDetails 타입 객체
//implements함으로써 PrincipalDetails도 UserDetails타입이 된다 = PrincipalDetails객체안에 넣을 수 있게 됨를 Authentication객체

public class PrincipalDetails implements UserDetails{

	private User user; //콤포지션
	
	public PrincipalDetails(User user) { //생성자
		this.user = user;
	}
	
	//해당 유저의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		//user.getRole(); //근데 얘가 스트링 타입이라 리턴 불가
		
		Collection<GrantedAuthority> collect = new ArrayList<>(); //어레이리스트는 콜렉션의 자식
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole(); //여기는 리턴타입 스트링이니까 가능
			}
		});
		
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { //네 계정 만료됐니
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { //네 계정 잠겼니
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //네 계정 비번이 1년이 지났지
		return true;
	}

	@Override
	public boolean isEnabled() { //네 계정이 활성화돼있니
		//우리 사이트에서 회원이 1년동안 로그인을 안 하면 휴면계정으로 하기로 함
		//유저 모델에  private Timestamp loginDate; 이걸 만들어서 로그인할때마다 그 날짜를 여기에 넣어놔
		//여기에 user.getLoiginDate(); 가져와서 
		//현재시간 - 로긴시간 => 1년을 초과하면 리턴 false
		return true;
	}
	

}
