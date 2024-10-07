package com.hypersoft.textcraft

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.text.Layout
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.AlignmentSpan
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.material.textview.MaterialTextView
import com.hypersoft.textcraft.utils.VerticalImageSpan


class TextCraft @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    MaterialTextView(context, attrs, defStyleAttr) {
    private var verticalText: Boolean = false
    private var verticalTextDirection: Int = 0
    private var cornerRadius: Float = 0f
    private var rippleEnabled: Boolean = false
    private var rippleColor: Int = ContextCompat.getColor(context, R.color.ripple_color)
    private var gradientRadius = 100
    private var gradientType = GradientDrawable.LINEAR_GRADIENT
    private var gradientOrientation = 0
    private var userClickListener: OnClickListener? = null

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.TextCraft, 0, 0).apply {
            try {
                cornerRadius = getDimension(R.styleable.TextCraft_cornerRadius, 0f)

                var heading = getString(R.styleable.TextCraft_headingText) ?: ""
                val headingColor = getColor(R.styleable.TextCraft_headingColor, ContextCompat.getColor(context, android.R.color.black))
                val headingSize = getDimension(R.styleable.TextCraft_headingSize, 24f)
                val headingUnderline = getBoolean(R.styleable.TextCraft_headingUnderline, false)
                val headingUnderlineText = getString(R.styleable.TextCraft_headingUnderlineText) ?: ""
                val headingStrikeThrough = getBoolean(R.styleable.TextCraft_headingStrikeThrough, false)
                val strikeHeadingText = getString(R.styleable.TextCraft_strikeHeadingText) ?: ""
                val headingGravity = getInt(R.styleable.TextCraft_headingGravity, Gravity.START)
                val headingStyle = getInt(R.styleable.TextCraft_headingTextStyle, Typeface.NORMAL)
                val isHeadingGradient = getBoolean(R.styleable.TextCraft_setGradientHead, false)
                var gradientSpecificHeadText = getString(R.styleable.TextCraft_gradientSpecificHeadText) ?: ""
                val headGradientStartColor = getColor(R.styleable.TextCraft_headGradientStartColor, headingColor)
                val headGradientCenterColor = getColor(R.styleable.TextCraft_headGradientCenterColor, headingColor)
                val headGradientEndColor = getColor(R.styleable.TextCraft_headGradientEndColor, headingColor)
                val gradientHeadTextOrientation = getInt(R.styleable.TextCraft_gradientOrientationHeadText, 0)
                val headDrawableStart = getDrawable(R.styleable.TextCraft_headingDrawableStart)
                val headDrawableEnd = getDrawable(R.styleable.TextCraft_headingDrawableEnd)

                val headArray = if (isHeadingGradient) {
                    intArrayOf(headGradientStartColor, headGradientCenterColor, headGradientEndColor)
                } else {
                    gradientSpecificHeadText = ""
                    intArrayOf()
                }

                var subheading = getString(R.styleable.TextCraft_subheadingText) ?: ""
                val subheadingColor = getColor(R.styleable.TextCraft_subheadingColor, ContextCompat.getColor(context, android.R.color.darker_gray))
                val subheadingSize = getDimension(R.styleable.TextCraft_subheadingSize, 18f)
                val subheadingUnderline = getBoolean(R.styleable.TextCraft_subheadingUnderline, false)
                val subheadingUnderlineText = getString(R.styleable.TextCraft_subheadingUnderlineText) ?: ""
                val subheadingStrikeThrough = getBoolean(R.styleable.TextCraft_subheadingStrikeThrough, false)
                val strikeSubheadingText = getString(R.styleable.TextCraft_strikeSubheadingText) ?: ""
                val subheadingGravity = getInt(R.styleable.TextCraft_subheadingGravity, Gravity.START)
                val subheadingStyle = getInt(R.styleable.TextCraft_subheadingTextStyle, Typeface.NORMAL)
                val isSubheadingGradient = getBoolean(R.styleable.TextCraft_setGradientSubHead, false)
                var gradientSpecificSubheadText = getString(R.styleable.TextCraft_gradientSpecificSubHeadText) ?: ""
                val subheadGradientStartColor = getColor(R.styleable.TextCraft_subheadGradientStartColor, subheadingColor)
                val subheadGradientCenterColor = getColor(R.styleable.TextCraft_subheadGradientCenterColor, subheadingColor)
                val subheadGradientEndColor = getColor(R.styleable.TextCraft_subheadGradientEndColor, subheadingColor)
                val gradientSubHeadTextOrientation = getInt(R.styleable.TextCraft_gradientOrientationSubHeadText, 0)
                val subheadDrawableStart = getDrawable(R.styleable.TextCraft_subheadingDrawableStart)
                val subheadDrawableEnd = getDrawable(R.styleable.TextCraft_subheadingDrawableEnd)

                val subheadArray = if (isSubheadingGradient) {
                    intArrayOf(subheadGradientStartColor, subheadGradientCenterColor, subheadGradientEndColor)
                } else {
                    gradientSpecificSubheadText = ""
                    intArrayOf()
                }

                var paragraph = getString(R.styleable.TextCraft_paragraphText) ?: ""
                val paragraphColor = getColor(R.styleable.TextCraft_paragraphColor, ContextCompat.getColor(context, android.R.color.black))
                val paragraphSize = getDimension(R.styleable.TextCraft_paragraphSize, 16f)
                val paragraphUnderline = getBoolean(R.styleable.TextCraft_paragraphUnderline, false)
                val paragraphUnderlineText = getString(R.styleable.TextCraft_paragraphUnderlineText) ?: ""
                val paragraphStrikeThrough = getBoolean(R.styleable.TextCraft_paragraphStrikeThrough, false)
                val strikeParagraphText = getString(R.styleable.TextCraft_strikeParagraphText) ?: ""
                val paragraphGravity = getInt(R.styleable.TextCraft_paragraphGravity, Gravity.START)
                val paragraphStyle = getInt(R.styleable.TextCraft_paragraphTextStyle, Typeface.NORMAL)
                val isParagraphGradient = getBoolean(R.styleable.TextCraft_setGradientParagraph, false)
                val gradientSpecificParagraphText = getString(R.styleable.TextCraft_gradientSpecificParagraphText) ?: ""
                val paragraphGradientStartColor = getColor(R.styleable.TextCraft_paragraphGradientStartColor, paragraphColor)
                val paragraphGradientCenterColor = getColor(R.styleable.TextCraft_paragraphGradientCenterColor, paragraphColor)
                val paragraphGradientEndColor = getColor(R.styleable.TextCraft_paragraphGradientEndColor, paragraphColor)
                val gradientParagraphTextOrientation = getInt(R.styleable.TextCraft_gradientOrientationParagraphText, 0)
                val paragraphDrawableStart = getDrawable(R.styleable.TextCraft_paragraphDrawableStart)
                val paragraphDrawableEnd = getDrawable(R.styleable.TextCraft_paragraphDrawableEnd)

                val paragraphArray = if (isParagraphGradient) {
                    intArrayOf(paragraphGradientStartColor, paragraphGradientCenterColor, paragraphGradientEndColor)
                } else {
                    gradientSpecificSubheadText = ""
                    intArrayOf()
                }

                heading = updateAsDrawableAvailable(headDrawableStart, headDrawableEnd, heading)
                subheading = updateAsDrawableAvailable(subheadDrawableStart, subheadDrawableEnd, subheading)
                paragraph = updateAsDrawableAvailable(paragraphDrawableStart, paragraphDrawableEnd, paragraph)

                rippleEnabled = getBoolean(R.styleable.TextCraft_rippleEnabled, false)
                rippleColor = getColor(R.styleable.TextCraft_rippleColor, rippleColor)

                // Stroke attributes
                val strokeWidth = getDimension(R.styleable.TextCraft_strokeWidth, 0f)
                val strokeColor = getColor(R.styleable.TextCraft_strokeColor, Color.BLACK)
                gradientRadius = getInt(R.styleable.TextCraft_gradientRadius, 100)
                gradientType = getInt(R.styleable.TextCraft_gradientType, GradientDrawable.LINEAR_GRADIENT)
                gradientOrientation = getInt(R.styleable.TextCraft_gradientOrientation, 0)

                val gradientBackground = getBoolean(R.styleable.TextCraft_gradientBackground, false)
                val gradientStartColor = getColor(R.styleable.TextCraft_gradientStartColor, ContextCompat.getColor(context, android.R.color.transparent))
                val gradientCenterColor = getColor(R.styleable.TextCraft_gradientCenterColor, ContextCompat.getColor(context, android.R.color.transparent))
                val gradientEndColor = getColor(R.styleable.TextCraft_gradientEndColor, ContextCompat.getColor(context, android.R.color.transparent))

                verticalText = getBoolean(R.styleable.TextCraft_verticalText, false)
                verticalTextDirection = getInt(R.styleable.TextCraft_verticalTextDirection, 0)


                // Set the styled text with gravity
                setStyledText(
                    heading, headingStyle, headingColor, headingSize, headingUnderline, headingStrikeThrough, strikeHeadingText, headingGravity, headArray,
                    subheading, subheadingStyle, subheadingColor, subheadingSize, subheadingUnderline, subheadingStrikeThrough, strikeSubheadingText, subheadingGravity, subheadArray,
                    paragraph, paragraphStyle, paragraphColor, paragraphSize, paragraphUnderline, paragraphStrikeThrough, strikeParagraphText, paragraphGravity, paragraphArray,
                    gradientSpecificHeadText, gradientSpecificSubheadText, gradientSpecificParagraphText,
                    gradientHeadTextOrientation, gradientSubHeadTextOrientation, gradientParagraphTextOrientation,
                    headingUnderlineText, subheadingUnderlineText, paragraphUnderlineText,
                    headDrawableStart, headDrawableEnd, subheadDrawableStart, subheadDrawableEnd, paragraphDrawableStart, paragraphDrawableEnd
                )

                applyCornerRadius(cornerRadius, strokeWidth, strokeColor, gradientBackground, gradientStartColor, gradientCenterColor, gradientEndColor)

                if (rippleEnabled) {
                    super.setOnClickListener { view ->
                        applyRippleEffect()
                        userClickListener?.onClick(view)
                    }
                }


            } finally {
                recycle()
            }
        }
    }



    fun addImage(atText: String, @DrawableRes imgSrc: Int, imgWidth: Int, imgHeight: Int) {
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
        this.setText(ssb, BufferType.SPANNABLE)
    }


    private fun updateAsDrawableAvailable(start: Drawable?, end: Drawable?, text: String): String {
        return buildString {
            if (start != null) append("\uE000") // Add start placeholder if start drawable is present
            append(text)
            if (end != null) append("\uE001") // Add end placeholder if end drawable is present
        }
    }

    private fun setStyledText(
        heading: String, headingStyle: Int, headingColor: Int, headingSize: Float, headingUnderline: Boolean, headingStrikeThrough: Boolean, strikeHeadingText: String, headingGravity: Int, headArray: IntArray,
        subheading: String, subheadingStyle: Int, subheadingColor: Int, subheadingSize: Float, subheadingUnderline: Boolean, subheadingStrikeThrough: Boolean, strikeSubheadingText: String, subheadingGravity: Int, subheadArray: IntArray,
        paragraph: String, paragraphStyle: Int, paragraphColor: Int, paragraphSize: Float, paragraphUnderline: Boolean, paragraphStrikeThrough: Boolean, strikeParagraphText: String, paragraphGravity: Int, paragraphArray: IntArray,
        gradientSpecificHeadText: String, gradientSpecificSubheadText: String, gradientSpecificParagraphText: String,
        gradientHeadTextOrientation: Int, gradientSubHeadTextOrientation: Int, gradientParagraphTextOrientation: Int,
        headingUnderlineText: String, subheadingUnderlineText: String, paragraphUnderlineText: String,
        headDrawableStart: Drawable?, headDrawableEnd: Drawable?,
        subheadDrawableStart: Drawable?, subheadDrawableEnd: Drawable?,
        paragraphDrawableStart: Drawable?, paragraphDrawableEnd: Drawable?
    ) {
        val fullText = buildString {
            if (heading.isNotEmpty()) append("$heading\n")
            if (subheading.isNotEmpty()) append("$subheading\n")
            if (paragraph.isNotEmpty()) append(paragraph)
        }

        val spannableString = SpannableString(fullText)

        var start = 0

        if (heading.isNotEmpty()) {
            val end = heading.length
            applySpan(spannableString, heading, headingColor, headingSize, start, headingUnderline, headingStrikeThrough, strikeHeadingText, StyleSpan(getStyle(headingStyle)), headingGravity, headArray, headingUnderlineText)
            start = end + 1

            applyGradientOnText(spannableString, headArray, fullText, gradientSpecificHeadText.ifEmpty { heading }, gradientHeadTextOrientation, headingSize)

            applyDefaultTextColor(spannableString, headingColor, fullText, heading)
        }

        if (subheading.isNotEmpty()) {
            val end = start + subheading.length
            applySpan(spannableString, subheading, subheadingColor, subheadingSize, start, subheadingUnderline, subheadingStrikeThrough, strikeSubheadingText, StyleSpan(getStyle(subheadingStyle)), subheadingGravity, subheadArray, subheadingUnderlineText)
            start = end + 1

            applyGradientOnText(spannableString, subheadArray, fullText, gradientSpecificSubheadText.ifEmpty { subheading }, gradientSubHeadTextOrientation, subheadingSize)

            applyDefaultTextColor(spannableString, subheadingColor, fullText, subheading)
        }

        if (paragraph.isNotEmpty()) {
            applySpan(spannableString, paragraph, paragraphColor, paragraphSize, start, paragraphUnderline, paragraphStrikeThrough, strikeParagraphText, StyleSpan(getStyle(paragraphStyle)), paragraphGravity, paragraphArray, paragraphUnderlineText)

            applyGradientOnText(spannableString, paragraphArray, fullText, gradientSpecificParagraphText.ifEmpty { paragraph }, gradientParagraphTextOrientation, paragraphSize)

            applyDefaultTextColor(spannableString, paragraphColor, fullText, paragraph)
        }

        // Call the new method to set drawables
        setDrawables(spannableString, heading, subheading, paragraph, headDrawableStart, headDrawableEnd, subheadDrawableStart, subheadDrawableEnd, paragraphDrawableStart, paragraphDrawableEnd)

        text = spannableString
    }

    // New method to handle drawable settings
    private fun setDrawables(
        spannableString: SpannableString,
        heading: String,
        subheading: String,
        paragraph: String,
        headDrawableStart: Drawable?,
        headDrawableEnd: Drawable?,
        subheadDrawableStart: Drawable?,
        subheadDrawableEnd: Drawable?,
        paragraphDrawableStart: Drawable?,
        paragraphDrawableEnd: Drawable?
    ) {
        var start = 0

        if (heading.isNotEmpty()) {
            val end = heading.length
            headDrawableStart?.let {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
                spannableString.setSpan(VerticalImageSpan(it), start, start + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            }
            headDrawableEnd?.let {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
                spannableString.setSpan(VerticalImageSpan(it), end - 1, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            }
            start = end + 1
        }

        if (subheading.isNotEmpty()) {
            val end = start + subheading.length
            subheadDrawableStart?.let {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
                spannableString.setSpan(VerticalImageSpan(it), start, start + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            }
            subheadDrawableEnd?.let {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
                spannableString.setSpan(VerticalImageSpan(it), end - 1, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            }
            start = end + 1
        }

        if (paragraph.isNotEmpty()) {
            paragraphDrawableStart?.let {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
                spannableString.setSpan(VerticalImageSpan(it), start, start + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            }
            paragraphDrawableEnd?.let {
                it.setBounds(0, 0, it.intrinsicWidth, it.intrinsicHeight)
                spannableString.setSpan(VerticalImageSpan(it), start + paragraph.length - 1, start + paragraph.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            }
        }
    }


    private fun getStyle(style: Int): Int {
        return when (style) {
            0 -> Typeface.NORMAL
            1 -> Typeface.BOLD
            2 -> Typeface.ITALIC
            3 -> Typeface.BOLD_ITALIC
            else -> Typeface.NORMAL
        }
    }


    private fun applyCornerRadius(cornerRadius: Float, strokeWidth: Float, strokeColor: Int, gradientBackground: Boolean, gradientStartColor: Int, gradientCenterColor: Int, gradientEndColor: Int) {
        val shape = GradientDrawable()
        shape.cornerRadius = cornerRadius
        if (gradientBackground) {
            shape.colors = intArrayOf(gradientStartColor, gradientCenterColor, gradientEndColor)
            shape.gradientType = gradientType

            shape.orientation = when (gradientOrientation) {
                0 -> GradientDrawable.Orientation.TOP_BOTTOM
                1 -> GradientDrawable.Orientation.TR_BL
                2 -> GradientDrawable.Orientation.RIGHT_LEFT
                3 -> GradientDrawable.Orientation.BR_TL
                4 -> GradientDrawable.Orientation.BOTTOM_TOP
                5 -> GradientDrawable.Orientation.BL_TR
                6 -> GradientDrawable.Orientation.LEFT_RIGHT
                else -> GradientDrawable.Orientation.TL_BR
            }
            shape.gradientRadius = gradientRadius.toFloat()
        } else {
            getBackgroundColor()?.let { shape.setColor(it) }
        }
        if (strokeWidth > 0) {
            shape.setStroke(strokeWidth.toInt(), strokeColor)
        }

        background = shape
    }

    override fun setOnClickListener(l: OnClickListener?) {
        if (rippleEnabled) {
            userClickListener = l
        } else {
            super.setOnClickListener(l)
        }
    }

    private fun applyRippleEffect() {
        val contentDrawable = background ?: ColorDrawable(Color.TRANSPARENT)
        val maskDrawable = ColorDrawable(Color.WHITE)
        val rippleDrawable = RippleDrawable(ColorStateList.valueOf(rippleColor), contentDrawable, maskDrawable)
        background = rippleDrawable
        if (userClickListener != null) {
            super.setOnClickListener { view ->
                userClickListener?.onClick(view)
            }
        }
    }


    private fun getBackgroundColor(): Int? {
        val background = background
        if (background is ColorDrawable) {
            return background.color
        }
        return null
    }

    private fun applyGradientOnText(spannableString: SpannableString, gradientColor: IntArray, fullText: String, text: String, orientation: Int, textSize: Float) {
        if (gradientColor.isNotEmpty()) {
            val specificStart = fullText.indexOf(text)
            if (specificStart != -1) {
                val specificEnd = specificStart + text.length
                applyGradientSpan(spannableString, text, gradientColor, specificStart, specificEnd, orientation, textSize)
            }
        }
    }

    fun addImage(atText: String, imgSrc: Drawable, imgWidth: Int, imgHeight: Int) {
        val ssb = SpannableStringBuilder(this.text)

        imgSrc.mutate()
        imgSrc.setBounds(
            0, 0,
            imgWidth,
            imgHeight
        )
        val start = text.indexOf(atText)
        ssb.setSpan(VerticalImageSpan(imgSrc), start, start + atText.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        this.setText(ssb, BufferType.SPANNABLE)
    }

    private fun applyDefaultTextColor(spannableString: SpannableString, defaultTextColor: Int, fullText: String, text: String) {
        val specificStart = fullText.indexOf(text)
        if (specificStart == -1) {
            return
        }
        val specificEnd = specificStart + text.length
        val colorSpanStart = ForegroundColorSpan(defaultTextColor)
        spannableString.setSpan(colorSpanStart, specificStart, specificEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }


    private fun applyGradientSpan(spannable: SpannableString, text: String, gradientColors: IntArray, start: Int, end: Int, orientation: Int, textSize: Float) {
        val shader = createGradientShader(text, gradientColors, orientation, textSize)
        val shaderSpan = object : CharacterStyle() {
            override fun updateDrawState(tp: TextPaint) {
                tp.shader = shader
            }
        }
        spannable.setSpan(shaderSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    // Same createGradientShader method as before
    private fun createGradientShader(text: String, gradientColors: IntArray, orientation: Int, textSize: Float): Shader {
        val textWidth = text.length * 20f // Estimate text width (you may adjust this)
        val textHeight = textSize + 5f // Adjust based on your text size

        return when (orientation) {
            0 -> LinearGradient( // horizontal
                0f, 0f, textWidth, 0f, gradientColors, null, Shader.TileMode.CLAMP
            )

            1 -> LinearGradient( // vertical
                0f, 0f, 0f, textHeight, gradientColors, null, Shader.TileMode.CLAMP
            )

            2 -> LinearGradient( // diagnol
                0f, 0f, textWidth, textHeight, gradientColors, null, Shader.TileMode.CLAMP
            )

            else -> LinearGradient(
                0f, 0f, 0f, textHeight, gradientColors, null, Shader.TileMode.CLAMP
            )
        }
    }

    private fun applySpan(
        spannable: SpannableString, text: String, color: Int, size: Float, start: Int,
        underline: Boolean, strikeThrough: Boolean, strikeThroughText: String, styleSpan: StyleSpan?,
        gravity: Int, gradientColorArray: IntArray, underLineSpecific: String
    ) {
        val end = start + text.length
        if (gradientColorArray.isEmpty()) {
            spannable.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        spannable.setSpan(RelativeSizeSpan(size / textSize), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        styleSpan?.let { spannable.setSpan(it, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }
        val alignment = when (gravity) {
            3 -> Layout.Alignment.ALIGN_NORMAL // start
            5 -> Layout.Alignment.ALIGN_CENTER // center
            17 -> Layout.Alignment.ALIGN_OPPOSITE // end
            else -> Layout.Alignment.ALIGN_NORMAL
        }
        spannable.setSpan(AlignmentSpan.Standard(alignment), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        if (underline) {
            val underlineStart = text.indexOf(underLineSpecific)
            if (underlineStart != -1 && underLineSpecific.isNotEmpty()) {
                val underlineEnd = underlineStart + underLineSpecific.length
                spannable.setSpan(UnderlineSpan(), underlineStart, underlineEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            } else {
                spannable.setSpan(UnderlineSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

        }
        if (strikeThrough) {

            val strikeStart = text.indexOf(strikeThroughText)
            if (strikeStart != -1) {
                val strikeEnd = strikeStart + strikeThroughText.length
                spannable.setSpan(StrikethroughSpan(), start + strikeStart, start + strikeEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
    }




    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (verticalText) {
            super.onMeasure(heightMeasureSpec, widthMeasureSpec)
            setMeasuredDimension(measuredHeight, measuredWidth)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (verticalText) {
            val textPaint = paint
            textPaint.color = currentTextColor
            textPaint.drawableState = drawableState

            canvas.save()
            if (verticalTextDirection == 0) {
                canvas.translate(width.toFloat(), 0f)
                canvas.rotate(90f)
            } else {
                canvas.translate(0f, height.toFloat())
                canvas.rotate(-90f)
            }
            canvas.translate(compoundPaddingLeft.toFloat(), extendedPaddingTop.toFloat())
            layout?.draw(canvas)
//            drawDrawables(canvas)
            canvas.restore()
        } else {
            val path = Path()
            val rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
            path.addRoundRect(rectF, cornerRadius, cornerRadius, Path.Direction.CW)
            canvas.clipPath(path)
            super.onDraw(canvas)
        }
    }


}