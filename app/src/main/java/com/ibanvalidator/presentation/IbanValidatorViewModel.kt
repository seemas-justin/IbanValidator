package com.ibanvalidator.presentation

import androidx.lifecycle.*
import com.ibanvalidator.domain.IbanValidatorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IbanValidatorViewModel

@Inject
constructor(
    val ibanValidatorRepository: IbanValidatorRepository
): ViewModel() {
    private val _validationState: MutableLiveData<Boolean> = MutableLiveData()
     val validationState: LiveData<Boolean>
    get() = _validationState

    fun validateIban(iban: String){
        if (isLocalValidationSuccess(iban)){
            viewModelScope.launch {
                val isValid = ibanValidatorRepository.validateIban(iban)
                _validationState.postValue(isValid)
            }
        }else{
            _validationState.postValue(false)
        }
    }

    fun isLocalValidationSuccess(iban: String): Boolean {
        return iban.length > 5
    }

}

