package com.ll.exam;

public class Rq {
    String url;
    String path;
    String queryStr;
    // http://www.naver.com/abc/ddd/fff?age=10&id=40
    // ? 를 기준으로 좌 (path) 우 (queryString)
    public Rq (String url) {
        this.url = url;
        // getIntParam 과 getPath 에서 반복되는 내용 정리
        String [] urlBits = url.split("\\?", 2);
        this.path = urlBits[0];

        if (urlBits.length == 2) { // queryString 없이 path만 있을 경우에 대비
            this.queryStr = urlBits[1];
        }
    }
    public int getIntParam(String paramName, int defaultValue) {

        // 삭제명령어 뒤에 ?id 자체를 입력하지 않은경우
        if (queryStr == null) {
            return defaultValue;
        }

        String[] bits= queryStr.split("&", 2);

        for (String urlBit : bits) {
            String[] paraNameAndValue = urlBit.split("=", 2);

            String paraName_ = paraNameAndValue[0];
            String paraValue = paraNameAndValue[1];

            if (paramName.equals(paraName_)) {
                return Integer.parseInt(paraValue);
            }
        }

        return defaultValue;
    }

    public String getPath() {
        return path;
    }
}
