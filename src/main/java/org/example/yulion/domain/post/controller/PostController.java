package org.example.yulion.domain.post.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.yulion.domain.post.dto.request.PostCreateRequest;
import org.example.yulion.domain.post.dto.response.PostCommonListResponse;
import org.example.yulion.domain.post.dto.response.PostCommonSummaryResponse;
import org.example.yulion.domain.post.dto.response.PostDetailResponse;
import org.example.yulion.domain.post.service.PostService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1/posts")
@RestController
public class PostController {
/*
     필요한 API
     1. 게시글 생성
     2. 게시글 조회 (상세 조회)
     3. 게시글 수정
     4. 게시글 삭제
     5. 게시글 목록 조회
         5-1. id + 파트 + 제목 + 작성자 + 작성일 + 조회수 (Notice, 커뮤니티)
         5-2. id + 파트 + 제목 +  팀원 (스터디, 팀빌딩, Homework)

     6. 게시글 요약 목록 조회 (세가지)
         6-1. 제목 + 날짜 (Notice, 커뮤니티, Q&A, Homework))
         6-2. 제목 + 멘토 (스터디)
         6-3. 제목 + 팀원 (팀빌딩)

*/

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 1. 게시글 생성
    @PostMapping("")
    public ResponseEntity<PostDetailResponse> createPost(@RequestBody PostCreateRequest request) {
        PostDetailResponse response = postService.addPost(request);

        return ResponseEntity.ok(response);
    }

    // 2. 게시글 조회 (상세 조회)
    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable Long id) {
        PostDetailResponse response = postService.getPost(id);

        return ResponseEntity.ok(response);
    }

    // 3. 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<PostDetailResponse> modifyPost(@PathVariable Long id, @RequestBody PostCreateRequest request) {
        PostDetailResponse response = postService.modifyPost(id, request);

        return ResponseEntity.ok(response);
    }

    // 4. 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        PostDetailResponse response = postService.deletePost(id);

        return ResponseEntity.ok(response);
    }

    // 5. 게시글 목록조회
    @GetMapping("/notice") // page => 페이지 번호, criteria => 정렬 기준 (기본은 작성일자)
    public ResponseEntity<?> getNoticeList(@RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
                                                                   @RequestParam(required = false, defaultValue = "createAt", value = "criteria") String criteria,
                                                                   @RequestParam(required = false, defaultValue = "10", value = "size") int size) {
        Pageable pageable = PageRequest.of(pageNo, size, Sort.by(criteria).descending());
        PostCommonListResponse response = postService.getNoticeList(pageable);

        return ResponseEntity.ok(response);
    }
}
