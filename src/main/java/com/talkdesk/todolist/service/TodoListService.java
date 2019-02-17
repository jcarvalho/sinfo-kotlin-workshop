package com.talkdesk.todolist.service;

import com.talkdesk.todolist.model.TodoListItem;
import com.talkdesk.todolist.repository.TodoListItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class TodoListService {

    private final TodoListItemRepository todoListItemRepository;

    public TodoListService(TodoListItemRepository todoListItemRepository) {
        this.todoListItemRepository = todoListItemRepository;
    }

    public Flux<TodoListItem> getItemsForUser(String userId) {
        return todoListItemRepository.findAllByUserId(userId);
    }

}
