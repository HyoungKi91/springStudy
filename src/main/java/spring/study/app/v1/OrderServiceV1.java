package spring.study.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.study.trace.TraceStatus;
import spring.study.trace.springTrace.SpringTraceV1;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepositoryV1;
    private final SpringTraceV1 traceV1;

    public void orderItem(String itemId){
        TraceStatus status = null;
        try{
            status = traceV1.begin("OrderService.orderItem()");
            orderRepositoryV1.save(itemId);
            traceV1.end(status);
        }catch (Exception e) {
            traceV1.exception(status , e);
            throw e;
        }
    }
}
