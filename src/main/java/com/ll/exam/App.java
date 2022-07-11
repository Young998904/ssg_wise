package com.ll.exam;

import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    // 각종 함수들에서 전역변수로 사용할 변수들
    // 외부에서 호출되지 않을 변수들은 private 처리
    private Scanner sc;
    // 마지막 명언 글의 번호 (run 함수가 끝날 때까지 유지됨)
    private int wiseSayingLastId;
    private List<WiseSaying> wiseSayings;

    public App() { // 생성자를 통해 생성
        sc = new Scanner(System.in);
        // 마지막 명언 글의 번호 (run 함수가 끝날 때까지 유지됨)
        wiseSayingLastId = 0;
        wiseSayings = new ArrayList<>();
    }

    public void run() {

        System.out.println("=== 명언 SSG ===");

        outer : // label : 해당 반복문의 별명을 만듦
        while (true) {
            System.out.printf("명령 : ");
            String cmd = sc.nextLine().trim();

            Rq rq = new Rq(cmd);

            switch (rq.getPath()) {
                case "종료" :
                    break outer;
                case "등록" :
                    write(rq);
                    break; // break 하지 않으면 하위 case 들도 수행됨
                case "목록" :
                    list(rq);
                    break;
                case "삭제" :
                    remove(rq);
                    break;
            }
        }
        sc.close();
    }

    private void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("===================");
        for (int i = wiseSayings.size()-1; i >= 0; i--) {
            WiseSaying wiseSaying_ = wiseSayings.get(i);
            System.out.printf("%d / %s / %s \n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
        }
    }

    private void remove(Rq rq) {
        // 명령어 형태 : 삭제?id=(삭제할 번호)
        int paramId = rq.getIntParam("id", 0);
        // id 를 입력했으면 해당 값이, 아니면 디폴트 값 0 이 나옴
        if (paramId == 0) { // 입력된 id가 없다면 작업 중단
            System.out.println("id를 입력해주세요");
            return;
        }

        WiseSaying foundWiseSaying = findById(paramId);

        if (foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다...\n", paramId);
            return;
        }

        wiseSayings.remove(foundWiseSaying);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);
    }

    // 삭제 & 수정 기능에서 공통적으로 쓰일 메서드 삭제 기능에서 따로 분리
    private WiseSaying findById(int paramId) {
        // url에 입력된 id 에 해당하는 명언 객체  찾기
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == paramId) { // 찾음
                return wiseSaying;
            }
        }
        return null;
    }

    private void write(Rq rq) { // 혹시 모르니 rq 추
        System.out.printf("명언 : ");
        String content = sc.nextLine().trim();
        System.out.printf("작가 : ");
        String author = sc.nextLine().trim();
        int id = ++wiseSayingLastId; // 마지막 번호 하나 증가 후 id에 담아둠

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        System.out.printf("%d번 명언이 등록되었습니다. \n", wiseSayingLastId);
    }
}