package com.hypersoft.textcraft

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val upperText = findViewById<MaterialTextView>(R.id.upperText)
        val textCraft = findViewById<TextCraft>(R.id.ctv2)
        textCraft.addImage("^%^", R.drawable.ic_bs_coins_in_text, imgWidth = 40, imgHeight = 40)
        textCraft.setOnClickListener {
            // Handle click event here
        }


//        val colorArray = intArrayOf(
//            resources.getColor(R.color.darkerGray, this.theme),
//            resources.getColor(R.color.teal_200, this.theme),
//            resources.getColor(R.color.darkerGray, this.theme),
//        )
//        upperText.shadeTextColor(upperText.text.toString(), colorArray)


    }
}