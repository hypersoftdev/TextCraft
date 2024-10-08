package com.hypersoft.craftzone

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textview.MaterialTextView

import com.hypersoft.textcraft.TextCraft
import com.hypersoft.textcraft.extensions.Extension.shadeTextColor
import com.hypersoft.textcraft.extensions.Extension.strikeThroughTextLocalized

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

        val textCraft = findViewById<TextCraft>(R.id.cTv)
        val upperText = findViewById<MaterialTextView>(R.id.upperText)
        textCraft.addImage("placeholder", R.drawable.ic_bs_coins_in_text, imgWidth = 40, imgHeight = 40)
        textCraft.setOnClickListener {
            // Handle click event here
        }



        upperText.shadeTextColor(upperText.text.toString(),  intArrayOf(Color.RED,Color.BLUE))


    }
}