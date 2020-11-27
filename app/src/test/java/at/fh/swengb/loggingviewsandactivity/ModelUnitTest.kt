package at.fh.swengb.loggingviewsandactivity

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

class ModelUnitTest {
    @Test
    fun averageForEmptyRates_isCorrect() {
        // test whether the average is 0.0 when ratings are empty
        val lesson = Lesson(
            "4711",
            "Testing",
            "25.11.2020",
            "Unit Testing",
            LessonType.PRACTICAL,
            listOf(Lecturer("Tester")),
            mutableListOf(),
            ""
        )
        val rating: Double = lesson.ratingAverage()

        assertThat(rating).isWithin(1.0e-10).of(0.0)
        assertEquals(rating, 0.0 , 1.0e-10)
    }
    @Test
    fun averageForNonEmptyRates_isCorrect() {
        // test whether the average function is working right
        val ratings = (1..10).map{LessonRating(it.toDouble(),"rating ${it}")}

        val lesson = Lesson(
            "4711",
            "Testing",
            "25.11.2020",
            "Unit Testing",
            LessonType.PRACTICAL,
            listOf(Lecturer("Tester")),
            ratings.toMutableList(),
            ""
        )
        val rating: Double = lesson.ratingAverage()
        val correctRating: Double = ratings.map{it.ratingValue}.average()
        assertThat(rating).isWithin(1.0e-10).of(correctRating)
        assertEquals(correctRating, rating, 1.0e-10)
    }
}