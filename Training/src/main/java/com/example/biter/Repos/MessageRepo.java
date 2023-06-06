package com.example.biter.Repos;

import com.example.biter.Domain.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepo extends CrudRepository<Message, Long> {

    Page<Message> findByTagLike(String tag, Pageable pageable);

    Page<Message> findAll(Pageable pageable);

    Optional<Message> findById(Long id);

}
