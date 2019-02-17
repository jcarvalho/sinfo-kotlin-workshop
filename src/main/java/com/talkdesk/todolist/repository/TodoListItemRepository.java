package com.talkdesk.todolist.repository;

import com.talkdesk.todolist.model.TodoListItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TodoListItemRepository extends ReactiveCrudRepository<TodoListItem, String> {

    public Flux<TodoListItem> findAllByUserId(String userId);

}
