## How to run

## JDK-8

In this example, we compare old/new JDK-8 memory behavior inside containers.  
This proves the older JVM does not respect the container memory allocation.

### Expected outtput:

Old:
```
## Run docker image (not limiting container memory)
openjdk version "1.8.0_92-internal"
OpenJDK Runtime Environment (build 1.8.0_92-internal-alpine-r1-b14)
OpenJDK 64-Bit Server VM (build 25.92-b14, mixed mode)
Initial Memory (Xms) : 196mb
Max Memory (Xmx) : 2775mb

## Run docker image (limiting container memory to 1gb)
openjdk version "1.8.0_92-internal"
OpenJDK Runtime Environment (build 1.8.0_92-internal-alpine-r1-b14)
OpenJDK 64-Bit Server VM (build 25.92-b14, mixed mode)
Initial Memory (Xms) : 196mb
Max Memory (Xmx) : 2775mb
```
New:
```
## Run docker image (not limiting container memory)
openjdk version "1.8.0_212"
OpenJDK Runtime Environment (IcedTea 3.12.0) (Alpine 8.212.04-r0)
OpenJDK 64-Bit Server VM (build 25.212-b04, mixed mode)
Initial Memory (Xms) : 196mb
Max Memory (Xmx) : 2775mb

## Run docker image (limiting container memory to 1gb)
openjdk version "1.8.0_212"
OpenJDK Runtime Environment (IcedTea 3.12.0) (Alpine 8.212.04-r0)
OpenJDK 64-Bit Server VM (build 25.212-b04, mixed mode)
Initial Memory (Xms) : 16mb
Max Memory (Xmx) : 247mb
```

## JDK-11

Java memory eating program (*will consume memory in chunks of 10mb until app goes down*)

* When **Xmx < ContainerMemory**: Java will throw an exception: OutOfMemoryError
* When **Xmx > ContainerMemory**: Container gets killed

```
## Run docker image (ok) >> docker run --rm -ti --memory=256m -e JAVA_OPTS=-Xms50M -Xmx50M myjdk11
openjdk version "11.0.10" 2021-01-19
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.10+9)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.10+9, mixed mode)
## 1    Used Memory   : 11 mb   Free Memory   : 36 mb   Total Memory  : 48 mb   Max Memory    : 48 mb
## 2    Used Memory   : 20 mb   Free Memory   : 27 mb   Total Memory  : 48 mb   Max Memory    : 48 mb
## 3    Used Memory   : 30 mb   Free Memory   : 17 mb   Total Memory  : 48 mb   Max Memory    : 48 mb
## 4    Used Memory   : 40 mb   Free Memory   : 7 mb    Total Memory  : 48 mb   Max Memory    : 48 mb
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at StressMemory.consume(StressMemory.java:20)
        at StressMemory.main(StressMemory.java:8)

## Run docker image (nok) >> docker run --rm -ti --memory=50m -e JAVA_OPTS=-Xms256M -Xmx256M myjdk11
openjdk version "11.0.10" 2021-01-19
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.10+9)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.10+9, mixed mode)
## 1    Used Memory   : 14 mb   Free Memory   : 233 mb  Total Memory  : 247 mb  Max Memory    : 247 mb
## 2    Used Memory   : 24 mb   Free Memory   : 223 mb  Total Memory  : 247 mb  Max Memory    : 247 mb
## 3    Used Memory   : 34 mb   Free Memory   : 213 mb  Total Memory  : 247 mb  Max Memory    : 247 mb
## 4    Used Memory   : 44 mb   Free Memory   : 203 mb  Total Memory  : 247 mb  Max Memory    : 247 mb
## 5    Used Memory   : 54 mb   Free Memory   : 193 mb  Total Memory  : 247 mb  Max Memory    : 247 mb
## 6    Used Memory   : 64 mb   Free Memory   : 183 mb  Total Memory  : 247 mb  Max Memory    : 247 mb
Killed
```