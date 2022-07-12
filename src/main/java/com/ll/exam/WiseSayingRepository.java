package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingRepository { // 데이터 수정, 저장, 복원 등의 역할 수행

    public int wiseSayingLastId;
    public List<WiseSaying> wiseSayings;

    WiseSayingRepository () {
        wiseSayingLastId = 0;
        wiseSayings = new ArrayList<>();
    }
    // 삭제 & 수정 기능에서 공통적으로 쓰일 메서드 삭제 기능에서 따로 분리
    public WiseSaying findById(int paramId) {
        // url에 입력된 id 에 해당하는 명언 객체  찾기
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == paramId) { // 찾음
                return wiseSaying;
            }
        }
        return null;
    }
}
