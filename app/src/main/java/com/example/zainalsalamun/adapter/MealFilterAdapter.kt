package com.example.zainalsalamun.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zainalsalamun.R
import com.example.zainalsalamun.model.MealClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.meal_item.view.*

class MealFilterAdapter(var mealList: List<MealClass> = ArrayList()): RecyclerView.Adapter<MealFilterAdapter.MealViewHolder>() {

    var clickListener: ClickListener? = null
    fun setOnClickListener(clickListener: ClickListener){
        this.clickListener = clickListener
    }

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {
        private lateinit var mealList: MealClass

        fun bindMeal(meal: MealClass) {
            this.mealList = meal
            Picasso.get()
                .load(meal.strMealThumb)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.mealImage)
            itemView.mealTitle.text = meal.strMeal

        }

        override fun onClick(v: View?) {
            clickListener?.onClick(mealList)
        }

        init {
            itemView.setOnClickListener(this)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val mealViewHolder = LayoutInflater.from(parent.context).inflate(R.layout.meal_item,parent,false)
        return MealViewHolder(mealViewHolder)
    }

    override fun getItemCount(): Int = mealList.size

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bindMeal(mealList[position])
    }

    fun updateMeal(mealList: List<MealClass>) {
        this.mealList = mealList
        notifyDataSetChanged()
    }

}

interface ClickListener {
    fun onClick(mealList: MealClass)
}