package spring.study.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.study.trace.TraceStatus;
import spring.study.trace.springTrace.SpringTraceV1;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final SpringTraceV1 traceV1;

    public void save(String itemId){
        TraceStatus status = null;
        try{
            status = traceV1.begin("OrderRepository.orderItem()");

            //저장
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외!");
            }
            sleep(1000); //상품 저장하는데 1초 정도 걸린다는 가정

            traceV1.end(status);
        }catch (Exception e){
            traceV1.exception(status , e);
            throw  e;
        }

    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
