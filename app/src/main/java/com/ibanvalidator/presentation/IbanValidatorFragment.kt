package com.ibanvalidator.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ibanvalidator.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_ibanvalidator.*

@AndroidEntryPoint
class IbanValidatorFragment : Fragment() {

    private val viewModel: IbanValidatorViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_ibanvalidator, container, false)
        val button = rootView.findViewById<Button>(R.id.button_validate)


        button.setOnClickListener {
            viewModel.validateIban(editTextIban.text.toString())
        }
      intialiseObservers()
        return rootView
    }

    fun intialiseObservers(){
        viewModel.validationState.observe(this
        ) {
            if (it) {
                Toast.makeText(activity, getString(R.string.iban_validation_success), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, getString(R.string.iban_validtion_failed), Toast.LENGTH_LONG).show()
            }
        }
    }
}