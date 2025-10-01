import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BenchmarkTest {

    @Test
    public void test() {
        Assertions.assertThat(true).isTrue();
    }

    @Test
    public void benchmarkTest() {
        int iterations = 1_000_000;
        
        // Warm-up phase (helps JIT compilation)
        for (int i = 0; i < 10_000; i++) {
            statement1();
            statement2();
            statement3();
        }
        
        // Benchmark Statement 1
        long startTime1 = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            statement1();
        }
        long endTime1 = System.nanoTime();
        long duration1 = endTime1 - startTime1;
        
        // Benchmark Statement 2
        long startTime2 = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            statement2();
        }
        long endTime2 = System.nanoTime();
        long duration2 = endTime2 - startTime2;
        
        // Benchmark Statement 3
        long startTime3 = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            statement3();
        }
        long endTime3 = System.nanoTime();
        long duration3 = endTime3 - startTime3;
        
        // Print results
        System.out.println("=== Benchmark Results ===");
        System.out.println("Iterations: " + iterations);
        System.out.println();
        System.out.println("Statement 1:");
        System.out.println("  Total time: " + duration1 + " ns");
        System.out.println("  Average time per iteration: " + (duration1 / iterations) + " ns");
        System.out.println("  Total time in ms: " + (duration1 / 1_000_000.0) + " ms");
        System.out.println();
        System.out.println("Statement 2:");
        System.out.println("  Total time: " + duration2 + " ns");
        System.out.println("  Average time per iteration: " + (duration2 / iterations) + " ns");
        System.out.println("  Total time in ms: " + (duration2 / 1_000_000.0) + " ms");
        System.out.println();
        System.out.println("Statement 3:");
        System.out.println("  Total time: " + duration3 + " ns");
        System.out.println("  Average time per iteration: " + (duration3 / iterations) + " ns");
        System.out.println("  Total time in ms: " + (duration3 / 1_000_000.0) + " ms");
        System.out.println();
        
        // Find fastest
        long fastest = Math.min(duration1, Math.min(duration2, duration3));
        System.out.println("=== Comparison ===");
        System.out.println("Statement 1 is " + String.format("%.2fx", (double) duration1 / fastest) + " relative to fastest");
        System.out.println("Statement 2 is " + String.format("%.2fx", (double) duration2 / fastest) + " relative to fastest");
        System.out.println("Statement 3 is " + String.format("%.2fx", (double) duration3 / fastest) + " relative to fastest");
    }
    
    // Replace these with your actual statements to benchmark
    private void statement1() {
        // Example: String concatenation
        String result = "Hello" + " " + "World";
    }
    
    private void statement2() {
        // Example: StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("Hello").append(" ").append("World");
        String result = sb.toString();
    }
    
    private void statement3() {
        // Example: String.format
        String result = String.format("%s %s", "Hello", "World");
    }
}
