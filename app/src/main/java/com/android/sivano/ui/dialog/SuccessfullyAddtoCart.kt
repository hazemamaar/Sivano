package com.android.sivano.ui.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.android.sivano.R
import com.android.sivano.databinding.DialigAddTocartBinding
import com.android.sivano.databinding.DialogSuccssfulProfileBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SuccessfullyAddtoCart: DialogFragment() {
    private var _binding: DialigAddTocartBinding? = null
    private val binding get()  = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialigAddTocartBinding.inflate(LayoutInflater.from(requireContext()))
        return MaterialAlertDialogBuilder(requireContext())
            .setBackground(ColorDrawable(Color.TRANSPARENT))
            .setView(binding.root)
            .setCancelable(true)
            .create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.keepBrowsing.setOnClickListener{
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
}