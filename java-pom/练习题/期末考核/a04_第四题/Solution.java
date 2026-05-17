package 其他.d38_期末考核.a04_第四题;

import java.util.*;
import java.util.concurrent.*;

/**
 * ### 第四题（20）
 * 需求
 * 红包雨游戏，某企业有100名员工，员工的工号依次是1, 2，3, 4，..到100。
 * 现在公司举办了年会活动，活动中有一个红包雨环节，要求共计发出200个红包雨。
 * 其中
 * 小红包在[1 - 30]元之间，总占比为80%，
 * 大红包[31-100]元，总占比为20%
 * 具体的功能点如下
 * 1、系统模拟上述要求产生200个红包。
 * 2、模拟100个员工抢红包雨，需要输出哪个员工抢到哪个红包的过程，活动结束时需要提示活动结束。
 * 3、活动结束后，请1对100名员工按照所抢红包的总金额进行降序排序展示，**例如：3号员工抢红包总计：293元、1号员工抢红包总计250元，....**
 */
public class Solution {
    public static int total = 0;
    public static List<Integer> money;
    public static HashMap<Integer, Future<Integer>> futureTaskHm = new HashMap<>();
    public static HashMap<String, Integer> result = new HashMap<>();
    public static final int N = 100;
    public static CountDownLatch cdl = new CountDownLatch(N); // 多线程同时启动

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 产生200个红包
        Random random = new Random();
        money = new Random()
                .ints(200, 0, 10)
                .map(i -> {
                    if (i <= 1) {
                        return random.nextInt(31, 101);
                    }
                    return random.nextInt(1, 31);
                })
                .boxed()
                .toList();
        int totalMoney = money.stream().reduce(0, (a, b)->a+b);
        System.out.println("总共有%s元".formatted(totalMoney));
        // 2. 模拟100个员工开始抢红包
        // 使用线程池同时启动所有线程
        ExecutorService es = Executors.newFixedThreadPool(N);

        for (int i = 0; i < N; i++) {
            FutureTask<Integer> ft = new FutureTask<>(new Employee("员工" + i + "号"));
            Thread thread = new Thread(ft);
            es.submit(thread);
            futureTaskHm.put(i, ft);
            cdl.countDown();
        }
        for (int i = 0; i < N; i++) {
            Integer integer = futureTaskHm.get(i).get();
            result.put(String.format("员工%d号", i), integer);
        }
        es.shutdown(); // 关闭线程池，继续执行主线程
        // 3. 排序
        System.out.println("活动结束");
        System.out.println("总共的人数是"+ result.size());
        result.keySet()
                .stream()
                .filter(a->result.get(a) > 0)
                .sorted(Comparator.comparingInt(a -> -result.get(a)))
                .map(s->String.format("%3s获得了%d", s, result.get(s)))
                .forEach(System.out::println);



    }
}

class Employee implements Callable<Integer> {
    private String name;


    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public Integer call() throws InterruptedException {
        int money = 0;
        try {
            Solution.cdl.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            synchronized (Solution.class) {
                if (Solution.total >= 200) {
                    break;
                }
                money += Solution.money.get(Solution.total++);
            }
            Thread.sleep(10);
        }
        return money;
    }
}
