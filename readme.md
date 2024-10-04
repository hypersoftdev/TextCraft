**TextCraft README**

**Overview**

TextCraft is a custom Android view that extends MaterialTextView to provide advanced text styling capabilities, including gradient colors, strike-through, underline, vertical text alignment, drawable integration, and ripple effects. This view allows developers to apply specific styles to the heading, subheading, and paragraph sections individually. It also supports localization for strike-through, gradient text effects, and integrates extension methods for additional functionalities.

**Features**

- **Customizable Headings, Subheadings, and Paragraphs**
  - Set text, color, size, underline, strike-through, text style (bold, italic), and gravity for each section.
- **Gradient Support**
  - Apply gradients separately to the heading, subheading, and paragraph with start, center, and end colors.
  - Control the gradient orientation (horizontal, vertical, diagonal) for each section.
- **Strike-through and Underline**
  - Apply strike-through and underline effects for specific substrings in the heading, subheading, and paragraph sections.
- **Drawable Support**
  - Add drawable icons to the start, centre or end of each section (heading, subheading, paragraph).
- **Ripple Effect**
  - Enable ripple effect on text when clicked, with customizable ripple color.
- **Stroke, Background and Corner Radius**
  - Add stroke width and color, and apply gradient backgrounds (linear, radial, sweep). Also give facility to apply corner radius to background applied either gradient or solid.
- **Vertical Text and Direction**
  - Enable vertical text for all sections and set its direction (up/down).

**Attribute Summary**

| **Attribute** | **Format** | **Description** |
| --- | --- | --- |
| cornerRadius | dimension | Set corner radius for the view's background. |
| headingText | string | Text for the heading. |
| headingColor | color | Color for the heading text. |
| headingSize | dimension | Font size for the heading text. |
| headingUnderline | boolean | Enable/disable underline for the heading. |
| headingUnderlineText | string | Apply underline to a specific substring in the heading. |
| headingStrikeThrough | boolean | Enable/disable strike-through effect for the heading. |
| strikeHeadingText | string | Apply strike-through to a specific substring in the heading. |
| setGradientHead | boolean | Enable/disable gradient for the heading text. |
| gradientSpecificHeadText | string | Apply gradient to a specific substring in the heading. |
| headGradientStartColor | color | Start color for heading gradient. |
| headGradientCenterColor | color | Center color for heading gradient. |
| headGradientEndColor | color | End color for heading gradient. |
| gradientOrientationHeadText | enum | Set gradient orientation (horizontal, vertical, diagonal) for heading text. |
| headingTextStyle | enum | Set heading text style (normal, bold, italic, boldItalic). |
| headingGravity | enum | Set gravity (start, center, end) for heading text. |
| subheadingText, paragraphText | string | Similar attributes for subheading and paragraph as the heading attributes above. |
| rippleEnabled | boolean | Enable/disable ripple effect on text click. |
| rippleColor | color | Set ripple color when enabled. |
| strokeWidth, strokeColor | dimension, color | Set stroke width and color around the text view. |
| verticalText | boolean | Enable vertical text for the sections. |
| verticalTextDirection | enum | Set vertical text direction (up or down). |
| headingDrawableStart, subheadingDrawableStart, paragraphDrawableStart | reference | Set drawable at the start of each section. |
| headingDrawableEnd, subheadingDrawableEnd, paragraphDrawableEnd | reference | Set drawable at the end of each section. |

**Usage**

**XML Example:**

<com.example.customviews.TextCraft

android:layout_width="match_parent"

android:layout_height="wrap_content"

app:headingText="Welcome"

app:headingColor="@color/black"

app:headingSize="24sp"

app:setGradientHead="true"

app:headGradientStartColor="@color/red"

app:headGradientCenterColor="@color/blue"

app:headGradientEndColor="@color/green"

app:subheadingText="Introduction"

app:subheadingColor="@color/gray"

app:subheadingSize="18sp"

app:subheadingStrikeThrough="true"

app:rippleEnabled="true"

app:rippleColor="@color/rippleEffect"

app:paragraphText="This is a customizable TextCraft view."

app:paragraphColor="@color/darkGray"

app:verticalText="false"/>

**Extension Methods**

**Strike-through with Localization**

materailTextView.strikeThroughTextLocalized(R.string.full_text, "targetText")

This extension applies a localized strike-through effect at the same position in the string as the English version of the text.

**Shade Text Color**

val colors = intArrayOf(Color.RED, Color.BLUE)

materialTextView.shadeTextColor("Gradient Text", colors)

This method applies a gradient shading effect to the specified text.

**Add Image in Center**

materialTextView.addImage("Insert Here", R.drawable.ic_image, imgWidth = 40, imgHeight = 40)

This method inserts an image in place of the specified text substring.

**Future Work**

- **Support for More Text Effects**: Incorporate additional text effects such as shadow, embossing, or glow.
- **Improved Localization**: Enhance localization capabilities for more languages.
- **More Gradient Patterns**: Allow more complex gradient patterns, including circular gradients.
- **Drawable Tinting**: Add support for tinting drawables based on the text color.
- **Accessibility Features**: Incorporate accessibility improvements such as voice feedback or high-contrast modes.
- **Padding and Margins**: Padding and margins are intended to be added in future for all three Head, Subhead and Paragraph

**About Me:**

I am Nisar Bahoo, Android Developer at Hypersoft Inc. I specialize in creating custom views, optimizing Android applications, and working with Kotlin extensions to enhance the user experience.