package com.christianfleschhut.solardata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.christianfleschhut.solardata.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val inputEmail = binding.etEmail
            val inputPassword = binding.etPassword

            if (inputEmail.text!!.isNotEmpty() && inputPassword.text!!.isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter login data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}