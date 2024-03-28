package spring.study.trace.springTrace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spring.study.trace.TraceId;
import spring.study.trace.TraceStatus;

@Slf4j
@Component
public class SpringTraceV1 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";


    //로그 시작
    //로그 메시지를 파라미터로 받아서 시작 로그를 출력
    //응답 결과로 현재 로그의 상태인 TraceStatus를 반환
    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}" , traceId.getId() , addSpace(START_PREFIX , traceId.getLevel()),message);
        return new TraceStatus(traceId , startTimeMs , message);
    }

    //로그 종료
    //파라미터로 시작 로그의 상태(TraceStatus)를 전달 받음 이 값을 이용하여 실행 시간을 계산
    public void end(TraceStatus status) { //종료
        complete(status , null);
    }

    //로그를 예외 상황에 종료
    //TraceStatus , Exception 정보를 함께 전달받아 출력
    public void exception(TraceStatus status, Exception e) { //예외
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
    }

    private static String addSpace(String prefix ,  int level){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < level; i++){
            sb.append((i == level - 1) ? "|" + prefix :  "|     ");
        }
        return sb.toString();
    }
}
