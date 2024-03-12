package org.example.yulion.domain.comment.dto.response;

import org.example.yulion.domain.comment.domain.Comment;

public record CommentResponse (
        Long id,
        Long userId,
        String content,
        Long parentCommentId
){
    public static CommentResponse from(Comment comment){
        return new CommentResponse(
                comment.getId(),
                comment.getWriter().getId(),
                comment.getContent(),
                comment.getParentComment().getId()
        );
    }
}
