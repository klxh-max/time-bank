package com.tb.util;


import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;

public class ProjectIDCreate {

    private static final AtomicInteger SEQ = new AtomicInteger(1);
    private static final DecimalFormat FORMAT = new DecimalFormat("00");

    private static final int MAX_PAD_SIZE = 100;

    /**
     * 基于时间生成八位短码
     * <p>
     * 该方法并不安全，如果调用间隔在10纳秒内会出现重复值，或者分布式调用也可能出现重复值
     *
     * @return 生成的标识符
     */
    public static synchronized String gen() {
        StringBuilder builder = new StringBuilder(System.nanoTime() / 1000 + "");
        //incrementAndGet()方法在一个无限循环体内，不断尝试将一个比当前值大1的新值赋给自己，
        // 如果失败则说明在执行"获取-设置"操作的时已经被其它线程修改过了，
        // 于是便再次进入循环下一次操作，直到成功为止。
        if (SEQ.incrementAndGet() % 10 == 0) {
            SEQ.incrementAndGet();
        }
        builder.append(FORMAT.format(SEQ.get()));
        if ((MAX_PAD_SIZE - 1) == SEQ.get()) {
            SEQ.set(1);
        }
        long v = Long.parseLong(builder.reverse().toString());
        return Base62.encode(v);
    }


    private ProjectIDCreate() {
        throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
    }

}
