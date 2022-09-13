package com.android.sivano.ui.fragment.setup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.sivano.R
import com.android.sivano.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {

    private lateinit var _binding : FragmentSplashScreenBinding
    val binding get() = _binding
    lateinit var s:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding= FragmentSplashScreenBinding.inflate(inflater,container,false)
        return _binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       lifecycleScope.launch {
           delay(3000)
           withContext (Dispatchers.Main){
               findNavController().navigate(R.id.action_splashScreenFragment_to_onBoardingFragment)
           }
       }
    }
}