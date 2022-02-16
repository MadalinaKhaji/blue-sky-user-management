package com.workshop.usermanagement.repo;

import com.workshop.usermanagement.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {}
