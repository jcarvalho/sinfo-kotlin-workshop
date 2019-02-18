package com.talkdesk.todolist.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class TodoListItem(
    @Id val id: String,
    @Indexed val userId: String,
    val title: String,
    val description: String,
    val completed: Boolean = false
) {

    fun isCompleted() = completed

}
