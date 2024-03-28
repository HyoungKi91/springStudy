package spring.study.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String itemId){
        //저장
        if(itemId.equals("ex")){
            throw new IllegalStateException("예외!");
        }
        sleep(1000); //상품 저장하는데 1초 정도 걸린다는 가정
    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
