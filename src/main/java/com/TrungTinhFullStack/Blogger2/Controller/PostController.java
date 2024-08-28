package com.TrungTinhFullStack.Blogger2.Controller;

import com.TrungTinhFullStack.Blogger2.Entity.Post;
import com.TrungTinhFullStack.Blogger2.Repository.PostRepository;
import com.TrungTinhFullStack.Blogger2.Service.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

//    @PostMapping
//    public ResponseEntity<?> createPost(@RequestBody Post post) {
//        try {
//            Post createdPost = postService.savePost(post);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
//
//        }catch (Exception e) {
//              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @PostMapping
    public Post createPost(@RequestParam("name") String name,
                           @RequestParam("content") String content,
                           @RequestParam("postedBy") Long userId,
                           @RequestParam("img") MultipartFile img,
                           @RequestParam("tags") List<String> tags,
                           @RequestParam("category") Long category) throws IOException {
        return postService.createPost(name, content, userId, img, tags, category);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId) {
        try{
            Post post = postService.getPostById(postId);
            return ResponseEntity.ok(post);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable Long postId,
            @RequestParam("name") String name,
            @RequestParam("content") String content,
            @RequestParam("postedBy") Long userId,
            @RequestParam(value = "img", required = false) MultipartFile img,
            @RequestParam("tags") List<String> tags,
            @RequestParam("category") Long category) throws IOException {
        try {
            Post updatedPost = postService.updatePost(postId, name, content, userId, img, tags,category);
            return ResponseEntity.ok(updatedPost);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    @GetMapping("/byUser/{userId}")
    public List<Post> getAllPostByUserId(@PathVariable Long userId) {
        return postService.findByPostedById(userId);
    }
    //----------//

    @PutMapping("/{postId}/like")
    public ResponseEntity<?> likePost(@PathVariable Long postId) {
        try {
            postService.likePost(postId);
            return ResponseEntity.ok(new String[]{"Post liked successfully"});
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{postId}/Unlike")
    public ResponseEntity<?> UnlikePost(@PathVariable Long postId) {
        try {
            postService.unLikePost(postId);
            return ResponseEntity.ok(new String[]{"Post Unliked successfully"});
        }catch(EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchByName(@PathVariable String name) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(postRepository.findByNameContaining(name));
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/newPost")
    public List <Post> findLast3Posts() {
        return postRepository.findLast3Posts();
    }
}
