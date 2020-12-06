package com.example.zainalsalamun.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zainalsalamun.R
import com.example.zainalsalamun.adapter.ClickListener
import com.example.zainalsalamun.adapter.MealFilterAdapter
import com.example.zainalsalamun.model.MealClass
import com.example.zainalsalamun.viewmodel.MealViewModel
import com.example.zainalsalamun.viewmodel.MealDetailViewModel
import com.example.zainalsalamun.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_meal.*

class HomeFragment : Fragment(), ClickListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mealFilterAdapter: MealFilterAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_meal, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealRecyclerView.layoutManager = GridLayoutManager(context,2, GridLayoutManager.VERTICAL,false)
        mealFilterAdapter = MealFilterAdapter()
        mealRecyclerView.adapter = mealFilterAdapter
        mealFilterAdapter.setOnClickListener(this)
        observeViewModel()
    }

    fun observeViewModel() {
        var mealViewModel: MealViewModel = ViewModelProvider(this).get(MealViewModel::class.java)
        mealViewModel.loadMeal("Chicken")
        mealViewModel.getMealByCategory().observe(viewLifecycleOwner, Observer {
            mealRecyclerView.visibility = View.VISIBLE
            txtLoadError.visibility = View.GONE
            mealFilterAdapter.updateMeal(it)
        })

        mealViewModel.getLoadingError().observe(viewLifecycleOwner, Observer {
            txtLoadError.visibility = View.VISIBLE
            mealRecyclerView.visibility = View.GONE
        })
    }

    override fun onClick(mealList: MealClass) {

        if(!TextUtils.isEmpty(mealList.idMeal)){
            val mealDetailViewModel: MealDetailViewModel = ViewModelProvider(this).get(
                MealDetailViewModel::class.java)
            var mealID = mealList.idMeal
            var mealTitle = mealList.strMeal
            var action = HomeFragmentDirections.actionNavHomeToMealDetailFragment(mealID, mealTitle)
            findNavController().navigate(action)
        }
    }
}