package org.core;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Word Count (concordances) algorithm
 */
public class ToStreamFromFileTest {

    /*
    to read from a file @main class, you need
     1- a separate folder, and
     2- you must provide absolute path !!!..

     a path from a jar ain't work!!!

     so below code works from IDE, but not from command line !
     */
    @Test
    void wordCountTest() {
        wordCount.get().forEach((w,i) -> System.out.println(w + " | " + i) );
    }

    /*
      it gets a stream from a file,
         - .getResource("/..") works as a test.. But you can not read a file from within a jar!
      then, also gets a stream from regex!
     */
   public static Supplier<Map<String, Long>> wordCount =
           () -> {
               try {
                   return Files.lines(Paths.get(ToStreamFromFileTest.class.getResource("concordances.txt").toURI())
                                   .toAbsolutePath())
                           .flatMap(line -> Pattern.compile("\\w+").matcher(line).results())
                           .collect(Collectors.groupingBy(MatchResult::group, Collectors.counting()));

               } catch (IOException | URISyntaxException e) { throw new RuntimeException(e); }

           };

}
