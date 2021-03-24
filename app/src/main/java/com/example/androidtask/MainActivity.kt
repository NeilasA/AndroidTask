package com.example.androidtask

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.ListAdapter
import com.example.androidtask.api.Data
import com.example.androidtask.api.TodosJson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*

const val  BASE_URL="https://gorest.co.in"

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"

    private  var titlesList = mutableListOf<String>()
    private  var updatedAtList = mutableListOf<String>()
    private lateinit var mTodosViewModel: TodosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE

        mTodosViewModel = ViewModelProvider(this).get(TodosViewModel::class.java)

        //Calls Api for Data
        val service = RetrofitClientInstance.retrofitInstance?.create(ApiRequests::class.java)
        val call = service?.getTodos()
        call?.enqueue( object : Callback<TodosJson> {
            //Checks if api is responsive
            override fun onResponse(call: Call<TodosJson>, response: Response<TodosJson>) {
                val body = response.body()
                val todos= body?.data
                val size = todos?.size?.minus(1)
                val title = todos!![0].title.intern()
                val updated = todos[0].updated_at.intern()
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                ShowTodosFromApi(title,updated)

                //goes true date and adds each todos to database

                for(i in 0..size!!) {
                    insertDataToDatabase( i,"Title: ${todos[i].title.intern()}", "Updated At: ${todos[i].updated_at.intern()}")
                }

            }
            //If Api is not responsive show date from Data base (not implemented)
            override fun onFailure(call: Call<TodosJson>, t: Throwable) {

                ShowTodosFromDB()
                Toast.makeText(applicationContext,"This should show todos from Database",Toast.LENGTH_SHORT).show()

            }

        })

    }

    private fun ShowTodosFromApi(titleList: String , updatedList: String){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = RecyclerAdapter(titlesList, updatedAtList)
        }
    }
    private fun ShowTodosFromDB(){

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = RecyclerAdapterForDbData(titlesList, updatedAtList)
        }

    }

    private fun insertDataToDatabase(id: Int, title: String, updatedAt: String){
        titlesList.add(title)
        updatedAtList.add(updatedAt)

        val todos = Data(id, title, updatedAt)
        mTodosViewModel.insertData(todos)


    }
}




