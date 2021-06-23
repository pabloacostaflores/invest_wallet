package com.pabloacosta.investwallet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pabloacosta.investwallet.Presenter.HomePresenter
import com.pabloacosta.investwallet.R
import com.pabloacosta.investwallet.`interface`.HomeInterface
import com.pabloacosta.investwallet.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), HomeInterface.View {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var presenter : HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = HomePresenter(this, this)

        binding.registerButtonRegisterActivity.setOnClickListener {
            presenter.registerUser()
        }
    }

    override fun email(): String {
        return binding.editTextTextEmailRegisterActivity.text.toString().trim()
    }

    override fun password(): String {
        return binding.editTextTextPasswordRegisterActivity.text.toString().trim()
    }

    override fun isLogin() {
        TODO("Not yet implemented")
    }

    override fun errorLogin() {
        TODO("Not yet implemented")
    }

    override fun registerUser() {
        finish()
    }
}