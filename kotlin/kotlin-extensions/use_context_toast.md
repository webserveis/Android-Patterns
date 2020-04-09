# Usar extención de Kotlin Context.Toast


### Método de extención Context.Toast
```kotlin
fun Context?.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) = this?.let { Toast.makeText(it, text, duration).show() }
fun Context?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_SHORT) = this?.let { Toast.makeText(it, textId, duration).show() }
```

### Su uso
```kotlin
//Desde un Activity
toast("Toast Message")
toast(R.string.recurso_string)

//Desde un fragment
context?.toast("Toast Message")
context?.toast(R.string.recurso_string)
```

Para cambiar la duración del mensaje toast en pantalla
```
toast("Toast Message", Toast.LENGTH_LONG)
```
