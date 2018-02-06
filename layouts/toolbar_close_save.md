### Plantilla

![introducir la descripción de la imagen aquí][2]

### Recursos
El mismo Android studio genera el icono `close` se debe canbiar su color `#FFFFFF`

### Configuración
Modificar el `AndroidManifest.xml`

Asignar el padre de la actividad en este caso `MainActivity`

```xml
<activity
  android:name=".EditActivity"
  android:label="@string/title_activity_edit"
  android:parentActivityName="com.sample.EditActivity"
  android:parentActivityName=".MainActivity"
  android:theme="@style/AppTheme.NoActionBar">
  <meta-data
    android:name="android.support.PARENT_ACTIVITY"
    android:value="com.sample.MainActivity" />
        
</activity>
```
Si no deseamos que al regresar a la actividad padre se recre de nuevo, debemos asignar `android:launchMode="singleTop"` en la actividad padre.

**Layout**

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.webserveis.app.servermonitortools.AddServerActivity">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/app_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/AppTheme.AppBarOverlay">

        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            app:popupTheme="@style/AppTheme.PopupOverlay" />

    </android.support.design.widget.AppBarLayout>

</android.support.constraint.ConstraintLayout>
```

**Menu**

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:myapp="http://schemas.android.com/apk/res-auto">
    <item android:id="@+id/action_save"
        android:title="@string/menu_save"
        myapp:showAsAction="always|withText" />
</menu>
```

**Código de la actividad**

```java
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_server);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        toolbar.setTitle(getTitle());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_save:
                Toast.makeText(this, "Save action", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
```
[2]: https://i.stack.imgur.com/NHw9K.png
