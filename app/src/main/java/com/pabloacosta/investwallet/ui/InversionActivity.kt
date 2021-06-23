package com.pabloacosta.investwallet.ui

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import com.pabloacosta.investwallet.Presenter.InversionPresenter
import com.pabloacosta.investwallet.`interface`.InversionInterface
import com.pabloacosta.investwallet.data.PagosUser
import com.pabloacosta.investwallet.data.Repository
import com.pabloacosta.investwallet.databinding.ActivityInversionBinding
import com.pabloacosta.investwallet.utils.Porcentaje
import dev.ahrsoft.modtoast.ModToast

class InversionActivity : AppCompatActivity(), InversionInterface.View {

    private lateinit var binding: ActivityInversionBinding
    private val repository = Repository()
    private lateinit var presenter : InversionPresenter
    private var isTypeInversio = 0
    private val porcentaje = Porcentaje()
    private var porcentajeGanancia = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInversionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intialView()
        presenter = InversionPresenter(this, this)
    }

    override fun onResume() {
        super.onResume()
        presenter.getInformationUser()
    }

    private fun intialView() {
        val data = repository.getList()
        val allNames = ArrayList<String>()
        data.forEach {
            allNames.add(it.name)
        }
        val dataAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, allNames)
        dataAdapter.setDropDownViewResource(R.layout.simple_spinner_item)

        with(binding){


            btnSaveInversion.setOnClickListener {
                presenter.setInversion()
            }

            spinnerServices.adapter = dataAdapter

            swTypeInversion.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked){
                    swTypeInversion.text = "Anual"
                    isTypeInversio = 1
                }else{
                    swTypeInversion.text = "Mensual"
                    isTypeInversio = 0
                }
            }

            editTextNumber.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    presenter.validateDateInversion()

                    val position = data[binding.spinnerServices.selectedItemPosition]

                    if (s!!.length >1){
                        porcentajeGanancia = porcentaje.generateGanancia(s.toString(), position.valor, isTypeInversio)
                        textTotalGanancia.text = "$porcentajeGanancia"
                    }else{
                        textTotalGanancia.text = ""
                    }

                }

                override fun afterTextChanged(s: Editable?) {

                }
            })

        }
    }

    override fun inversion(): String {
        return binding.editTextNumber.text.toString()
    }

    override fun selectInversion(): Int {
        return binding.spinnerServices.selectedItemPosition
    }

    override fun typeInversion(): Int {
        return isTypeInversio
    }

    @SuppressLint("SetTextI18n")
    override fun successInversion(money: Float, inversion : Float) {
        binding.tvMontoActual.text = "Saldo actual: $money MXN"
    }

    override fun listInversiones(listInversion: List<PagosUser>) {
        TODO("Not yet implemented")
    }

    override fun notMoney() {
        startActivity(Intent(this, PayActivity::class.java))
    }

    override fun invalidData() {
        ModToast("Datos invalidos",0)
    }

    override fun saveMoneyUser() : String {
        TODO("Not yet implemented")
    }

    override fun errorNoFoudInversion() {
        binding.editTextNumber.error = "No cuentas con fondos suficientes"
        binding.tvMontoActual.text = ""
        binding.btnSaveInversion.isEnabled = false
    }

    @SuppressLint("SetTextI18n")
    override fun restaTotalInverion(resta: Float) {
        binding.tvMontoActual.text = "Saldo actual: $resta MXN"
        binding.btnSaveInversion.isEnabled = true
    }

    override fun finishInversion() {
        finish()
    }
}