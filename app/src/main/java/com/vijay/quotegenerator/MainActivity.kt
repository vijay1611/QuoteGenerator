package com.vijay.quotegenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.vijay.quotegenerator.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getQuote()
        binding.nextBtn.setOnClickListener{
            getQuote()
        }
    }



    private fun getQuote(){
        setInProgress(true)

        GlobalScope.launch {
            try {
                val response = RetrofitInstance.quoteApi.getRandomQuote()
                runOnUiThread{
                    setInProgress(false)
                    response.body()?.first()?.let {
                            setUI(it)
                    }
                }
            }catch (ex:Exception){
                Toast.makeText(this@MainActivity,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun setUI(quote : QuoteModel) {
        binding.quoteText.text =quote.q
        binding.author.text=quote.a
    }
    private fun setInProgress(inProgress:Boolean){
        if(inProgress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.nextBtn.visibility=View.GONE
        }else{
            binding.progressBar.visibility = View.GONE
            binding.nextBtn.visibility=View.VISIBLE
        }
    }
}