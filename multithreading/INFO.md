Since the advent of multicore processors, the most effective way to speed your applications is to write software that can fully exploit multicore processors.

TODO: You saw that you can split large tasks and make each subtask run in parallel with the others. You also learned how the fork/join framework (available since Java 7) and parallel streams (new in Java 8) allow you to accomplish this task in a simpler, more effective way than working directly with threads.

Conversely, when you’re dealing with concurrency instead of parallelism, or when your main goal is to perform several loosely related tasks on the same CPUs, keeping their cores as busy as possible to maximize the throughput of your application, you want to avoid blocking a thread and wasting its computational resources while waiting (potentially for quite a while) for a result from a remote service or from interrogating a database.

Java offers two main tool sets for such circumstances. First, as you’ll see in chapters 16 and 17, the Future interface, and particularly its Java 8 CompletableFuture implementation, often provide simple and effective solutions (chapter 16). More recently, Java 9 added the idea of reactive programming, built around the idea of the so-called publish-subscribe protocol via the Flow API, which offers more sophisticated programming approaches

Concurrency is a programming property (overlapped execution) that can occur even for a single-core machine, whereas parallelism is a property of execution hardware (simultaneous execution).

Initially, Java had locks (via synchronized classes and methods), Runnables and Threads.
In 2004, Java 5 introduced the java.util.concurrent package, which supported more expressive concurrency, particularly the ExecutorService[1] interface (which decoupled task submission from thread execution), as well as Callable<T> and Future<T>, which produced higher-level and result-returning variants of Runnable and Thread and used generics
Java 7 added java.util.concurrent.Recursive-Task to support fork/join implementation of divide-and-conquer algorithms, and Java 8 added support for Streams and their parallel processing (building on the newly added support for lambdas).
Java further enriched its concurrency features by providing support for composing Futures (via the Java 8 CompletableFuture implementation of Future, section 15.4 and chapter 16), and Java 9, provided explicit support for distributed asynchronous programming.
There the application worked by contacting various web services and combining their information in real time for a user or to expose it as a further web service. This process is called reactive programming, and Java 9 provides support for it via the publish-subscribe protocol (specified by the java.util.concurrent.Flow interface; see section 15.5 and chapter 17). A key concept of CompletableFuture and java.util.concurrent.Flow is to provide programming structures that enable independent tasks to execute concurrently wherever possible and in a way that easily exploits as much as possible of the parallelism provided by multicore or multiple machines.
parallel Stream iteration is a higher-level concept than explicit use of threads. In other words, this use of Streams abstracts a given use pattern of threads. This abstraction into Streams is analogous to a design pattern, but with the benefit that much of the complexity is implemented inside the library rather than being boilerplate code.

Java 5 provided the Executor framework and the idea of thread pools as a higher-level idea capturing the power of threads, which allow Java programmers to decouple task submission from task execution.
Java threads access operating-system threads directly. The problem is that operating-system threads are expensive to create and to destroy (involving interaction with page tables), and moreover, only a limited number exist.
The Java ExecutorService provides an interface where you can submit tasks and obtain their results later. The expected implementation uses a pool of threads, which can be created by one of the factory methods
These threads are returned to the pool when their tasks terminate. One great outcome is that it’s cheap to submit thousands of tasks to a thread pool while keeping the number of tasks to a hardware-appropriate number. Several configurations are possible, including the size of the queue, rejection policy, and priority for different tasks.
Thread pools are better than explicit thread manipulation in almost all ways, but you need to be aware of two “gotchas:”
* A thread pool with k threads can execute only k tasks concurrently
* Java typically waits for all threads to complete before allowing a return from main to avoid killing a thread executing vital code. Therefore, it’s important in practice and as part of good hygiene to shut down every thread pool before exiting the program (because worker threads for this pool will have been created but not terminated, as they’re waiting for another task submission)

Java 8 Streams give you a way to exploit parallel hardware. This exploitation happens in two stages. First, you replace external iteration (explicit for loops) with internal iteration (using Stream methods). Then you can use the parallel() method on Streams to allow the elements to be processed in parallel by the Java runtime library instead of rewriting every loop to use complex thread-creation operations. An additional advantage is that the runtime system is much better informed about the number of available threads when the loop is executed than is the programmer, who can only guess.
But if you know that f and g don’t interact, or you don’t care, you want to execute f and g in separate CPU cores, which makes the total execution time only the maximum of that of the calls to f and g instead of the sum.

* Support for concurrency in Java has evolved and continues to evolve. Thread pools are generally helpful but can cause problems when you have many tasks that can block.
* Making methods asynchronous (returning before all their work is done) allows additional parallelism, complementary to that used to optimize loops.
* You can use the box-and-channel model to visualize asynchronous systems.
* The Java 8 CompletableFuture class and the Java 9 Flow API can both represent box-and-channel diagrams.
* The CompletableFuture class expresses one-shot asynchronous computations. Combinators can be used to compose asynchronous computations without the risk of blocking that’s inherent in traditional uses of Futures.
* The Flow API is based on the publish-subscribe protocol, including backpressure, and forms the basis for reactive programming in Java.
* Reactive programming can be used to implement a reactive system.