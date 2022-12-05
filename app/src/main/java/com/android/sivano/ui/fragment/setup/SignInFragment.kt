package com.android.sivano.ui.fragment.setup

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.android.sivano.R
import com.android.sivano.common.helpers.MyLocation
import com.android.sivano.data.local.ComplexPreferences
import com.android.sivano.databinding.FragmentSignInBinding
import com.android.sivano.common.helpers.MyValidation
import com.android.sivano.common.uitil.C.FCM_TOKEN
import com.android.sivano.common.uitil.C.TOKEN
import com.android.sivano.common.uitil.C.USER
import com.android.sivano.ui.viewmodel.AuthViewModel
import com.android.sivano.common.uitil.Resource
import com.android.sivano.common.uitil.toast
import com.android.sivano.data.entities.auth.UserInfoDto
import com.android.sivano.domin.model.UserInfoDB
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private lateinit var _binding: FragmentSignInBinding
    private val binding get() = _binding
    private val authViewModel: AuthViewModel by viewModels()
    var tok:String="null"
    private var latLong: LatLng?=null
    private lateinit var locationResult: MyLocation.LocationResult
    private val myLocation by lazy {   MyLocation()}
    @Inject
    lateinit var complexPreferences: ComplexPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationResult = object : MyLocation.LocationResult() {
            override fun gotLocation(location: Location?) {
                Log.i("location", "gotLocation: ${location.toString()}")
                location?.let {
                    latLong= LatLng(it.latitude,it.longitude)
                }
            }
        }

        binding.signinBtn.setOnClickListener(View.OnClickListener {

            if (!MyValidation.isValidEmail(binding.inputTextEmail.text.toString())) {
                toast("Please Check Your Email")
            } else if (binding.inputTextPassword.text.toString().length <= 5) {
                toast("please Check your Password")
            } else {
                login()
                authViewModel.loginMutableLiveData.observe(
                    viewLifecycleOwner,
                    Observer { response ->
                        when (response) {
                            is Resource.Success -> {
                                 hideProgress()
                                response.data?.let { newsResponse ->
                                    complexPreferences?.apply {
                                        putString(TOKEN,newsResponse.token)
                                        putObject(USER,newsResponse)
                                        commit()

                                    }
                                    FirebaseMessaging.getInstance().token.addOnSuccessListener {
                                        // FCM token send to to your server
                                        authViewModel.fcmToken(
                                            com.android.sivano.data.entities.auth.FcmTokenOtd(
                                                it
                                            )
                                        )
                                        authViewModel.fcmTokenSharedFlow.onEach {
                                            complexPreferences?.apply {
                                                it.data?.let { it1 ->
                                                    putString(
                                                        FCM_TOKEN,
                                                        it1.token
                                                    )
                                                }
                                                commit()
                                            }
                                        }
                                        Log.e("fcm_logout", "onViewCreated: "+it )

                                    }.addOnCompleteListener {
                                        // After success event this event is triggered.

                                    }.addOnFailureListener {
                                        // In case any exception is occurred handle it here.
                                    }
//                                    generateTokenFcm()
                                    findNavController().navigate(R.id.action_signInFragment_to_defaultActivity2)
                                }
                            }
                            is Resource.Error -> {
                                 hideProgress()
                                response.message?.let { message ->
                                    toast(message)
                                }
                            }
                            is Resource.Loading -> {
                                 showProgress()
                            }
                            else -> {}
                        }
                    })
            }

        })
    }
   fun showProgress(){
        binding.spinKit.visibility=View.VISIBLE
    }
    fun hideProgress(){
        binding.spinKit.visibility=View.GONE
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