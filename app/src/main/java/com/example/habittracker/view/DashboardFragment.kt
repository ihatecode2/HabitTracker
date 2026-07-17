package com.example.habittracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittracker.R
import com.example.habittracker.adapter.HabitAdapter
import com.example.habittracker.databinding.FragmentDashboardBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.HabitViewModel

class DashboardFragment : Fragment(), HabitCardListener {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: HabitViewModel

    private val adapter = HabitAdapter(
        arrayListOf(),
        this
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(
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

        binding.rvHabit.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvHabit.adapter = adapter

        viewModel.habitList.observe(viewLifecycleOwner) { habits ->
            adapter.updateData(habits)

            if (habits.isEmpty()) {
                binding.txtEmpty.visibility = View.VISIBLE
                binding.rvHabit.visibility = View.GONE
            } else {
                binding.txtEmpty.visibility = View.GONE
                binding.rvHabit.visibility = View.VISIBLE
            }
        }

        binding.fabAddHabit.setOnClickListener {
            findNavController().navigate(
                R.id.actionCreateHabitFragment
            )
        }

        viewModel.refresh()
    }

    override fun onIncreaseClick(habit: Habit) {
        viewModel.increaseProgress(habit)
    }

    override fun onDecreaseClick(habit: Habit) {
        viewModel.decreaseProgress(habit)
    }

    override fun onTitleClick(habit: Habit) {
        val action = DashboardFragmentDirections.actionEditHabitFragment(habit.id)
        findNavController().navigate(action)
    }
}