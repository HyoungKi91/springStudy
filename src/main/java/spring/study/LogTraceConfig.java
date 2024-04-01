package spring.study;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.study.trace.logTrace.FieldLogTrace;
import spring.study.trace.logTrace.LogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
        return new FieldLogTrace();
    }
}
