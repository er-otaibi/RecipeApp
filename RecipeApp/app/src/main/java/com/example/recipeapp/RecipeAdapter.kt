package com.example.recipeapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_row.view.*

class RecipeAdapter ( private val myRecipe: List<RecipeDetails.Datum>):  RecyclerView.Adapter<RecipeAdapter.ItemViewHolder>(){
    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recipe_row,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var list1 = myRecipe[position]

        holder.itemView.apply {
            tvTitle.text = "Title: ${list1.title}"
            tvAuthor.text = "Author: ${list1.author}"
            cvCard.setOnClickListener {

                linearLayout.isVisible = false
                layoutFullDetail.isVisible = true
                titl2.text = "Title: ${list1.title}"
                author2.text = "Author: ${list1.author}"
                ingredients2.text ="Ingredients: ${list1.ingredients}"
                instructions2.text = "Instructions: ${list1.instructions}"
                button.setOnClickListener {
                    layoutFullDetail.isVisible = false
                    linearLayout.isVisible = true
                }
            }
        }
    }
    override fun getItemCount() = myRecipe.size
}