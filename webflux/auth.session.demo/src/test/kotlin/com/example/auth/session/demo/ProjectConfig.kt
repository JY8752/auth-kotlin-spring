package com.example.auth.session.demo

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.spring.SpringExtension

class ProjectConfig : AbstractProjectConfig() {
    override fun extensions() = listOf(SpringExtension)

//    override suspend fun beforeProject() {
//        println("--------------------------- before project --------------------------------------------------")
//        val builder = BlockHound.builder()
//
//        builder.allowBlockingCallsInside(
//            "org.springframework.session.data.redis.ReactiveRedisSessionRepository",
//            "createSession"
//        ).allowBlockingCallsInside(
//            "org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory",
//            "getReactiveConnection"
//        )
//
//        val serviceLoader = ServiceLoader.load(
//            BlockHoundIntegration::class.java
//        )
//
//        Stream
//            .concat(
//                StreamSupport.stream(serviceLoader.spliterator(), false), Stream.of(
//                    CoroutinesBlockHoundIntegration(),
//                    StandardOutputIntegration(),
//                    ReactorIntegration(),
//                    RxJava2Integration()
//                )
//            )
//            .sorted()
//            .forEach { integration: BlockHoundIntegration? ->
//                builder.with(
//                    integration
//                )
//            }
//        builder.install()
//    }
}