# JSONパラメーター、セッションを使用した認証

デフォルトのフォーム認証だとJSON形式のパラメーターリクエストに対応していないのでJSONリクエストできるように。
セッションはアプリ停止させるとリセットされてしまうのでRedisに格納されるように。
せっかくなのでWebFluxを使用してみた。

## login

```
curl -XPOST -i -c cookie.txt -H 'Content-Type: application/json' \
-d '{"mail_address": "test@test.com", "password": "password"}' \
localhost:8080/login
```

## hello

```
curl -i -b cookie.txt localhost:8080/hello
```

## 参考
WebFlux + Spring Security めっちゃ参考になった
https://nosix.hatenablog.com/entry/2018/07/30/143921

ちょっと難しかったけど参考になった
https://www.greptips.com/posts/1342/

Redisのセッション設定.WebFluxだと色々変わるから注意が必要
https://zenn.dev/daisuzz/scraps/a1d95e1a6e1cab