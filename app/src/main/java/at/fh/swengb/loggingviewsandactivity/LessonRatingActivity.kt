package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lesson_rating.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_rating.*

class LessonRatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)
        val lessonID = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)

        loadLessonDetails(lessonID)
        /*
        if (lessonID != null){
        lesson_rating_header.text = LessonRepository.lessonById(lessonID)?.name ?: "noname"
        }
        */
        rate_lesson.setOnClickListener{
            val ratingValue = lesson_rating_bar.rating.toDouble()
            val ratingFeedback = lesson_feedback.text.toString()
            val rating = LessonRating (
                    ratingValue,
                    ratingFeedback
            )
            LessonRepository.rateLesson(lessonID ?: "", rating,
                success= {
                    val resultIntent = Intent()
                    setResult(Activity.RESULT_OK, resultIntent)
                    finish()
                },
                error = {
                    Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
                }
            )



        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }
    fun sharing(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,lesson_rating_header.text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.share -> consume{sharing()}
            R.id.xyz -> consume{sharing()}

            else -> super.onOptionsItemSelected(item)
        }
    }







    fun loadLessonDetails(lessonID: String?){
        if (lessonID == null) {
            lesson_rating_header.text = "nonamefound"
            return
        }
        LessonRepository.lessonById(lessonID,
            success= {
                lesson_rating_header.text = it.name

            },
            error = {
                Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            }
        )
    }
}