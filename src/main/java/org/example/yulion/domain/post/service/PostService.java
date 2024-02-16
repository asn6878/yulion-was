package org.example.yulion.domain.post.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.example.yulion.domain.category.domain.Category;
import org.example.yulion.domain.category.repository.CategoryRepository;
import org.example.yulion.domain.part.domain.Part;
import org.example.yulion.domain.part.repository.PartRepository;
import org.example.yulion.domain.post.domain.Post;
import org.example.yulion.domain.post.dto.request.PostCreateRequest;
import org.example.yulion.domain.post.dto.response.PostCommonListResponse;
import org.example.yulion.domain.post.dto.response.PostCommonSummaryResponse;
import org.example.yulion.domain.post.dto.response.PostDetailResponse;
import org.example.yulion.domain.post.repository.PostRepository;
import org.example.yulion.domain.user.domain.User;
import org.example.yulion.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PartRepository partRepository;

    // 1. Controller => createPost 메서드에 매핑되는 Service 로직
    public PostDetailResponse addPost(PostCreateRequest request) {
        // 인증 구현전 테스팅용 유저 정보 가져오기
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));

        Post post = createPost(request, user);
        Post savedPost = postRepository.save(post);

        log.info("savedPost : {}", savedPost);

        return PostDetailResponse.from(savedPost);
    }

    public Post createPost(final PostCreateRequest request, final User user) {
        Category category = categoryRepository.findById(request.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 없음"));
        Part part = partRepository.findById(request.getPart())
                .orElseThrow(() -> new IllegalArgumentException("해당 파트 없음"));

        return Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .members(request.getMembers())
                .part(part)
                .category(category)
                .writer(user)
                .build();
    }

    public PostDetailResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));

        return PostDetailResponse.from(post);
    }

    public PostDetailResponse modifyPost(Long id, PostCreateRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));
        Category category = categoryRepository.findById(request.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 없음"));
        Part part = partRepository.findById(request.getPart())
                .orElseThrow(() -> new IllegalArgumentException("해당 파트 없음"));

        post.modifyPost(request.getTitle(), request.getContent(), request.getMembers(), category, part);
        final Post savedPost = postRepository.save(post);

        return PostDetailResponse.from(savedPost);
    }

    public PostDetailResponse deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));
        postRepository.delete(post);

        return PostDetailResponse.from(post);
    }

    public PostCommonListResponse getNoticeList(Pageable pageable){
        Category category = categoryRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 없음"));
        Page<Post> posts = postRepository.findAllByCategoryOrderByCreateAt(category, pageable);

        List<PostCommonSummaryResponse> postResponses = posts.getContent().stream()
                .map(PostCommonSummaryResponse::from)
                .collect(Collectors.toList());

        return PostCommonListResponse.from(postResponses, posts.getNumber(), posts.getTotalPages());
    }

    public PostCommonListResponse getCommunityList(Pageable pageable){
        Category category = categoryRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 없음"));
        Page<Post> posts = postRepository.findAllByCategoryOrderByCreateAt(category, pageable);

        List<PostCommonSummaryResponse> postResponses = posts.getContent().stream()
                .map(PostCommonSummaryResponse::from)
                .collect(Collectors.toList());

        return PostCommonListResponse.from(postResponses, posts.getNumber(), posts.getTotalPages());
    }
}
