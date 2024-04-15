package spring.study.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalFieldServiceTest {
    private ThreadLocal<String> nameStore = new ThreadLocal<>();
    public String logic(String name){
        log.info("저장 name={} -> nameStore={}" , name , nameStore.get());
        nameStore.set(name);
        sleep(100);
        log.info("조회 nameStore={}" , nameStore.get());
        return nameStore.get();
    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
