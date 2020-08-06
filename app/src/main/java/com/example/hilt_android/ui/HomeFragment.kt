package com.example.hilt_android.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hilt_android.R
import com.example.hilt_android.databinding.FragmentHomeBinding
import com.example.hilt_android.network.ResponseResult
import com.example.hilt_android.practice_network.Resource
import com.example.hilt_android.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment(
    private val something: String
) : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(Companion.TAG, "onViewCreated: $something")

        viewModel.getUsers().observe(viewLifecycleOwner,
            Observer {
                when (it) {
                    is ResponseResult.Success -> {
                        showSnackSuccess("Success", true)
                        val users = it.data
                        users.forEach {
                            binding.response.text =
                                binding.response.text.toString() + it.title + "\n" + it.body + "\n\n"
                        }
                    }
                    is ResponseResult.Error -> {
                        showErrorMessage(it.type)
                    }
                    is ResponseResult.Pending -> {
                        loadingProgress(true)
                    }
                    is ResponseResult.Complete -> {
                        loadingProgress(false)
                    }
                }
            })

        viewModel.getUser(1).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.response.text = it.data?.title + "\n" + it.data?.userId + "\n\n"
                    loadingProgress(false)
                }
                is Resource.Error -> {
                    showSnackError(it.message!!, true)
                    binding.response.text = "${it.data?.title}\n ${it.data?.userId} \n\n"
                }
                is Resource.Loading -> {
                    loadingProgress(true)
                }
            }
        })
    }

    companion object {
        private const val TAG = "appDebug"
    }
}
