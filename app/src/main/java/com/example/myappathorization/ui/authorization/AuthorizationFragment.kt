package com.example.myappathorization.ui.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.myappathorization.LoginViewModel
import com.example.myappathorization.databinding.FragmentAuthorizationBinding
import com.jakewharton.rxbinding2.widget.RxTextView


class AuthorizationFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()

    private lateinit var binding: FragmentAuthorizationBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.token.observe(viewLifecycleOwner){token ->

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        //binding = null
    }
}