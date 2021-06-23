package com.pabloacosta.investwallet.Presenter

import android.content.Context
import com.pabloacosta.investwallet.Model.InversionModel
import com.pabloacosta.investwallet.`interface`.InversionInterface
import com.pabloacosta.investwallet.data.PagosUser

class InversionPresenter(private val view : InversionInterface.View, val context: Context) :
    InversionInterface.Model.MoneyUser, InversionInterface.Model.PayMoneyUser,
    InversionInterface.Model.NotMoneyUser,
    InversionInterface.Model.MoneyInversionUser,
    InversionInterface.Presenter  {

    private val model = InversionModel(context)

    override fun succesMoney(money: Float, inversion : Float) {
        view.successInversion(money, inversion)
    }

    override fun errorMoney() {
        view.notMoney()
    }

    override fun getInformationUser() {
        model.GetMoneyUser(this)
    }

    override fun setInversion() {

        val pagosUser = PagosUser(view.inversion().toFloat(), view.typeInversion())

        model.GetInversionMoneyUser(this, pagosUser)
    }

    override fun setMoneyUser() {
        if (view.saveMoneyUser().isEmpty()){
            view.invalidData()
        }else{
            model.SetPayMoneyUser(this, view.saveMoneyUser().toFloat())
        }
    }

    override fun validateDateInversion() {
        if (view.inversion().isEmpty()){
            view.invalidData()
        }else{
            model.getNotMoneyUser(this, view.inversion().toFloat())
        }
    }

    override fun successSPayMoney() {
        view.notMoney()
    }

    override fun moneyUser(resta: Float) {
        view.restaTotalInverion(resta)
    }

    override fun errorNotMoney() {
        view.errorNoFoudInversion()
    }

    override fun succesInversionMoney() {
        view.finishInversion()
    }
}