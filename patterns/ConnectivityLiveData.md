# Detectar la conectividad de Internet con LiveData en Kotlin Android
Si se requiere detectar la conexión a internet permamente y así tener un control cuando se pierde la conexión y informar el usuario, usando los componentes de arquitectura de Android combinando viewModel con LiveData

### Creando ConnectivityLiveData

Usando la nuevas API `ConnectivityManager.NetworkCallback()` para obtener la conectividad en combinación con `LiveData` (de Android Architecture Components) podemos crear una maravillosa clase observable para proporcionar esta devolución de llamada de cambio de conectividad.

Archivo original de ConnectivityLiveData extraído de aquí la modificación es que al detectar conexión a una red, comprueba si hay conexión saliente a internet, mediante ping a un servidor google

```kotlin
class ConnectivityLiveData internal constructor(private val connectivityManager: ConnectivityManager) :
    LiveData<Boolean>() {

    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    constructor(application: Application) : this(
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    )

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network?) {
            postValue(isInternetOn())
        }

        override fun onLost(network: Network?) {
            postValue(false)
        }

    }

    override fun onActive() {
        super.onActive()
        postValue(isInternetOn())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val builder = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
        }
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    fun isNetworkIsAvailable(): Boolean {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

    fun isInternetOn(): Boolean {
        if (isNetworkIsAvailable()) {
            try {
                val p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com")
                val value = p.waitFor()
                return value == 0

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }
}
```

### Implementando en un ViewModel

El `ViewModel` debe extenderse de `AndroidViewModel` de esa forma se puede obtener el contexto de la aplicación necesario para obtener el `ConnectivityManager`

```kotlin
class MyViewModel(application: Application) : AndroidViewModel(application) {
 
    val connectivity: LiveData<Boolean>
 
    init {
        connectivity = ConnectivityLiveData(application)
    }
```

### Observando el ConnectivityLiveData
Por último debemos crear el observador del estado de conectividad

```kotlin
private val mViewModel: IpController by lazy {
    ViewModelProviders.of(this).get(MyViewModel::class.java)
}
...
mViewModel.connectivity .observe(this, Observer {
 
    it?.let {
        if (it) {
            Log.d(TAG, "Internet ON")
        } else {
            Log.w(TAG, "Internet OFF")
        }
    }
 
 
})
```

### Enlaces
Apunte creado a base de https://android.jlelse.eu/connectivitylivedata-6861b9591bcc

