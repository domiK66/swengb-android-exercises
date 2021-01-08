package at.fh.swengb.loggingviewsandactivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("MyActivity", "onCreate")
        Log.d("MyActivity", "onCreate")
        Log.i("MyActivity", "onCreate")
        Log.w("MyActivity", "onCreate")
        Log.e("MyActivity", "onCreate")

        share_main.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }

        open_views.setOnClickListener {
            val intent = Intent(this, ViewsActivity::class.java)
            startActivity(intent)
        }

        open_rating.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }

        open_lessons.setOnClickListener {
            val intent = Intent(this, LessonListActivity::class.java)
            startActivity(intent)
        }

        open_settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString(SettingsActivity.usernamekey, null)
        username_main.text = savedUsername

        val isNightMode = sharedPreferences.getBoolean(SettingsActivity.darkmodekey, true)
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

    }

    override fun onStart() {
        super.onStart()
        Log.w("MyActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.w("MyActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.w("MyActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.w("MyActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("MyActivity", "onDestroy")
    }
}
