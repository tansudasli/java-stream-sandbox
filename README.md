# java fundamentals - streams

This project is about 

- functional programming
- streams
- parallelStreams
- Optional
- functional interfaces 

## Stream concepts

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

## how to run

- `./gradlew clean build jar jmhJar`
 