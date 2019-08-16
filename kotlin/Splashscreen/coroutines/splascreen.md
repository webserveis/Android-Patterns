Usando coroutines

## Dependencias
```gradle
    implementation 'androidx.core:core-ktx:1.0.2'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.0'
```

### Diseñando la Splashscreen
La splashscreen se muestra mediante un activity

Archivo AndroidManifest.xml
```xml
<activity android:name=".SplashScreen"
          android:theme="@style/AppTheme.SplashScreen">
```

El fondo a mostrar en la splashscreen se define al archivo drawable/background_splash.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:opacity="opaque" >

    <!-- The background color, preferably the same as your normal theme -->
    <item android:drawable="@color/colorSplashScreen"/>

    <item><!-- android:top="-48dp" -->
        <bitmap
            android:src="@drawable/splashscreen"
            android:gravity="center" />
    </item>
</layer-list>
```
