# Patrón Resource LiveData

Usaremos el patrón de diseño Resource que parte de un LiveData es decir que se deberá usar dentro de un ViewModel, los canales para distribuir los datos son 3

 - LOADING: Para cuando un proceso se este ejecutando
 - ERROR: Para cuando un proceso ha finalizado con error
 - SUCCES: Los datos que han finalizado
 
 **Resource.kt**
 ```kotlin
 class Resource<T> private constructor(val status: Status, val data: T?, val exception: AppException?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }
        fun <T> error(exception: AppException?): Resource<T> {
            return Resource(
                Status.ERROR,
                null,
                exception
            )
        }
        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}
  ```
  
### Declaración de datos con tipo Resource
Dentro del `ViewModel` se debe especificar que el retorno de datos viene encapsulado con `Resource`

```kotlin
val mResultData = MutableLiveData<Resource<List<String>>>()
```

### Especificación de canal de distribución
Dependiendo del momento los datos requieren usar un canal de distribucción especifico

**LOADING**

```kotlin
mResultData.postValue(Resource.loading(null)))
```

**SUCCESS**
```kotlin
mResultData.postValue(Resource.success(list))
```

### Observando datos

```kotlin
private val changeObserver2 = Observer<Resource<List<String>>> { value ->
    value?.also {
        when (value.status) {
            Resource.Status.LOADING -> {
                Log.d(TAG, "Loading...." + value.data)
            }
            Resource.Status.SUCCESS -> {
                Log.d(TAG, "Success....")
                Log.d(TAG, value.data.toString())
            }
            Resource.Status.ERROR -> {
                Log.d(TAG, "Error....")
            }
        }
    }
}
...
mViewModel.mResultData.observe(this, changeObserver)
```
### Código fuente
Código de implmentación: https://gist.github.com/webserveis/9d1057f93062e49b0a853a165c67ae42

