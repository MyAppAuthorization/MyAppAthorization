package com.example.myappathorization

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myappathorization.ui.home.HomeFragment
import com.jakewharton.rxbinding2.widget.RxTextView

class FirstActivity : AppCompatActivity() {
    lateinit var textEmailAddress: TextView
    lateinit var textPassword:TextView
    lateinit var buttonAuth: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        textEmailAddress = findViewById(R.id.textEmailAddress)
        textPassword = findViewById(R.id.textPassword)
        buttonAuth = findViewById(R.id.buttonAuth)

        RxTextView.afterTextChangeEvents(textEmailAddress)
            .subscribe({

                if (textEmailAddress.length() > 0) {
                    textPassword.isEnabled = true
                }
                else{
                    textPassword.isEnabled = false
                }
            })
        RxTextView.afterTextChangeEvents(textPassword)
            .subscribe({

                if ((textEmailAddress.length() > 0) && (textPassword.length() > 0)) {
                    buttonAuth.isEnabled = true
                }
                else {
                    buttonAuth.isEnabled = false
                }
            })
        buttonAuth.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                homeIntent()
            }
        })
    }
    fun homeIntent() {
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }
}