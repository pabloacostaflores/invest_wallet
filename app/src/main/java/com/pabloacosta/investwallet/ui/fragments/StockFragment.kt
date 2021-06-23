package com.pabloacosta.investwallet.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.pabloacosta.investwallet.R
import com.pabloacosta.investwallet.databinding.FragmentDashBinding
import com.pabloacosta.investwallet.databinding.FragmentStockBinding

class StockFragment : Fragment() {

    private var _binding : FragmentStockBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(): StockFragment = StockFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStockBinding.inflate(inflater, container, false)
        initialView()
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initialView() {
        with(binding){
            webViewStock.settings.javaScriptEnabled = true
            webViewStock.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (url != null) {
                        view?.loadUrl(url)
                    }
                    return true
                }
            }
            webViewStock.loadUrl("https://money.cnn.com/data/markets")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}