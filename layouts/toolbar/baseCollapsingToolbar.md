

Layout Mainactivity.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        tools:context=".MainActivity">

    <com.google.android.material.appbar.AppBarLayout
            android:id="@+id/app_bar"
            android:layout_height="280dp"
            android:fitsSystemWindows="true"
            android:layout_width="match_parent"
            android:theme="?actionBarTheme">

        <com.google.android.material.appbar.CollapsingToolbarLayout
                android:id="@+id/toolbar_layout"
                android:fitsSystemWindows="true"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                app:toolbarId="@+id/toolbar"
                app:titleEnabled="false"
                app:layout_scrollFlags="scroll|exitUntilCollapsed"
                app:contentScrim="?attr/colorPrimary">

            <androidx.appcompat.widget.Toolbar
                    android:id="@+id/toolbar"
                    android:layout_width="match_parent"
                    android:layout_height="?attr/actionBarSize"
                    app:layout_collapseMode="pin"
                    android:background="?attr/colorPrimary"
                    app:popupTheme="?actionBarPopupTheme"/>

        </com.google.android.material.appbar.CollapsingToolbarLayout>
    </com.google.android.material.appbar.AppBarLayout>

    <include layout="@layout/content_scrolling"/>

    <com.google.android.material.floatingactionbutton.FloatingActionButton
            android:id="@+id/fab"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:layout_anchor="@id/app_bar"
            app:layout_anchorGravity="bottom|end"
            android:layout_margin="@dimen/fab_margin"
            app:srcCompat="@drawable/ic_add_24dp"/>

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

Layout content_scrolling.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.core.widget.NestedScrollView
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"
        tools:showIn="@layout/activity_scrolling"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".ScrollingActivity">

    <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="@dimen/text_margin"
            android:text="@string/large_text"/>

</androidx.core.widget.NestedScrollView>
```
