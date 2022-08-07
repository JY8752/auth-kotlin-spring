package com.example.auth.session.demo

//@AutoService(BlockHoundIntegration::class)
//class CustomBlockHoundIntegration : BlockHoundIntegration {
//    override fun applyTo(builder: BlockHound.Builder) {
//        builder.allowBlockingCallsInside(
//            "org.springframework.session.data.redis.ReactiveRedisSessionRepository",
//            "createSession"
//        ).allowBlockingCallsInside(
//            "org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory",
//            "getReactiveConnection"
//        )
//    }
//}