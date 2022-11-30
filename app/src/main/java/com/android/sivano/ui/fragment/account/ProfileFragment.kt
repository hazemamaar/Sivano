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
import com.android.sivano.common.uitil.C.USER
import com.android.sivano.data.entities.auth.UserInfoDto
import com.android.sivano.data.local.ComplexPreferences
import com.android.sivano.databinding.FragmentAccountBinding
import com.android.sivano.databinding.FragmentProfileBinding
import com.android.sivano.domin.model.User
import com.android.sivano.ui.viewmodel.AccountViewModel
import com.android.sivano.ui.viewmodel.ProfileViewMode
import com.bumptech.glide.Glide
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment @Inject constructor(val complexPreferences: ComplexPreferences) : Fragment() {
    private lateinit var _binding: FragmentProfileBinding
    private val binding get() = _binding
    private val profileViewMode: ProfileViewMode by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllInfo()
        binding.confirmBtn.setOnClickListener{
            updateProfile()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getAllInfo() {
        profileViewMode.profile()
        profileViewMode.profileSharedFlow.onEach {
            val profileModel = it.data
            if (profileModel != null) {
                Glide.with(requireContext()).load(profileModel.image).into(binding.profileImage)
                binding.inputTextLayoutEmail.hint = profileModel.email
                binding.inputTextLayoutName.hint = profileModel.name
                binding.inputTextLayoutMobilePhone.hint = profileModel.phone
                binding.inputTextLayoutPassword.hint = "********"
            }
        }.launchIn(lifecycleScope)

    }

    private fun updateProfile() {
//        var user :Class<User> = User::class.java
//       complexPreferences.getObject(USER,user)
//        Log.e("updateProfile", "updateProfile: "+user.name )
        val email = binding.inputTextEmail.text.toString()
        val password = binding.inputTextPassword.text.toString()
        val name = binding.inputTextName.text.toString()
        val phone = binding.inputTextMobilePhone.text.toString()
        if (email != null && password !=null &&name!=null &&phone!=null) {
            val userInfoDto = UserInfoDto(
                email = email,
                password = password,
                "https://student.valuxapps.com/storage/uploads/users/zSMwP5zQlf_1669528219.jpeg",
                name,
                phone
            )
            profileViewMode.updateProfile(userInfoDto)
            profileViewMode.updateProfileSharedFlow.onEach {
                if (it.data != null) {
                    findNavController().navigate(R.id.action_profileFragment2_to_successDialogFragment)
                }
            }.launchIn(lifecycleScope)
        }

    }
}