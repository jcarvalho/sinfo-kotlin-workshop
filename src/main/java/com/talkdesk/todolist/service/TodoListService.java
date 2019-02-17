package com.talkdesk.todolist.service;

import com.talkdesk.todolist.model.TodoListItem;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Collections;

@Service
public class TodoListService {

    public Flux<TodoListItem> getItemsForUser(String userId) {
        return Flux.fromIterable(Collections.singletonList(new TodoListItem(
                "some-id",
                userId,
                "Some Title",
                "Some Description",
                false
        )));
    }

}
