package com.android.sivano.ui.fragment.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.sivano.R
import com.android.sivano.databinding.FragmentAccountBinding
import com.android.sivano.databinding.FragmentProfileBinding
import com.android.sivano.ui.viewmodel.AccountViewModel
import com.android.sivano.ui.viewmodel.ProfileViewMode
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var _binding: FragmentProfileBinding
    private val binding get() = _binding
    private val profileViewMode: ProfileViewMode by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllInfo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun getAllInfo(){
        profileViewMode.profile()
        profileViewMode.profileSharedFlow.onEach {
            val profileModel = it.data
            if (profileModel != null) {
                Glide.with(requireContext()).load(profileModel.image).into(binding.profileImage)
                binding.inputTextLayoutEmail.hint = profileModel.email
                binding.inputTextLayoutName.hint = profileModel.name
                binding.inputTextLayoutMobilePhone.hint = profileModel.phone
                binding.inputTextLayoutPassword.hint="********"
            }
        }.launchIn(lifecycleScope)

    }
}