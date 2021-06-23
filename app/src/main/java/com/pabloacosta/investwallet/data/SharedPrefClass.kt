package com.pabloacosta.investwallet.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences




class SharedPrefClass (context: Context) {

    private var mySharedPref = context.getSharedPreferences("DATA_WALLET", Context.MODE_PRIVATE)
    private var editShared = mySharedPref.edit()

    fun getMoneyUser() : Float{
        return mySharedPref.getFloat("money_deposit", 0.0f)
    }

    fun setMoneyUser(money : Float){
        editShared.putFloat("money_deposit", money)
        editShared.commit()
    }


    fun getMoneyInversion() : Float{
        return mySharedPref.getFloat("money_inversion", 0.0f)
    }

    fun setMoneyInversion(money: Float){
        editShared.putFloat("money_inversion", money)
        editShared.commit()
    }


    fun getPassword() : String? {
        return mySharedPref.getString("password","")
    }

    fun setPassword(pass : String){
        editShared.putString("password", pass)
        editShared.commit()
    }

    fun getEmail() : String? {
        return mySharedPref.getString("email","")
    }

    fun setEmail(email : String){
        editShared.putString("email", email)
        editShared.commit()
    }

    fun isLogin() : Boolean{
        return  mySharedPref.getBoolean("isLogin", false)
    }

    fun setIsLogin(isLogin : Boolean){
        editShared.putBoolean("isLogin", isLogin)
        editShared.commit()
    }
}