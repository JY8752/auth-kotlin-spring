# JWTを使用した認証機能

## login

```
[~/work/myapp/study/auth-kotlin-spring/auth.jwt.demo] % curl -XPOST -i localhost:8080/login -H "Content-Type: application/json" -d '{"username": "user", "password": "password"}'                                                                                                           

HTTP/1.1 200 OK
Content-Type: text/plain;charset=UTF-8
Content-Length: 172
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1 ; mode=block
Referrer-Policy: no-referrer

eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjYwMDA3MDI4LCJleHAiOjE2NjAwMzU4Mjh9.3B-csutSqJ4cvuPWsDZHuxtBjmw-js7FlmVcADofDXCnuFT163Dj-lFrzDeQReFRSFhX7sPEeGFbfem8lafOJQ%     
```

## hello

```
[~/work/myapp/study/auth-kotlin-spring/auth.jwt.demo] % curl localhost:8080/hello -i -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjYwMDAwODM2LCJleHAiOjE2NjAwMjk2MzZ9.K_Khrs5nVR02UQ4BIgGC5OY2tXrlt75o8gfQE7ANbHBExpIqJZNxeDvvdSyWFAsxKxCUtLw-r_uCuy67HZw2Tg"

HTTP/1.1 200 OK
Content-Type: text/plain;charset=UTF-8
Content-Length: 5
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1 ; mode=block
Referrer-Policy: no-referrer

Hello% 
```

## 参考サイト

ほぼそのままkotlinに置き換えさせていただきました
https://ard333.medium.com/authentication-and-authorization-using-jwt-on-spring-webflux-29b81f813e78

