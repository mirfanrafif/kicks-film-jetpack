package com.mirfanrafif.kicksfilm.ui.splashscreen

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mirfanrafif.kicksfilm.R
import com.mirfanrafif.kicksfilm.data.FilmData
import com.mirfanrafif.kicksfilm.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_tahun)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_tahun)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavorites() {
        onView(withId(R.id.favoriteMenu)).perform(click())
        onView(withId(R.id.tv_favorite_movies_label)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_favorite_tv_label)).check(matches(isDisplayed()))
    }

    @Test
    fun setMovieAsFavorite() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_tahun)).check(matches(isDisplayed()))
        onView(withId(R.id.favoriteFab)).perform(click())
    }

    @Test
    fun setTvShowAsFavorite() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_tahun)).check(matches(isDisplayed()))
        onView(withId(R.id.favoriteFab)).perform(click())
    }


}