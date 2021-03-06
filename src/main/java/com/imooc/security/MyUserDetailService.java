package com.imooc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService, SocialUserDetailsService{

	private Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);
	
	@Autowired 
	PasswordEncoder passwordEncoder;
	
	//表单用户登陆的时候用的
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("表单登陆用户名: " + username);
		return buildUser(username);
	}

	//社交登陆的时候用的，传进来的是根据社交用户的open—id查出来的userid
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info("社交用户登陆Id: " + userId);
		return buildUser(userId);
	}

	private SocialUserDetails buildUser(String userId) {
		logger.info("登陆用户名:" + userId);
		//根据用户名查找用户信息
		//根据查找到的用户信息判断用户是否被冻结
		String password = passwordEncoder.encode("123456");
		logger.info("数据库密码是:" + password);
		return new SocialUser(userId, password, true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}
	

}
