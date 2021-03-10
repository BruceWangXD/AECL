package com.aecl.demo.controller;

import com.aecl.demo.entity.*;
import com.aecl.demo.security.AdminJwtHelper;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class BackController extends BaseController {

    /**
     * get all received messages by person ID
     *
     * @return user List
     */
    @ApiOperation("get all users")
    @GetMapping("/users")
    public List<PersonInfo> listAllUsers(@RequestHeader("Authorization") String token) {
        Integer id = adminJwtHelper.getUserIDFromToken(token);
        if (id == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        List<PersonInfo> temp = userService.getAllUsers();
        for (PersonInfo personInfo : temp) {
            personInfo.setPassword(null);
        }
        return userService.getAllUsers();
    }

    /**
     * get all courses
     *
     * @return courses List
     */
    @ApiOperation("get all courses")
    @GetMapping("/courses")
    public List<Course> listAllCourses(@RequestHeader("Authorization") String token) {
        Integer id = adminJwtHelper.getUserIDFromToken(token);
        if (id == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        return courseService.getAllCourse();
    }


    /**
     * get all posts
     *
     * @return posts List
     */
    @ApiOperation("get all posts")
    @GetMapping("/posts")
    public List<Post> listAllPosts(@RequestHeader("Authorization") String token) {
        Integer id = adminJwtHelper.getUserIDFromToken(token);
        if (id == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        return postService.getAllPost();
    }

    /**
     * get all exercises
     *
     * @return exercise List
     */
    @ApiOperation("get all exercises")
    @GetMapping("/exercises")
    public List<Exercise> listAllExercise(@RequestHeader("Authorization") String token) {
        Integer id = adminJwtHelper.getUserIDFromToken(token);
        if (id == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        return exerciseService.getAllExercises();
    }

    /**
     * get all comments
     *
     * @return comments List
     */
    @ApiOperation("get all comments")
    @GetMapping("/comments")
    public List<Comment> listAllComments(@RequestHeader("Authorization") String token) {
        Integer id = adminJwtHelper.getUserIDFromToken(token);
        if (id == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        return commentService.getAllComment();
    }

    /**
     * get all exercise categories
     *
     * @return exercise category List
     */
    @ApiOperation("get all comments")
    @GetMapping("/exer_category")
    public List<CateExercise> listAllCategories(@RequestHeader("Authorization") String token) {
        Integer id = adminJwtHelper.getUserIDFromToken(token);
        if (id == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        return categoryService.listAllCategory();
    }

    /**
     * delete a comment
     *
     * @param id
     * @return
     **/
    @ApiOperation("Delete a comment")
    @ApiImplicitParam(name = "id", value = "Comment ID", required = true, dataType = "Long")
    @DeleteMapping("comments")
    public String deleteComment(@RequestParam(value = "id") Integer id, @RequestHeader("Authorization") String token) {
        Integer uid = adminJwtHelper.getUserIDFromToken(token);
        String email = adminJwtHelper.getUsernameFromToken(token);
        if (uid == null || email == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        } else {
            String storedEmail = adminService.getEmailById(uid);
            if (!email.equals(storedEmail)) {
                throw new InsufficientAuthenticationException("Token Corrupted");
            }
        }
        commentService.deleteCommentById(id);
        return null;
    }

    /**
     * delete a post
     *
     * @param id
     * @return
     **/
    @ApiOperation("Delete a post")
    @ApiImplicitParam(name = "id", value = "Post ID", required = true, dataType = "Integer")
    @DeleteMapping("posts")
    public String deletePost(@RequestParam(value = "id") Integer id, @RequestHeader("Authorization") String token) {
        Integer uid = adminJwtHelper.getUserIDFromToken(token);
        String email = adminJwtHelper.getUsernameFromToken(token);
        if (uid == null || email == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        } else {
            String storedEmail = adminService.getEmailById(uid);
            if (!email.equals(storedEmail)) {
                throw new InsufficientAuthenticationException("Token Corrupted");
            }
        }
        postService.deletePostById(id);
        return null;
    }

    /**
     * delete a user
     *
     * @param id
     * @return
     **/
    @ApiOperation("Delete a User")
    @ApiImplicitParam(name = "id", value = "User ID", required = true, dataType = "Integer")
    @DeleteMapping("users")
    public String deleteUser(@RequestParam(value = "id") Integer id, @RequestHeader("Authorization") String token) {
        Integer uid = adminJwtHelper.getUserIDFromToken(token);
        String email = adminJwtHelper.getUsernameFromToken(token);
        if (uid == null || email == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        } else {
            String storedEmail = adminService.getEmailById(uid);
            if (!email.equals(storedEmail)) {
                throw new InsufficientAuthenticationException("Token Corrupted");
            }
        }
        commentService.deleteCommentByUserId(id);
        favExerciseService.deleteFavExerciseByUid(id);
        messageService.deleteMsgByUid(id);
        List<Post> posts = postService.getAllPostByUid(id);
        for (Post post : posts) {
            postService.deletePostById(post.getPostId());
        }
        userService.deleteUserById(id);
        return null;
    }

    /**
     * delete an exercise
     *
     * @param id
     * @return
     **/
    @ApiOperation("Delete an exercise")
    @ApiImplicitParam(name = "id", value = "Exercise ID", required = true, dataType = "Integer")
    @DeleteMapping("exercises")
    public String deleteExercise(@RequestParam(value = "id") Integer id, @RequestHeader("Authorization") String token) {
        Integer uid = adminJwtHelper.getUserIDFromToken(token);
        String email = adminJwtHelper.getUsernameFromToken(token);
        if (uid == null || email == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        } else {
            String storedEmail = adminService.getEmailById(uid);
            if (!email.equals(storedEmail)) {
                throw new InsufficientAuthenticationException("Token Corrupted");
            }
        }
        favExerciseService.deleteFavExerciseByEid(id);
        exerciseService.deleteExerciseId(id);
        return null;
    }


}
