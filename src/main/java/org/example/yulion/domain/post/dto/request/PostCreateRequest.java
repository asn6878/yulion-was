package org.example.yulion.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Getter
public class PostCreateRequest {
    @NotBlank
    private String title;

    private String content;
    private String members;
    private Long category;
    private Long part;

    @Builder
    public PostCreateRequest(String title, String content, String members, Long category, Long part) {
        this.title = title;
        this.content = content;
        this.members = members;
        this.category = category;
        this.part = part;
    }
}
