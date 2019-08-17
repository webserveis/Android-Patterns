# Patrón SingleLiveEvent

**Android Architecture Components - Kotlin - AndroidX**

Cuando usamos programación reactiva/ funcional y usamos los observadores de datos, pueda que necesitamos que el observador al recibir los datos no vuelva a recibirlos si se rota el dispositivo, se puede usar para informar el usuario de algo, para obtener ese resultado debemos implementar el patrón **SingleLiveEvent**

Archivo SingleLiveEvent.kt
```kotlin
open class SingleLiveEvent<out T&gt;(private val content: T) {
 
    var hasBeenHandled = false
        private set // Allow external read but not write
 
    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
 
    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}
```

### Declaración en ViewModel 
Dentro del viewmodel debemos implementar el SingleLiveEvent

```kotlin
//Notify Single SingleLiveEvent
private val _notifyEvent = MutableLiveData<SingleLiveEvent<String>>()
val notifyEvent: LiveData<SingleLiveEvent<String>>
   get() = _notifyEvent
```

### Observador del SingleLiveEvent
```kotlin
mViewModel.notifyEvent.observe(this, Observer { it ->
    it.getContentIfNotHandled()?.let {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }
})
```

### Notificar un Evento
Para notificar un evento solo es necesario cambiar el valor de la variable que hemos declarado con SingleLiveEvent en este caso _notifyEvent
```kotlin
_notifyEvent.value = SingleLiveEvent("tarea finalizada")
```
o bien usar dentro de coroutines
```kotlin
_notifyEvent.postValue(SingleLiveEvent("procesando datos"))
```
