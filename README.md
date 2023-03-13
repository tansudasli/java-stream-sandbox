# java fundamentals - streams

This project is about 

- Collections API
- Stream API
- Collectors API
- Functional API 
- parallelStreams

## Stream concepts

<details>
<summary>Collections API ::: General combinations</summary>

- not every combination makes sense 
- some combinations are abstract, so you must implement

```
    Concurrent | Linked     |  Array   | List
                 Navigable  |  Hash    | Map
                 Priority   |  Tree    | Set
                                       | Queue
                                       | Table
Basicaly the meanings                                     
- concurrent = thread-safe
- Array = index-based, better random access
- Linked = doubly-linked nodes, order preserved
- hash = hashed

LinkedHashMap = order-oreserved + hashed into buckets + k,v based store
                                       
```
```
Performances
             insert    delete    get
- Array        O(n)      O(n)    O(1)  
- Linked       O(1)      O(1)    O(n)
- Map          O(1)      O(1)    O(1)    
```

</details>

<details>
<summary>Collections API ::: Map</summary>

- Map means, _one key = one value_, But if you put a `List<V>`, you can hold **many values for one key**
- default initial bucket = 16, load factor 0.75
- if (size = lf * initial) -> resize *= 2
- use .entrySet().stream() for streaming
- no duplicate key

```
  - Map<k, v>, unsorted, no-null-key                  
  - HashMap, unsorted, null-key,                      
  - LinkedHashMap, insert-order, null-key             
  - ConcurrentHashMap, no-null-key, trade-safe        
  - TreeMap, sorted natural-order or Comparator-based 

```
</details>

<details>
<summary>Get a stream from Regex</summary>

- `Stream<String>  xx =  Pattern.compile("\\w+).matcher("source").results()`

</details>
<details>
<summary> Get a stream from File</summary>

- define `PATH = "..." as absolute path`
- `Stream<String> xx =  Files.lines(Paths.get(PATH))`

</details>
<details>
<summary> Get a stream from int[] ::: primitives (Arrays.stream)</summary>

- `IntStream<Integer> xx =  Arrays.stream(int[])`
- then `.boxed` to get `Stream<Integer>`, if you need!

</details>
<details>
<summary> Get a stream from Collections ::: wrappers (.stream)</summary>

- `List<Integer> xx = new ArrayList<>(Arrays.asList(1, 3, 4))`
- `Stream<Integer> yy = xx.stream()`


</details>
<details>
<summary>Threading ::: in java-streams</summary>

 - if it is not parallel, same thread, sequential (beginning2end), gets a person then completes all-intermediate-steps.
   And, repeats the same as person and other tasks

    ```
    1 thread

        1 time  (so no need for combiner)  !! no need combiner (never enters addAll step)
           collect:::new

        N times
                  sequentially
           map:::      ->      collect:::add

        N times, last step. terminal ops. sequential
           forEach:::
    ```

</details>
<details>
<summary>Threading ::: in java-parallelStreams</summary>

 - If it is parallel, leverages multi-threads, and all intermediate-steps can be happened in any order!.
 So, combiner is must!.

   ```
   N thread
       N times
           collect::new (no optimization, which is interesting, that's why we need combiner!) -> collect::map
           -> collect::new  -> collect::new  -> collect::map
           -> collect::add ......... -> collect::addAll
           -> collect::new ......

   1 Thread
        @last step, foreach steps,  (terminal operation), sequential
   ```

</details>
<details>
<summary>Methods ::: Stream API vs Collectors API </summary>

some critical things changes the methods we may see!
- min vs Collectors.minBy
- Stream<T> vs Stream<T extends Integer> (clipping to an integer type)
- IntStream vs Stream<Integer>
- Transformation: IntStream -> boxed() -> Stream<Integer>
- `stream().map()` is the same as `collect(Collectors.toMap()`
- map (one by one mapping) vs Collectors.groupingBy (One by List mapping, so in downstream you may reduce it T also) 
- (:Map<T extends Integer, List<T>)
- if you have Map<k, v>, use `.entrySet().stream()` to do computing

```
   .stream()
   .map(::getIntegerTypeSomething)       //Stream<Integer>
   .min(Comparator.naturalOrder())
  ---
   .stream()
   .map(::getIntegerTypeSomething)        //Stream<Integer>
   .collect(Collectors.minBy(Comparator.naturalOrder()))
```

```
   .stream()
   .mapToInt(::getIntegerTypeSomething)   //IntStream
   .summaryStatistics()
   ---
   .stream()
   .collect(Collectors.summarizingInt(::getIntegerTypeSomething))
```


</details>

## how to run

- `./gradlew clean build jar jmhJar`
 