package spring.study.trace.springTrace;

import org.junit.jupiter.api.Test;
import spring.study.trace.TraceStatus;

class SpringTraceV2Test {

    @Test
    void begin_end(){
        SpringTraceV2 traceV2 = new SpringTraceV2();
        TraceStatus status1 = traceV2.begin("SpringHello1");
        TraceStatus status2 = traceV2.beginSn(status1.getTraceId() , "SpringHello2");
        traceV2.end(status2);
        traceV2.end(status1);
    }

}