package com.example.taskproject.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.taskproject.R
import com.example.taskproject.databinding.FragmentCustomUrlInputBottomSheetBinding
import com.example.taskproject.model.CustomUrl
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CustomUrlInputBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: FragmentCustomUrlInputBottomSheetBinding
    interface OnFragmentInteractionListener {
        fun getData(customUrl: CustomUrl)
    }

     var _listener: OnFragmentInteractionListener? = null
    fun OnSaveClickListener(listener: OnFragmentInteractionListener) {
        _listener = listener
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomUrlInputBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            val link = binding.customUrlInput
            val label = binding.labelInput
            val priceSwitch = binding.priceSwitch.isChecked
            val price = if(priceSwitch) "Rs ${binding.priceInput.text.toString()}" else "Rs 0"

            if(link.text.isNullOrEmpty() || label.text.isNullOrEmpty()) {
                binding.customUrlInput.error = "Required"
                return@setOnClickListener
            }

            if(label.text.isNullOrEmpty()) {
                binding.labelInput.error = "Required"
                return@setOnClickListener
            }
            
            if(priceSwitch && binding.priceInput.text.isNullOrEmpty()) {
                binding.priceInput.error = "Required"
                return@setOnClickListener
            }

            val customUrl = CustomUrl(link.text.toString(), label.text.toString(),price)
            _listener?.getData(customUrl)
            dismiss()
        }
    }

}