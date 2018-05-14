Crear una Splashscreen en Kotlin de Android


Archivos
TaskModel.kt
SplashScreenActivity.kt
SplashScreenViewModel.kt 

Styles

```xml
    <style name="AppTheme.SplashTheme" parent="@android:style/Theme.NoTitleBar.Fullscreen">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
        <item name="android:windowFullscreen">true</item>
        <item name="android:windowEnterAnimation">@android:anim/fade_in</item>
        <item name="android:windowBackground">@drawable/splashscreen_bg</item>
    </style>
```

hacer refrencia en manifest con `android:theme="@style/AppTheme.SplashTheme"`
