package com.pabloacosta.investwallet.`interface`

import com.pabloacosta.investwallet.data.PagosUser

interface InversionInterface {

    interface Model{
        interface MoneyUser{
            fun succesMoney(money : Float, inversion: Float)
            fun errorMoney()
        }
        fun GetMoneyUser(moneyUser: MoneyUser)


        interface MoneyInversionUser{
            fun succesInversionMoney()
        }
        fun GetInversionMoneyUser(moneyInversionUser: MoneyInversionUser, pagosUser: PagosUser)



        interface SetInformation{
            fun succesSetMoney(money : Float)
            fun errorMoney()
        }
        fun SetMoneyUser(setInformation: SetInformation, pagosUser: PagosUser)

        interface PayMoneyUser{
            fun successSPayMoney()
        }
        fun SetPayMoneyUser(payMoneyUser: PayMoneyUser, payMoney: Float)

        interface NotMoneyUser{
            fun moneyUser(resta: Float)
            fun errorNotMoney()
        }
        fun getNotMoneyUser(notMoneyUser: NotMoneyUser, totalInversion : Float)
    }

    interface View{
        fun inversion() : String
        fun selectInversion(): Int
        fun typeInversion(): Int
        fun successInversion(money: Float, inversion: Float)
        fun listInversiones(listInversion : List<PagosUser>)
        fun notMoney()
        fun invalidData()
        fun saveMoneyUser() : String
        fun errorNoFoudInversion()
        fun restaTotalInverion(resta : Float)
        fun finishInversion()
    }

    interface Presenter{
        fun getInformationUser()
        fun setInversion()
        fun setMoneyUser()
        fun validateDateInversion()
    }

}