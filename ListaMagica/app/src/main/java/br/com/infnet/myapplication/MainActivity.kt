package br.com.infnet.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import br.com.infnet.myapplication.databinding.ActivityMainBinding
import br.com.infnet.myapplication.models.UserInfo
import br.com.infnet.myapplication.repository.ReceitaRepository
import br.com.infnet.myapplication.viewmodel.MainViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: ReceitaRepository

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        MobileAds.initialize(this){}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        setup()
    }

    private fun setup() {
        repository = ReceitaRepository.get()
        setupViews()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnLogout.setOnClickListener {
            repository.logout()
            startLoginActivity()
        }
    }

    private fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun setupViews() {
        setTitle("Principal")
        var userId = FirebaseAuth.getInstance().currentUser!!.uid
        val documentReference = repository.getUserInfo(userId)
        documentReference
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.tvWelcome.text = "Olá ${document.get("usuarioNome")}"
                    Log.d(br.com.infnet.myapplication.repository.TAG, "DocumentSnapshot data: ${document}")
                } else {
                    Log.d(br.com.infnet.myapplication.repository.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(br.com.infnet.myapplication.repository.TAG, "get failed with ", exception)
            }

    }
}