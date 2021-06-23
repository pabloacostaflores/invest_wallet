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
import com.pabloacosta.investwallet.databinding.FragmentCryptoBinding
import com.pabloacosta.investwallet.databinding.FragmentStockBinding


class CryptoFragment : Fragment() {

    private var _binding : FragmentCryptoBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance(): CryptoFragment = CryptoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCryptoBinding.inflate(inflater, container, false)
        initialView()
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initialView() {
        with(binding){
            webViewCrypto.settings.javaScriptEnabled = true
            webViewCrypto.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (url != null) {
                        view?.loadUrl(url)
                    }
                    return true
                }
            }
            webViewCrypto.loadUrl("https://coinmarketcap.com/es/")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
