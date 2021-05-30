package site.yoonsang.agetoeatschoollunch.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import site.yoonsang.agetoeatschoollunch.model.SchoolInfo
import site.yoonsang.agetoeatschoollunch.repository.RegisterRepository
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel() {

    val schoolName = MutableLiveData<String>()
    val searchSuccess = MutableLiveData<Boolean>()

    private val _schoolInfo = MutableLiveData<List<SchoolInfo>>()
    val schoolInfo: LiveData<List<SchoolInfo>>
        get() = _schoolInfo

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    val editorAction = MutableLiveData<(String?) -> Boolean>()

    init {
        schoolName.value = ""
        searchSuccess.value = false
        editorAction.value = {
            getSchoolInfo()
            true
        }
    }

    private fun getSchoolInfo() {
        viewModelScope.launch {
            try {
                val response = repository.getSchoolInfo(schoolName.value!!)
                if (response.isSuccessful) {
                    val schoolResponseInfo = response.body()?.schoolResponseInfo
                    if (schoolResponseInfo != null) {
                        searchSuccess.value = true
                        _schoolInfo.postValue(schoolResponseInfo[1].schoolInfo)
                    } else {
                        searchSuccess.value = false
                    }
                }
            } catch (e: IOException) {
                _toastMessage.postValue("네트워크 통신에 실패했습니다")
            }
        }
    }

    fun clearEditText() {
        schoolName.value = ""
    }
}