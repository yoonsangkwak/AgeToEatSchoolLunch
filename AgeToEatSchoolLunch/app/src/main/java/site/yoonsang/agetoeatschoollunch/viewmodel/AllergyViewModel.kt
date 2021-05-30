package site.yoonsang.agetoeatschoollunch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import site.yoonsang.agetoeatschoollunch.model.Allergy
import site.yoonsang.agetoeatschoollunch.repository.AllergyRepository
import javax.inject.Inject

@HiltViewModel
class AllergyViewModel @Inject constructor(
    private val repository: AllergyRepository
) : ViewModel() {

    val allergies = repository.getAllergies()

    fun insert(allergy: Allergy) {
        viewModelScope.launch {
            repository.insert(allergy)
        }
    }

    fun update(allergy: Allergy) {
        viewModelScope.launch {
            repository.update(allergy)
        }
    }
}