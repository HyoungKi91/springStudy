package spring.study.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.study.trace.TraceStatus;
import spring.study.trace.springTrace.SpringTraceV1;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderServiceV1;
    private final SpringTraceV1 traceV1;

    @GetMapping("/v1/request")
    public String request(String itemId){
        TraceStatus status = null;
        try{
            status = traceV1.begin("OrderController.request()");
            orderServiceV1.orderItem(itemId);
            traceV1.end(status);
            return "OK";
        }catch (Exception e){
            traceV1.exception(status , e);
            throw e;
        }
    }
}
