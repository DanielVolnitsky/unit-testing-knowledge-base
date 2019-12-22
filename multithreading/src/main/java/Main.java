import sum.FuturesIntSum;
import sum.PureThreadsIntSum;
import sum.SynchronousIntSum;

import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * You may want to build a website that collects and summarizes social-media
 * sentiment on a given topic to your French users.
 * To do so, you could use the Facebook or Twitter API to find trending comments
 * about that topic in many languages and rank the most relevant ones with your internal algorithms.
 * Then you might use Google Translate to translate the comments into French or use Google Maps
 * to geolocate their authors, aggregate all this information, and display it on your website.
 * <p>
 * If any of these external network services are slow to respond,
 * of course, you’ll want to provide partial results to your users,
 * perhaps showing your text results alongside a generic map with a question mark
 * in it instead of showing a blank screen until the map server responds or times out.
 */
public class Main {

  private static Supplier<Integer> synchronousComputer = () -> new SynchronousIntSum()
      .compute(new IntFunctions()::f, new IntFunctions()::g);

  private static Supplier<Integer> threadsComputer = () -> new PureThreadsIntSum()
      .compute(new IntFunctions()::f, new IntFunctions()::g);

  private static Supplier<Integer> futuresComputer =
      () -> new FuturesIntSum(Executors.newFixedThreadPool(2))
          .compute(new IntFunctions()::f, new IntFunctions()::g);

  private static Supplier<Integer> compFutureComputer = () -> new PureThreadsIntSum()
      .compute(new IntFunctions()::f, new IntFunctions()::g);

  public static void main(String[] args) {
    Integer result = capturingTime().apply(compFutureComputer);
    System.out.println(result);
  }

  private static Function<Supplier<Integer>, Integer> capturingTime() {
    return delegate -> {
      long before = System.currentTimeMillis();
      Integer result = delegate.get();
      long after = System.currentTimeMillis();
      System.out.println("took " + (after - before) + " ms");
      return result;
    };
  }
}
