package spring.study.trace.logTrace;

import lombok.extern.slf4j.Slf4j;
import spring.study.trace.TraceId;
import spring.study.trace.TraceStatus;

@Slf4j
public class FieldLogTrace implements LogTrace{

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();
    @Override
    public TraceStatus begin(String message) {
        snTraceId();
        TraceId traceId = traceIdHolder.get();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}" , traceId.getId() , addSpace(START_PREFIX , traceId.getLevel()),message);
        return new TraceStatus(traceId , startTimeMs , message);
    }

    private void snTraceId(){
        if(traceIdHolder.get() == null){
            traceIdHolder.set(new TraceId());
        }else{
            traceIdHolder.set(traceIdHolder.get().createNextId());
        }
    }
    @Override
    public void end(TraceStatus status) {
        complete(status , null);

    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status , e);
    }

    //로그 출력
    private void complete(TraceStatus status , Exception e){
        Long stopTimeMs = System.currentTimeMillis();
        Long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if(e == null){
            log.info("[{}] {}{} time={}ms" , traceId.getId() ,addSpace(COMPLETE_PREFIX , traceId.getLevel()) ,status.getMessage(), resultTimeMs);
        }else{
            log.info("[{}] {}{} time={}ms ex={}" , traceId.getId() ,addSpace(EX_PREFIX , traceId.getLevel()) ,status.getMessage() , resultTimeMs,e.toString());
        }
        
        releaseTraceId();
    }

    private void releaseTraceId() {
        if(traceIdHolder.get().isFirstLevel()){
            traceIdHolder.remove();
        }else{
            traceIdHolder.set(traceIdHolder.get().createPrevId());
        }
    }

    private static String addSpace(String prefix ,  int level){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < level; i++){
            sb.append((i == level - 1) ? "|" + prefix :  "|     ");
        }
        return sb.toString();
    }
}
