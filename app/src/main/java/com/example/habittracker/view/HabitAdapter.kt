package com.example.habittracker.adapter

import android.content.res.ColorStateList
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.R
import com.example.habittracker.model.Habit

class HabitAdapter(private val habitList: ArrayList<Habit>) :
    RecyclerView.Adapter<HabitAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.txtHabitName)
        val txtDesc: TextView = view.findViewById(R.id.txtHabitDesc)
        val txtProgress: TextView = view.findViewById(R.id.txtProgressCount)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val txtStatus: TextView = view.findViewById(R.id.txtStatusBadge)
        val btnPlus: Button = view.findViewById(R.id.btnIncrement)
        val btnMinus: Button = view.findViewById(R.id.btnDecrement)
        val imgIcon: ImageView = view.findViewById(R.id.imgHabitIcon)
        val imgCheck: ImageView = view.findViewById(R.id.imgCompletedCheck)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.habit_card_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = habitList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habit = habitList[position]

        holder.txtName.text = habit.name
        holder.txtDesc.text = habit.description
        holder.txtProgress.text = habit.progressText
        holder.progressBar.progress = habit.progressPercentage
        holder.imgIcon.setImageResource(habit.icon)

        if (habit.isCompleted) {
            holder.txtStatus.text = "Completed"
            holder.txtStatus.setBackgroundResource(R.drawable.bg_badge_green)

            holder.progressBar.progressTintList = ColorStateList.valueOf(
                ContextCompat.getColor(holder.itemView.context, R.color.green_completed)
            )

            holder.imgCheck.visibility = View.VISIBLE

        } else {
            holder.txtStatus.text = "In Progress"
            holder.txtStatus.setBackgroundResource(R.drawable.bg_badge_gray)

            holder.progressBar.progressTintList = ColorStateList.valueOf(
                ContextCompat.getColor(holder.itemView.context, R.color.purple_primary)
            )

            holder.imgCheck.visibility = View.GONE
        }

        holder.btnPlus.setOnClickListener {
            habit.increment()
            notifyItemChanged(position)
        }

        holder.btnMinus.setOnClickListener {
            habit.decrement()
            notifyItemChanged(position)
        }
    }

    fun updateData(newList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newList)
        notifyDataSetChanged()
    }
}