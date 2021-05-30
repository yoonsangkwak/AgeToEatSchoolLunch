package site.yoonsang.agetoeatschoollunch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    private val _mealBreakfast = MutableLiveData<MealInfo>()
    val mealBreakfast: LiveData<MealInfo>
        get() = _mealBreakfast

    private val _mealLunch = MutableLiveData<MealInfo>()
    val mealLunch: LiveData<MealInfo>
        get() = _mealLunch

    private val _mealDinner = MutableLiveData<MealInfo>()
    val mealDinner: LiveData<MealInfo>
        get() = _mealDinner

    private val _noBreakfast = MutableLiveData<Boolean>()
    val noBreakfast: LiveData<Boolean>
        get() = _noBreakfast

    private val _noLunch = MutableLiveData<Boolean>()
    val noLunch: LiveData<Boolean>
        get() = _noLunch

    private val _noDinner = MutableLiveData<Boolean>()
    val noDinner: LiveData<Boolean>
        get() = _noDinner

    init {
        _noBreakfast.value = true
        _noLunch.value = true
        _noDinner.value = true
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
                        for (mealInfo in mealResponseInfo[1].mealInfo) {
                            when (mealInfo.mealType) {
                                "조식" -> {
                                    _mealBreakfast.postValue(mealInfo)
                                    _noBreakfast.postValue(false)
                                }
                                "중식" -> {
                                    _mealLunch.postValue(mealInfo)
                                    _noLunch.postValue(false)
                                }
                                "석식" -> {
                                    _mealDinner.postValue(mealInfo)
                                    _noDinner.postValue(false)
                                }
                            }
                        }
                    }
                }
            } catch (e: IOException) {
                _toastMessage.postValue("네트워크 통신에 실패했습니다")
            }
        }
    }

    fun changeDate() {
        _noBreakfast.postValue(true)
        _noLunch.postValue(true)
        _noDinner.postValue(true)
    }

    fun setSchoolName(schoolName: String) {
        _schoolName.postValue(schoolName)
    }

    fun setSelectDate(time: Long) {
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd (E)", Locale.KOREA)
        val selectDate = simpleDateFormat.format(time)
        _selectDate.postValue(selectDate)
    }
}