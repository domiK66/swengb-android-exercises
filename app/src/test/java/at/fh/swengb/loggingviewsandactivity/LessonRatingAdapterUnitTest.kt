package at.fh.swengb.loggingviewsandactivity


import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LessonRatingAdapterUnitTest {
    val lessons = (1..15).map {
        Lesson(
            "Lesson ${it}",
            "Testing",
            "25.11.2020",
            "Unit Testing",
            LessonType.PRACTICAL,
            listOf(Lecturer("Tester")),
            mutableListOf(),
            ""
        )
    }

    @Test
    fun itemCount_isCorrect() {
        /*
        * 1. test the itemCount() for an empty list
        * 2. test the itemCount() for a non-empty list
        */
        val adapter = LessonAdapter({print(it)})
        assertEquals(0,adapter.itemCount)
        adapter.lessonList = lessons
        assertEquals(15,adapter.itemCount)
    }
    @Test
    fun binding_isCorrect() {
        val adapter = LessonAdapter({print(it)})
        adapter.lessonList = lessons
        val mockHolder = Mockito.mock(LessonViewHolder::class.java)
        adapter.onBindViewHolder(mockHolder,9)
        verify(mockHolder,times(1)).bindItem(lessons[9])
    }
}

