package com.blog.comment.domain;

import java.sql.Timestamp;

public class Comment {

    private Integer commentId;
    private Integer postId;
    private Integer userId;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Comment(
            Integer commentId,
            Integer postId,
            Integer userId,
            String content,
            Timestamp createdAt,
            Timestamp updatedAt
    ) {
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public Integer getPostId() {
        return postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCommentId(Integer commentId) { this.commentId = commentId; }
}
