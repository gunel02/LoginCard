package com.example.logincard.activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.logincard.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        initListener()

    }

    private fun initListener() {
        binding?.loginButton?.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}