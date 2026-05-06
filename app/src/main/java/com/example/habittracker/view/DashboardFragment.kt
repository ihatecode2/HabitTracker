package com.example.habittracker.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.habittracker.R
import com.example.habittracker.adapter.HabitAdapter
import com.example.habittracker.databinding.FragmentDashboardBinding
import com.example.habittracker.viewmodel.HabitViewModel

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: HabitViewModel
    private val adapter = HabitAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())
            .get(HabitViewModel::class.java)

        binding.rvHabit.layoutManager = LinearLayoutManager(context)
        binding.rvHabit.adapter = adapter

        viewModel.habitList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        binding.fabAddHabit.setOnClickListener {
            findNavController().navigate(R.id.actionCreateHabitFragment)
        }
    }
}