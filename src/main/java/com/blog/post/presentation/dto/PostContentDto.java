package com.blog.post.presentation.dto;

public class PostContentDto {
    private String contentType;  // "text" or "image"
    private String content;
    private Integer order;

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() { return content; }
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrder() { return order; }
    public void setOrder(Integer order) {
        this.order = order;
    }
}
