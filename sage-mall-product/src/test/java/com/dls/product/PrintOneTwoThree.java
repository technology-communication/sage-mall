package com.dls.product;

/**
 * @author 张金行
 * @Title: PrintOneTwoThree
 * @ProjectName sage-mall
 * @Description:
 * @date 2020/04/21 23:44
 */
public class PrintOneTwoThree {
    public static void main(String[] args) {
        Print p1 = new Print(0);
        Print p2 = new Print(1);
        Print p3 = new Print(2);

        new Thread(p1, "p1").start();
        new Thread(p2, "p2").start();
        new Thread(p3, "p3").start();

        while (Thread.activeCount() > 2){
            System.out.println(Thread.activeCount());
        };
        System.out.println("Done!");
    }
}

class Print implements Runnable {
    private static int state = 0;
    private int id;
    private static Object lock = new Object();

    public Print(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("---1---");
        synchronized (lock) {
            System.out.println("---2---");
            while (state < 30) {
                System.out.println("---3---");
                if (state % 3 == id) {
                    switch (id) {
                        case 0:
                            System.out.println("["
                                    + Thread.currentThread().getName() + "]" + "A");
                            break;

                        case 1:
                            System.out.println("["
                                    + Thread.currentThread().getName() + "]" + "B");
                            break;

                        case 2:
                            System.out.println("["
                                    + Thread.currentThread().getName() + "]" + "C");
                            break;

                        default:
                            break;
                    }
                    state++;
                    System.out.println(state);
                    lock.notifyAll();
                } else {
                    try {
                        System.out.println(Thread.currentThread().getName()+"---wait---");
                        lock.wait();
                        System.out.println(Thread.currentThread().getName()+"---unwait---");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
