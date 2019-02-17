package com.talkdesk.todolist.controller;

import com.talkdesk.todolist.model.TodoListItem;
import com.talkdesk.todolist.service.TodoListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/todo/{userId}")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping
    public Flux<TodoListItem> listItems(@PathVariable String userId) {
        return todoListService.getItemsForUser(userId);
    }

}
