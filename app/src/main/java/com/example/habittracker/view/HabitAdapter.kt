package com.example.habittracker.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.R
import com.example.habittracker.databinding.HabitCardItemBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.view.HabitCardListener

class HabitAdapter(
    private val habitList: ArrayList<Habit>,
    private val listener: HabitCardListener
) : RecyclerView.Adapter<HabitAdapter.ViewHolder>() {

    class ViewHolder(
        val binding: HabitCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val binding = HabitCardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val habit = habitList[position]

        holder.binding.habit = habit
        holder.binding.listener = listener

        holder.binding.imgHabitIcon.setImageResource(habit.icon)

        if (habit.isCompleted) {
            holder.binding.txtStatusBadge.setBackgroundResource(
                R.drawable.bg_badge_green
            )

            holder.binding.txtStatusBadge.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    android.R.color.white
                )
            )

            holder.binding.progressBar.progressTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.green_completed
                    )
                )

            holder.binding.btnIncrement.isEnabled = false
        } else {
            holder.binding.txtStatusBadge.setBackgroundResource(
                R.drawable.bg_badge_gray
            )

            holder.binding.txtStatusBadge.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.gray_text
                )
            )

            holder.binding.progressBar.progressTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.purple_primary
                    )
                )

            holder.binding.btnIncrement.isEnabled = true
        }

        holder.binding.btnDecrement.isEnabled =
            habit.progress > 0

        holder.binding.executePendingBindings()
    }

    fun updateData(newList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newList)
        notifyDataSetChanged()
    }
}