package hu.szmozes.reactive.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.concurrent.StructuredTaskScope;
import java.util.stream.IntStream;

@RestController
public class Java21ScopeController {

    @GetMapping("/list/java21scope")
    public ResponseEntity<List<List<Integer>>> java21Scope() throws Throwable {
        var numberOfLists = 100;
        var listSizes = IntStream.range(0, numberOfLists)
                .mapToObj(_ -> 2)
                .toList();

        try (var scope = StructuredTaskScope.open()) {
            var subtasks = IntStream
                    .range(0, numberOfLists)
                    .mapToObj(listSizes::get)
                    .map(size -> scope.fork(() -> getIntList(size)))
                    .toList();
            scope.join();
            var res = subtasks.stream()
                    .map(StructuredTaskScope.Subtask::get)
                    .toList();
            return ResponseEntity.ok(res);
        }
    }

    private List<Integer> getIntList(int size) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return IntStream.range(0, size).map(_ -> new Random().nextInt(10)).boxed().toList();
    }

}
