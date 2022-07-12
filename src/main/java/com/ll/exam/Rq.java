package com.ll.exam;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    String url;
    String path;
    Map<String, String> queryParams;
    // http://www.naver.com/abc/ddd/fff?age=10&id=40
    // ? 를 기준으로 좌 (path) 우 (queryString)
    public Rq (String url) {
        this.url = url;
        // getIntParam 과 getPath 에서 반복되는 내용 정리
        String [] urlBits = url.split("\\?", 2);
        this.path = urlBits[0];

        queryParams = new HashMap<>();

        if (urlBits.length == 2) { // queryString 없이 path만 있을 경우에 대비
            String queryStr = urlBits[1];

            String[] paramBits = queryStr.split("&");

            for (String paramBit : paramBits) {
                String[] paramNameAndValue = paramBit.split("=", 2);

                // (명령어)?id= 형태로 명령어를 입력한 경우
                if (paramNameAndValue.length == 1) {
                    continue;
                }
                String paramName = paramNameAndValue[0].trim();
                String paramValue= paramNameAndValue[1].trim();

                queryParams.put(paramName, paramValue);
            }
        }
    }
    public int getIntParam(String paramName, int defaultValue) {
        if (queryParams.containsKey(paramName) == false) {
            return defaultValue;
        }

        String paramValue = queryParams.get(paramName);

        if(paramValue.length() == 0) {
            return defaultValue;
        }

        return Integer.parseInt(queryParams.get(paramName));

//        // 삭제명령어 뒤에 ?id 자체를 입력하지 않은경우
//        if (queryStr == null) {
//            return defaultValue;
//        }
//
//        String[] bits= queryStr.split("&", 2);
//
//        for (String urlBit : bits) {
//            String[] paraNameAndValue = urlBit.split("=", 2);
//
//            String paraName_ = paraNameAndValue[0];
//            String paraValue = paraNameAndValue[1];
//
//            if (paramName.equals(paraName_)) {
//                return Integer.parseInt(paraValue);
//            }
//        }
//
//        return defaultValue;
    }

    public String getPath() {
        return path;
    }
}
