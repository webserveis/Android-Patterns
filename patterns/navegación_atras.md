Según la documentación de Google sobre [patrones de navegación en Material Design][1], debemos dar la posibilidad al usuario poder volver hacia atrás, mediante un botón a la barra del título o bien usando el botón (físico,virtual) del propio Sistema Android, [más información sobre patrón de navegación hacia atrás][2].

![alt text][3]
Esquema del patrón básico de navegación entre actividades.

## Añadir el Botón UP
![alt text][4]

El botón `UP` es el icono de flecha que muchas apps introducen en las actividades para realizar la acción volver a la actividad anterior.


    if (getSupportActionBar() != null) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



### Detectar su pulsación
Código `java` para detectar la pulsación del Back UP Button:


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



### Modificar su aspecto
Podemos cambiar el símbolo de la flechita por otro

    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);


o bien usando `style.xml`

    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <item name="android:homeAsUpIndicator">@drawable/ic_close</item>
    </style>



## Interceptar evento Ir hacia atrás
![alt text][5]

Es posible interceptar la acción ir hacia atrás con el evento `onBackPressed`, este evento es lanzado cuando se realiza la pulsación:

 - **Botón UP** dentro de la aplicación.
 - **Botón virtual** en la barra de navegación del sistema Android.
 -  **Botón físico** ir hacia atrás.


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //código al pulsar ir hacia atrás
    }



Con la función `onBackPressed()` podemos iniciar la acción ir hacia atrás des de cualquier parte.

## Establecer patrón de navegación
![alt text][6]

Si queremos forzar que al pulsar sobre el `botón UP` su regreso sea una determinada activity, en el archivo `AndroidManifest.xml` se debe especificar `android:parentActivityName` la actividad de regreso y también en `meta-data`.


      <activity
            android:name=".AActivity"
            android:label="@string/title_activity_main2"
            android:parentActivityName=".MainActivity"
            android:theme="@style/AppTheme.NoActionBar">
            <meta-data
                android:name="android.support.PARENT_ACTIVITY"
                android:value="com.webserveis.app.testpatternnavigation.MainActivity" />
        </activity>
    


Solo con especificar eso, al pulsar sobre el `botón UP` lo hará automáticamente, en caso interceptar la acción con `onOptionsItemSelected` deberemos hacer uso de `NavUtils.navigateUpFromSameTask(this)` para delegar la acción.


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Log.i(TAG, "onOptionsItemSelected: ");
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



## Prevenir evento onBackPressed
Si deseamos que al interceptar la acción ir hacia atrás no realice ninguna acción.


    @Override
    public void onBackPressed() {  }



  [1]: https://material.google.com/patterns/navigation.html#navigation-defining-your-navigation
  [2]: https://developer.android.com/design/patterns/navigation.html
  [3]: https://developer.android.com/design/media/navigation_up_vs_back_gmail.png
  [4]: http://i.imgur.com/yEXdmG2.png
  [5]: http://i.stack.imgur.com/9Qwwt.png
  [6]: https://developer.android.com/design/media/navigation_between_siblings_market2.png



