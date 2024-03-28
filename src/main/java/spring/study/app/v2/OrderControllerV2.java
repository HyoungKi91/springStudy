package spring.study.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.study.trace.TraceStatus;
import spring.study.trace.springTrace.SpringTraceV1;
import spring.study.trace.springTrace.SpringTraceV2;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderServiceV2;
    private final SpringTraceV2 traceV2;

    @GetMapping("/v2/request")
    public String request(String itemId){
        TraceStatus status = null;
        try{
            status = traceV2.begin("OrderController.request()");
            orderServiceV2.orderItem(status.getTraceId() , itemId);
            traceV2.end(status);
            return "OK";
        }catch (Exception e){
            traceV2.exception(status , e);
            throw e;
        }
    }
}
