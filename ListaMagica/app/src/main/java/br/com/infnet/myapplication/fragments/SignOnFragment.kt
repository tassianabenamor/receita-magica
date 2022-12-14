package br.com.infnet.myapplication.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import br.com.infnet.myapplication.MainActivity
import br.com.infnet.myapplication.databinding.FragmentSignOnBinding
import br.com.infnet.myapplication.models.Endereco
import br.com.infnet.myapplication.models.UserInfo
import br.com.infnet.myapplication.repository.ApiService
import br.com.infnet.myapplication.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignOnFragment : Fragment() {

    val viewModel by activityViewModels<LoginViewModel>()
    private var _binding: FragmentSignOnBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignOnBinding.inflate(inflater, container, false)
        val view = binding.root
        setup()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setup(){
        setupView()
        setupClickListeners()
    }

    private fun setupView() {
        activity?.setTitle("Receita Mágica")
    }

    private fun setupClickListeners() {
        binding.apply {
            btnSignOn.setOnClickListener {
                onSignOnClick()
            }
            inputCep.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (inputCep.text.toString().length == 8){
                    val imm: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(requireActivity().currentFocus!!.windowToken, 0)
                    getEnderecoByCep(inputCep.text.toString())
                    return@OnKeyListener true
                }
                false
            })
        }
    }

    private fun getEnderecoByCep(cep: String){
        val service = ApiService.createPostService()
        val call: Call<Endereco> = service.getCep(cep)
        call.enqueue(object : Callback<Endereco> {
            override fun onResponse(call: Call<Endereco>, r: Response<Endereco>) {
                fillForm(r.body() as Endereco)
            }
            override fun onFailure(call: Call<Endereco>, t: Throwable) {
                toast("Não conseguimos encontrar seu cep")
            }
        })
    }

    private fun fillForm(endereco: Endereco){
        binding.apply {
            tvLogradouro.text = endereco.logradouro
            tvBairro.text = endereco.bairro
            tvLocalidade.text = endereco.cidade
            tvUf.text = endereco.estado
        }
    }

    private fun onSignOnClick() {
    binding.apply {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            val confirmPassword = inputConfirmPassword.text.toString()
            if ( (password == confirmPassword) && password.length > 5){
                signOn(email, password)
            }
        }
    }

    fun signOn(email: String, password: String){
        viewModel.signOn(email, password).addOnCompleteListener {
            if(it.isSuccessful){

                    salvarDadosUsuario()

                    toast("Cadastrado com Sucesso")
                    startMainActivity()
                }else{
                    var msgErro : String = ""
                    try{
                        throw it.exception!!
                    }catch (e: FirebaseAuthWeakPasswordException){
                        msgErro = "Digite uma senha com no mínimo 6 caracteres"
                    }catch (e: FirebaseAuthUserCollisionException){
                        msgErro = "Esse e-mail já foi utilizado"
                    }catch (e: FirebaseAuthInvalidCredentialsException){
                        msgErro = "Informe um e-mail válido"
                    }catch (e: Exception){
                        msgErro = "Ocorreu um erro no seu cadastro."
                    }
                    toast(msgErro)
            }
        }
    }

    private fun salvarDadosUsuario() {
        var userInfo: UserInfo = getUserInfoFromInput()
        userInfo.setUserId(viewModel.repository.getCurrentUser()?.uid.toString())
        viewModel.registerUser(userInfo)
    }

    fun getUserInfoFromInput(): UserInfo{
        binding.apply {
            return UserInfo(
                inputName.text.toString(),
                inputCep.text.toString(),
                tvLogradouro.text.toString(),
                inputNumero.text.toString(),
                inputComplemento.text.toString(),
                tvBairro.text.toString(),
                tvLocalidade.text.toString(),
                tvUf.text.toString()
            )
        }
    }


    fun startMainActivity(){
        startActivity(Intent(requireContext(), MainActivity::class.java))
        activity?.finish()
    }
}