package com.talkdesk.todolist.service;

import com.talkdesk.todolist.model.TodoListItem;
import com.talkdesk.todolist.repository.TodoListItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@Service
public class TodoListService {

    private final TodoListItemRepository todoListItemRepository;

    public TodoListService(TodoListItemRepository todoListItemRepository) {
        this.todoListItemRepository = todoListItemRepository;
    }

    public Flux<TodoListItem> getItemsForUser(String userId) {
        return todoListItemRepository.findAllByUserId(userId);
    }

    public Mono<TodoListItem> createItem(String userId, String title, String description) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(title);
        Objects.requireNonNull(description);

        TodoListItem item = new TodoListItem(
                UUID.randomUUID().toString(),
                userId,
                title,
                description,
                false
        );
        return todoListItemRepository.save(item);
    }

}
