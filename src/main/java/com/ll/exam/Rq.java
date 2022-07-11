package com.ll.exam;

public class Rq {
    public String url;
    public Rq (String url) {
        this.url = url;
    }
    public int getIntParam(String paramName, int defaultValue) {
        String [] urlBits = url.split("\\?", 2);
        // 2의 역할 : 구분자 기준으로 최대 2개로만 나눈다.

        // 삭제명령어 뒤에 ?id 자체를 입력하지 않은경우
        if (urlBits.length == 1) {
            return defaultValue;
        }

//        System.out.println(urlBits[1]); // 출력값 : id=10
        urlBits= urlBits[1].split("&", 2);
        // return 값 : ["id=10", "no=1"]

        for (String urlBit : urlBits) {
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
        // "삭제?id=1"
        String [] urlBits = url.split("\\?", 2);
        return urlBits[0];
    }
}
