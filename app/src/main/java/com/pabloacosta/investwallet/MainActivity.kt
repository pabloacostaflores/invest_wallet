package com.pabloacosta.investwallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            val intent:Intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }*/
        setup()
    }
    private fun setup(){
        title = "Autentication"

        registerButton.setOnClickListener {
            if (editTextTextEmail.text.isNotEmpty() && editTextTextPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(editTextTextEmail.text.toString(),
                    editTextTextPassword.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                            showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        }else{
                            showAlert()
                        }
                }
            }
        }
        login_button.setOnClickListener {
            if (editTextTextEmail.text.isNotEmpty() && editTextTextPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(editTextTextEmail.text.toString(),
                    editTextTextPassword.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    }else{
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al autenticar el usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}