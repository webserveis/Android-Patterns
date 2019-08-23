# Animación de barra de herramientas con AndroidX
Demostración de los diferentes tipos de desplazamiento (scroll) de la barra de herramientas en vistas de android.

Para animar la barra de herramientas se hace con la propiedad app:layout_scrollFlags y puede tener los siguientes valores y también combinados usando el carácter | como separador

## Scroll
Con está bandera, al realizar un desplazamiento hacia arriba para mostrar más contenido, la barra de herramienta se ocultará, volverá a aparecer justo cuando volvemos al inicio del contenido


Código de ejemplo
```xml
<com.google.android.material.appbar.AppBarLayout
        android:id="@+id/app_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="?actionBarTheme">

    <androidx.appcompat.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:layout_scrollFlags="scroll"
            app:popupTheme="?actionBarPopupTheme" />

</com.google.android.material.appbar.AppBarLayout>
```
## EnterAlways
Con esa bandera al realizar un desplazamiento de mostrar más contenido se ocultará la barra de herramientas y volverá aparecer cuando se haga el desplazamiento inverso


Código de ejemplo
```xml
<com.google.android.material.appbar.AppBarLayout
    android:id="@+id/app_bar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:theme="?actionBarTheme">

<androidx.appcompat.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        app:layout_scrollFlags="scroll|enterAlways"
        app:popupTheme="?actionBarPopupTheme" />

</com.google.android.material.appbar.AppBarLayout>
```
## Snap
Esta bandera proporciona un comportamiento de desplazamiento “pegajoso”. Según cuando hacemos scroll y la parte visible sea suficiente se mostrará la barra de herramientas al completo, como un efecto imantado.

```xml
<com.google.android.material.appbar.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="?actionBarTheme">

    <androidx.appcompat.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:layout_scrollFlags="scroll|enterAlways|snap"
            app:popupTheme="?actionBarPopupTheme" />

    <com.google.android.material.tabs.TabLayout
            android:id="@+id/tabs"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:tabTextColor="?attr/actionMenuTextColor"
            android:background="?attr/colorPrimary" />
</com.google.android.material.appbar.AppBarLayout>
```
## EnterAlwaysCollapsed
Se debe usar con combinación con la bandera enterAlways y sobre un CollapsingToolbarLayout , al hacer desplzamiento inverso se mostrará la barra de herramientas en su tamaño mínimo (Collapsed) y si nos deslizamos al inicio del contenido se expenderá .

```xml
<com.google.android.material.appbar.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="180dp"
        android:theme="?actionBarTheme">

    <com.google.android.material.appbar.CollapsingToolbarLayout
            android:id="@+id/toolbar_layout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:fitsSystemWindows="true"
            app:contentScrim="?attr/colorPrimary"
            app:layout_scrollFlags="scroll|enterAlways|enterAlwaysCollapsed"
            app:toolbarId="@+id/toolbar">

            <ImageView
                    android:id="@+id/header"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:fitsSystemWindows="true"
                    android:scaleType="centerCrop"
                    android:src="@drawable/landscape_light"
                    app:layout_collapseMode="parallax" />

            <androidx.appcompat.widget.Toolbar
                    android:id="@+id/toolbar"
                    android:layout_width="match_parent"
                    android:layout_height="?attr/actionBarSize"
                    app:layout_collapseMode="pin"
                    app:popupTheme="?actionBarPopupTheme" />

        </com.google.android.material.appbar.CollapsingToolbarLayout>
    </com.google.android.material.appbar.AppBarLayout>
   ```
