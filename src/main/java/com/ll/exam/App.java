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

    public App() { // 생성자를 통해 생성
        sc = new Scanner(System.in);
        // 마지막 명언 글의 번호 (run 함수가 끝날 때까지 유지됨)
    }

    public void run() {
        // 컨트롤러 생성
        WiseSayingController wiseSayingController = new WiseSayingController(sc);

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
                    wiseSayingController.write(rq);
                    break; // break 하지 않으면 하위 case 들도 수행됨
                case "목록" :
                    wiseSayingController.list(rq);
                    break;
                case "삭제" :
                    wiseSayingController.remove(rq);
                    break;
                case "수정" :
                    wiseSayingController.modify(rq);
                    break;
            }
        }
        sc.close();
    }
}