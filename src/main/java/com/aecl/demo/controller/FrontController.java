package com.aecl.demo.controller;


import com.aecl.demo.entity.*;
import com.aecl.demo.util.BCryptUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class FrontController extends BaseController {

    /**
     * Get All Courses
     *
     * @return course list json
     */
    @ApiOperation("Get All Courses")
    @GetMapping(value = "/courses", produces = "application/json")
    // Get all courses
    public List<Course> listAllCourses() {
        List<Course> courses = courseService.getAllCourse();
        return courses;
    }

    /**
     * get top 5 hot courses
     *
     * @return Hot courses list
     */
    @ApiOperation("Get Top 5 Hottest Courses")
    @GetMapping(value = "/courses/hot", produces = "application/json")
    public List<Course> listAllHotCourses() {
        return courseService.getHotCourses();
    }


    /**
     * get all posts by Course ID
     *
     * @return posts list
     */
    @ApiOperation("Get All Posts by CourseID")
    @GetMapping(value = "/posts", produces = "application/json")
    // CID : course id
    public List<Post> listAllPosts(@RequestParam Integer cid) {
        return postService.getAllPostByCid(cid);
    }

    /**
     * get all pinned posts by Course ID
     *
     * @return posts list
     */
    @ApiOperation("Get All Pinned Posts by CourseID")
    @GetMapping(value = "/posts/pinned", produces = "application/json")
    // to achieve all pinPost
    public List<Post> listAllPinnedPost(@RequestParam Integer cid) {
        return postService.getAllPinnedPostByCid(cid);
    }


    /**
     * Get the specific post by Post ID
     *
     * @param id Post Identifier
     * @return specific post
     */
    @ApiOperation("Get the specific post by Post ID")
    @GetMapping("/posts/{id}")
    public Post getPostById(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        Integer uid = jwtHelp.getUserIDFromToken(token);
        // catch can't get users id
        if (uid == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        int cid = postService.getCourseIdByPid(id);
        // check post ID and courses ID
        boolean flag = userService.checkByPidCid(uid, cid);
        if (flag) {
            // Got successfully
            return postService.getOneByPid(id);
        } else { // deny access as can't authenticate users
            throw new AuthenticationCredentialsNotFoundException("401 Forbidden");
        }
    }


    /**
     * Add a new post
     */
    @ApiOperation("Add a new post")
    @PostMapping("/posts")
    public String addMessage(@RequestHeader("Authorization") String token, Post post) {
        Integer uid = jwtHelp.getUserIDFromToken(token);
        if (uid == null) { // deny access as can't authenticate users
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        boolean flag = userService.checkByPidCid(uid, post.getPcourseId());
        if (flag) {
            // Set all the details of new Post : time, person ID
            post.setPostTime(new Date());
            post.setPpersonId(uid);
            postService.addPost(post);
            return null;
        } else {
            // Set fault
            throw new AuthenticationCredentialsNotFoundException("401 Forbidden");
        }

    }


    /**
     * get all exercise categories
     *
     * @return exercise category list
     */
    @ApiOperation("Get All exercise category")
    @GetMapping(value = "/exercise_category", produces = "application/json")
    // Simply Show all exercise category
    public List<CateExercise> listAllExerciseCategory() {
        return categoryService.listAllCategory();
    }


    /**
     * get exercise category by id
     *
     * @param id Category ID
     * @return exercise category info
     */
    @ApiOperation("get category info by category ID")
    @GetMapping("/exercise_category/{id}")
    // Return onr by given ID of the category
    public CateExercise getCategoryById(@PathVariable Integer id) {
        return categoryService.getOneById(id);
    }

    @ApiOperation("Add an exercise to favourite")
    @PostMapping("/exercises/fav")
    public String addFavExercise(@RequestHeader("Authorization") String token, @RequestParam(value = "eid") Integer exerciseId) {
        Integer id = jwtHelp.getUserIDFromToken(token);
        if (id == null) {
            // Couldn't authenticate users
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        // Create new Favourites
        FavExercise favExercise = new FavExercise();
        Integer flag = favExerciseService.getFavExerciseByUidPid(id, exerciseId);
        if (flag == null) {// Haven't created it before
            // Set all details about Favourite ID
            favExercise.setFexerciseId(exerciseId);
            favExercise.setFpersonId(id);
            favExerciseService.addFavExercise(favExercise);
            return null;
        } else {
            return null;
        }
    }

    /**
     * get all exercises
     *
     * @param categoryID category identifier of exercise
     * @return exercises list
     */
    @ApiOperation("Get All exercises")
    @GetMapping(value = "/exercises")
    public List<Exercise> listAllExercises(@RequestParam(value = "id", required = false) Integer categoryID) {
        if (categoryID == null) {
            // IF don't give specific categoryID , lists all users ID
            return exerciseService.getAllExercises();
        } else {
            // Lists the specific exercise
            return exerciseService.getExercisesByCategoryId(categoryID);
        }
    }

    /**
     * get exercise by id
     *
     * @param id Exercise ID
     * @return exercise info
     */
    @ApiOperation("get exercise info by exercise ID")
    @GetMapping("/exercises/{id}")
    public Exercise getExerciseById(@PathVariable int id) {
        return exerciseService.getOneById(id);
    }

    /**
     * Add comment to a post
     */
    @ApiOperation("Add comment to a post")
    @PostMapping("/comments/{id}")
    public String addPostComment(@RequestHeader("Authorization") String token, @PathVariable Integer id, Comment comment) {
        Integer uid = jwtHelp.getUserIDFromToken(token);
        if (uid == null) {
            // Couldn't authenticate users
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        int cid = postService.getCourseIdByPid(id); // Courses ID
        boolean flag = userService.checkByPidCid(uid, cid);
        if (flag) {
            //Set all details about comment : Time , Person ID
            comment.setCommentTime(new Date());
            comment.setCpersonId(uid);
            comment.setCpostId(id);
            commentService.addComment(comment);
            return null;
        } else {
            // Set fault
            throw new AuthenticationCredentialsNotFoundException("401 Forbidden");
        }
    }

    /**
     * get all comments by post ID
     *
     * @param id Post ID
     * @return comment List
     */
    @ApiOperation("get all comments by post id")
    @GetMapping("/comments/{id}")
    public List<Comment> listAllComment(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        Integer pid = jwtHelp.getUserIDFromToken(token); // Actually User ID
        // // Couldn't authenticate users
        if (pid == null) {
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        int cid = postService.getCourseIdByPid(id);
        boolean flag = userService.checkByPidCid(pid, cid);
        if (flag) {
            // List all comments
            return commentService.getAllCommentByPid(id);
        } else {
            throw new AuthenticationCredentialsNotFoundException("401 Forbidden");
        }

    }


    /**
     * delete a message
     *
     * @param id
     * @return
     **/
    @ApiOperation("Delete a message")
    @ApiImplicitParam(name = "id", value = "Message ID", required = true, dataType = "Long")
    @DeleteMapping("messages/{id}")
    // Delete the message by post ID
    public String deleteMessage(@PathVariable Integer id) {
        messageService.deleteMessageById(id);
        return null;
    }


    @ApiOperation("Add a message")
    @PostMapping("/messages")
    public String addMessage(@RequestHeader("Authorization") String token, Message message) {
        Integer senderId = jwtHelp.getUserIDFromToken(token);
        if (senderId == null) {
            // Couldn't get the user
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        // Set the message details : Time , user ID
        message.setTime(new Date());
        message.setSenderId(senderId);
        messageService.addMessage(message);
        return null;
    }

    /**
     * get all received messages by person ID
     *
     * @return messages received List
     */
    @ApiOperation("get all messages received")
    @GetMapping("/messages")
    public List<Message> listAllRecvMsg(@RequestHeader("Authorization") String token) {
        Integer id = jwtHelp.getUserIDFromToken(token);
        if (id == null) {
            // Couldn't get the user
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        // If successfully get the user, return corresponding messages
        return messageService.getAllRecvMsgByUID(id);
    }

    /**
     * get specific message received by ID
     *
     * @param id message ID
     * @return Message INFO
     */
    @ApiOperation("get specific message received by ID")
    @GetMapping("/messages/{id}")
    public Message getCommentByID(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        return messageService.getOneById(id);
    }


    @ApiOperation("drop a user's course")
    @PatchMapping("/users/courses")
    public String dropUserCourse(@RequestHeader("Authorization") String token, @RequestParam(value = "id") Integer courseId) {
        Integer id = jwtHelp.getUserIDFromToken(token);
        if (id == null) {
            // Couldn't get the user
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        userService.updateCourse(id, courseId);
        return null;
    }

    /**
     * Create a new user by given information
     */
    @ApiOperation("Register a new user")
    @PostMapping("/users")
    public String addUser(PersonInfo personInfo) {
        String pwd = personInfo.getPassword();
        String regex = "^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{6,12}$";
        boolean isMatch = Pattern.matches(regex, pwd);
        if (isMatch) {
            try {
                userService.addUser(personInfo);
                return null;
            } catch (DataIntegrityViolationException e) {
                throw new RuntimeException("Username/Email is already occupied");
            }

        } else {
            throw new RuntimeException("Password should be of length 6-12 with number and lowercase letter and uppercase letter");
        }
    }


    @ApiOperation("get users courses")
    @GetMapping("/users/courses")
    public List<Course> getUserCourses(@RequestHeader("Authorization") String token) {
        Integer id = jwtHelp.getUserIDFromToken(token);
        if (id == null) {
            // Couldn't authenticate the user
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        return userService.getAllUserCourses(id);
    }

    @ApiOperation("get user Favourite Exercises")
    @GetMapping("/users/exercises")
    public List<Exercise> getUserFavExercises(@RequestHeader("Authorization") String token) {
        Integer id = jwtHelp.getUserIDFromToken(token);
        if (id == null) {
            // Couldn't authenticate the user
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        // return the favourite courses belongs to Users
        return favExerciseService.getAllFavExercise(id);
    }


    @ApiOperation("get users name and ID")
    @GetMapping("/users/list")
    public List<PersonInfo> getUsers() {
        return userService.getAllUsersFiltered();
    }


    @ApiOperation("Change User Password")
    @PatchMapping("/users")
    public String changePwd(@RequestHeader("Authorization") String token,
                            @RequestParam(value = "pwd") String pwd,
                            @RequestParam(value = "newPwd") String newPwd) {
        BCryptUtil bCryptUtil = new BCryptUtil(); // A tool to encrypt password
        Integer id = jwtHelp.getUserIDFromToken(token);
        if (id == null) {
            // Couldn't authenticate the user
            throw new InsufficientAuthenticationException("Token Corrupted");
        }
        String regex = "^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{6,12}$";
        boolean isMatch = Pattern.matches(regex, newPwd);
        if (!isMatch) {
            throw new RuntimeException("Password should be of length 6-12 with number and lowercase letter and uppercase letter");
        }

        // Get old password that has been hashed
        String storedHash = userService.getHashById(id);
        // To match the old password
        if (bCryptUtil.matches(pwd, storedHash)) {
            // If matched , then can create new password
            PersonInfo temp = new PersonInfo();
            temp.setPersonId(id);
            // hash the new Password
            String newpwdHash = bCryptUtil.encode(newPwd);
            temp.setPassword(newpwdHash);
            userService.updateUser(temp);
            return null;
        } else {
            throw new RuntimeException("Invalid Crendentials");
        }

    }
}
