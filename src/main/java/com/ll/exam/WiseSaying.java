package com.ll.exam;

public class WiseSaying {
    int id;
    String content;
    String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
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
                    "content" : %s,
                    "author" : %s
                }
                """.stripIndent()
                .formatted(id, content, author)
                .trim();
    }
}