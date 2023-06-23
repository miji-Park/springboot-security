package com.cos.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security1.model.User;

//CRUD 함수를 JpaRepository가 들고 있음
//@Repository라는 어노테이션이 없어도 IoC된다(빈으로 자동 등록) 왜냐면 JpaRepository 상속했기때문에 
public interface UserRepository extends JpaRepository<User, Integer>{
	//findBy 규칙 -> Username 문법
	//select * from user where username = 1?
	public User findByUsername(String username); //Jpa 쿼리 메서드를 검색해보자
  
}
