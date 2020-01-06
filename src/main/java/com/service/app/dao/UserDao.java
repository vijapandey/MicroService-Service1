package com.service.app.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.service.app.model.User;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-security-oauth2-example
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 31/12/17
 * Time: 15.48
 * To change this template use File | Settings | File Templates.
 */


@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
