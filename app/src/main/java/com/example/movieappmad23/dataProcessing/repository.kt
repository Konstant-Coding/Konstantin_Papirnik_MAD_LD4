package com.example.movieappmad23.dataProcessing

import com.example.movieappmad23.dataProcessing.TaskDao
import com.example.movieappmad23.dataProcessing.Task

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun add(task: Task) = taskDao.add(task)

    suspend fun delete(task: Task) = taskDao.delete(task)

    suspend fun update(task: Task) = taskDao.update(task)

    fun getAllTasks() = taskDao.readAll()
}