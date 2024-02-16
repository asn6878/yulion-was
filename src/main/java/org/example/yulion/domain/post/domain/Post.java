package org.example.yulion.domain.post.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.example.yulion.domain.category.domain.Category;
import org.example.yulion.domain.common.BaseTimeEntity;
import org.example.yulion.domain.part.domain.Part;
import org.example.yulion.domain.user.domain.User;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "part_id")
    private Part part;

    @Column(nullable = false)
    private String title;

    private String content;

    @ColumnDefault("0")
    private Long viewCnt;

    @Column(nullable = true)
    private String members;

    @Builder
    private Post (String title, String content, Long viewCnt, String members, User writer, Category category, Part part){
        this.title = title;
        this.content = content;
        this.viewCnt = viewCnt;
        this.members = members;
        this.writer = writer;
        this.category = category;
        this.part = part;
    }

    // 연관관계 매핑 메소드
//    public void setWriter(User user){
//        this.writer = user;
//        user.getPosts().add(this);
//    }
    public void modifyPost(String title, String content, String members, Category category, Part part){
        this.title = title;
        this.content = content;
        this.members = members;
        this.category = category;
        this.part = part;
    }

}
