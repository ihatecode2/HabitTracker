package com.example.habittracker.Data.Repository

import com.example.habittracker.Model.DailyRecord
import java.util.Date

interface DailyRecordRepository {
    fun saveDailyRecord(dailyRecord: DailyRecord)
    fun getDailyRecord(date:Date): DailyRecord?
    fun deleteDailyRecord(date: Date)
}