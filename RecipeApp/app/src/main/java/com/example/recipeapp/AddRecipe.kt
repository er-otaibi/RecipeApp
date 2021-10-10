package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddRecipe : AppCompatActivity() {
    lateinit var etTitle:EditText
    lateinit var etAuthor:EditText
    lateinit var etIngredients:EditText
    lateinit var etInstructions:EditText
    lateinit var saveBtn: Button
    lateinit var viewBtn : Button

    var title = ""
    var author = ""
    var ingredients = ""
    var instructions = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        supportActionBar?.hide()

        etTitle = findViewById(R.id.etTitle)
        etAuthor = findViewById(R.id.etAuthor)
        etIngredients = findViewById(R.id.etIngredients)
        etInstructions = findViewById(R.id.etInstructions)
        viewBtn = findViewById(R.id.viewBtn)
        saveBtn = findViewById(R.id.saveBtn)

        viewBtn.setOnClickListener {
            val intent = Intent(this, ViewRecipe::class.java)
            startActivity(intent) }

        saveBtn.setOnClickListener {
            title = etTitle.text.toString()
            author = etAuthor.text.toString()
            ingredients = etIngredients.text.toString()
            instructions = etInstructions.text.toString()
            if (title.isNotEmpty() && author.isNotEmpty() && ingredients.isNotEmpty() && instructions.isNotEmpty()){
                addRecipe()
                Toast.makeText(this, "New Recipe is added to your list!", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Please Enter a Full Recipe", Toast.LENGTH_LONG).show()
            }
            clearText()


        }
    }

    private fun clearText() {
        etTitle.setText("")
        etAuthor.setText("")
        etIngredients.setText("")
        etInstructions.setText("")
    }

    private fun addRecipe() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)


        apiInterface?.addRecipe(RecipeDetails.Datum(title , author , ingredients , instructions))?.enqueue(object :
            Callback<List<RecipeDetails.Datum>> {

            override fun onFailure(call: Call<List<RecipeDetails.Datum>>, t: Throwable) {
                call.cancel()
            }

            override fun onResponse(
                call: Call<List<RecipeDetails.Datum>>,
                response: Response<List<RecipeDetails.Datum>>
            ) {

                response.body()!!
            }

        }
        )
    }
}