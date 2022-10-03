package com.android.sivano.ui.fragment.setup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.sivano.R
import com.android.sivano.data.local.ComplexPreferences
import com.android.sivano.databinding.FragmentSignInBinding
import com.android.sivano.common.helpers.MyValidation
import com.android.sivano.entities.UserInfoDto
import com.android.sivano.ui.viewmodel.AuthViewModel
import com.android.sivano.common.uitil.Resource
import com.android.sivano.common.uitil.toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private lateinit var _binding: FragmentSignInBinding
    private val binding get() = _binding
    private val authViewModel: AuthViewModel by viewModels()
    @Inject
    lateinit var complexPreferences: ComplexPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signinBtn.setOnClickListener(View.OnClickListener {

            if (!MyValidation.isValidEmail(binding.inputTextEmail.text.toString())) {
                toast("Please Check Your Email")
            } else if (binding.inputTextPassword.text.toString().length <= 7) {
                toast("please Check your Password")
            } else {
                login()
                authViewModel.loginMutableLiveData.observe(
                    viewLifecycleOwner,
                    Observer { response ->
                        when (response) {
                            is Resource.Success -> {
                                // hideProgress()
                                response.data?.let { newsResponse ->
                                    toast(newsResponse.email)
                                    complexPreferences?.apply {
                                        putString("token",newsResponse.token)
                                        putObject("user",newsResponse)
                                        commit()
                                        findNavController().navigate(R.id.action_signInFragment_to_defaultActivity2)
                                    }
                                }
                            }
                            is Resource.Error -> {
                                // hideProgress()
                                response.message?.let { message ->
                                    toast(message)
                                }
                            }
                            is Resource.Loading -> {
                                // ShowProgress()
                                toast("loading")
                            }
                            else -> {}
                        }
                    })
            }
//            findNavController().navigate(R.id.action_signInFragment_to_defaultActivity2)
        })
    }
    private fun login() {
        authViewModel.login(
            UserInfoDto(
                email = binding.inputTextEmail.text.toString(),
                password = binding.inputTextPassword.text.toString(),
                phone = "12345678924",
                name = "hadksgskl",
                image = "https://student.valuxapps.com/storage/uploads/users/nPMHXzZWo2_1659098156.jpeg"
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

}