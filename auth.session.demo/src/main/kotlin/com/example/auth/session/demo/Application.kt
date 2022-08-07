package com.example.auth.session.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application

fun main(args: Array<String>) {
//	BlockHound.builder().nonBlockingThreadPredicate { current: Predicate<Thread> ->
//		current.or { thread: Thread -> thread.name == "main" }
//	}
//    .with(CoroutinesBlockHoundIntegration())
//    .with(StandardOutputIntegration())
//    .with(ReactorIntegration())
//    .with(RxJava2Integration())
//    .install()

//    val builder = BlockHound.builder()
//
//    builder.allowBlockingCallsInside(
//        "org.springframework.session.data.redis.ReactiveRedisSessionRepository",
//        "createSession"
//    ).allowBlockingCallsInside(
//        "org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory",
//        "getReactiveConnection"
//    )
//        .with(CoroutinesBlockHoundIntegration())
//        .with(StandardOutputIntegration())
//        .with(ReactorIntegration())
//        .with(RxJava2Integration())
//
//    val serviceLoader = ServiceLoader.load(
//        BlockHoundIntegration::class.java
//    )
//
//    Stream
//        .concat(StreamSupport.stream(serviceLoader.spliterator(), false), Stream.of())
//        .sorted()
//        .forEach { integration: BlockHoundIntegration? ->
//            builder.with(
//                integration
//            )
//        }
//    builder.install()

//	BlockHound.install()
    runApplication<Application>(*args)
}
