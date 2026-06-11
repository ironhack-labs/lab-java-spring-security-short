# Blog Security Lab

A simple Spring Boot blog content management system with Spring Security.

## Requirements covered

- Author table
- Blog post table
- Relationship between Author and BlogPost
- Public GET routes
- Admin-only POST, PUT, and DELETE routes
- Initial seed data

## Admin user

```text
username: admin
password: 1234
```

## Public routes

```http
GET /api/authors
GET /api/authors/{id}
GET /api/posts
GET /api/posts/{id}
```

## Admin-only routes

```http
POST /api/authors
PUT /api/authors/{id}
DELETE /api/authors/{id}
POST /api/posts
PUT /api/posts/{id}
DELETE /api/posts/{id}
```

## Run

```bash
mvn spring-boot:run
```

## H2 Console

```text
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:blogdb
Username: sa
Password: empty
```
