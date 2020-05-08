package com.urise.webapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainConcurrency {
    private static final int THREAD_NUMBER = 10000;
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread1.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
                    counter++;
                }
            }

        }).start();
        //thread1.join();

        System.out.println(thread1.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREAD_NUMBER);
        CountDownLatch latch = new CountDownLatch(THREAD_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService completionService = new ExecutorCompletionService(executorService);


//        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();
//        System.out.println(thread1.getState());
        for (int i = 0; i < THREAD_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() ->
                    //Thread thread = new Thread(() -> {
            {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
                latch.countDown();
                return 5;
            });
            completionService.poll();
//            thread.start();
//            threads.add(thread);
        }

        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(counter);

        System.out.println(threads.size());
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
//        final String lock1 = "name1";
//        final String lock2 = "name2";
//        deadLock(lock1, lock2);
//        deadLock(lock2, lock1);
        System.out.println("\nmin value");
        int[] massive = {3, 4, 5, 4};
        System.out.println(minValue(massive));
        int[] massive2 = {};
        System.out.println(minValue(massive2));

        System.out.println("\nodd or even");
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5)));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5)));

        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, 7)));
    }

    private static final Object LOCK = new Object();
    private final AtomicInteger atomicCounter = new AtomicInteger();

    //    private static final Object LOCK = new Object();
//    private static final Lock lock = new ReentrantLock();
    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final Lock WRITE_LOCK = reentrantReadWriteLock.writeLock();
    private static final Lock READ_LOCK = reentrantReadWriteLock.readLock();
    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

    private void inc() {
//        synchronized (this) {	//        synchronized (this) {
//        synchronized (MainConcurrency.class) {	//        synchronized (MainConcurrency.class) {
        counter++;    //        WRITE_LOCK.lock();
//        try {
        atomicCounter.incrementAndGet();
//            counter++;
//        } finally {
//            WRITE_LOCK.unlock();
//        }
//                wait();	//                wait();
//                readFile	//                readFile
//                ...	//                ...
//        }	//        }
    }

    private static void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("Wait " + lock1);
            synchronized (lock1) {
                System.out.println("Name: " + lock1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Wait " + lock2);
                synchronized (lock2) {
                    System.out.println("Name " + lock2);
                }
            }
        }).start();
    }

    private static Integer minValue(int[] values) {
//        return IntStream.of(values)
//                .boxed()
//                // .allMatch(number->((number<=9)&&(number>=0))
//                .distinct()
//                .sorted(Comparator.reverseOrder())
//                .sequential()
//                .reduce(
//                        new TreeMap<Integer, Integer>(),
//                        (r, v) -> {
//                            System.out.print(r);
//                            System.out.println(" " + v);
//                            r.put(r.size(), v);
//                            return r;
//                        },
//                        (r1, r2) -> {
//                            throw new UnsupportedOperationException("Stream should be sequential");
//                        }
//                )
//                .entrySet()
//                .stream()
//                .mapToInt(e -> (int) (e.getValue() * (Math.pow(10, e.getKey()))))
//                .sum();
        return IntStream.of(values)
                .boxed()
                .distinct()
                .sorted()
                .reduce((left, next) -> (left * 10 + next))
                .orElse(0);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> map = integers.stream()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0));
        return map.get(map.get(false).size() % 2 != 0);
    }
}