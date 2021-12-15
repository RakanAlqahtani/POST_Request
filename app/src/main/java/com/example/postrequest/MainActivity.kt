package com.example.postrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postrequest.api.APIClient
import com.example.postrequest.api.APIInterface
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_row.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.lang.Exception

class MainActivity : AppCompatActivity() {
     var items: Model = Model()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RVAdapter(items)
        RVmain.adapter = adapter
        RVmain.layoutManager = LinearLayoutManager(this)

        var apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.getItems()?.enqueue(object : Callback<Model>{

            override fun onResponse(call: Call<Model>, response: Response<Model>) {

                try {

                    for ( i in 0..response.body()!!.size +1 ) {
                        items.add(response.body()!![i])
                        adapter.notifyDataSetChanged()
                    }
                }catch (e : Exception){


                }
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {

            }

        })

button.setOnClickListener{

    apiInterface!!.addItem(
        ModelItem(
            TvL.text.toString(),
            TvN.text.toString(),
            0
            )
    ).enqueue(object : Callback<ModelItem>{

        override fun onResponse(call: Call<ModelItem>, response: Response<ModelItem>) {
            Toast.makeText(applicationContext, "User added!", Toast.LENGTH_LONG).show()
        recreate()
        }

        override fun onFailure(call: Call<ModelItem>, t: Throwable) {

        }
    })
}



    }
}