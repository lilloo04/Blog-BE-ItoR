package com.blog.post.application;

import com.blog.post.domain.Post;
import com.blog.post.domain.PostContent;
import com.blog.post.domain.PostRepository;
import com.blog.post.domain.PostService;
import com.blog.post.presentation.dto.PostContentDto;
import com.blog.post.presentation.dto.PostRequest;
import com.blog.post.presentation.dto.PostResponse;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostResponse createPost(PostRequest request, Integer userId) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Post post = new Post(
                null,
                userId,
                request.getTitle(),
                now,
                now,
                null
        );
        postRepository.save(post);

        List<PostContent> contents = request.getContents().stream()
                .map(dto -> new PostContent(
                        null,
                        post.getPostId(),
                        userId,
                        dto.getContentType(),
                        dto.getContent(),
                        dto.getOrder()
                )).collect(Collectors.toList());

        post.setContents(contents);
        postRepository.saveContents(contents);

        PostResponse response = new PostResponse();
        response.setPostId(post.getPostId());
        response.setUserId(userId);
        response.setTitle(post.getTitle());
        response.setCreatedAt(post.getCreatedAt().toString());

        List<PostContentDto> contentDtos = contents.stream()
                .map(c -> {
                    PostContentDto dto = new PostContentDto();
                    dto.setContentType(c.getContentType());
                    dto.setContent(c.getContent());
                    dto.setOrder(c.getOrder());
                    return dto;
                }).collect(Collectors.toList());

        response.setContents(contentDtos);
        return response;
    }

    @Override
    public PostResponse getPostById(Integer postId) {
        Post post = postRepository.findById(postId);

        PostResponse response = new PostResponse();
        response.setPostId(post.getPostId());
        response.setUserId(post.getUserId());
        response.setTitle(post.getTitle());
        response.setCreatedAt(post.getCreatedAt().toString());

        List<PostContentDto> contentDtos = post.getContents().stream()
                .map(c -> {
                    PostContentDto dto = new PostContentDto();
                    dto.setContentType(c.getContentType());
                    dto.setContent(c.getContent());
                    dto.setOrder(c.getOrder());
                    return dto;
                }).collect(Collectors.toList());

        response.setContents(contentDtos);
        return response;
    }

    @Override
    public PostResponse updatePost(Integer postId, PostRequest request, Integer userId) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Post post = new Post(postId, userId, request.getTitle(), null, now, null);
        postRepository.deleteContents(postId, userId);
        postRepository.saveContents(request.getContents().stream()
                .map(dto -> new PostContent(
                        null,
                        postId,
                        userId,
                        dto.getContentType(),
                        dto.getContent(),
                        dto.getOrder()
                )).collect(Collectors.toList())
        );

        Post updated = postRepository.findById(postId);
        return getPostById(updated.getPostId());
    }

    @Override
    public void deletePost(Integer postId, Integer userId) {
        postRepository.deleteContents(postId, userId);
        postRepository.delete(postId, userId);
    }
}