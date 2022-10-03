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
import com.android.sivano.databinding.FragmentSignUpBinding
import com.android.sivano.common.helpers.MyValidation
import com.android.sivano.entities.UserInfoDto
import com.android.sivano.ui.viewmodel.AuthViewModel
import com.android.sivano.common.uitil.Resource
import com.android.sivano.common.uitil.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var _binding: FragmentSignUpBinding
    private val binding get() = _binding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupBtn.setOnClickListener(View.OnClickListener {

            if (!MyValidation.isValidEmail(binding.inputTextEmail.text.toString())) {
                toast("Please Check Your Email")
            } else if (binding.inputTextPassword.text.toString().length <= 7) {
                toast("please Check your Password")
            } else {
                register()
                authViewModel.registerMutableLiveData.observe(
                    viewLifecycleOwner,
                    Observer { response ->
                        when (response) {
                            is Resource.Success -> {
                                // hideProgress()
                                response.data?.let { response ->
                                    toast("Done")
                                    findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
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
        })

        binding.signInTxt.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

   private fun register() {
        authViewModel.register(
            UserInfoDto(
                email = binding.inputTextEmail.text.toString(),
                name = binding.inputTextName.text.toString(),
                password = binding.inputTextPassword.text.toString(),
                phone = binding.inputTextMobilePhone.text.toString(),
                //  phone ="01003254636",
                image = "https://student.valuxapps.com/storage/uploads/users/nPMHXzZWo2_1659098156.jpeg"
            )
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }


}