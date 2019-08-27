# Plantilla básica con SearchView
Plantilla base con barra de herramientas con un cuadro de busqueda permamente debajo de la acciones principales.

 - Activity
 - AppBar
 - SearchvView
 - NestedScroller


### PLANTILLA XML
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        tools:context=".MainActivity">

    <com.google.android.material.appbar.AppBarLayout
            android:id="@+id/app_bar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:theme="?actionBarTheme">

        <androidx.appcompat.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                app:layout_scrollFlags="scroll|snap|enterAlways"
                app:popupTheme="?actionBarPopupTheme" />


        <androidx.appcompat.widget.SearchView
                android:id="@+id/search_view"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="4dp"
                android:background="@drawable/bg_rounded_search"
                android:imeOptions="actionSearch|flagNoExtractUi"
                android:theme="@style/ThemeOverlay.MaterialComponents.Light"
                app:iconifiedByDefault="false"
                app:queryHint="@string/search"
                app:searchIcon="@null" />

    </com.google.android.material.appbar.AppBarLayout>

    <androidx.core.widget.NestedScrollView
            android:id="@+id/view_pager"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior" />

</androidx.coordinatorlayout.widget.CoordinatorLayout>

```
