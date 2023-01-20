# gradle fundamentals - multi module


Project scope and java command scopes have different things!

Some key points to consider are:
 - gradle run vs java jar or java -cp (where leads no main manifest attribute)
 - multiple modules vs one module
 - handling central gradle staffs vs keep everything separated


- [x] if you use java commands: fp, depends on the _core_ project jar. so add this jar, too. 
  - `java -cp core/build/libs/core-1.0-SNAPSHOT.jar:fp/build/libs/fp-1.0-SNAPSHOT.jar org.example.Main`
  - , or put the core jar into a local rep.m, then let gradle get from there !
                               
- [x] if, you use gradle commands 
  - `./gradlew :core:clean  && ./gradlew :core:build`, then 
  - `./gradlew :fp:clean  && ./gradlew :fp:build`, then
  - `./gradlew :fp:run` to run, 

When you use multi-modules in Gradle, there may be need some orchestration parts in the central _build.gradle_ file(s)! 
This does not come with automatically.
 