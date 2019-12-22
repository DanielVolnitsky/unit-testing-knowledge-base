package sum;

import java.util.function.Supplier;

public class SynchronousIntSum implements Sum<Integer> {

  @Override
  public Integer compute(Supplier<Integer> s1, Supplier<Integer> s2) {
    Integer r1 = s1.get();
    Integer r2 = s2.get();
    return r1 + r2;
  }
}
