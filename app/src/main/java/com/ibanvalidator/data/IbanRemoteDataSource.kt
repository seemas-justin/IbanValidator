package com.ibanvalidator.data

import com.ibanvalidator.data.models.ValidateIbanResult
import retrofit2.http.GET
import retrofit2.http.Query

interface IbanRemoteDataSource {
    @GET("iban_validate")
    suspend fun validateIban(@Query("iban_number")  ibanNumber: String): ValidateIbanResult
}