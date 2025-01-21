package com.example.taskproject

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskproject.adapters.CustomUrlListRecyclerAdapter
import com.example.taskproject.databinding.ActivityCustomLinkBinding
import com.example.taskproject.fragments.CustomUrlInputBottomSheet
import com.example.taskproject.model.CustomUrl
import com.example.taskproject.viewmodel.CustomLinkViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.launch

class CustomLinkActivity : AppCompatActivity(){
    val tag = "ProjectDebug"
    lateinit var binding: ActivityCustomLinkBinding
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: CustomUrlListRecyclerAdapter
    lateinit var viewModel: CustomLinkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCustomLinkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = ViewModelProvider(this).get(CustomLinkViewModel::class.java)

        binding.backArrow.setOnClickListener {
            finish()
        }


        setUpRecyclerView()
        AddCustomUrl()
    }

    private fun setUpRecyclerView() {
        Log.d("ProjectDebug", "setUpRecyclerView: ")

        viewModel.customUrls.observe(this) { urls ->
            Log.d(tag, "setUpRecyclerView: ${urls}")

            recyclerAdapter = CustomUrlListRecyclerAdapter(urls, this)
            recyclerView.adapter = recyclerAdapter
            recyclerAdapter.notifyDataSetChanged()
        }

        recyclerView = binding.customUrlrecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
    }


    private fun AddCustomUrl() {
        binding.floatingActionButton.setOnClickListener {
            val bottomSheet = CustomUrlInputBottomSheet()
            bottomSheet.show(supportFragmentManager, "CustomUrlInputBottomSheet")
            bottomSheet.OnSaveClickListener(object : CustomUrlInputBottomSheet.OnFragmentInteractionListener {
                override fun getData(customUrl: CustomUrl) {
                    viewModel.addCustomUrl(customUrl)
                }
            })
        }
    }
}