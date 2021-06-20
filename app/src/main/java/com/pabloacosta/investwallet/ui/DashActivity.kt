package com.pabloacosta.investwallet.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pabloacosta.investwallet.Presenter.InversionPresenter
import com.pabloacosta.investwallet.R
import com.pabloacosta.investwallet.`interface`.InversionInterface
import com.pabloacosta.investwallet.adapter.AdapterInversiones
import com.pabloacosta.investwallet.data.PagosUser
import com.pabloacosta.investwallet.data.Repository
import com.pabloacosta.investwallet.data.SharedPrefClass
import com.pabloacosta.investwallet.databinding.ActivityDashBinding

class DashActivity : AppCompatActivity(), InversionInterface.View{

    private lateinit var binding: ActivityDashBinding
    private val repository = Repository()
    private lateinit var adapter : AdapterInversiones
    private lateinit var presenter: InversionPresenter
    private lateinit var sharedPrefClass : SharedPrefClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialView()
    }

    @SuppressLint("SetTextI18n")
    private fun initialView() {

        sharedPrefClass = SharedPrefClass(this)

        presenter = InversionPresenter(this,this)

        val data = repository.getList()
        adapter = AdapterInversiones(data)
        with(binding){
            fabAddInversion.setOnClickListener {
                startActivity(Intent(this@DashActivity, InversionActivity::class.java))
            }
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(this@DashActivity)
            recyclerView.addItemDecoration(
                DividerItemDecoration(this@DashActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getInformationUser()
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

    @SuppressLint("SetTextI18n")
    override fun successInversion(money: Float, inversion: Float) {
        binding.tvTotalInversion.text = "$ $money MXN"
        binding.textSaldo.text = "$ $inversion MXN"

    }

    override fun listInversiones(listInversion: List<PagosUser>) {
        TODO("Not yet implemented")
    }

    @SuppressLint("SetTextI18n")
    override fun notMoney() {
        binding.tvTotalInversion.text = "Sin saldo"
        binding.textSaldo.text = "Sin inversion"
    }

    override fun invalidData() {
        TODO("Not yet implemented")
    }

    override fun saveMoneyUser() : String {
        TODO("Not yet implemented")
    }

    override fun errorNoFoudInversion() {
        TODO("Not yet implemented")
    }

    override fun restaTotalInverion(resta: Float) {
        TODO("Not yet implemented")
    }

    override fun finishInversion() {
        finish()
    }
}