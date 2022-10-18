package com.marvel.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class MainCoroutineRule : TestRule, TestCoroutineScope by TestCoroutineScope() {

    private val testCoroutinesDispatcher = TestCoroutineDispatcher()
    private val testCoroutinesScope = TestCoroutineScope(testCoroutinesDispatcher)

    override fun apply(base: Statement?, description: Description?) =
        object : Statement() {
            override fun evaluate() {

                Dispatchers.setMain(testCoroutinesDispatcher)

                base?.evaluate()

                cleanupTestCoroutines()
                Dispatchers.resetMain()
            }
        }

    fun runTest(block: suspend TestCoroutineScope.() -> Unit) = testCoroutinesScope.runBlockingTest(block)
}
