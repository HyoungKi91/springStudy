package spring.study.trace.logTrace;

import spring.study.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end (TraceStatus status);
    void exception(TraceStatus status , Exception e);
}
