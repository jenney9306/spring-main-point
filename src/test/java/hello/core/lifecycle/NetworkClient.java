package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
//javax java 진영에서 공식적으로 지원하는것임

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println(" 생성자 호출 = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connet() {
        System.out.println("connect url = " + url);
    }

    public void call(String message) {
        System.out.println("url = " + url + " message " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("disconnect url = " + url);
    }

    @PostConstruct
    public void init() throws Exception {
        connet();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }
}
