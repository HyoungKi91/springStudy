package spring.study.app.v4;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.study.trace.TraceStatus;
import spring.study.trace.logTrace.LogTrace;
import spring.study.trace.template.AbstractTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace traceV4;

    public void save(String itemId){
        AbstractTemplate<Void> template = new AbstractTemplate<>(traceV4) {
            @Override
            protected Void call() {
                //저장
                if(itemId.equals("ex")){
                    throw new IllegalStateException("예외!");
                }
                sleep(1000); //상품 저장하는데 1초 정도 걸린다는 가정
                return null;
            }
        };
        template.execute("OrderRepository.orderItem()");
    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
