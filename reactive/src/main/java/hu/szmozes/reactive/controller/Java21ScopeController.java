package hu.szmozes.reactive.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RestController
public class Java21ScopeController {

    @GetMapping("/list/java21scope")
    public ResponseEntity<List<List<Integer>>> java21Scope() {
        var numberOfLists = new Random().nextInt(2, 5);
        var listSizes = IntStream.range(0, numberOfLists)
                .mapToObj(i -> 2)
                .toList();

        List<List<Integer>> res = IntStream
                .range(0, numberOfLists)
                .mapToObj(listSizes::get)
                .map(this::getIntList)
                .toList();
        return ResponseEntity.ok(res);

    }

    private List<Integer> getIntList(int size) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return IntStream.range(0, size).boxed().toList();
    }

}
