package com.pabloacosta.investwallet

//import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*This part redirects to the "Register Activity", but now it is not going to be used. I will
          leave this in case of necessary.*/
        /*
        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            val intent:Intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }*/
        setup()
        session()
    }

    override fun onStart() {
        super.onStart()

        authLayout.visibility = View.VISIBLE
    }


    private fun session(){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if(email != null && provider != null){
            authLayout.visibility = View.INVISIBLE
            showHome(email, ProviderType.valueOf(provider))
        }
    }

    private fun setup(){
        title = "Autentication"

        //When pressing the registerButton
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
        //When pressing the login_button
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
        //For GOOGLE AUTHETICATION
        googleLoginButton.setOnClickListener {
            //Configuration
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }
    }
    //Show alert in case of error
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al autenticar el usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Go to Home activity
    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)

                if(account != null){

                    val credential = GoogleAuthProvider.getCredential(account.idToken,null)

                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if (it.isSuccessful){
                            showHome(account.email?:"", ProviderType.GOOGLE)
                        }else{
                            showAlert()
                        }
                    }
                }
            } catch (e: ApiException){
                showAlert()
            }
        }
    }
}