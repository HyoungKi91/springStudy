package spring.study.trace.springTrace;

import org.junit.jupiter.api.Test;
import spring.study.trace.TraceStatus;

import static org.junit.jupiter.api.Assertions.*;

class SpringTraceV1Test {

    @Test
    void begin_end(){
        SpringTraceV1 traceV1 = new SpringTraceV1();
        TraceStatus status = traceV1.begin("SpringHello");
        traceV1.end(status);
    }

}