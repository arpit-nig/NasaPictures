package com.nigamar.nasapictures

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import com.nigamar.nasapictures.ui.MainActivity
import org.junit.After
import org.junit.Before


class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup(){
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }


    @After
    fun tearDown() {
        scenario.close()
    }
}