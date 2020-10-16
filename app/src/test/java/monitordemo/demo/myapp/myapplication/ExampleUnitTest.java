package monitordemo.demo.myapp.myapplication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(5, 2 + 3);
    }

    private volatile boolean flag;

    @Test
    public void fun2() {
        //volatile的高速缓存 测试
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (flag) {
                        System.out.println("set flag to false");
                        flag = false;
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (!flag) {
                        System.out.println("set flag to true");
                        flag = true;
                    }
                }
            }
        }.start();
    }

    private String value;

    @Test
    public void fun3() {
        //volatile的禁止指令重排 测试
        new Thread() {
            @Override
            public void run() {
                value = "hello";
                flag = true;
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    System.out.println(value.toUpperCase());
                }
            }
        }).start();
    }
}