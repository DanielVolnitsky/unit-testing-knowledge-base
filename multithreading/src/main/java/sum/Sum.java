package sum;

import java.util.function.Supplier;

public interface Sum<T> {

  T compute(Supplier<T> s1, Supplier<T> s2);
}
