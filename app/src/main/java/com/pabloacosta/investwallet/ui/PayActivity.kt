package com.pabloacosta.investwallet.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.braintreepayments.cardform.view.CardForm
import com.pabloacosta.investwallet.Presenter.InversionPresenter
import com.pabloacosta.investwallet.`interface`.InversionInterface
import com.pabloacosta.investwallet.data.PagosUser
import com.pabloacosta.investwallet.databinding.ActivityPayBinding
import dev.ahrsoft.modtoast.ModToast


class PayActivity : AppCompatActivity(), InversionInterface.View {

    private lateinit var binding: ActivityPayBinding
    private lateinit var presenter : InversionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        presenter = InversionPresenter(this, this)
        binding.cardForm.cardRequired(true)
            .expirationRequired(true)
            .cvvRequired(true)
            .cardholderName(CardForm.FIELD_REQUIRED)
            .actionLabel("Purchase")
            .setup(this)


        with(binding){
            btnBuy.setOnClickListener {
                presenter.setMoneyUser()
            }
        }
    }

    override fun inversion(): String {
        TODO("Not yet implemented")
    }

    override fun selectInversion(): Int {
        TODO("Not yet implemented")
    }

    override fun typeInversion(): Int {
        TODO("Not yet implemented")
    }

    override fun successInversion(money: Float, inversion : Float) {
        TODO("Not yet implemented")
    }

    override fun listInversiones(listInversion: List<PagosUser>) {
        TODO("Not yet implemented")
    }

    override fun notMoney() {
        finish()
    }

    override fun invalidData() {
        ModToast("Ingresa un monto valido",0)
    }

    override fun saveMoneyUser(): String {
        return binding.editPago.text.toString().trim()
    }

    override fun errorNoFoudInversion() {
        TODO("Not yet implemented")
    }

    override fun restaTotalInverion(resta: Float) {
        TODO("Not yet implemented")
    }

    override fun finishInversion() {
        TODO("Not yet implemented")
    }
}