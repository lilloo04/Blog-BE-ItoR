package com.blog.comment.domain;

import com.blog.comment.presentation.dto.CommentCreateRequest;
import com.blog.comment.presentation.dto.CommentUpdateRequest;
import com.blog.comment.presentation.dto.CommentResponse;

import java.util.List;

public interface CommentService {

    CommentResponse createComment(Integer postId, CommentCreateRequest request, Integer userId);

    List<CommentResponse> getComments(Integer postId);

    CommentResponse updateComment(Integer postId, Integer commentId, CommentUpdateRequest request, Integer userId);

    void deleteComment(Integer postId, Integer commentId, Integer userId);
}
