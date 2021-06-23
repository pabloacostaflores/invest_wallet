package com.pabloacosta.investwallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pabloacosta.investwallet.databinding.ActivityHomeBinding
import com.pabloacosta.investwallet.ui.fragments.CryptoFragment
import com.pabloacosta.investwallet.ui.fragments.DashFragment
import com.pabloacosta.investwallet.ui.fragments.StockFragment


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        with(binding){
            bnvMenu.setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.navHomeFragment -> {
                        val fragment = DashFragment.newInstance()
                        openFragment(fragment)
                        true
                    }
                    R.id.navStockFragment -> {
                        val fragment = StockFragment.newInstance()
                        openFragment(fragment)
                        true
                    }
                    R.id.navCryptoFragment -> {
                        val fragment = CryptoFragment.newInstance()
                        openFragment(fragment)
                        true
                    }
                    else -> false
                }
            }
            bnvMenu.selectedItemId = R.id.navHomeFragment
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}