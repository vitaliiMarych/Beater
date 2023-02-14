package com.example.biter.Repos;

import com.example.biter.Domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTagLike(String tag);
}
