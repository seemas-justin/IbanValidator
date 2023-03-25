package com.ibanvalidator.data

import com.google.gson.Gson
import com.ibanvalidator.data.api.IbanValidationService
import com.ibanvalidator.data.models.IbanValidationError
import com.ibanvalidator.data.models.ValidateIbanResult
import com.ibanvalidator.domain.IbanValidatorRepository
import javax.inject.Inject

class IbanValidatorRepositoryImpl
@Inject constructor(
    private val ibanValidationService: IbanValidationService,
    private val gson: Gson
): IbanValidatorRepository {

    override suspend fun validateIban(iban: String) : Result<ValidateIbanResult?> {
        val res = ibanValidationService.validateIban(iban)
        if(res.isSuccessful){
            return Result.success(res.body())
        }else {
            if(res.code() == 422) {
                val ibanValidationError = gson.fromJson<IbanValidationError>(res.errorBody()?.string(), IbanValidationError::class.java)
                return Result.success(ValidateIbanResult(
                    isValid = false,
                    message = if (ibanValidationError.errorDetails.ibanNumber.isNotEmpty()) ibanValidationError.errorDetails.ibanNumber[0] else "Some exception"
                ))
            }
            return Result.failure(Throwable("Server exception"))
        }
    }


}
