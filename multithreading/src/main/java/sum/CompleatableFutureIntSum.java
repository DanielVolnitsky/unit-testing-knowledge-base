package sum;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Instead of allowing a method to return its result at the same time that it physically returns to
 * the caller (synchronously), you allow it to return physically before producing its result
 * <p>
 * Futures appeared in Java 5 and were enriched into CompletableFuture in Java 8 to make them composable.
 */
public class CompleatableFutureIntSum implements Sum<Integer> {

  @Override
  @SneakyThrows
  public Integer compute(Supplier<Integer> s1, Supplier<Integer> s2) {
    CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(s1);
    CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(s2);

    /**
     * without knowing anything about computations in the Futures a and b,
     * it creates a computation that’s scheduled to run in the thread pool only when
     * both of the first two computations have completed.
     * */
    CompletableFuture<Integer> resultFuture = cf1.thenCombine(cf2, Integer::sum);
    return resultFuture.get();

    //can be done just like this
    //return supplyAsync(s1).thenCombine(supplyAsync(s2), Integer::sum);
  }
}
