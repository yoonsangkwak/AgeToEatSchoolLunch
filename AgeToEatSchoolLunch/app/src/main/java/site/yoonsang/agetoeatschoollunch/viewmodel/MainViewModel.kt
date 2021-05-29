package site.yoonsang.agetoeatschoollunch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import site.yoonsang.agetoeatschoollunch.model.Allergy
import site.yoonsang.agetoeatschoollunch.model.MealInfo
import site.yoonsang.agetoeatschoollunch.repository.MainRepository
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _schoolName = MutableLiveData<String>()
    val schoolName: LiveData<String>
        get() = _schoolName

    private val _selectDate = MutableLiveData<String>()
    val selectDate: LiveData<String>
        get() = _selectDate

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    private val _mealInfo = MutableLiveData<List<MealInfo>>()
    val mealInfo: LiveData<List<MealInfo>>
        get() = _mealInfo

    private val _noMeal = MutableLiveData<Boolean>()
    val noMeal: LiveData<Boolean>
        get() = _noMeal

    val allergies = repository.getAllergies()

    init {
        _schoolName.value = ""
        _selectDate.value = ""
        _noMeal.value = false
    }

    fun getMealResponse(officeCode: String, schoolCode: String, mealDate: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMealResponse(
                    officeCode, schoolCode, mealDate
                )
                if (response.isSuccessful) {
                    val mealResponseInfo = response.body()?.mealResponseInfo
                    if (mealResponseInfo != null) {
                        _mealInfo.postValue(mealResponseInfo[1].mealInfo)
                        _noMeal.postValue(false)
                    } else {
                        _noMeal.postValue(true)
                    }
                }
            } catch (e: IOException) {
                _toastMessage.postValue("네트워크 통신에 실패했습니다")
            }
        }
    }

    fun setSchoolName(schoolName: String) {
        _schoolName.postValue(schoolName)
    }

    fun setSelectDate(time: Long) {
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd (E)", Locale.KOREA)
        val selectDate = simpleDateFormat.format(time)
        _selectDate.postValue(selectDate)
    }

    fun insert(allergy: Allergy) {
        viewModelScope.launch {
            repository.insert(allergy)
        }
    }

    fun delete(allergy: Allergy) {
        viewModelScope.launch {
            repository.delete(allergy)
        }
    }
}