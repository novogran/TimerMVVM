package com.example.timermvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as MyApplication).viewModel
        val textView = findViewById<TextView>(R.id.textView)
        val observable = TextObservable()
        observable.observe(object : TextCallback{
            override fun updateText(text: String) = runOnUiThread {
                textView.text = text
            }
        })
        viewModel.init(observable)
    }

    override fun onResume() {
        super.onResume()
        viewModel.resumeCounting()
    }

    override fun onPause() {
        super.onPause()
        viewModel.pauseCounting()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }
}