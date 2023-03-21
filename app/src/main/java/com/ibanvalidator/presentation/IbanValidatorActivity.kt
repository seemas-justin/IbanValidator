package com.ibanvalidator.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibanvalidator.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IbanValidatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ibanvalidator)
    }
}