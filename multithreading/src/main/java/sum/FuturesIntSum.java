package sum;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * this code is still polluted by the boilerplate code involving explicit calls to submit.
 * You need a better way of expressing this idea, analogous to how internal iteration on Streams
 * avoided the need to use thread-creation syntax to parallelize external iteration
 */
@RequiredArgsConstructor
public class FuturesIntSum implements Sum<Integer> {

  private final ExecutorService executorService;

  @Override
  @SneakyThrows
  public Integer compute(Supplier<Integer> s1, Supplier<Integer> s2) {
    Future<Integer> a = executorService.submit(s1::get);
    Future<Integer> b = executorService.submit(s2::get);

    return a.get() + b.get();
  }
}
