package sum;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.function.Supplier;

/**
 * Some of the complexity here has to do with transferring results back from the thread. Only final outer-object variables can be used in lambdas or inner classes,
 * but the real problem is all the explicit thread manipulation.
 * <p>
 * effectively final variable: a variable which is not declared as final but whose value is never changed after initialization is effectively final.
 */
public class PureThreadsIntSum implements Sum<Integer> {

  @Override
  @SneakyThrows
  public Integer compute(Supplier<Integer> s1, Supplier<Integer> s2) {
    Result r = new Result();

    Thread t1 = new Thread(() -> r.a = s1.get());
    t1.start();
    Thread t2 = new Thread(() -> r.b = s2.get());
    t2.start();

    t1.join();
    t2.join();

    return r.a + r.b;
  }

  @Getter
  @Setter
  private static class Result {
    private Integer a;
    private Integer b;
  }
}
