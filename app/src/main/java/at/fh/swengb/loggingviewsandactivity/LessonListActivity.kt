package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_lesson_list.*
import kotlinx.android.synthetic.main.activity_rating.*

class LessonListActivity : AppCompatActivity() {
    companion object {
        val EXTRA_LESSON_ID = "LESSON_ID_EXTRA"
        val ADD_OR_EDIT_RATING_REQUEST = 1
        val EXTRA_LESSON_NAME = "LESSON_NAME_EXTRA"
    }
    val lessonAdapter = LessonAdapter() {
        Toast.makeText(this, "Lesson with name: ${it.name} has been clicked", Toast.LENGTH_LONG).show()
        val intent = Intent(this, LessonRatingActivity::class.java)
        intent.putExtra(EXTRA_LESSON_ID, it.id)
        startActivityForResult(intent, ADD_OR_EDIT_RATING_REQUEST)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)
        LessonRepository.lessonsList(
            success = {
                lessonAdapter.updateList(it)

            },
            error = {
                Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            }
        )
        lesson_recycler_view.layoutManager = LinearLayoutManager(this)
        lesson_recycler_view.adapter = lessonAdapter
        // parseJson()
        SleepyAsyncTask().execute()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_OR_EDIT_RATING_REQUEST && resultCode == RESULT_OK){
            LessonRepository.lessonsList(
                    success = {
                        lessonAdapter.updateList(it)
                    },
                    error = {
                        Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
                    }
            )
        }
    }

    fun parseJson(){
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<Lesson>(Lesson::class.java)
        val lesson = jsonAdapter.fromJson("""
            {
                "id": "1",
                "name": "Lecture 0",
                "date": "09.10.2019",
                "topic": "Introduction",
                "type": "LECTURE",
                "lecturers": [
                    {
                        "name": "Lukas Bloder"
                    },
                    {
                        "name": "Sanja Illes"
                    }
                ],
                "ratings": []
            }
        """.trimIndent()
        )
        Log.i("parseJson()", lesson?.name.toString())
    }
}