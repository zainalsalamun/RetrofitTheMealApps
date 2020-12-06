package com.example.zainalsalamun.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zainalsalamun.R
import com.example.zainalsalamun.model.MealCategory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.meal_detail_item.view.*

class MealDetailAdapter(var mealList: List<MealCategory> = ArrayList()): RecyclerView.Adapter<MealDetailAdapter.MealDetailViewHolder>() {

    inner class MealDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        private lateinit var mealList: MealCategory

        fun bindMeal(meal: MealCategory) {
            this.mealList = meal
            Picasso.get()
                .load(meal.strMealThumb)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.mealDetailImage)
            itemView.mealDetailTitle.text = meal.strMeal
            itemView.mealDetailDec.text = meal.strInstructions

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealDetailViewHolder {
        val mealViewHolder = LayoutInflater.from(parent.context).inflate(R.layout.meal_detail_item,parent,false)
        return MealDetailViewHolder(mealViewHolder)
    }

    override fun getItemCount(): Int = mealList.size

    override fun onBindViewHolder(holder: MealDetailViewHolder, position: Int) {
        holder.bindMeal(mealList[position])
    }

    fun updateMeal(mealList: List<MealCategory>) {
        this.mealList = mealList
        notifyDataSetChanged()
    }

}
