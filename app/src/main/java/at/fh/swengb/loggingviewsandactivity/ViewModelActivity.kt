package at.fh.swengb.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity() {
    private val viewModel: ViewModelActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        viewModel.getTextLiveData().observe(this) {
            livedata_text.text = it
        }
        livedata_button.setOnClickListener {
            viewModel.addText(livedata_text_input.text.toString())
        }

    }
}