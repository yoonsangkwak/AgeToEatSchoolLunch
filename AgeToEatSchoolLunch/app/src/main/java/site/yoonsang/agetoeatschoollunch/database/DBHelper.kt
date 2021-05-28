package site.yoonsang.agetoeatschoollunch.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import site.yoonsang.agetoeatschoollunch.model.Allergy

const val DB_NAME = "AgeToEatSchoolLunch"
const val DB_VERSION = 1
const val TABLE_NAME = "Allergy"
const val COL_ID = "id"
const val COL_NAME = "name"
const val COL_CHECKED = "checked"

class DBHelper(
    context: Context,
    name: String,
    version: Int
): SQLiteOpenHelper(context, name, null, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        val create = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " name, " +
                COL_CHECKED + " checked " + ")"
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun insertData(allergy: Allergy) {
        val wd = writableDatabase
        val values = ContentValues()
        values.put(COL_NAME, allergy.name)
        values.put(COL_CHECKED, allergy.checked)
        wd.insert(TABLE_NAME, null, values)
        wd.close()
    }

    fun selectAllData(): ArrayList<Allergy> {
        val list = arrayListOf<Allergy>()
        val select = "SELECT * FROM $TABLE_NAME"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select, null)

        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex(COL_NAME))
            val checked = cursor.getInt(cursor.getColumnIndex(COL_CHECKED))
            val allergy = Allergy(name, checked)
            list.add(allergy)
        }
        cursor.close()
        rd.close()

        return list
    }

    fun updateData(allergy: Allergy) {
        val wd = writableDatabase
        val values = ContentValues()
        values.put(COL_NAME, allergy.name)
        values.put(COL_CHECKED, allergy.checked)
        wd.update(TABLE_NAME, values, "$COL_NAME = ?", arrayOf(allergy.name))
        wd.close()
    }
}