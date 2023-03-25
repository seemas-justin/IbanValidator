package com.ibanvalidator.data.api

import com.ibanvalidator.data.models.ValidateIbanResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IbanValidationService {
    @GET("iban_validate")
    suspend fun validateIban(@Query("iban_number")  ibanNumber: String): Response<ValidateIbanResult>
}