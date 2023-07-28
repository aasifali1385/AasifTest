package com.aasif.test.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.aasif.test.MyApplication
import com.aasif.test.databinding.ActivitySignupBinding
import com.aasif.test.data.Signup
import com.aasif.test.viewmodels.MainViewModel
import com.aasif.test.viewmodels.MainViewModelFactory

class SignupActivity : AppCompatActivity() {

    private lateinit var bind: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(bind.root)


        val repository = (application as MyApplication).repository
        val viewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]


        bind.register.setOnClickListener {
            when {
                bind.nameEdit.text!!.isEmpty() -> bind.nameEdit.error = "Please Enter Name"
                bind.phoneEdit.text!!.isEmpty() -> bind.phoneEdit.error = "Please Enter Phone"
                bind.emailEdit.text!!.isEmpty() -> bind.emailEdit.error = "Please Enter Email"
                bind.passwordEdit.text!!.isEmpty() -> bind.passwordEdit.error =
                    "Please Enter Password"

                bind.conPasswordEdit.text!!.isEmpty() -> bind.conPasswordEdit.error =
                    "Please Enter Confirm Password"

                bind.passwordEdit.text.toString() != bind.conPasswordEdit.text.toString() -> Toast.makeText(
                    this,
                    "Password and Confirm Password are not Matched",
                    Toast.LENGTH_SHORT
                ).show()

                else -> {
                    viewModel.register(
                        Signup(
                            bind.nameEdit.text.toString(),
                            bind.emailEdit.text.toString(),
                            bind.phoneEdit.text.toString(),
                            bind.passwordEdit.text.toString(),
                            "0",
                            "TOKEN122133"
                        ), {
                            startActivity(Intent(this, HomeActivity::class.java))
                            finish()
                        }, {
                            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }


        bind.loginAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}