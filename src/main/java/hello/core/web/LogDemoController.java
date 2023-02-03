package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor // 자동 DI 코드 줄이는 애노테이션
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider; // MyLogger를 주입받는 것이 아닌 DL 할 수 있는 객체를 주입받는 것

    @RequestMapping("log-demo") // http://localhost:8080/log-demo
    @ResponseBody // view 랜더링 없이 string 그대로 보낸다
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();

        // HTTP 요청이 들어왔을 때 생긴 MyLogger 빈을 찾아서 반환해달라는 뜻
        MyLogger myLogger = myLoggerProvider.getObject();

        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");

        logDemoService.logic("testId");

        return "OK";
    }
}