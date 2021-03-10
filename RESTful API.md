# RESTful API Ver: 1.0

### Back-Controller

```
GET /admin/users - get all users
GET /admin/courses - get all courses
GET /admin/posts - get all posts
GET /admin/exercises - get all exercises
GET /admin/comments - get all comments
GET /admin/exer_category - get all exercise categor

POST /admin/courses - add a new course
POST /admin/exercises - add a new exercise
POST /admin/exercises - add a new exercise_category

DELETE /admin/users - delete a user
DELETE /admin/posts - delete a post
DELETE /admin/exercises - delete an exercise
DELETE /admin/comments - delete a comment

```

### Front-Controller

Authentication

```
GET /api/auth/current - get current user details
POST /api/auth/login - login
POST /api/auth/adminLogin - Admin Login
DELETE /api/auth/logout - logout
```

Courses

```
GET /api/courses - get all courses
GET /api/courses/hot - get top 5 hot courses
```

Post

```
GET /api/posts - get all discussions (?cid=2)
GET /api/posts/pinned - get all pinned posts (?cid=2)
GET /api/posts/hottest - get all hottest posts (?cid=2)
GET /api/posts/:id - get discussion by id
POST /api/posts - add a new post
```

Exercise Category

```
GET /api/exercise_category - get all exercise category
GET /api/exercise_category/:id - get exercise category by id
```

Exercise

```
GET /api/exercises - get all exercises（?categories=2）
GET /api/exercises/:id - get exercise by id
POST /api/exercises/fav - add exercise to favourite
```

Comment

```
GET /api/comments/:id - get all comments by post id
POST /api/comments/:id - add comment to post
```

Users: token authentication

```
GET /api/users/list - get users(name, id)
POST /api/users - register a new user
PATCH /api/users - update a user
GET /api/users/exercises - get favourite exercises
GET /api/users/courses - get user courses
PATCH /api/users/courses - drop a user's course (?cid=1)
```

Messages: token authentication

```
GET /api/messages - get all messages received
GET /api/messages/:id - get specific message received by ID
POST /api/messages - add a message
DELETE /api/messages/:id - delete a message
```