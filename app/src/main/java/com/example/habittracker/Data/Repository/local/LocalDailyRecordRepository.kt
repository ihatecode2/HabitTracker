package com.example.habittracker.repository

import android.content.Context
import com.example.habittracker.Data.Repository.DailyRecordRepository
import com.example.habittracker.Model.DailyRecord
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.Date

/* NOTES:
 * A Local File manager,
 * using `File` library, for `DailyRecord` CRUD using `JSON`
 * Each file represents one day: "{date.time}_dailyrecord.json"
 */
class LocalDailyRecordRepository(private val context: Context) : DailyRecordRepository {

    //region Utilities
    private val fileSuffix = "_dailyrecord.json"
    private fun getFile(date: Date) = File(context.filesDir, "${date.time}$fileSuffix")
    //endregion

    //region DailyRecord
    override fun saveDailyRecord(dailyRecord: DailyRecord) {
        getFile(dailyRecord.date).writeText(Json.encodeToString(dailyRecord))
    }

    override fun getDailyRecord(date: Date): DailyRecord? {
        val file = getFile(date)
        if (!file.exists()) return null
        return Json.decodeFromString(file.readText())
    }

    override fun deleteDailyRecord(date: Date) {
        val file = getFile(date)
        if (file.exists()) {
            file.delete()
        }
    }
    //endregion

}