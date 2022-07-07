package com.ll.exam;

import java.util.Scanner;

public class App {
    public void run() {
        int count = 1;
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
                    String wise_saying = sc.nextLine();
                    System.out.printf("작가 : ");
                    String who = sc.nextLine();
                    System.out.printf("%d번 명언이 등록되었습니다. \n", count);
                    count ++;
            }
        }
        sc.close();
    }
}
