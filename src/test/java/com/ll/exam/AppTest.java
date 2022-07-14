package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Map;
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

    @Test
    void 파일에_객체를_저장() {
//        Util.saveToFile("1.json", "내용"); // 정상 실행 시 최상위 경로에 1.json 이 생성됨
        Util.mkdir("test_data"); // 정상 실행 시 최상위 경로에 test_data 폴더 생성됨
        WiseSaying wiseSaying = new WiseSaying(1, "내 사전에 불가능은 없다.", "나폴레옹");
        Util.saveToFile("test_data/1.json", wiseSaying.toJson());
        // 정상 실행 시 test_data 폴더 경로에 1.json 이 생성됨
        String rs = Util.readFromFile("test_data/1.json");

        assertEquals(wiseSaying.toJson(), rs);
    }

//    @Test
//    void 파일에_저장된_내용_가져오기 () { // 현상황 : 한글 깨짐 문제 발생
//        Util.mkdir("test_data");
//        Util.saveToFile("test_data/1.json", "내용\n내용");
//        String context = Util.readFromFile("test_data/1.json");
//
//        assertEquals("내용\n내용", context);
//        System.out.println(context);
//    }

    @Test
    void 파일에_있는_JSON을_맵으로_변환() {
        Util.mkdir("test_data");
        WiseSaying wiseSaying = new WiseSaying(1, "내 사전에 불가능은 없다.", "나폴레옹");
        Util.saveToFile("test_data/1.json", wiseSaying.toJson());

        String rs = Util.readFromFile("test_data/1.json");
        Map<String, Object> map = Util.jsonToMap(rs);

        assertEquals(1, map.get("id"));
        assertEquals("내 사전에 불가능은 없다.", map.get("content"));
        assertEquals("나폴레옹", map.get("author"));
    }

    @Test
    void 파일에_있는_JSON을_객체로_변환() {
        Util.mkdir("test_data");
        WiseSaying wiseSaying = new WiseSaying(1, "내 사전에 불가능은 없다.", "나폴레옹");
        Util.saveToFile("test_data/1.json", wiseSaying.toJson());

        String rs = Util.readFromFile("test_data/1.json");
        Map<String, Object> map = Util.jsonToMap(rs);
        WiseSaying loadedWiseSaying = new WiseSaying(map);

        assertEquals(wiseSaying.id, loadedWiseSaying.id);
        assertEquals(wiseSaying.content, loadedWiseSaying.content);
        assertEquals(wiseSaying.author, loadedWiseSaying.author);
        assertEquals(wiseSaying, loadedWiseSaying);
       // 내용이 같아도 다른 객체로 인식됨 -> equals 함수 오버라이딩 필요
    }
}
