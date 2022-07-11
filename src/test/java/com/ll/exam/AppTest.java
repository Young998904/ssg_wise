package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void JunitInstall() {
        int rs = 10 + 20;
        assertEquals(30, rs);
    }

    //스캐너 테스트
    @Test
    public void TestScanner() { // 키보드 직접 입력이 아닌 파일로 입력받아 수행
        String input = """
                등록
                명언1
                작가1
                """.stripIndent();
        InputStream in = new ByteArrayInputStream((input.getBytes())); // input을 사용
        Scanner sc = new Scanner(in);

        String cmd = sc.nextLine().trim();
        String content = sc.nextLine().trim();
        String author = sc.nextLine().trim();

        assertEquals("등록", cmd);
        assertEquals("명언1", content);
        assertEquals("작가1", author);
    }

    // 출력을 문자열로 받기
    @Test
    public void 표준출력을_리다이렉션하여_결과를_문자열로_받기() throws IOException {
        // 표준출력을 리다이렉션
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        System.out.println("안녕");

        String rs = output.toString().trim();

        // 표준출력을 원상복구
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        output.close();

        assertEquals("안녕", rs);
    }

    @Test
    public void Rq_getIntParam() { // 삭제 기능
        Rq rq = new Rq("삭제?id=1"); // url에서 ?id=1 을 파라미터라고 함
        int id = rq.getIntParam("id", 0);

        // 1단계 : getIntParam 을 통해 반환 받는 값이 1 이어야 함
        assertEquals(1, id);
    }
    @Test
    public void Rq_getIntParam_2() { // 검색 기능
        Rq rq = new Rq("검색?id=10&no=1");
        int id = rq.getIntParam("id", 0);
        int no = rq.getIntParam("no", 0);

        assertEquals(10, id);
        assertEquals(1, no);
    }
    @Test
    public void Rq_getPath() { // 명령어 획득
        Rq rq = new Rq("삭제?id=1");

        String path = rq.getPath();
        assertEquals("삭제", path);

    }
}
