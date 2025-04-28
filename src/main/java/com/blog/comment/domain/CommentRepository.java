package com.blog.comment.domain;

import java.sql.Timestamp;
import java.util.List;

public interface CommentRepository {

    void save(Comment comment);

    List<Comment> findByPostId(Integer postId);

    Comment findById(Integer postId, Integer commentId);

    void update(Integer commentId, Integer postId, Integer userId, String content, Timestamp updatedAt);

    void delete(Integer postId, Integer commentId, Integer userId);
}
