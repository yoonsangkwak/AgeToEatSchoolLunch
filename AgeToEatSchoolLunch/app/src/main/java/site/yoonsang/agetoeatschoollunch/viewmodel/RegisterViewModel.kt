package site.yoonsang.agetoeatschoollunch.viewmodel

import androidx.lifecycle.*
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import site.yoonsang.agetoeatschoollunch.repository.RegisterRepository
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel() {

    val schoolName = MutableLiveData<String>()

    val schoolInfo = Transformations.switchMap(schoolName) {
        repository.getSchoolInfo(schoolName.value!!).cachedIn(viewModelScope)
    }

    fun clearEditText() {
        schoolName.value = ""
    }
}