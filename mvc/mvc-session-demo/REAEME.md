# MVC Spring Security Demo

Spring application(API) + Spring Security(Session)のデモアプリ

## login

```
curl -X POST -H "Content-Type: application/json" -d '{"email": "user", "password": "password"}' localhost:8080/login -i -c cookie.txt

HTTP/1.1 200 
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Set-Cookie: SESSION=ZDE2NTkxNzQtYzk3NC00MDcxLTg4ZWQtNTFmYjg5NWRlOTZh; Path=/; HttpOnly; SameSite=Lax
Content-Length: 0
Date: Tue, 09 Aug 2022 15:19:59 GMT
```

## hello

```
[~/work/myapp/study/auth-kotlin-spring/mvc/mvc-session-demo] % curl -i -b cookie.txt localhost:8080/hello                                                                                           

HTTP/1.1 200 
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: text/plain;charset=UTF-8
Content-Length: 5
Date: Tue, 09 Aug 2022 15:22:26 GMT

Hello%    
```

## 参考

AuthenticationManagerの登録の仕方がよくわからず
https://stackoverflow.com/questions/72381114/spring-security-upgrading-the-deprecated-websecurityconfigureradapter-in-spring

Spring Security 5.7についてだいぶ実装を追っている
https://zenn.dev/misaka/scraps/3ce785913f1bc3

アーキテクチャ見ないとよくわからん
https://spring.pleiades.io/spring-security/reference/servlet/authentication/architecture.html#servlet-authentication-authenticationmanager

sessionログインについて
https://www.greptips.com/posts/1342/