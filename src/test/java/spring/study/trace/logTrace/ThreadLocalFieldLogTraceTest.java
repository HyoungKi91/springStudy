package spring.study.trace.logTrace;

import org.junit.jupiter.api.Test;
import spring.study.trace.TraceStatus;

class ThreadLocalFieldLogTraceTest {

    FieldLogTrace fieldLogTrace = new FieldLogTrace();
    @Test
    void FieldLogTraceTest(){
        TraceStatus status1 = fieldLogTrace.begin("hello1");
        TraceStatus status2 = fieldLogTrace.begin("hello2");
        fieldLogTrace.end(status2);
        fieldLogTrace.end(status1);
    }

    @Test
    void FieldLogTraceExceptionTest(){
        TraceStatus status1 = fieldLogTrace.begin("hello1");
        TraceStatus status2 = fieldLogTrace.begin("hello2");
        fieldLogTrace.exception(status2 , new IllegalStateException());
        fieldLogTrace.exception(status1 , new IllegalStateException());
    }


}