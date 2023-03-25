package com.ibanvalidator.domain

import com.ibanvalidator.data.models.ValidateIbanResult

data class ValidationEntity(
    val isValid: Boolean,
    val validationMessage: String?
){
    companion object {
        fun mapFromModel(validateIbanResult: ValidateIbanResult): ValidationEntity{
            return ValidationEntity(
                isValid = validateIbanResult.isValid,
                validationMessage = validateIbanResult.message,
            )
        }
    }
}

