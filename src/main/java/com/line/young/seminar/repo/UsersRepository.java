/**
 * Copyright@ LINE 2019
 */
package com.line.young.seminar.repo;

import org.springframework.data.repository.CrudRepository;
import com.line.young.seminar.entity.UserInfo;

/**
 * 
 * @author jounghunyoung@gmail.com
 * 
 */
public interface UsersRepository extends CrudRepository<UserInfo, String> {}
