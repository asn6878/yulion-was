package org.example.yulion.domain.post.service;

import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.yulion.domain.category.domain.Category;
import org.example.yulion.domain.category.repository.CategoryRepository;
import org.example.yulion.domain.part.domain.Part;
import org.example.yulion.domain.part.repository.PartRepository;
import org.example.yulion.domain.post.domain.Post;
import org.example.yulion.domain.post.dto.request.PostCreateRequest;
import org.example.yulion.domain.post.dto.response.PostDetailResponse;
import org.example.yulion.domain.post.repository.PostRepository;
import org.example.yulion.domain.user.domain.User;
import org.example.yulion.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));
        Post post = createPost(request, user);
        Post savedPost = postRepository.save(post);

        return PostDetailResponse.from(savedPost);
    }

    public Post createPost(final PostCreateRequest request, final User user) {
        Category category = categoryRepository.findByName(request.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 없음"));
        Part part = partRepository.findByName(request.getPart())
                .orElseThrow(() -> new IllegalArgumentException("해당 파트 없음"));

        return Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .members(request.getMembers())
                .category(category)
                .writer(user)
                .part(part)
                .build();
    }

}
