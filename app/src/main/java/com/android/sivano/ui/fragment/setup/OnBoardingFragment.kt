package com.android.sivano.ui.fragment.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.sivano.R

import com.android.sivano.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment() {

    private lateinit  var _binding:FragmentOnBoardingBinding
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val bounce: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.shake).apply {
//            duration=500
//            repeatCount = ObjectAnimator.INFINITE
//            repeatMode = ObjectAnimator.RESTART
//        }
//        binding.startView.animation = bounce
//        binding.startView.startRippleAnimation();
        binding.startTxt.setOnClickListener(View.OnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_signUpFragment)
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding = FragmentOnBoardingBinding.inflate(inflater,container,false)
        return binding.root
    }

}