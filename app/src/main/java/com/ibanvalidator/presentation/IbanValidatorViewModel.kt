package com.ibanvalidator.presentation

import androidx.lifecycle.*
import com.ibanvalidator.domain.IbanValidatorRepository
import com.ibanvalidator.domain.ValidationEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IbanValidatorViewModel

@Inject
constructor(
    val ibanValidatorRepository: IbanValidatorRepository
): ViewModel() {
    private val _validationState: MutableLiveData<ValidationEntity> = MutableLiveData()
     val validationState: LiveData<ValidationEntity>
    get() = _validationState


    fun validateIban(iban: String){
        if (isLocalValidationSuccess(iban)){
            viewModelScope.launch {
                ibanValidatorRepository.validateIban(iban)
                    .onSuccess {
                    _validationState.postValue(it?.let { it1 -> ValidationEntity.mapFromModel(it1) })
                }
            }
        }else{
            _validationState.postValue(
                ValidationEntity(
                    isValid = false,
                    validationMessage = "Some exception occurred - Validation failed"
            )
            )

        }
    }

    fun isLocalValidationSuccess(iban: String): Boolean {
        return iban.length > 5
    }

}

