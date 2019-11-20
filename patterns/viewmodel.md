# apuntes de ViewModel de Android

Implementación de ViewModel de los componentes de arquitectura de Android (Jetpack)


**MyViewModel.kt**
```kotlin
class MyViewModel : ViewModel() {

    var countSayHello: MutableLiveData<Int> = MutableLiveData(0)

    fun getWelcomeCount(): LiveData<Int> {
        return countSayHello
    }

    fun sayHelloWorld() {
        countSayHello.postValue(countSayHello.value?.inc())
    }

}
```

**MainActivity.kt**
```kotlin
class MainActivity : AppCompatActivity() {

    private val mViewModel: MyViewModel by lazy {
        ViewModelProviders.of(this).get(MyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mViewModel.getWelcomeCount().observe(this, Observer<Int> {
            val outText = "Hello world! $it"
            tv_display.text = outText
        })

        fab.setOnClickListener { view ->
            toast("Hello world!")
            mViewModel.sayHelloWorld()
        }
    }

    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}
```

**content_main.xml**
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    tools:context=".MainActivity"
    tools:showIn="@layout/activity_main">

    <TextView
        android:id="@+id/tv_display"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
