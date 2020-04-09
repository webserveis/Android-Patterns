# Extenciones de Kotlin
Kotlin facilita la vida del desarrollador de Android. La funcionalidad de extensión de Kotlin es una de mis características favoritas, evita tener que escribir código repetitivo y usar código re forma regular.

 - [Context](#context)
 - Activity
 - Fragment
 - ViewGroup
 - View
 - String
 - NavControler
 - Drawable


## <a name="context"></a> Context
Extenciones de Kotlin que parten de Context

Context.toast Métodos de extensión para mostrar un mensaje toast en pantalla
```kotlin
/**
 * Método de extensión para mostrar brindis por contexto.
 */
fun Context?.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) = this?.let { Toast.makeText(it, text, duration).show() }
 
/**
 * Método de extensión para mostrar brindis por contexto.
 */
fun Context?.toast(@StringRes textId: Int, duration: Int = Toast.LENGTH_SHORT) = this?.let { Toast.makeText(it, textId, duration).show() }
```
[Ejemplo de uso](use_context_toast.md)

Context.vibrate Método de extensión para hacer vibrar el dispositivo.

```kotlin
/**
 * Método de extensión para hacer vibrar.
 */
fun Context.vibrate(pattern: LongArray = longArrayOf(0, 150)) {
    val vibrator =
        applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator? ?: return
 
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator.vibrate(
            VibrationEffect.createWaveform(pattern, VibrationEffect.DEFAULT_AMPLITUDE)
        )
 
    } else {
        @Suppress("DEPRECATION")
        vibrator.vibrate(pattern, -1)
    }
}
```

Context.getColorCompat Método de extensión para proporcionar un acceso más simple para obtener el color de un recurso a partir de us ID

```kotlin
/**
 * Método de extensión para obtener el color del resource.
 */
fun Context.getColorCompat(@ColorRes id: Int) = ContextCompat.getColor(this, id)
```
