/**
 * 
 */
package com.service.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.service.app.dao.UserDetailsRepository;
import com.service.app.model.UserInfo;

/**
 * @author vijpande
 *
 */
@Repository
@Transactional
public class UserInfoService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public UserInfo getUserInfoByUserName(String userName) {
		short enabled = 1;
		return userDetailsRepository.findByUserNameAndEnabled(userName, enabled);
	}

	public List<UserInfo> getAllActiveUserInfo() {
		return userDetailsRepository.findAllByEnabled((short) 1);
	}

	public Optional<UserInfo> getUserInfoById(Integer id) {
		return userDetailsRepository.findById(id);
	}

	public UserInfo addUser(UserInfo userInfo) {
		userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
		return userDetailsRepository.save(userInfo);
	}

	public UserInfo updateUser(UserInfo userInfo) {
		return userDetailsRepository.save(userInfo);
	}

	public void deleteUser(Integer id) {
		userDetailsRepository.deleteById(id);
	}
}