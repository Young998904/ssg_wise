package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private Scanner sc;
    private WiseSayingRepository wiseSayingRepository;

    WiseSayingController (Scanner sc) {
        wiseSayingRepository = new WiseSayingRepository();
        this.sc = sc;
    }

    public void modify(Rq rq) { // remove 와 유사
        // 명령어 형태 : 수정?id=(수정할 번호)
        int paramId = rq.getIntParam("id", 0);

        // id 를 입력했으면 해당 값이, 아니면 디폴트 값 0 이 나옴
        if (paramId == 0) { // 입력된 id가 없다면 작업 중단
            System.out.println("id를 입력해주세요");
            return;
        }

        WiseSaying foundWiseSaying = wiseSayingRepository.findById(paramId);

        if (foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다...\n", paramId);
            return;
        }

        // 수정
        System.out.printf("명언 (기존) : %s \n", foundWiseSaying.content);
        System.out.printf("명언 : ");
        foundWiseSaying.content = sc.nextLine();
        System.out.printf("작가 (기존) : %s \n", foundWiseSaying.author);
        System.out.printf("작가 : ");
        foundWiseSaying.author = sc.nextLine();

        System.out.printf("%d번 명언이 수정되었습니다.\n", paramId);
    }

    public void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("===================");
        for (int i = wiseSayingRepository.wiseSayings.size()-1; i >= 0; i--) {
            WiseSaying wiseSaying_ = wiseSayingRepository.wiseSayings.get(i);
            System.out.printf("%d / %s / %s \n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
        }
    }

    public void remove(Rq rq) {
        // 명령어 형태 : 삭제?id=(삭제할 번호)
        int paramId = rq.getIntParam("id", 0);
        // id 를 입력했으면 해당 값이, 아니면 디폴트 값 0 이 나옴
        if (paramId == 0) { // 입력된 id가 없다면 작업 중단
            System.out.println("id를 입력해주세요");
            return;
        }

        WiseSaying foundWiseSaying = wiseSayingRepository.findById(paramId);

        if (foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다...\n", paramId);
            return;
        }

        wiseSayingRepository.wiseSayings.remove(foundWiseSaying);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);
    }



    public void write(Rq rq) { // 혹시 모르니 rq 추
        System.out.printf("명언 : ");
        String content = sc.nextLine().trim();
        System.out.printf("작가 : ");
        String author = sc.nextLine().trim();
        int id = ++wiseSayingRepository.wiseSayingLastId; // 마지막 번호 하나 증가 후 id에 담아둠

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayingRepository.wiseSayings.add(wiseSaying);

        System.out.printf("%d번 명언이 등록되었습니다. \n", wiseSayingRepository.wiseSayingLastId);
    }
}
