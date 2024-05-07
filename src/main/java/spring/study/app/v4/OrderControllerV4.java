package spring.study.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.study.trace.TraceStatus;
import spring.study.trace.logTrace.LogTrace;
import spring.study.trace.template.AbstractTemplate;

import java.util.AbstractCollection;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {
    private final OrderServiceV4 orderServiceV4;
    private final LogTrace traceV4;

    @GetMapping("/v4/request")
    public String request(String itemId){
        AbstractTemplate<String> stringAbstractTemplate = new AbstractTemplate<>(traceV4){

            @Override
            protected String call() {
                orderServiceV4.orderItem(itemId);
                return "OK";
            }
        };
        return stringAbstractTemplate.execute("OrderController.request()");
    }
}
