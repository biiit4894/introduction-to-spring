package hello.hellospringkyh.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component // ComponentScan도 가능, but bean에 등록해서 구분지어지도록 하는 것을 더 선호함. 이 강의에서는 일단 컴포넌트 스캔을 쓰기로
@Aspect
public class TimeTraceAop {
    @Around("execution(* hello.hellospringkyh..*(..))")
    //@Around("execution(* hello.hellospringkyh.service..*(..))") //Service와 Service 사이의 시간만 로깅하도록 설정도 가능

    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
