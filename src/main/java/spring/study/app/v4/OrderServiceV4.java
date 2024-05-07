package spring.study.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.study.trace.TraceStatus;
import spring.study.trace.logTrace.LogTrace;
import spring.study.trace.template.AbstractTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final OrderRepositoryV4 orderRepositoryV4;
    private final LogTrace traceV4;

    public void orderItem(String itemId){
        AbstractTemplate<Void> template = new AbstractTemplate<>(traceV4) {
            @Override
            protected Void call() {
                orderRepositoryV4.save(itemId);
                return null;
            }
        };
        template.execute("OrderService.orderItem()");
    }
}
