package at.fh.swengb.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import at.fh.swengb.loggingviewsandactivity.LessonListActivity.Companion.EXTRA_LESSON_ID
import at.fh.swengb.loggingviewsandactivity.LessonListActivity.Companion.EXTRA_LESSON_NAME
import kotlinx.android.synthetic.main.activity_lesson_note.*


class  LessonNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_note)
        val lessonID = intent.getStringExtra(EXTRA_LESSON_ID)
        val lessonName = intent.getStringExtra(EXTRA_LESSON_NAME)

        textView_lesson.text = lessonName.toString()
        val newnote =  LessonRepository.findSameID(applicationContext, lessonID.toString())
        editText_multiline.setText(newnote?.text)

        node_button.setOnClickListener() {
            val note = LessonNote(lessonID.toString(), lessonName.toString(),editText_multiline.text.toString())
            LessonRepository.addLessonNote(applicationContext,note)
            val newnote =  LessonRepository.findSameID(applicationContext, lessonID.toString())
            editText_multiline.setText(newnote?.text)
            finish()
        }
    }
}