package org.example.yulion.domain.post.repository;

import org.example.yulion.domain.category.domain.Category;
import org.example.yulion.domain.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    Page<Post> findAllByCategoryOrderByCreateAt(Category category, Pageable pageable);
}
