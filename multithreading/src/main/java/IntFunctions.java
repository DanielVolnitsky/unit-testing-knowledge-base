import lombok.SneakyThrows;

public class IntFunctions implements Functions<Integer> {

  @Override
  @SneakyThrows
  public Integer f() {
    System.out.println(Thread.currentThread() + " doing a mathematical optimization task");
    Thread.sleep(1000);
    return 11;
  }

  @Override
  @SneakyThrows
  public Integer g() {
    System.out.println(Thread.currentThread() + " doing a mathematical optimization task");
    Thread.sleep(1000);
    return 8;
  }
}
