package com.hypersoft.textcraft.extensions

import android.content.res.Configuration
import android.graphics.LinearGradient
import android.graphics.Shader
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.StrikethroughSpan
import android.util.Log
import android.widget.TextView
import android.widget.TextView.BufferType
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.hypersoft.textcraft.utils.VerticalImageSpan
import java.util.Locale

object Extension {

    fun MaterialTextView.strikeThroughTextLocalized(@StringRes fullTextResId: Int, targetText: String) {
        // Get the full string in English (default locale)
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(Locale.ENGLISH)  // Force English locale
        val englishFullText = context.createConfigurationContext(configuration).getString(fullTextResId)

        // Find the index of the targetText in the English string
        val startIndex = englishFullText.indexOf(targetText)

        // If the target text is found in the English version, proceed
        if (startIndex != -1) {
            // Get the localized full string based on the current locale
            val localizedFullText = context.getString(fullTextResId)

            // Apply strike-through at the same index range on the localized string
            val spannableString = SpannableString(localizedFullText)
            spannableString.setSpan(
                StrikethroughSpan(),
                startIndex,
                startIndex + targetText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setText(spannableString, BufferType.SPANNABLE)
        } else {
            // Optionally handle the case where the targetText is not found in the English string
            Log.w("StrikeThrough", "Target text not found in English string")
        }
    }

    fun TextView.strikeThroughTextLocalized(@StringRes fullTextResId: Int, targetText: String) {
        // Get the full string in English (default locale)
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(Locale.ENGLISH)  // Force English locale
        val englishFullText = context.createConfigurationContext(configuration).getString(fullTextResId)

        // Find the index of the targetText in the English string
        val startIndex = englishFullText.indexOf(targetText)

        // If the target text is found in the English version, proceed
        if (startIndex != -1) {
            // Get the localized full string based on the current locale
            val localizedFullText = context.getString(fullTextResId)

            // Apply strike-through at the same index range on the localized string
            val spannableString = SpannableString(localizedFullText)
            spannableString.setSpan(
                StrikethroughSpan(),
                startIndex,
                startIndex + targetText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setText(spannableString, BufferType.SPANNABLE)
        } else {
            // Optionally handle the case where the targetText is not found in the English string
            Log.w("StrikeThrough", "Target text not found in English string")
        }
    }

    fun MaterialTextView.shadeTextColor(text : String, @ColorInt colors : IntArray) {
        val paint: TextPaint = paint
        val width = paint.measureText(text)
        val textShader: Shader = LinearGradient(
            0f, 0f, width,textSize,
            colors, null, Shader.TileMode.CLAMP
        )
        paint.setShader(textShader)

    }

    fun MaterialTextView.addImage(atText: String, @DrawableRes imgSrc: Int, imgWidth: Int, imgHeight: Int) {
        val ssb = SpannableStringBuilder(this.text)

        val drawable = ContextCompat.getDrawable(this.context, imgSrc) ?: return
        drawable.mutate()
        drawable.setBounds(
            0, 0,
            imgWidth,
            imgHeight
        )
        val start = text.indexOf(atText)
        ssb.setSpan(VerticalImageSpan(drawable), start, start + atText.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        this.setText(ssb, TextView.BufferType.SPANNABLE)
    }

    fun MaterialButton.addImage(atText: String, @DrawableRes imgSrc: Int, imgWidth: Int, imgHeight: Int) {
        val ssb = SpannableStringBuilder(this.text)

        val drawable = ContextCompat.getDrawable(this.context, imgSrc) ?: return
        drawable.mutate()
        drawable.setBounds(0, 0,
            imgWidth,
            imgHeight)
        val start = text.indexOf(atText)
        ssb.setSpan(VerticalImageSpan(drawable), start, start + atText.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        this.setText(ssb, TextView.BufferType.SPANNABLE)
    }


}