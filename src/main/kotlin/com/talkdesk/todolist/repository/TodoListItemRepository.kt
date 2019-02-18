package com.talkdesk.todolist.repository

import com.talkdesk.todolist.model.TodoListItem
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface TodoListItemRepository : ReactiveCrudRepository<TodoListItem, String> {

    fun findAllByUserId(userId: String): Flux<TodoListItem>

}
