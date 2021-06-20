package com.pabloacosta.investwallet.Model

import android.content.Context
import com.pabloacosta.investwallet.`interface`.HomeInterface
import com.pabloacosta.investwallet.data.SharedPrefClass
import kotlin.math.log

class HomeModel(val context: Context) : HomeInterface.Model {

    private val sharedPrefClass = SharedPrefClass(context)

    override fun sharedRegister(
        register: HomeInterface.Model.Register,
        email: String,
        pass: String
    ) {
        sharedPrefClass.setEmail(email)
        sharedPrefClass.setPassword(pass)
        register.succesRegister()
    }

    override fun ServerLogin(login: HomeInterface.Model.Login, email: String, pass: String) {
        val email1 = sharedPrefClass.getEmail().toString()
        val pass1 = sharedPrefClass.getPassword().toString()

        if (email1 == email && pass1 == pass){
            sharedPrefClass.setIsLogin(true)
            login.succesLogin()
        }else{
            login.errorLogin()
        }

    }

    override fun ServerisLogin(isLogin: HomeInterface.Model.isLogin) {
        val Login = sharedPrefClass.isLogin()
        isLogin.succesLogin(Login)
    }
}