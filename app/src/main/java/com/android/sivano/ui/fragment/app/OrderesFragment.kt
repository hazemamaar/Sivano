package com.android.sivano.ui.fragment.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.sivano.R
import com.android.sivano.databinding.FragmentCartBinding
import com.android.sivano.databinding.FragmentOrdersBinding

class OrderesFragmentFragment : Fragment() {

    private lateinit var _binding: FragmentOrdersBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       _binding =FragmentOrdersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.payBtn.setOnClickListener {
            findNavController().navigate(R.id.action_orderesFragmentFragment_to_successfullyAddtoCart)
        }
    }

}