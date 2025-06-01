package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        try {
            Optional<Post> post = postRepository.findById(id);
            if (post.isPresent()) {
                return ResponseEntity.ok(post.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                        "error", "Error 404 not found",
                        "status", "404"
                    ));
        } catch (Exception e) {
            System.err.println("Error getting post: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                        "error", "Error processing request",
                        "status", "500",
                        "message", e.getMessage()
                    ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody Post newPost) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setEmail(newPost.getEmail());
            post.setPasscode(newPost.getPasscode());
            post.setActive(newPost.getActive());
            post.setEcCode(newPost.getEcCode());
            post.setBjValue(newPost.getBjValue());
            return ResponseEntity.ok(postRepository.save(post));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                    "error", "Error 404 not found",
                    "status", "404"
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(post -> {
                    postRepository.delete(post);
                    return ResponseEntity.ok()
                            .body(Map.of(
                                "message", "Post deleted successfully",
                                "status", "200"
                            ));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                            "error", "Error 404 not found",
                            "status", "404"
                        )));
    }

    @GetMapping("/bjValue/{bjValue}")
    public List<Post> getPostsByBjValue(@PathVariable String bjValue) {
        return postRepository.findByBjValue(bjValue);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post newPost) {
        try {
            Post savedPost = postRepository.save(newPost);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                        "message", "Post created successfully",
                        "status", "201",
                        "data", savedPost
                    ));
        } catch (Exception e) {
            System.err.println("Error creating post: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                        "error", "Error creating post",
                        "status", "500",
                        "message", e.getMessage()
                    ));
        }
    }
}