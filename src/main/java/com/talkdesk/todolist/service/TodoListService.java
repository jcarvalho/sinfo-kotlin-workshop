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

    public Mono<TodoListItem> completeItem(String userId, String id) {
        return todoListItemRepository.findById(id)
                .filter((it) -> it.getUserId().equals(userId))
                .map((item) -> {
                    if (item.isCompleted()) {
                        throw new ItemAlreadyCompletedException(id);
                    } else {
                        return item;
                    }
                })
                .flatMap((item) -> todoListItemRepository.save(new TodoListItem(
                        item.getId(),
                        item.getUserId(),
                        item.getTitle(),
                        item.getDescription(),
                        true
                )));
    }

    public static class ItemAlreadyCompletedException extends RuntimeException {
        public ItemAlreadyCompletedException(String id) {
            super("Item with ID " + id + " is already completed");
        }
    }
}
