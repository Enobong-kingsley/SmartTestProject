package com.codinginflow.simplecachingexample.features.userdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.codinginflow.simplecachingexample.R
import com.codinginflow.simplecachingexample.databinding.ActivityUserDetailBinding
import com.codinginflow.simplecachingexample.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity() {

    private val viewModel : UserDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userDetailAdapter = UserDetailAdapter()
        binding.apply {
            recyclerView.apply {
                adapter = userDetailAdapter
                layoutManager = LinearLayoutManager(this@UserDetailActivity)
            }

            viewModel.userDetails.observe(this@UserDetailActivity){ response ->
                when(response) {
                    is Resource.Error -> {
                        textViewError.text = response.error
                        Toast.makeText(this@UserDetailActivity,"Error ${response.error}",Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Success -> {
                        userDetailAdapter.submitList(response.data?.data ?: emptyList())
                    }

//                    is Resource.Loading -> {
//
//                    }
                }

            }
        }
    }
}