package hello.core;

/*
* 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 샌성하고 연결하는 책임을 가지느 별도의 설정 클래스
* - 관심사 분리 목적
*  · 구성에 관한 것은 여기서 다 한다.
*  · 배우는 본인의 역할인 배역을 수행하는 것에만 집중
*  · 남자배우 디카프리오는 어떤 여자 주인공이 선택되더라도 똑같이 공연을 할 수 있어야 함
*  · 공연을 구성하고, 담당 배우를 섭외하고, 역할에 맞는 배우를 지정하는 책임을 담당하는 별도의 공연 기획자(=AppConfig)
*  · 공연 기획자를 만들고, 배우와 공연 기획자의 책임을 확실히 분리
*/

//
public class AppConfig {

    /*
        이 전에는 객체를 생성하고, 해당 객체에 어떤 interface 가 들어가야 하는지, 할당하는 지 이런 내용을 해당 객체의 Impl 에서 직접 할당 했었다.
        ex) MemberServiceImpl 클래스 내부 : private final MembeRepository memberRepository = new MemoryMemberRepository();
        - 이 형태는 배우가 직접 담당 배우들을 섭외하는 격
        - 따라서 앞으로 그런 내용들은 AppConfig 로 옮겨준다.

        MemberServiceImpl 을 반환하는 메서드를 선언
    */


}
