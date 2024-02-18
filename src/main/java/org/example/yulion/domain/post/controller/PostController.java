package org.example.yulion.domain.post.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.yulion.domain.post.dto.request.PostCreateRequest;
import org.example.yulion.domain.post.dto.response.PostCommonListResponse;
import org.example.yulion.domain.post.dto.response.PostCommonSummaryResponse;
import org.example.yulion.domain.post.dto.response.PostDetailResponse;
import org.example.yulion.domain.post.dto.response.PostEducationListResponse;
import org.example.yulion.domain.post.service.PostService;
import org.example.yulion.global.auth.userdetails.CustomUserDetails;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1/posts")
@RestController
@Tag(name = "Post", description = "게시글 관련 API")
public class PostController {
/*
     필요한 API
     1. 게시글 생성
     2. 게시글 조회 (상세 조회)
     3. 게시글 수정
     4. 게시글 삭제
     5. 게시글 목록 조회
         5-1. id + 파트 + 제목 + 작성자 + 작성일 + 조회수 (Notice, 커뮤니티) (Common)
         5-2. id + 파트 + 제목 +  팀원 (스터디, 팀빌딩, Homework) (Education)

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
    @Operation(summary = "게시글 생성", description = "category ID는 다음과 같습니다. 1: 공지사항, 2: 커뮤니티, 3: 스터디, 4: 팀빌딩, 5: 과제<br>part ID는 다음과 같습니다. 1:BE, 2:FE, 3:UI/UX, 4:ALL")
    @PostMapping("")
    public ResponseEntity<PostDetailResponse> createPost(@RequestBody PostCreateRequest request,
                                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        PostDetailResponse response = postService.addPost(request, userDetails.getId());

        return ResponseEntity.ok(response);
    }

    // 2. 게시글 조회 (상세 조회)
    @Operation(summary = "게시글 조회 (상세 조회)")
    @GetMapping("/{id}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable Long id) {
        PostDetailResponse response = postService.getPost(id);

        return ResponseEntity.ok(response);
    }

    // 3. 게시글 수정
    @Operation(summary = "게시글 수정")
    @PutMapping("/{id}")
    public ResponseEntity<PostDetailResponse> modifyPost(@PathVariable Long id,
                                                         @RequestBody PostCreateRequest request,
                                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        PostDetailResponse response = postService.modifyPost(id, request, userDetails.getId());

        return ResponseEntity.ok(response);
    }

    // 4. 게시글 삭제
    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDetailResponse> deletePost(@PathVariable Long id,
                                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        PostDetailResponse response = postService.deletePost(id, userDetails.getId());

        return ResponseEntity.ok(response);
    }

    // 5-1-1. 게시글 목록조회 (공지사항)
    @Operation(summary = "게시글 목록 조회 (공지사항)")
    @GetMapping("/notice") // page => 페이지 번호, criteria => 정렬 기준 (기본은 작성일자)
    public ResponseEntity<PostCommonListResponse> getNoticeList(Pageable pageable) {
        Long cId = 1L;
        PostCommonListResponse response = postService.getCommonList(pageable, cId);

        return ResponseEntity.ok(response);
    }

    // 5-1-2. 게시글 목록조회 (커뮤니티)
    @Operation(summary = "게시글 목록 조회 (커뮤니티)")
    @GetMapping("/community")
    public ResponseEntity<PostCommonListResponse> getCommunityList(Pageable pageable) {
        Long cId = 2L;
        PostCommonListResponse response = postService.getCommonList(pageable, cId);

        return ResponseEntity.ok(response);
    }

    // 5-2-1. 게시글 목록조회 (스터디)
    @Operation(summary = "게시글 목록 조회 (스터디)")
    @GetMapping("/study")
    public ResponseEntity<PostEducationListResponse> getStudyList(Pageable pageable) {
        Long cId = 3L;
        PostEducationListResponse response = postService.getEducationList(pageable, cId);

        return ResponseEntity.ok(response);
    }

    // 5-2-2. 게시글 목록조회 (팀빌딩)
    @Operation(summary = "게시글 목록 조회 (팀빌딩)")
    @GetMapping("/team")
    public ResponseEntity<PostEducationListResponse> getTeamList(Pageable pageable) {
        Long cId = 4L;
        PostEducationListResponse response = postService.getEducationList(pageable, cId);

        return ResponseEntity.ok(response);
    }

    // 5-2-3. 게시글 목록조회 (과제)
    @Operation(summary = "게시글 목록 조회 (과제)")
    @GetMapping("/homework")
    public ResponseEntity<PostCommonListResponse> getHomeworkList(Pageable pageable) {
        Long cId = 5L;
        PostCommonListResponse response = postService.getCommonList(pageable, cId);

        return ResponseEntity.ok(response);
    }

    //
}
