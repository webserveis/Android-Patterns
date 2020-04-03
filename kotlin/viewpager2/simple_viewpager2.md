# Usar ViewPager version 2 Android Kotlin

ViewPager es uno de los componentes más utilizados en Android. Tiene su funcionalidad única de deslizar entre páginas. Las vistas se usa Fragment ya que administra el ciclo de vida de cada página por sí mismo.

La segunda revisión de ese componente ViewPager2, incorpora nuevas funcionalidades de uso, para más información Documentación oficial de ViewPager2 y ViewPager2

### Novedades
Mejoras de la implementación anterior de ViewPager:

 - Compatibilidad con el diseño de derecha a izquierda (RTL)
- Compatibilidad con orientación vertical
- Compatibilidad con Fragment support confiable (incluido el control de cambios en la colección Fragment subyacente).
- Animaciones para cambios en conjuntos de datos (incluida la compatibilidad con DiffUtil).
- Permite deshabilitar el gesto de deslizar entre vistas
- Declaración en el Layout

```xml
<androidx.viewpager2.widget.ViewPager2
     android:id="@+id/viewPager"
     android:layout_width="match_parent"
     android:layout_height="match_parent" />
```

### Creando ViewPagerAdapter
Ahora se tiene que crear el adaptador de las vistas

```kotlin
private inner class CustomPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()
 
    override fun getItemCount(): Int {
        return mFragmentList.size
    }
 
    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }
 
    fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }
 
    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
 
}
```
### Inicialización
Donde queremos usar el viewPager2 inicializar como el antiguo

```kotlin
val pagerAdapter = CustomPagerAdapter(this)
pagerAdapter.addFragment(Fragment1(), getString(R.string.fragment1_title))
pagerAdapter.addFragment(Fragment2(), getString(R.string.fragment2_title))
pagerAdapter.addFragment(Fragment3(), getString(R.string.fragment3_title))
 
viewPager.adapter = pagerAdapter
```

### Interceptar el cambio de vista
Si necesitamos detectar que vista se ha cambiado se realiza con el registerOnPageChangeCallback

```kotlin
viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        //@Todo posición actual con position
    }
}
```

### Deshabilitar el gesto deslizar en ViewPager2
Si se quiere deshabilitar el gesto de deslizar en el ViewPager2, se hace con el método setIsUserInputEnabled

```kotlin
viewPager.isUserInputEnabled = false
```
