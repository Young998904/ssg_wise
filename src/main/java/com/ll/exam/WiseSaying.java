package com.ll.exam;

import java.util.Map;

public class WiseSaying {
    int id;
    String content;
    String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
    public WiseSaying (Map<String, Object> map) {
        this.id = (int) map.get("id");
        this.content = (String) map.get("content");
        this.author = (String) map.get("author");
    }
    @Override
    public String toString() {
        return "WiseSaying{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
    public String toJson() {// stripIndent() : 중괄호 없애는 역할
        return """
                {
                    "id" : %d,
                    "content" : "%s",
                    "author" : "%s"
                }
                """.stripIndent()
                .formatted(id, content, author)
                .trim();
    }
    public boolean equals (Object o) {
        if (this == o) return true; // 가리키는 객체가 똑같으면 무조건 true;

        if (o instanceof WiseSaying == false) return false; // 연결 객체가 WiseSaying 클래스가 아니면 false

        WiseSaying other = (WiseSaying) o;
        if(this.id != other.id) return false;
        if(!this.content.equals(other.content)) return false;
        if(!this.author.equals(other.author)) return false;

        return true;
    }
}