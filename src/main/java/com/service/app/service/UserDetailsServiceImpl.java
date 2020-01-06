package com.service.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.service.app.dao.UserDetailsRepository;
import com.service.app.model.UserInfo;

@Service(value = "userService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserInfoService userInfoDAO;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		try {
			//Use this method to generate the password for user and save into userinfo table for validation 
			//String pw_hash = BCrypt.hashpw("publicuser", BCrypt.gensalt()); 
			UserInfo userInfo = userInfoDAO.getUserInfoByUserName(userName);
			String roles = userInfo.getRole();
			//GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
			logger.info("Database result :" + userInfo.toString() + ", authority : " + roles);
			return new User(userInfo.getUserName(), userInfo.getPassword(), getAuthority(roles) /*Arrays.asList(authority)*/);
		} catch (Exception ex) {
			logger.error("Error: " + ex.getMessage());
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	}

	/***
	 * add Authority for authorization 
	 * @param roles
	 * @return
	 */
	private List<SimpleGrantedAuthority> getAuthority(String roles) {
		if(roles != null && !roles.isEmpty()) {
			List<SimpleGrantedAuthority> list = new ArrayList<>();
			String [] role = roles.split(",");
			for (int i = 0; i < role.length; i++) {
				list.add(new SimpleGrantedAuthority(role[i]));
				logger.info(i + ": Role =" + role[i] +", list :" +list.size());
			}
			return list;
		} else {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_PUBLIC"));
		}
	}

	public List<UserInfo> findAll() {
		List<UserInfo> list = new ArrayList<UserInfo>();
		userDetailsRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	public void delete(Integer id) {
		userDetailsRepository.deleteById(id);
	}

	public UserInfo save(UserInfo userInfo) {
		return userDetailsRepository.save(userInfo);
	}

}