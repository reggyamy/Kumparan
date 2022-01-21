package com.reggya.kumparantechnicaltest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.reggya.kumparantechnicaltest.core.utils.EspressoIdlingResource
import com.reggya.kumparantechnicaltest.home.HomeActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get: Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource())
    }

    @Test
    fun loadPosts(){
        onView(withId(R.id.rv_posts)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_posts)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
    }

    @Test
    fun loadDetailPosts(){
        onView(withId(R.id.rv_posts)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }


}