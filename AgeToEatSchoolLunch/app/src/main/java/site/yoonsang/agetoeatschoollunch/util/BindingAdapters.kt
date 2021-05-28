package site.yoonsang.agetoeatschoollunch.util

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("bindEditorAction")
    fun bindEditorAction(view: TextView, callback: (String) -> Boolean) {
        view.setOnEditorActionListener { _, id, _ ->
            when (id) {
                EditorInfo.IME_ACTION_DONE,
                EditorInfo.IME_ACTION_SEARCH -> {
                    callback(view.text.toString())
                }
                else -> false
            }
        }
    }
}