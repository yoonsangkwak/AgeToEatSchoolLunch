package site.yoonsang.agetoeatschoollunch.view.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.agetoeatschoollunch.view.models.SchoolInfoRow
import site.yoonsang.agetoeatschoollunch.view.repository.RegisterRepository
import site.yoonsang.agetoeatschoollunch.view.views.adapter.SchoolsAdapter

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RegisterRepository(application)
    private var schools = arrayListOf<SchoolInfoRow>()
    private var schoolsAdapter = SchoolsAdapter(this)
    var schoolName: String? = null

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