package com.example.recipeapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewRecipe : AppCompatActivity() {
    var myRecipeList = arrayListOf<RecipeDetails.Datum>()
    lateinit var rvMain : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_recipe)
        supportActionBar?.hide()
        rvMain = findViewById(R.id.rvMain)
        rvMain.adapter =RecipeAdapter(myRecipeList)
        rvMain.layoutManager = LinearLayoutManager(this)

        getRecipes()
    }


    private fun getRecipes(){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.getRecipe()?.enqueue(object : Callback<List<RecipeDetails.Datum>> {
            override fun onResponse(
                call: Call<List<RecipeDetails.Datum>>,
                response: Response<List<RecipeDetails.Datum>>
            ) {

                val resource = response.body()!!
                for ( i in resource){

                    var listTitle = i.title.toString()
                    var listAuthor = i.author.toString()
                    var listInget = i.ingredients.toString()
                    var listInsruc = i.instructions.toString()
                    myRecipeList.add(RecipeDetails.Datum(listTitle,listAuthor, listInget, listInsruc))

                }
                rvMain.adapter?.notifyDataSetChanged()
                rvMain.scrollToPosition(myRecipeList.size - 1)

            }

            override fun onFailure(call: Call<List<RecipeDetails.Datum>>, t: Throwable) {
                call.cancel()
            }
        })

    }
}