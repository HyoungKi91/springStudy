package spring.study.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldServiceTest {
    private String nameStore;

    public String logic(String name){
        log.info("저장 name={} -> nameStore={}" , name , nameStore);
        nameStore = name;
        sleep(100);
        log.info("조회 nameStore={}" , nameStore);
        return nameStore;
    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
