package com.example.habittracker.view

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habittracker.R
import com.example.habittracker.databinding.FragmentCreateHabitBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.HabitViewModel

class CreateHabitFragment : Fragment() {

    private lateinit var binding: FragmentCreateHabitBinding
    private lateinit var viewModel: HabitViewModel

    private val iconList = listOf(
        R.drawable.ic_habit_bedtime,
        R.drawable.ic_habit_edit_note,
        R.drawable.ic_habit_fitness_center,
        R.drawable.ic_habit_home,
        R.drawable.ic_habit_lightbulb,
        R.drawable.ic_habit_local_drink,
        R.drawable.ic_habit_menu_book,
        R.drawable.ic_habit_music_note,
        R.drawable.ic_habit_nature,
        R.drawable.ic_habit_pets,
        R.drawable.ic_habit_psychology,
        R.drawable.ic_habit_restaurant,
        R.drawable.ic_habit_running,
        R.drawable.ic_habit_savings,
        R.drawable.ic_habit_schedule,
        R.drawable.ic_habit_self_improvement,
        R.drawable.ic_habit_task_alt,
        R.drawable.ic_habit_walking,
        R.drawable.ic_habit_wash_hand,
        R.drawable.ic_habit_work
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())
            .get(HabitViewModel::class.java)

        val iconNames = listOf(
            "Sleep",
            "Write Notes",
            "Workout",
            "Stay Home",
            "Get Ideas",
            "Drink Water",
            "Read Book",
            "Listen Music",
            "Go Outside",
            "Take Care of Pets",
            "Mental Health",
            "Eat Meals",
            "Running",
            "Save Money",
            "Daily Schedule",
            "Meditation",
            "Complete Tasks",
            "Walking",
            "Wash Hands",
            "Work"
        )


        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            iconNames
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIcon.adapter = spinnerAdapter

        binding.btnCreateHabit.setOnClickListener {

            val name = binding.txtNewHabitName.text.toString()
            val desc = binding.txtNewHabitDesc.text.toString()
            val goal = binding.txtNewGoal.text.toString().toIntOrNull() ?: 0
            val unit = binding.txtNewUnit.text.toString()

            val selectedIndex = binding.spinnerIcon.selectedItemPosition
            val icon = iconList[selectedIndex]

            val habit = Habit(
                name = name,
                description = desc,
                goal = goal,
                progress = 0,
                unit = unit,
                icon = icon)

            if (habit.isValid()) {
                viewModel.addHabit(habit)

                Toast.makeText(context, "Habit berhasil dibuat", Toast.LENGTH_SHORT).show()

                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "Input tidak valid", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}