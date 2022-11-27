package com.android.sivano.ui.fragment.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.sivano.R

import com.android.sivano.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private lateinit  var _binding:FragmentOnBoardingBinding
    private val binding get() = _binding
    var shake:Animation?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val bounce: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.shake).apply {
//            duration=500
//            repeatCount = ObjectAnimator.INFINITE
//            repeatMode = ObjectAnimator.RESTART
//        }
//        binding.startView.animation = bounce
//        binding.startView.startRippleAnimation();
        shake =AnimationUtils.loadAnimation(requireContext(),R.anim.shake)
        binding.startTxt.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_signUpFragment)
        })
        binding.startTxt.startAnimation(shake)
//        shake.repeatCount=20// repeat the loop 20 times
//        shake.duration=100 // animation play time 100 ms
//        shake.duration;
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding = FragmentOnBoardingBinding.inflate(inflater,container,false)
        return binding.root
    }

}