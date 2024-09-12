package com.arun.SpringSecurity.appSecurity.repositories;

import com.arun.SpringSecurity.appSecurity.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
