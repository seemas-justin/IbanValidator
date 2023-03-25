package com.ibanvalidator.domain

import com.ibanvalidator.data.models.ValidateIbanResult


interface IbanValidatorRepository {
    suspend fun validateIban(iban: String): Result<ValidateIbanResult?>
}