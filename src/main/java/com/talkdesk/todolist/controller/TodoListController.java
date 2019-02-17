package com.talkdesk.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.Objects;

@RestController
@RequestMapping("/todo/{userId}")
public class TodoListController {

    @GetMapping
    public Flux<TodoListItem> listItems(@PathVariable String userId) {
        return Flux.fromIterable(Collections.singletonList(new TodoListItem(
                "some-id",
                userId,
                "Some Title",
                "Some Description",
                false
        )));
    }

    private class TodoListItem {

        private String id;
        private String userId;
        private String title;
        private String description;
        private boolean completed;

        private TodoListItem(String id, String userId, String title, String description, boolean completed) {
            this.id = id;
            this.userId = userId;
            this.title = title;
            this.description = description;
            this.completed = completed;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TodoListItem that = (TodoListItem) o;
            return completed == that.completed &&
                    Objects.equals(id, that.id) &&
                    Objects.equals(userId, that.userId) &&
                    Objects.equals(title, that.title) &&
                    Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, userId, title, description, completed);
        }

        @Override
        public String toString() {
            return "TodoListItem{" +
                    "id='" + id + '\'' +
                    ", userId='" + userId + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", completed=" + completed +
                    '}';
        }
    }
}
