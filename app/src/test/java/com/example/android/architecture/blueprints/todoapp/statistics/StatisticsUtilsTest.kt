package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class StatisticsUtilsTest {

	// If there's no completed task and one active task,
	// then there are 100% percent active tasks and 0% completed tasks.
	@Test
	fun getActiveAndCompleteStatus_noCompleted_returnsHundredZero() {
		// Create an active tasks (the false makes this active)
		val tasks = listOf<Task>(
				Task("title", "description", isCompleted = false)
		)

		// Call our function
		val result = getActiveAndCompletedStats(tasks)

		// Check the result
		assertThat(result.completedTasksPercent, `is`(0f))
		assertThat(result.activeTasksPercent, `is`(100f))
	}

	// If there's 2 completed tasks and 3 active tasks,
	// then there are 40% percent completed tasks and 60% active tasks.
	@Test
	fun getActiveAndCompleteStatus_both_returnsFortySixty() {
		// Create an active tasks (the false makes this active)
		val tasks = listOf<Task>(
				Task("title", "description", isCompleted = true),
				Task("title", "description", isCompleted = true),
				Task("title", "description", isCompleted = false),
				Task("title", "description", isCompleted = false),
				Task("title", "description", isCompleted = false)
		)

		// Call our function
		val result = getActiveAndCompletedStats(tasks)

		// Check the result
		assertThat(result.completedTasksPercent, `is`(40f))
		assertThat(result.activeTasksPercent, `is`(60f))
	}

	@Test
	fun getActiveAndCompletedStats_empty_returnZeros() {
		val tasks = emptyList<Task>()

		val result = getActiveAndCompletedStats(tasks)

		assertThat(result.completedTasksPercent, `is`(0f))
		assertThat(result.activeTasksPercent, `is`(0f))
	}

	@Test
	fun getActiveAndCompletedStats_error_returnZeros() {
		val tasks = null

		val result = getActiveAndCompletedStats(tasks)

		assertThat(result.completedTasksPercent, `is`(0f))
		assertThat(result.activeTasksPercent, `is`(0f))
	}
}