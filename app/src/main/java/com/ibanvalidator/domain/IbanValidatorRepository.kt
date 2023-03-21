package com.ibanvalidator.domain

interface IbanValidatorRepository {
    suspend fun validateIban(iban: String): Boolean
}