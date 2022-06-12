package com.example.timermvvm

import org.junit.Assert.*
import org.junit.Test

class ModelTest{

    @Test
    fun test_start_with_saved_value(){
        val testDataSource = TestDataSource()
        val timerTicker = TestTimeTicker()
        val model = Model(testDataSource, timerTicker)
        val callback = TestCallback()
        testDataSource.saveInt("",5)
        model.start(callback)
        timerTicker.tick(1)
        val actual = callback.text
        val expected = "6"
        assertEquals(expected,actual)
    }

    @Test
    fun test_stop_after_2_second(){
        val testDataSource = TestDataSource()
        val timerTicker = TestTimeTicker()
        val model = Model(testDataSource, timerTicker)
        val callback = TestCallback()
        testDataSource.saveInt("",0)
        model.start(callback)
        timerTicker.tick(2)
        val actual = callback.text
        val expected = "2"
        assertEquals(expected,actual)

        model.stop()
        val saveCountActual = testDataSource.getInt("")
        val saveCounterExpected = 2
        assertEquals(saveCounterExpected,saveCountActual)
    }

    @Test
    fun test_start_after_stop(){
        val testDataSource = TestDataSource()
        val timerTicker = TestTimeTicker()
        val model = Model(testDataSource,timerTicker)
        val callback = TestCallback()
        testDataSource.saveInt("",10)
        model.start(callback)
        timerTicker.tick(2)
        val actual = callback.text
        val expected = "12"
        assertEquals(expected, actual)

        model.stop()
        val saveCountActual = testDataSource.getInt("")
        val savedCountExpected = 12
        assertEquals(savedCountExpected,saveCountActual)

        model.start(callback)
        timerTicker.tick(3)
        val actualText = callback.text
        val expectedText = "15"
        assertEquals(expectedText,actualText)
    }
}

private class TestCallback: TextCallback {
    var text = ""
    override fun updateText(str: String) {
        text = str
    }
}

private class TestDataSource: DataSource {
    private var int: Int = Int.MIN_VALUE
    override fun saveInt(key: String, value: Int) {
        int = value
    }

    override fun getInt(key: String) = int
}


