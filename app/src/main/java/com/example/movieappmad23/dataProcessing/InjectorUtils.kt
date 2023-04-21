package com.example.movieappmad23.dataProcessing

import android.content.Context
import com.example.movieappmad23.dataProcessing.TaskRepository
import com.example.movieappmad23.dataProcessing.Database

import com.example.tasksapplication.repositories.TaskRepository

import com.example.tasksapplication.TaskViewModelFactory
//noch adden

object InjectorUtils {
    private fun getTaskRepository(context: Context): TaskRepository{
        return TaskRepository(TaskDatabase.getDatabse(context).taskDao())
    }

    fun provideTaskViewModelFactory(context: Context): TaskViewModelFactory {
        val repository = getTaskRepository(context)
        return TaskViewModelFactory(repository)
    }
}