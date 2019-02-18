package com.talkdesk.todolist.service

import com.talkdesk.todolist.model.TodoListItem
import com.talkdesk.todolist.repository.TodoListItemRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TodoListService(
    private val todoListItemRepository: TodoListItemRepository
) {

    fun getItemsForUser(userId: String) = todoListItemRepository.findAllByUserId(userId)

    fun createItem(userId: String, title: String, description: String) = todoListItemRepository.save(TodoListItem(
        id = UUID.randomUUID().toString(),
        title = title,
        description = description,
        userId = userId
    ))

    fun completeItem(userId: String, id: String) = todoListItemRepository.findById(id)
        .filter { item -> item.userId == userId }
        .map { item -> item.takeUnless(TodoListItem::completed) ?: throw ItemAlreadyCompletedException(id) }
        .flatMap { item -> todoListItemRepository.save(item.copy(completed = true)) }

    fun deleteItem(userId: String, id: String) = todoListItemRepository.findById(id)
        .filter { item -> item.userId == userId }
        .flatMap { item -> todoListItemRepository.delete(item).thenReturn(item) }

    class ItemAlreadyCompletedException(id: String) : RuntimeException("Item with ID $id is already completed")
}
