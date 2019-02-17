package com.talkdesk.todolist.controller;

import com.talkdesk.todolist.model.TodoListItem;
import com.talkdesk.todolist.service.TodoListService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


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

    @PostMapping
    public Mono<TodoListItem> createItem(@PathVariable String userId, @RequestBody Mono<CreateItemRequest> request) {
        return request.flatMap((data) -> todoListService.createItem(userId, data.getTitle(), data.getDescription()));
    }

    public static class CreateItemRequest {

        private String title;
        private String description;

        public CreateItemRequest() {
        }

        public CreateItemRequest(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}
