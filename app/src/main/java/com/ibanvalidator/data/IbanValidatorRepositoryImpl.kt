package com.ibanvalidator.data

import com.ibanvalidator.domain.IbanValidatorRepository

class IbanValidatorRepositoryImpl
constructor(
    private val ibanRemoteDataSource: IbanRemoteDataSource
): IbanValidatorRepository {

    override suspend fun validateIban(iban: String) :Boolean {
        return ibanRemoteDataSource.validateIban(iban).isValid
    }

}
