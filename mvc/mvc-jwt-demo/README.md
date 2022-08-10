# MVCのJWT認証

## login

```
[~/work/myapp/study/auth-kotlin-spring/mvc/mvc-jwt-demo] % curl -X POST -H "Content-Type: application/json" -d '{"username": "user", "password": "password"}' localhost:8080/login -i                                                                                                          

HTTP/1.1 200 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjYwMTE0MjYyLCJleHAiOjE2NjAxNDMwNjJ9.tF0Y0leK749jOsEPKRtrcJmHEkKgO_oYtniY6fmqo44sGp3Sg6Po0G5ERi-5g88RrFpuFz0idFreNnq3vox1aA
X-Content-Type-Options: nosniff
```

## hello

```
[~/work/myapp/study/auth-kotlin-spring/mvc/mvc-jwt-demo] % curl localhost:8080/hello -v -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjYwMTE0MjYyLCJleHAiOjE2NjAxNDMwNjJ9.tF0Y0leK749jOsEPKRtrcJmHEkKgO_oYtniY6fmqo44sGp3Sg6Po0G5ERi-5g88RrFpuFz0idFreNnq3vox1aA"

*   Trying ::1:8080...
* Connected to localhost (::1) port 8080 (#0)
> GET /hello HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.77.0
> Accept: */*
> Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjYwMTE0MjYyLCJleHAiOjE2NjAxNDMwNjJ9.tF0Y0leK749jOsEPKRtrcJmHEkKgO_oYtniY6fmqo44sGp3Sg6Po0G5ERi-5g88RrFpuFz0idFreNnq3vox1aA
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< Vary: Origin
< Vary: Access-Control-Request-Method
< Vary: Access-Control-Request-Headers
< Set-Cookie: JSESSIONID=B1AB9DDB852B51FB63A2F7F247765DEA; Path=/; HttpOnly
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 1; mode=block
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 5
< Date: Wed, 10 Aug 2022 07:41:00 GMT
< 
* Connection #0 to host localhost left intact
Hello%    
```

## 参考

大変参考になりました<br>
https://qiita.com/nyasba/items/f9b1b6be5540743f8bac