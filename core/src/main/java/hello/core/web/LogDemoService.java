package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    // Proxy를 사용하는 방법
    private final MyLogger myLogger;

    public void logic(String id) {
        myLogger.log("service id = " + id);
    }

    // ObjectProvider 을 사용하는 방법
//    private final ObjectProvider<MyLogger> myLoggerProvider;
//
//    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject();
//        myLogger.log("service id = " + id);
//    }
}
