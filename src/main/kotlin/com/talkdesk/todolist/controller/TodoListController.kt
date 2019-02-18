package com.talkdesk.todolist.controller

import com.talkdesk.todolist.service.TodoListService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/todo/{userId}")
class TodoListController(
    private val todoListService: TodoListService
) {

    @GetMapping
    fun listItems(@PathVariable userId: String) = todoListService.getItemsForUser(userId)

    @PostMapping
    fun createItem(@PathVariable userId: String, @RequestBody request: Mono<CreateItemRequest>) =
        request.flatMap { data -> todoListService.createItem(userId, data.title, data.description) }

    data class CreateItemRequest(val title: String, val description: String)

    @PostMapping("/{id}")
    fun completeItem(@PathVariable userId: String, @PathVariable id: String) = todoListService.completeItem(userId, id)
        .map { item -> ResponseEntity.ok(item) }
        .defaultIfEmpty(ResponseEntity.notFound().build())
        .onErrorReturn(
            TodoListService.ItemAlreadyCompletedException::class.java,
            ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        )

    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable userId: String, @PathVariable id: String) = todoListService.deleteItem(userId, id)
        .map { ResponseEntity.noContent().build<Any>() }
        .defaultIfEmpty(ResponseEntity.notFound().build())
}
