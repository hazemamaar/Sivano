package com.android.sivano.ui.fragment.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.sivano.R
import com.android.sivano.data.entities.auth.ChangePasswordOtd
import com.android.sivano.databinding.FragmentAccountBinding
import com.android.sivano.databinding.FragmentChangePasswordBinding
import com.android.sivano.ui.viewmodel.AccountViewModel
import com.android.sivano.ui.viewmodel.ChangePasswordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ChangePasswordFragment : Fragment() {
    private lateinit var _binding: FragmentChangePasswordBinding
    private val binding get() = _binding
    private val changePasswordViewModel: ChangePasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.confirmBtn.setOnClickListener {
            changed()
        }
        binding.back.setOnClickListener{
            findNavController().popBackStack()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =FragmentChangePasswordBinding.inflate(inflater,container,false)
        return binding.root
    }

  private fun changed(){
      val currentPassword=binding.inputTextCurrentPassword.text.toString()
      val newPassword = binding.inputTextNewPassword.toString()
      if(currentPassword != null && newPassword !=null){
          changePasswordViewModel.changePassword(ChangePasswordOtd(currentPassword,newPassword))
          changePasswordViewModel.changePasswordSharedFlow.onEach {
              if(it.data?.email != null) {
                  findNavController().navigate(R.id.action_changePasswordFragment_to_successfullyProfileDialog)
              }
          }.launchIn(lifecycleScope)
      }
  }

}