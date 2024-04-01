package spring.study.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.study.trace.TraceStatus;
import spring.study.trace.logTrace.LogTrace;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepositoryV3;
    private final LogTrace traceV3;

    public void orderItem(String itemId){
        TraceStatus status = null;
        try{
            status = traceV3.begin("OrderService.orderItem()");
            orderRepositoryV3.save(itemId);
            traceV3.end(status);
        }catch (Exception e) {
            traceV3.exception(status , e);
            throw e;
        }
    }
}
