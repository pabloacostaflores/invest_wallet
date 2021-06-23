package com.pabloacosta.investwallet.Model

import android.content.Context
import com.pabloacosta.investwallet.`interface`.InversionInterface
import com.pabloacosta.investwallet.data.PagosUser
import com.pabloacosta.investwallet.data.SharedPrefClass

class InversionModel(val context: Context) : InversionInterface.Model {

    private val sharedPrefClass = SharedPrefClass(context)

    override fun GetMoneyUser(moneyUser: InversionInterface.Model.MoneyUser) {
        val money = sharedPrefClass.getMoneyUser()
        val inversion = sharedPrefClass.getMoneyInversion()
        if (money == 0.0f){
            moneyUser.errorMoney()
        }else{
            moneyUser.succesMoney(money, inversion)
        }
    }

    override fun GetInversionMoneyUser(
        moneyInversionUser: InversionInterface.Model.MoneyInversionUser,
        pagosUser: PagosUser
    ) {
        val saldo = sharedPrefClass.getMoneyUser() - pagosUser.monto

        val montoInversion = sharedPrefClass.getMoneyInversion() + pagosUser.monto

        sharedPrefClass.setMoneyUser(saldo)
        sharedPrefClass.setMoneyInversion(montoInversion)
        moneyInversionUser.succesInversionMoney()
    }

    override fun SetMoneyUser(
        setInformation: InversionInterface.Model.SetInformation,
        pagosUser: PagosUser
    ) {
        TODO("Not yet implemented")
    }

    override fun SetPayMoneyUser(
        payMoneyUser: InversionInterface.Model.PayMoneyUser,
        payMoney: Float
    ) {
        val money = sharedPrefClass.getMoneyUser()
        val moneyTotal = money + payMoney
        sharedPrefClass.setMoneyUser(moneyTotal)
        payMoneyUser.successSPayMoney()
    }

    override fun getNotMoneyUser(
        notMoneyUser: InversionInterface.Model.NotMoneyUser,
        totalInversion: Float
    ) {
        val data = sharedPrefClass.getMoneyUser()

        val total = data - totalInversion

        if (total < 0.0){
            notMoneyUser.errorNotMoney()
        }else{
            notMoneyUser.moneyUser(total)
        }

    }
}