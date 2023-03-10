package com.christianfleschhut.solardata

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.christianfleschhut.solardata.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, MainActivity::class.java)

        viewModel.userInfo.observe(this) { storedEmail ->
            if (storedEmail != null && storedEmail.isNotEmpty()) startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val inputEmail = binding.etEmail
            val inputPassword = binding.etPassword

            if (inputEmail.text!!.isNotEmpty() && inputPassword.text!!.isNotEmpty()) {
                viewModel.storeUserInfo(inputEmail.text.toString())
                startActivity(intent)
            } else {
                Toast
                    .makeText(
                        this,
                        getString(R.string.login_form_error_msg_missing_credentials),
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }
    }
}