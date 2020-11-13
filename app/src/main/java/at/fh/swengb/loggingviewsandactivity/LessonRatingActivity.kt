package at.fh.swengb.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lesson_rating.*

class LessonRatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)
        val lessonID = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)
        if (lessonID != null){
            lesson_rating_header.text = LessonRepository.lessonById(lessonID)?.name ?: "noname"
        }
    }
}