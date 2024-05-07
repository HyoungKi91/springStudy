package spring.study.trace.template;

import spring.study.trace.TraceStatus;
import spring.study.trace.logTrace.LogTrace;

public abstract class AbstractTemplate<T> {

    private final LogTrace logTrace;

    public AbstractTemplate(LogTrace logTrace){
        this.logTrace = logTrace;
    }

    public T execute(String message){
        TraceStatus status = null;
        try{
            status = logTrace.begin(message);

            //로직 호출
            T result = call();

            logTrace.end(status);

            return result;
        }catch (Exception e){
            logTrace.exception(status,e);
            throw e;
        }
    }

    protected abstract T call();
}
