package com.urise.webapp;

import java.util.*;
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

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();
        System.out.println(thread1.getState());
        for (int i = 0; i < THREAD_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }
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

        int[] massive = {3, 4, 5, 4};
        System.out.println(minValue(massive));

        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5)));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, -1)));
    }

    private static final Object LOCK = new Object();

    private synchronized void inc() {
//         try {
//            synchronized (this) {
//                counter++;
//                wait();                ///
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        counter++;
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

    static int minValue(int[] values) {
        return IntStream.of(values)
                .boxed()
                // .allMatch(number->((number<=9)&&(number>=0))
                .distinct()
                .sorted(Comparator.reverseOrder())
                .sequential()
                .reduce(
                        new TreeMap<Integer, Integer>(),
                        (r, v) -> {
                            System.out.print(r);
                            System.out.println(" " + v);
                            r.put(r.size(), v);
                            return r;
                        },
                        (r1, r2) -> {
                            throw new UnsupportedOperationException("Stream should be sequential");
                        }
                )
                .entrySet()
                .stream()
                .mapToInt(e -> (int) (e.getValue() * (Math.pow(10, e.getKey()))))
                .sum();
    }

    static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> map = integers.stream()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0));
        return map.get(map.get(false).size() % 2 != 0);

    }

}