package spring.study.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.study.trace.TraceStatus;
import spring.study.trace.logTrace.LogTrace;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
    private final OrderServiceV3 orderServiceV3;
    private final LogTrace traceV3;

    @GetMapping("/v3/request")
    public String request(String itemId){
        TraceStatus status = null;
        try{
            status = traceV3.begin("OrderController.request()");
            orderServiceV3.orderItem(itemId);
            traceV3.end(status);
            return "OK";
        }catch (Exception e){
            traceV3.exception(status , e);
            throw e;
        }
    }
}
