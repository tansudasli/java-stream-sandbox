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
<summary>Threading ::: in streams</summary>

- ...

</details>

<details>
<summary>Threading ::: in parallelStreams</summary>

- ....

</details>

## how to run

- `./gradlew clean build jar jmhJar`
 