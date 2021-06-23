package com.pabloacosta.investwallet.`interface`

interface HomeInterface {

    interface Model{
        interface Register{
            fun succesRegister()
        }
        fun sharedRegister(register: Register, email: String, pass: String)

        interface Login{
            fun succesLogin()
            fun errorLogin()
        }
        fun ServerLogin(login: Login, email: String, pass: String)


        interface isLogin{
            fun succesLogin(isLogin : Boolean)
        }
        fun ServerisLogin(isLogin: isLogin)
    }


    interface View{
        fun email(): String
        fun password(): String
        fun isLogin()
        fun errorLogin()
        fun registerUser()
    }


    interface Presenter{
        fun registerUser()
        fun loginUser()
        fun isLoginUser()
    }



}