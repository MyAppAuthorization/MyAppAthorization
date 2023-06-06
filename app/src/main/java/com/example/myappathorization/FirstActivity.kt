package com.example.myappathorization

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myappathorization.ui.home.HomeFragment

class FirstActivity : AppCompatActivity() {
    lateinit var buttonAuth: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        buttonAuth = findViewById(R.id.buttonAuth)

        buttonAuth.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                homeIntent()
            }
        })
    }
    fun homeIntent() {
        var homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }
}