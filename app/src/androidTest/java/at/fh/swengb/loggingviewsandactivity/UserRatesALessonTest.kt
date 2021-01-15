package at.fh.swengb.loggingviewsandactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserRatesALessonTest {
    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }
    @After
    fun tearDown() {
        Intents.release()
    }
    @Test
    fun UserRatesALesson() {
        onView(withId(R.id.open_lessons)).perform(ViewActions.click())
        intended(hasComponent(LessonListActivity::class.java.name))

        Thread.sleep(2000)
        onView(withId(R.id.lesson_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<LessonViewHolder>(
                    ViewMatchers.hasDescendant(
                        withText("Lecture 0")
                    ), ViewActions.click()
                )
            )
        intended(hasComponent(LessonRatingActivity::class.java.name))
        Thread.sleep(2000)
        onView(withId(R.id.lesson_rating_header)).check(ViewAssertions.matches(withText("Lecture 0")))
        onView(withId(R.id.rate_lesson)).perform(ViewActions.click())
        intended(hasComponent(LessonListActivity::class.java.name))
    }
}
