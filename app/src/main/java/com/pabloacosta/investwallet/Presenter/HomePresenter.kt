package com.pabloacosta.investwallet.Presenter

import android.content.Context
import com.pabloacosta.investwallet.Model.HomeModel
import com.pabloacosta.investwallet.`interface`.HomeInterface

class HomePresenter(val view : HomeInterface.View, val context: Context) :
    HomeInterface.Model.isLogin,
    HomeInterface.Model.Login,
    HomeInterface.Model.Register,
    HomeInterface.Presenter
{
    val model = HomeModel(context)

    override fun succesRegister() {
        view.registerUser()
    }

    override fun succesLogin() {
        view.isLogin()
    }

    override fun errorLogin() {
       view.errorLogin()
    }

    override fun succesLogin(isLogin: Boolean) {
        if (isLogin)
            view.isLogin()
    }

    override fun registerUser() {
        model.sharedRegister(this, view.email(), view.password())
    }

    override fun loginUser() {
        model.ServerLogin(this, view.email(), view.password())
    }

    override fun isLoginUser() {
        model.ServerisLogin(this)
    }
}