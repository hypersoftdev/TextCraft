[![](https://jitpack.io/v/hypersoftdev/TextCraft.svg)](https://jitpack.io/#hypersoftdev/TextCraft)

# TextCraft

**TextCraft** is a powerful and flexible custom Android view that extends `MaterialTextView`, designed to provide advanced text styling capabilities. With support for gradient colors, strike-through, underline, drawable integration, vertical text alignment, and ripple effects, TextCraft allows developers to style heading, subheading, and paragraph sections individually. It also includes Kotlin extension methods for enhanced functionality and supports localized strike-through and gradient text
effects.

## Gradle Integration

### Step A: Add Maven Repository

In your project-level **build.gradle** or **settings.gradle** file, add the JitPack repository:

```
repositories {
    google()
    mavenCentral()
    maven { url "https://jitpack.io" }
}
```  

### Step B: Add Dependencies

Next, include the library in your app-level **build.gradle** file. Replace x.x.x with the latest version [![](https://jitpack.io/v/hypersoftdev/TextCraft.svg)](https://jitpack.io/#hypersoftdev/TextCraft)

```
implementation com.github.hypersoftdev:TextCraft:x.x.x'
```

## Implementation

### XML Example:

```
<com.hypersoft.textcraft.TextCraft
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

## Attribute Summary

| Attribute                                                                   | Format           | Description                                                  |
|-----------------------------------------------------------------------------|------------------|--------------------------------------------------------------|
| `cornerRadius`                                                              | dimension        | Set corner radius for the view's background.                 |
| `headingText`                                                               | string           | Text for the heading.                                        |
| `headingColor`                                                              | color            | Color for the heading text.                                  |
| `headingSize`                                                               | dimension        | Font size for the heading text.                              |
| `headingUnderline`                                                          | boolean          | Enable/disable underline for the heading.                    |
| `headingUnderlineText`                                                      | string           | Apply underline to a specific substring in the heading.      |
| `headingStrikeThrough`                                                      | boolean          | Enable/disable strike-through effect for the heading.        |
| `strikeHeadingText`                                                         | string           | Apply strike-through to a specific substring in the heading. |
| `setGradientHead`                                                           | boolean          | Enable/disable gradient for the heading text.                |
| `gradientSpecificHeadText`                                                  | string           | Apply gradient to a specific substring in the heading.       |
| `headGradientStartColor`                                                    | color            | Start color for heading gradient.                            |
| `headGradientCenterColor`                                                   | color            | Center color for heading gradient.                           |
| `headGradientEndColor`                                                      | color            | End color for heading gradient.                              |
| `gradientOrientationHeadText`                                               | enum             | Set gradient orientation (horizontal, vertical, diagonal).   |
| `headingTextStyle`                                                          | enum             | Set heading text style (normal, bold, italic, boldItalic).   |
| `headingGravity`                                                            | enum             | Set gravity (start, center, end) for heading text.           |
| `subheadingText`, `paragraphText`                                           | string           | Similar attributes for subheading and paragraph sections.    |
| `rippleEnabled`                                                             | boolean          | Enable/disable ripple effect on text click.                  |
| `rippleColor`                                                               | color            | Set ripple color when enabled.                               |
| `strokeWidth`, `strokeColor`                                                | dimension, color | Set stroke width and color around the text view.             |
| `verticalText`                                                              | boolean          | Enable vertical text for the sections.                       |
| `verticalTextDirection`                                                     | enum             | Set vertical text direction (up or down).                    |
| `headingDrawableStart`, `subheadingDrawableStart`, `paragraphDrawableStart` | reference        | Set drawable at the start of each section.                   |
| `headingDrawableEnd`, `subheadingDrawableEnd`, `paragraphDrawableEnd`       | reference        | Set drawable at the end of each section.                     |

## Features

- **Customizable Headings, Subheadings, and Paragraphs**
    - Set text, color, size, underline, strike-through, text style (bold, italic), and gravity for each section.
    - **Heading Usage**
  ```
      app:headingText="@string/this_is_heading"
      app:headingColor="#219E40"
      app:headingSize="22sp"
      app:headingGravity="end"
      app:headingTextStyle="bold"
  ```
    - **SubHeading Usage**
  ```
      app:subheadingText="This is the Subheading"
      app:subheadingColor="@color/subheadingColor"
      app:subheadingSize="18sp"
      app:subheadingGravity="end"
      app:subheadingTextStyle="italic"
  ```
    - **Paragraph Usage**
  ```
      app:paragraphText="This is the Paragraph that provides additional information."
      app:paragraphColor="@color/paragraphColor"
      app:paragraphSize="16sp"
      app:paragraphGravity="end"
      app:paragraphTextStyle="boldItalic"
  ```
![screenshot](https://github.com/hypersoftdev/TextCraft/blob/master/Screens/Screen1.png?raw=true)

   - **Gradient Support**
        - Apply gradient effects separately to the heading, subheading, and paragraph sections and also sub sections of each.
        - Customize start, center, and end colors of the gradient.
        - Control gradient orientation (horizontal, vertical, diagonal) for each section.
        - **Heading Usage**
      ```
          app:setGradientHead="true"
          app:gradientOrientationHeadText="vertical" 
          app:gradientSpecificHeadText="Heading" <!-- leave it empty or dont use if want to apply on full -->
          app:headGradientStartColor="@color/white"
          app:headGradientCenterColor="@color/teal_200"
          app:headGradientEndColor="@color/subheadingColor"
      ```
        - **SubHeading Usage**
      ```
          app:setGradientSubHead="true"
          app:gradientOrientationSubHeadText="horizontal"
          app:gradientSpecificSubHeadText="This is the" <!-- leave it empty or dont use if want to apply on full -->
          app:subheadGradientStartColor="@color/orange"
          app:subheadGradientCenterColor="@color/black"
          app:subheadGradientEndColor="@color/orange"
      ```
        - **Paragraph Usage**
      ```
          app:setGradientParagraph="true"
          app:gradientSpecificParagraphText=""
          app:gradientOrientationParagraphText="horizontal"
          app:paragraphGradientStartColor="@color/black"
          app:paragraphGradientCenterColor="@color/teal_200"
          app:paragraphGradientEndColor="@color/black"
      ```

![screenshot](https://github.com/hypersoftdev/TextCraft/blob/master/Screens/Screen2.png?raw=true)

- **Strike-through and Underline**
    - Apply strike-through and underline effects to specific substrings in the heading, subheading, and paragraph sections.
    - **UnderLine**
        - **Heading Usage**
        ```
           app:headingUnderline="true"
           app:headingUnderlineText="is Heading"
        ```
        - **SubHeading Usage**
        ```
           app:subheadingUnderline="true"
           app:subheadingUnderlineText=""
        ```
        - **Paragraph Usage**
        ```
           app:paragraphUnderline="true"
           app:paragraphUnderlineText=""
        ```
![screenshot](https://github.com/hypersoftdev/TextCraft/blob/master/Screens/Screen3.png?raw=true)

   - **Strike-through**
        - **Heading Usage**
        ```
           app:headingStrikeThrough="true"
           app:strikeHeadingText="is Heading"
        ```
        - **SubHeading Usage**
        ```
           app:subheadingStrikeThrough="true"
           app:strikeSubheadingText="the"
        ```
        - **Paragraph Usage**
        ```
           app:paragraphStrikeThrough="true"
           app:strikeParagraphText="that provides"
        ```
![screenshot](https://github.com/hypersoftdev/TextCraft/blob/master/Screens/Screen4.png?raw=true)

- **Drawable Support**
    - Add drawable icons to the start, center, or end of any section (heading, subheading, paragraph).
    - **Heading Usage**
  ```
        app:headingDrawableStart="@drawable/ic_audio_player_premium"
        app:headingDrawableEnd="@drawable/ic_audio_player_premium"
  ```
    - **SubHeading Usage**
  ```
        app:subheadingDrawableStart="@drawable/ic_setting_privacy_policy"
        app:subheadingDrawableEnd="@drawable/ic_setting_privacy_policy"

  ```
    - **Paragraph Usage**
  ```
        app:paragraphDrawableStart="@drawable/left_shape"
        app:paragraphDrawableEnd="@drawable/right_shape"
  ```
    - **Drawable in Centre**
  ```
        val textCraft = findViewById<TextCraft>(R.id.ctv2)
        textCraft.addImage("placeholder", R.drawable.ic_bs_coins_in_text, imgWidth = 40, imgHeight = 40)
  ```

![screenshot](https://github.com/hypersoftdev/TextCraft/blob/master/Screens/Screen5.png?raw=true)
![screenshot](https://github.com/hypersoftdev/TextCraft/blob/master/Screens/Screen6.jpg?raw=true)

- **Ripple Effect**
    - Enable ripple effect on text click, with customizable ripple color.
    - **Usage**
  ```
        app:rippleEnabled="true"
        app:rippleColor="@color/headingColor"
  ```
  
![screenshot](https://github.com/hypersoftdev/TextCraft/blob/master/Screens/screen_gif.gif?raw=true)


- **Stroke, Background, and Corner Radius**
    - Set stroke width and color around the text view.
    - Apply gradient backgrounds (linear, radial, sweep) and control the corner radius for the view's background.
  - **Stroke**
  ```
        app:strokeColor="@color/trasnsparentOrange"
        app:strokeWidth="2dp"
  ```
  ![screenshot](https://github.com/hypersoftdev/TextCraft/blob/master/Screens/Screen8.PNG?raw=true)

    - **Background**
  ```
        app:gradientBackground="true"
        app:gradientStartColor="@color/teal_200"
        app:gradientCenterColor="@color/adsBgColor"
        app:gradientEndColor="@color/teal_700"
        app:gradientType="linear"
        app:gradientOrientation="bottom_left_top_right"
        app:gradientRadius="300"

  ```
  ![screenshot](https://github.com/hypersoftdev/TextCraft/blob/master/Screens/Screen9.png?raw=true)

    - **Corner Radius**
  ```
       app:cornerRadius="16dp"
  ```
![screenshot](https://github.com/hypersoftdev/TextCraft/blob/master/Screens/Screen10.PNG?raw=true)

- **Vertical Text and Direction**
    - Enable vertical text alignment for all sections and control its direction (up or down).
  ```
        app:verticalText="true"
        app:verticalTextDirection="down"
  ```

![screenshot](https://github.com/hypersoftdev/TextCraft/blob/master/Screens/Screen11.png?raw=true)
 

### Kotlin MaterialTextView Helpers

### 1. Strike-through with Localization

Applies a localized strike-through effect at the same position as in the English version of the text:

```
materialTextView.strikeThroughTextLocalized(R.string.full_text, "targetText")
```

### 2. Shade Text Color

Apply a gradient shading effect to text:

```
val colorArray = intArrayOf(
            resources.getColor(R.color.darkerGray, this.theme),
            resources.getColor(R.color.teal_200, this.theme),
            resources.getColor(R.color.darkerGray, this.theme),
        )
materialTextView.shadeTextColor("Gradient Text", colorArray)

```

### 3. Add Image in Center

Insert an image in place of text:

```
materialTextView.addImage("Insert Here", R.drawable.ic_image, imgWidth = 40, imgHeight = 40)

```

![screenshot](https://github.com/hypersoftdev/TextCraft/blob/master/Screens/Screen7.png?raw=true)

# Acknowledgements

This work would not have been possible without the invaluable contributions of **Nisar Bahoo**. His expertise, dedication, and unwavering support have been instrumental in bringing this project to fruition.

![screenshot](https://github.com/hypersoftdev/TextCraft/blob/master/Screens/profile_image.jpg?raw=true)

We are deeply grateful for Nisar Bahoo's involvement and his belief in the importance of this work. His contributions have made a significant impact, and we are honored to have had the opportunity to collaborate with him.

# LICENSE

Copyright 2023 Hypersoft Inc

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
