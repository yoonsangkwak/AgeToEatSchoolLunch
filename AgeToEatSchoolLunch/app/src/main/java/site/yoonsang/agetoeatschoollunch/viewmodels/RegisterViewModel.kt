package site.yoonsang.agetoeatschoollunch.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.models.SchoolInfoRow
import site.yoonsang.agetoeatschoollunch.repository.RegisterRepository
import site.yoonsang.agetoeatschoollunch.views.adapter.SchoolsAdapter

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RegisterRepository(application)
    private var schools = arrayListOf<SchoolInfoRow>()
    private var schoolsAdapter = SchoolsAdapter(this)
    var schoolName: String? = null
    var school: MutableLiveData<SchoolInfoRow> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getSchools() {
        schools.clear()
        repository.getSchools(schoolName).subscribe(
            { schoolResponse ->
                for (school in schoolResponse.schoolInfo[1].schoolInfoRow) {
                    schools.add(school)
                    Handler(Looper.getMainLooper()).post()
                    { schoolsAdapter.notifyDataSetChanged() }
                }
            }, { t ->
                Log.d("checkkk", t.message.toString())
            }
        )
    }

    fun viewInit(recyclerView: RecyclerView) {
        recyclerView.adapter = schoolsAdapter
        recyclerView.layoutManager = LinearLayoutManager(getApplication())
    }

    fun getSchool(position: Int) {
        school.value = schools[position]
    }

    fun getOfficeName(position: Int): String {
        return schools[position].officeName
    }

    fun getSchoolName(position: Int): String {
        return schools[position].schoolName
    }

    fun getSchoolLocation(position: Int): String {
        return schools[position].roadLocation
    }

    fun getSchoolsItem(): List<SchoolInfoRow> {
        return schools
    }
}