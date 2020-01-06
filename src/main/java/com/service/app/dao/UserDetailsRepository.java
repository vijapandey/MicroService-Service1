/**
 * 
 */
package com.service.app.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.service.app.model.UserInfo;

/**
 * @author vijpande
 *
 */
@Repository
@Transactional
public interface UserDetailsRepository extends CrudRepository<UserInfo, Integer> {
	public UserInfo findByUserNameAndEnabled(String userName, short enabled);

	public List<UserInfo> findAllByEnabled(short enabled);

	public Optional<UserInfo> findById(Integer id);

	public void deleteById(Integer id);
}