package com.pabloacosta.investwallet.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pabloacosta.investwallet.Presenter.InversionPresenter
import com.pabloacosta.investwallet.`interface`.InversionInterface
import com.pabloacosta.investwallet.adapter.AdapterInversiones
import com.pabloacosta.investwallet.data.PagosUser
import com.pabloacosta.investwallet.data.Repository
import com.pabloacosta.investwallet.data.SharedPrefClass
import com.pabloacosta.investwallet.databinding.FragmentDashBinding
import com.pabloacosta.investwallet.ui.InversionActivity

class DashFragment : Fragment(), InversionInterface.View {

    private var _binding : FragmentDashBinding? = null
    private val binding get() = _binding!!

    private val repository = Repository()
    private lateinit var adapter : AdapterInversiones
    private lateinit var presenter: InversionPresenter
    private lateinit var sharedPrefClass : SharedPrefClass


    companion object {
        fun newInstance(): DashFragment = DashFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashBinding.inflate(inflater, container, false)
        initialView()
        return binding.root
    }
    @SuppressLint("SetTextI18n")
    private fun initialView() {
        sharedPrefClass = SharedPrefClass(requireContext())

        presenter = InversionPresenter(this,requireContext())

        val data = repository.getList()
        adapter = AdapterInversiones(data)
        with(binding){
            fabAddInversion.setOnClickListener {
                startActivity(Intent(requireContext(), InversionActivity::class.java))
            }
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.addItemDecoration(
                DividerItemDecoration(requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        requireActivity().finish()
    }

}