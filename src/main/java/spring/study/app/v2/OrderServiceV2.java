package spring.study.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.study.trace.TraceId;
import spring.study.trace.TraceStatus;
import spring.study.trace.springTrace.SpringTraceV2;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepositoryV2;
    private final SpringTraceV2 traceV2;

    public void orderItem(TraceId traceId, String itemId){
        TraceStatus status = null;
        try{
            status = traceV2.beginSn(traceId,"OrderService.orderItem()");
            orderRepositoryV2.save(status.getTraceId(), itemId);
            traceV2.end(status);
        }catch (Exception e) {
            traceV2.exception(status , e);
            throw e;
        }
    }
}
