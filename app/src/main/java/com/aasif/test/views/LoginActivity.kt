package com.aasif.test.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aasif.test.MyApplication
import com.aasif.test.databinding.ActivityLoginBinding
import com.aasif.test.data.Login
import com.aasif.test.viewmodels.MainViewModel
import com.aasif.test.viewmodels.MainViewModelFactory


class LoginActivity : AppCompatActivity() {

    private lateinit var bind: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val viewModel = ViewModelProvider(
            this,
            MainViewModelFactory((application as MyApplication).repository)
        )[MainViewModel::class.java]
//        val viewModel: MainViewModel by viewModels { MainViewModelFactory(repository) }


        if (viewModel.isLoggedIn()) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }



        bind.login.setOnClickListener {
            when {
                bind.emailEdit.text!!.isEmpty() -> bind.emailEdit.error = "Please Enter Email"
                bind.passwordEdit.text!!.isEmpty() -> bind.passwordEdit.error =
                    "Please Enter Password"

                else -> {
                    viewModel.login(
                        Login(
                            bind.emailEdit.text.toString(),
                            bind.passwordEdit.text.toString(),
                            "0",
                            "TOKEN122133"
                        ), {
                            startActivity(Intent(this, HomeActivity::class.java))
                            finish()
                        }, {
                            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                        })
                }
            }
        }



        bind.createAccount.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

    }


}