# Java exercises and practice projects 

This project is a Java implementation of a design methodology and program notation that I am personally interested in.

# Requirement

* Java SE 17

# Feature in repository
## Promise

  Promise implementation added in ECMA 2015 by java
```
Promise<Integer> promise = Promise.resolve(() -> 1)
    .then(i -> i.toString() + "2")
    .then(str -> Integer.parseInt(str) * 3)
    .onSuccess(System.out::println) // Output 36 in your console
    .onFailure(Throwable::printStackTrace); // Not executed
```
https://github.com/reivosar/java/blob/main/common/src/main/java/reivosar/common/util/promise/Promise.java

## Cache

  Programs for thread-safe caching
```
Cache<String, String> cache = CacheFactory.getEternalLocalCache();
cache.put("key", "value1");
cache.put("key", "value2");

assertEquals(Optional.of("value1"), cache.get("key").first());
assertIterableEquals(List.of("value1", "value2"), cache.get("key").all());
assertEquals(2, cache.get("key").count());
```
https://github.com/reivosar/java/blob/main/common/src/main/java/reivosar/common/util/cache/Cache.java

## PDF Creator

  Programs for generating PDFs using PDF Box
```
final Path path = ～
final boolean result = PdfCreator.forCreatingNew()
                .append(0, 0f, 755f, 200, 100, 12, "center", "test")
                .createTo(outputPath);
```
https://github.com/reivosar/java/blob/main/common/src/main/java/reivosar/common/util/io/pdf/creator/PdfCreator.java
