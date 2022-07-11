package com.ll.exam;

import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void run() {

        System.out.println("=== 명언 SSG ===");
        Scanner sc = new Scanner(System.in);

        // 마지막 명언 글의 번호 (run 함수가 끝날 때까지 유지됨)
        int wiseSayingLastId = 0;

        List<WiseSaying> wiseSayings = new ArrayList<>();

        outer : // label : 해당 반복문의 별명을 만듦
        while (true) {
            System.out.printf("명령 : ");
            String cmd = sc.nextLine().trim();

            switch (cmd) {
                case "종료" :
                    break outer;
                case "등록" :
                    System.out.printf("명언 : ");
                    String content = sc.nextLine().trim();
                    System.out.printf("작가 : ");
                    String author = sc.nextLine().trim();
                    int id = ++wiseSayingLastId; // 마지막 번호 하나 증가 후 id에 담아둠

                    WiseSaying wiseSaying = new WiseSaying(id, content, author);
                    wiseSayings.add(wiseSaying);

                    System.out.printf("%d번 명언이 등록되었습니다. \n", wiseSayingLastId);
                    break; // break 하지 않으면 하위 case 들도 수행됨
                case "목록" :
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("===================");
                    for (int i = wiseSayings.size()-1; i >= 0; i--) {
                        WiseSaying wiseSaying_ = wiseSayings.get(i);
                        System.out.printf("%d / %s / %s \n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
                    }
            }
        }
        sc.close();
    }
}