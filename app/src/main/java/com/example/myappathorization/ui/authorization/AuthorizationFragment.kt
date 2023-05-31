package com.example.myappathorization.ui.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myappathorization.databinding.FragmentAuthorizationBinding
import com.jakewharton.rxbinding2.widget.RxTextView


class AuthorizationFragment : Fragment() {

    private var _binding: FragmentAuthorizationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val authorizationViewModel =
            ViewModelProvider(this).get(AuthorizationViewModel::class.java)

        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textAuthorization
        authorizationViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textPassword.isEnabled = false
        binding.buttonAuth.isEnabled = false

        RxTextView.afterTextChangeEvents(binding.textEmailAddress)
            .subscribe({

                if (binding.textEmailAddress.length() > 0) {
                    binding.textPassword.isEnabled = true
                }
                else{
                    binding.textPassword.isEnabled = false
                }
            })
        RxTextView.afterTextChangeEvents(binding.textPassword)
            .subscribe({

                if ((binding.textEmailAddress.length() > 0) && (binding.textPassword.length() > 0)) {
                    binding.buttonAuth.isEnabled = true
                }
                else {
                    binding.buttonAuth.isEnabled = false
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}