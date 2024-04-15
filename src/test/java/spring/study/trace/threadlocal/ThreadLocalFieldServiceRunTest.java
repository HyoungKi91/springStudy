package spring.study.trace.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.study.trace.threadlocal.code.FieldServiceTest;
import spring.study.trace.threadlocal.code.ThreadLocalFieldServiceTest;

@Slf4j
public class ThreadLocalFieldServiceRunTest {

    private ThreadLocalFieldServiceTest fieldServiceTest = new ThreadLocalFieldServiceTest();

    @Test
    void field(){
        log.info("main start");
        Runnable userA = () -> {fieldServiceTest.logic("userA");};
        Runnable userB = () -> {fieldServiceTest.logic("userB");};

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");
        
        threadA.start();
        sleep(1);
        threadB.start();
        sleep(3000);

        log.info("main exit");
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
