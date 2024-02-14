package org.example.yulion.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
public class PostCreateRequest {
    @NotBlank
    private String title;

    private String content;
    private String members;
    private String category;
    private String part;

    @Builder
    public PostCreateRequest(String title, String content, String members, String category, String part) {

        this.title = title;
        this.content = content;
        this.members = members;
        this.category = category;
        this.part = part;
    }
}
