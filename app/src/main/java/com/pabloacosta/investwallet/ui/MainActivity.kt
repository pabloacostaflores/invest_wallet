package com.pabloacosta.investwallet.ui

//import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.pabloacosta.investwallet.HomeActivity
import com.pabloacosta.investwallet.Presenter.HomePresenter
import com.pabloacosta.investwallet.`interface`.HomeInterface
import com.pabloacosta.investwallet.databinding.ActivityMainBinding
import dev.ahrsoft.modtoast.ModToast
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), HomeInterface.View {
/*
    private val GOOGLE_SIGN_IN = 100
    private val callbackManager = CallbackManager.Factory.create()*/

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter : HomePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        /*This part redirects to the "Register Activity", but now it is not going to be used. I will
          leave this in case of necessary.*/
        /*
        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            val intent:Intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }*/


    }

    private fun initView() {
        presenter = HomePresenter(this, this)
        presenter.isLoginUser()

        with(binding){
            registerButton.setOnClickListener {
                startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
            }
            login_button.setOnClickListener {
                presenter.loginUser()
            }
        }
    }

    override fun email(): String {
        return binding.editTextTextEmail.text.toString().trim()
    }

    override fun password(): String {
       return binding.editTextTextPassword.text.toString().trim()
    }

    override fun isLogin() {
        finish()
        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun errorLogin() {
        ModToast("Usuario y contraseña incorrectos",0)
    }

    override fun registerUser() {
        TODO("Not yet implemented")
    }
/*
    override fun onStart() {
        super.onStart()

        authLayout.visibility = View.VISIBLE
    }

    //This is for saving the state also
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
        //For GOOGLE AUTH
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
        //For FACEBOOK AUTH
        facebookLoginButton.setOnClickListener{

            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))

            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult>{
                    override fun onSuccess(result: LoginResult?) {
                        result?.let {
                            val token = it.accessToken

                            val credential = FacebookAuthProvider.getCredential(token.token)

                            FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                                if (it.isSuccessful){
                                    showHome(it.result?.user?.email ?:"", ProviderType.FACEBOOK)
                                }else{
                                    showAlert()
                                }
                            }
                        }
                    }

                    override fun onCancel() {

                    }

                    override fun onError(error: FacebookException?) {
                        showAlert()
                    }
            })
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

        callbackManager.onActivityResult(requestCode, resultCode, data)

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
    }*/
}