package com.pabloacosta.investwallet

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatViewInflater
import com.facebook.login.LoginManager
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType{
    BASIC,
    GOOGLE,
    FACEBOOK,
    TWITTER
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email?: "", provider?: "")

        //For saving the state of the APP
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

        bnvMenu.setOnNavigationItemSelectedListener (mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            val fragment = StockFragment()
            supportFragmentManager.beginTransaction().replace(R.id.flFragment, fragment, fragment.javaClass.getSimpleName())
                .commit()
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.StockFragment -> {
                val fragment = StockFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.CryptoFragment -> {
                val fragment = CryptoFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setup(email: String, provider: String){
        title = "Inicio"
        emailTextView.text = email
        providerTextView.text = provider

        logOutButton.setOnClickListener {
            //Cleaning data
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            if(provider == ProviderType.FACEBOOK.name){
                LoginManager.getInstance().logOut()
            }

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}