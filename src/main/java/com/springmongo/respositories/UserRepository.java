package com.springmongo.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
