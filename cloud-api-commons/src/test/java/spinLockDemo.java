import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: zbb
 * @create: 2020/4/28 14:43
 */
public class spinLockDemo
{
    // 原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock()
    {
        Thread thread = new Thread().currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in ");
        while(!atomicReference.compareAndSet(null,thread))
        {

        }
    }

    public static void main(String[] args)
    {
          /*
            得到一个A线程，获取锁，进入myLock方法()后，执行atomicReference.compareAndSet()方法，因为是第一个线程，
            atomicReference对象的原子值的初始值是null，A线程取得的值为null，根据判断，如果原子值不为null，则重新取值
            并与原子值进行比较和交换；如果为null，就将本线程传入原子值中，A线程执行5秒钟又将原子值从本线程更改为了null。
            当A线程还没有执行完毕时，进来一个B线程，B线程进入myLock方法中，复制原子值到工作内存，然后工作内存的值与主内存
            中的原子值进行比较和替换，根据volatile的可见性，此时原子值已经被修改为thread，工作内存中的值null与原子值不相同，
            则该操作失败，线程B重新取值null后与原子值进行比较和替换操作，由于线程A已将原子值修改为了null，并释放锁，此时原子值
            已修改为null，线程B工作内存的值与原子值相等，则操作成功，释放锁。
            操作后
          */
        spinLockDemo spinLockDemo = new spinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }// 等5秒
            spinLockDemo.myUnLock();
        },"A线程").start();

        // main线程等待一秒，让A线程先执行。
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            spinLockDemo.myLock();
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnLock();
        },"B线程").start();
    }

    public void myUnLock()
    {
        Thread thread = new Thread().currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myUnLock 我解锁了 ");
    }
}
