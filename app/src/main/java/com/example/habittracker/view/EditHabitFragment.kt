package com.example.habittracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.habittracker.R
import com.example.habittracker.databinding.FragmentEditHabitBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.HabitViewModel

class EditHabitFragment : Fragment(), EditHabitListener {

    private lateinit var binding: FragmentEditHabitBinding
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

    private val iconNames = listOf(
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEditHabitBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())
            .get(HabitViewModel::class.java)

        binding.listener = this

        setupIconSpinner()

        val habitId =
            EditHabitFragmentArgs
                .fromBundle(requireArguments())
                .habitId

        viewModel.fetchHabit(habitId)

        observeViewModel()

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupIconSpinner() {
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            iconNames
        )

        spinnerAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spinnerEditIcon.adapter = spinnerAdapter
    }

    private fun observeViewModel() {
        viewModel.selectedHabit.observe(
            viewLifecycleOwner
        ) { habit ->

            binding.habit = habit

            val iconPosition =
                iconList.indexOf(habit.icon)

            if (iconPosition >= 0) {
                binding.spinnerEditIcon
                    .setSelection(iconPosition)
            }
        }
    }

    override fun onSaveClick(view: View) {
        val habit = binding.habit

        if (habit == null) {
            Toast.makeText(
                requireContext(),
                "Data habit belum tersedia",
                Toast.LENGTH_SHORT
            ).show()

            return
        }

        val goal = binding.txtEditGoal.text
            .toString()
            .toIntOrNull() ?: 0

        val selectedIndex =
            binding.spinnerEditIcon.selectedItemPosition

        habit.goal = goal
        habit.icon = iconList[selectedIndex]

        if (habit.progress > habit.goal) {
            habit.progress = habit.goal
        }

        if (habit.isValid()) {
            viewModel.updateHabit(habit)

            Toast.makeText(
                requireContext(),
                "Habit berhasil diperbarui",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().popBackStack()
        } else {
            Toast.makeText(
                requireContext(),
                "Input tidak valid",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}