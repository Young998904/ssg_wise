package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void JUNIT_테스트_실험() {
        int rs = 10 + 20;
        assertEquals(30, rs);
    }

    //스캐너 테스트
    @Test
    public void Test_Scanner() { // 키보드 직접 입력이 아닌 파일로 입력받아 수행
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
}
