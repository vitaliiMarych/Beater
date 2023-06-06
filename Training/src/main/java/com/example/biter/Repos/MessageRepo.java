package com.example.biter.Repos;

import com.example.biter.Domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTagLike(String tag);

    Optional<Message> findById(Long id);

}
