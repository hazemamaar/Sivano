package com.android.sivano.ui.fragment.account

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.sivano.R
import com.android.sivano.common.uitil.toast
import com.android.sivano.data.local.ComplexPreferences
import com.android.sivano.databinding.FragmentAccountBinding
import com.android.sivano.databinding.FragmentCartBinding
import com.android.sivano.entities.LogoutFcmOtd
import com.android.sivano.ui.viewmodel.AccountViewModel
import com.android.sivano.ui.viewmodel.AuthViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : Fragment() {
    private lateinit var _binding: FragmentAccountBinding
    private val binding get() = _binding
    private val accountViewModel: AccountViewModel by viewModels()
    @Inject
    lateinit var complexPreferences:ComplexPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fcmToken=complexPreferences.getString("fcm_token")
        binding.logout.setOnClickListener {
            accountViewModel.logOut()
            accountViewModel.logoutSharedFlow.onEach {
                Log.e("logoutsuccess", "onViewCreated: "+it.data?.token )
                findNavController().navigate(R.id.signInFragment)
            }.launchIn(lifecycleScope)
        }
        accountViewModel.profile()
            accountViewModel.profileSharedFlow.onEach {
                val profile=it.data
                binding.profileName.text=profile?.name
                Glide.with(this).load(profile?.image).into(binding.userImage)
            }.launchIn(lifecycleScope)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
           _binding=FragmentAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

}
