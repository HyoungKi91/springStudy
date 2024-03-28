package spring.study.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.study.trace.TraceId;
import spring.study.trace.TraceStatus;
import spring.study.trace.springTrace.SpringTraceV2;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final SpringTraceV2 traceV2;

    public void save(TraceId traceId, String itemId){
        TraceStatus status = null;
        try{
            status = traceV2.beginSn(traceId, "OrderRepository.orderItem()");

            //저장
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외!");
            }
            sleep(1000); //상품 저장하는데 1초 정도 걸린다는 가정

            traceV2.end(status);
        }catch (Exception e){
            traceV2.exception(status , e);
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
