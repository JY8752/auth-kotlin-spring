package com.example.auth.session.demo

import io.kotest.core.spec.style.StringSpec
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Mono
import java.time.Duration

@SpringBootTest
@AutoConfigureWebTestClient
internal class SpringSecurityTest(
    private val webTestClient: WebTestClient
) : StringSpec({

    beforeSpec {
//        val builder = BlockHound.builder()
//
//        builder.allowBlockingCallsInside(
//            "org.springframework.session.data.redis.ReactiveRedisSessionRepository",
//            "createSession"
//        ).allowBlockingCallsInside(
//            "org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory",
//            "getReactiveConnection"
//        )
//            .with(CoroutinesBlockHoundIntegration())
//            .with(StandardOutputIntegration())
//            .with(ReactorIntegration())
//            .with(RxJava2Integration())
//
//        val serviceLoader = ServiceLoader.load(
//            BlockHoundIntegration::class.java
//        )
//
//        Stream
//            .concat(
//                StreamSupport.stream(serviceLoader.spliterator(), false), Stream.of()
//            )
//            .sorted()
//            .forEach { integration: BlockHoundIntegration? ->
//                builder.with(
//                    integration
//                )
//            }
//        builder.install()
    }

    beforeTest {
        //@WithMockUserがないので自前で認証情報差し込む
        SecurityContextHolder.getContext().authentication = PreAuthenticatedAuthenticationToken(null, null, emptyList())
    }

    "hello" {
        webTestClient.get().uri("/hello")
            .exchange()
            .expectStatus().isOk
            .expectBody<String>().isEqualTo("Hello")
    }

    "blocking hello" {
        Thread.sleep(1000)
        webTestClient.get().uri("/blocking/hello")
            .exchange()
            .expectStatus().isOk
            .expectBody<String>().isEqualTo("Blocking Hello")
    }
    "test" {
        Mono.delay(Duration.ofSeconds(1))
            .doOnNext { _ ->
                try {
                    Thread.sleep(10)
                } catch (e: InterruptedException) {
                    throw RuntimeException(e)
                }
            }
            .block()
    }
})