package com.pabloacosta.investwallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class StockFragment : Fragment() {
    /*
    TODO:
    monto taza y plazo para ir haciendo el calculo de lo que vaya a ganar
    el valor final queda en una base de datos
    */
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_stock, container, false)
        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
    }
}