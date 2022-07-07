package com.ll.exam;

import java.util.Scanner;

public class App {
    public void run() {
        // 마지막 명언 글의 번호 (run 함수가 끝날 때까지 유지됨)
        int wiseSayingLastId = 1;
        System.out.println("=== 명언 SSG ===");
        Scanner sc = new Scanner(System.in);

        outer : // label : 해당 반복문의 별명을 만듦
        while (true) {
            System.out.printf("명령 : ");
            String cmd = sc.nextLine().trim();

            switch (cmd) {
                case "종료" :
                    break outer;
                case "등록" :
                    System.out.printf("명언 : ");
                    String wise_saying = sc.nextLine().trim();
                    System.out.printf("작가 : ");
                    String who = sc.nextLine().trim();
                    System.out.printf("%d번 명언이 등록되었습니다. \n", wiseSayingLastId);
                    wiseSayingLastId ++;
                    break; // break 하지 않으면 하위 case 들도 수행됨
            }
        }
        sc.close();
    }
}
