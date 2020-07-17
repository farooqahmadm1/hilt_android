package com.example.hilt_android.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.hilt_android.R
import com.example.hilt_android.databinding.FragmentHomeBinding
import com.example.hilt_android.network.ResponseResult
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment(
    private val something: String
) : Fragment(){

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(Companion.TAG, "onViewCreated: $something")

        viewModel.getUsers().observe(viewLifecycleOwner,
            Observer {
                when(it){
                    is ResponseResult.Success -> {
                        val users = it.data
                        users.forEach {
                            binding.response.text = binding.response.text .toString() +it.title + "\n" + it.body + "\n\n"
                        }
                    }
                    is ResponseResult.Failure -> {
                        val error = it.error
                        // some error happened
                    }
                    is ResponseResult.Pending-> {
                        // start | in progress
                        // show Loading...
                    }
                    is ResponseResult.Complete-> {
                        // call ended | completed
                        // hide Loading...
                    }
                }
            })
    }

    companion object {
        private const val TAG = "appDebug"
    }
}
