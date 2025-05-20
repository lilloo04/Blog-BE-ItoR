package com.blog.post.domain;

import java.util.List;

public interface PostRepository {

    // 게시물 저장
    void save(Post post);

    // 게시물 내용 저장
    void saveContents(List<PostContent> contents);

    //게시물 전체 조회
    List<Post> findAll();

    // 게시물 단건 조회
    Post findById(int postId);

    // 게시물 삭제
    void delete(int postId, int userId);

    // 게시물 내용 삭제
    void deleteContents(int postId, int userId);
}
