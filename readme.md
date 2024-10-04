#TextCraft

**TextCraft** is a powerful and flexible custom Android view that extends `MaterialTextView`, designed to provide advanced text styling capabilities. With support for gradient colors, strike-through, underline, drawable integration, vertical text alignment, and ripple effects, TextCraft allows developers to style heading, subheading, and paragraph sections individually. It also includes Kotlin extension methods for enhanced functionality and supports localized strike-through and gradient text effects.

## Features

- **Customizable Headings, Subheadings, and Paragraphs**
  - Set text, color, size, underline, strike-through, text style (bold, italic), and gravity for each section.

- **Gradient Support**
  - Apply gradient effects separately to the heading, subheading, and paragraph sections.
  - Customize start, center, and end colors of the gradient.
  - Control gradient orientation (horizontal, vertical, diagonal) for each section.

- **Strike-through and Underline**
  - Apply strike-through and underline effects to specific substrings in the heading, subheading, and paragraph sections.

- **Drawable Support**
  - Add drawable icons to the start, center, or end of any section (heading, subheading, paragraph).

- **Ripple Effect**
  - Enable ripple effect on text click, with customizable ripple color.

- **Stroke, Background, and Corner Radius**
  - Set stroke width and color around the text view.
  - Apply gradient backgrounds (linear, radial, sweep) and control the corner radius for the view's background.

- **Vertical Text and Direction**
  - Enable vertical text alignment for all sections and control its direction (up or down).


## Attribute Summary

| Attribute                       | Format       | Description                                                |
|----------------------------------|--------------|------------------------------------------------------------|
| `cornerRadius`                   | dimension    | Set corner radius for the view's background.                |
| `headingText`                    | string       | Text for the heading.                                       |
| `headingColor`                   | color        | Color for the heading text.                                 |
| `headingSize`                    | dimension    | Font size for the heading text.                             |
| `headingUnderline`               | boolean      | Enable/disable underline for the heading.                   |
| `headingUnderlineText`           | string       | Apply underline to a specific substring in the heading.     |
| `headingStrikeThrough`           | boolean      | Enable/disable strike-through effect for the heading.        |
| `strikeHeadingText`              | string       | Apply strike-through to a specific substring in the heading.|
| `setGradientHead`                | boolean      | Enable/disable gradient for the heading text.               |
| `gradientSpecificHeadText`       | string       | Apply gradient to a specific substring in the heading.      |
| `headGradientStartColor`         | color        | Start color for heading gradient.                           |
| `headGradientCenterColor`        | color        | Center color for heading gradient.                          |
| `headGradientEndColor`           | color        | End color for heading gradient.                             |
| `gradientOrientationHeadText`    | enum         | Set gradient orientation (horizontal, vertical, diagonal).  |
| `headingTextStyle`               | enum         | Set heading text style (normal, bold, italic, boldItalic).  |
| `headingGravity`                 | enum         | Set gravity (start, center, end) for heading text.          |
| `subheadingText`, `paragraphText`| string       | Similar attributes for subheading and paragraph sections.   |
| `rippleEnabled`                  | boolean      | Enable/disable ripple effect on text click.                 |
| `rippleColor`                    | color        | Set ripple color when enabled.                              |
| `strokeWidth`, `strokeColor`     | dimension, color | Set stroke width and color around the text view.           |
| `verticalText`                   | boolean      | Enable vertical text for the sections.                      |
| `verticalTextDirection`          | enum         | Set vertical text direction (up or down).                   |
| `headingDrawableStart`, `subheadingDrawableStart`, `paragraphDrawableStart` | reference | Set drawable at the start of each section. |
| `headingDrawableEnd`, `subheadingDrawableEnd`, `paragraphDrawableEnd` | reference | Set drawable at the end of each section.   |

## Usage

### XML Example:

```
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
```

## Extension Methods

### 1. Strike-through with Localization

Applies a localized strike-through effect at the same position as in the English version of the text:

```
materialTextView.strikeThroughTextLocalized(R.string.full_text, "targetText")
```

### 2. Shade Text Color

Apply a gradient shading effect to text:


```
val colors = intArrayOf(Color.RED, Color.BLUE)
materialTextView.shadeTextColor("Gradient Text", colors)

```

### 3. Add Image in Center

Insert an image in place of text:

```
materialTextView.addImage("Insert Here", R.drawable.ic_image, imgWidth = 40, imgHeight = 40)

```
