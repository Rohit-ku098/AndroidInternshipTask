package com.example.taskproject.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.taskproject.R
import com.example.taskproject.databinding.FragmentCouponBottomSheetBinding
import com.example.taskproject.model.Brand
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

class CouponBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: FragmentCouponBottomSheetBinding
    lateinit var clipboardManager: ClipboardManager
    lateinit var brand: Brand

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCouponBottomSheetBinding.inflate(inflater, container, false)
        clipboardManager = requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        brand = arguments?.getSerializable("brand") as Brand
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val closeBtn = binding.closeBtn
        val brandImage = binding.brandImage
        val brandName = binding.brandName
        val brandLink = binding.brandLink
        val couponText = binding.couponText


        brandImage.setImageResource(brand.image)
        brandName.text = brand.name
        brandLink.text = brand.link
        couponText.text = brand.coupon

        closeBtn.setOnClickListener {
            dismiss()
        }

        binding.couponText.setOnClickListener {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("coupon", couponText.text.toString()))
            Toast.makeText(requireContext(), "Coupon Copied", Toast.LENGTH_SHORT).show()
        }

    }
}