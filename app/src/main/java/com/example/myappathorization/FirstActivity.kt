@file:Suppress("DEPRECATION")

package com.example.myappathorization

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myappathorization.databinding.ActivityFirstBinding
import com.example.myappathorization.ui.home.HomeFragment
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    private lateinit var mainApi: MainApi

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RxTextView.afterTextChangeEvents(binding.textEmailAddress)
            .subscribe({

                if (binding.textEmailAddress.length() > 0) {
                    binding.textPassword.isEnabled = true
                }
                else{
                    binding.textPassword.isEnabled = false
                }
            })
        RxTextView.afterTextChangeEvents(binding.textPassword)
            .subscribe({

                if ((binding.textEmailAddress.length() > 0) && (binding.textPassword.length() > 0)) {
                    binding.buttonAuth.isEnabled = true
                }
                else {
                    binding.buttonAuth.isEnabled = false
                }
            })
        initRetrofit()
        binding.apply {
            buttonAuth.setOnClickListener{
                //viewModel.token.value = ""

                val authDone = auth(
                    AuthRequest(
                        textEmailAddress.text.toString(),
                        textPassword.text.toString()
                    ))
            }
        }
    }
    private fun initRetrofit(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://w-ba.ru").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        mainApi = retrofit.create(MainApi::class.java)
    }

    private fun auth(authRequest: AuthRequest){
        CoroutineScope(Dispatchers.IO).launch {
            val user = mainApi.auth(authRequest)
            //homeIntent()

            if (!user.token.equals( "\"Error Login or password\"")){
                homeIntent()
            } else {
                withContext(Dispatchers.Main) {
                    val toast = Toast.makeText(this@FirstActivity,"Не верный логин или пароль", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                    toast.show()
                    binding.textEmailAddress.text.clear()
                    binding.textPassword.text.clear()
                }
            }

        }
    }
    fun homeIntent() {
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }
}