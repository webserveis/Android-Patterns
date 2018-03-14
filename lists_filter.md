Filtrar listas
```java
  private List<Banner> myCollectionA = new ArrayList<>();

  myCollectionA.add(new Banner("r01", "www.google.com", "en"));
  myCollectionA.add(new Banner("r01", "www.google.com", "es"));
  myCollectionA.add(new Banner("r02", "caminos de ronda", "es"));
  myCollectionA.add(new Banner("r03", "descubrir osona", "es"));
  myCollectionA.add(new Banner("r03", "Osona discover", "en"));
  myCollectionA.add(new Banner("r03", "Descobirr osona", "ca"));
  myCollectionA.add(new Banner("r02", "Camins de ronda", "ca"));
  myCollectionA.add(new Banner("r04", "Ruta del ferro", "ca"));
```

Filtrar por idioma

```java
  final List<Banner> myCollectionB = (List<Banner>) CustomPredicate.filter(myCollectionA,
          new CustomPredicate.IPredicate<Banner>() {
              public boolean apply(Banner objectOfA) {
                  return objectOfA.getLang().equals("es");
              }
          });

  final List<Banner> myCollectionC = (List<Banner>) CustomPredicate.filter(myCollectionA,
          new CustomPredicate.IPredicate<Banner>() {
              public boolean apply(Banner objectOfA) {
                  return objectOfA.getLang().equals("ca");
              }
          });

```
Obtener diferencias entre listas

```java
  List<Banner> missingObjects = (List<Banner>) CustomPredicate.filter(myCollectionB,
          new CustomPredicate.IPredicate<Banner>() {
              public boolean apply(Banner objectOfA) {
                  CustomPredicate.predicateParams = objectOfA.getRef();
                  return CustomPredicate.select(myCollectionC, new CustomPredicate.IPredicate<Banner>() {
                      public boolean apply(Banner objectOfB) {
                          return objectOfB.getRef().equals(CustomPredicate.predicateParams.toString());
                      }
                  }) == null;
              }
          });
```


CustomPredicate.java

```java
package com.webserveis.app.testlist;

import java.util.ArrayList;
import java.util.Collection;

public class CustomPredicate<B> {
    public static Object predicateParams;

    public interface IPredicate<T> { boolean apply(T type); }

    public static <T> Collection<T> filter(Collection<T> target, IPredicate<T> predicate) {
        Collection<T> result = new ArrayList<T>();
        for (T element : target) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public static <T> T select(Collection<T> target, IPredicate<T> predicate) {
        T result = null;
        for (T element : target) {
            if (!predicate.apply(element))
                continue;
            result = element;
            break;
        }
        return result;
    }

    public static <T> T select(Collection<T> target, IPredicate<T> predicate, T defaultValue) {
        T result = defaultValue;
        for (T element : target) {
            if (!predicate.apply(element))
                continue;
            result = element;
            break;
        }
        return result;
    }
}
```

