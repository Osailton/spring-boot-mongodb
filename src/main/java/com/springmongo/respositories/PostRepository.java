package com.springmongo.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
