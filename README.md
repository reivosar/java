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

## PDF Creator

  Programs for generating PDFs using PDF Box
```
final Path path = ～
final boolean result = PdfCreator.forCreatingNew()
                .append(0, 0f, 755f, 200, 100, 12, "center", "test")
                .createTo(outputPath);
```
https://github.com/reivosar/java/blob/main/common/src/main/java/reivosar/common/util/io/pdf/creator/PdfCreator.java
