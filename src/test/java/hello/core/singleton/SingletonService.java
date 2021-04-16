package hello.core.singleton;


public class SingletonService {

    // static = 클래스레벨에 올라가서 하나만 생성됨
    // 나자신을 생성함
    private static final SingletonService instance = new SingletonService();

    // 조회용
    // public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
    // 항상 같은 인스턴스만 반환한다
    public static SingletonService getInstance() {
        return instance;
    }

    // ****** 생성자를 private로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출 ");
    }

}
