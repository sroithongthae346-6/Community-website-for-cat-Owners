package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Reply;

@Repository
public interface ReplyRepository extends CrudRepository<Reply, Long>{

}
