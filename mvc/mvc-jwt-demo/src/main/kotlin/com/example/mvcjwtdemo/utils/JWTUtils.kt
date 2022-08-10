package com.example.mvcjwtdemo.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.*

class JWTUtils {
    companion object {
        private const val secret = "ThisIsSecretForJWTHS512SignatureAlgorithmThatMUSTHave64ByteLength"
        private const val expirationTime = "28800" //sec

        private val key by lazy { Keys.hmacShaKeyFor(secret.toByteArray()) }

        /**
         * tokenからClaim(キーと値のペア)を取得する
         */
        fun getAllClaimsFromToken(token: String): Claims? {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
        }

        /**
         * トークンからユーザー名を取得する
         */
        fun getUsernameFromToken(token: String): String {
            return getAllClaimsFromToken(token)?.subject ?: ""
        }

        /**
         * トークンから有効期限を取得する
         */
        fun getExpirationDateFromToken(token: String): Date? {
            return getAllClaimsFromToken(token)?.expiration
        }

        /**
         * トークンを作成する
         */
        fun generateToken(username: String): String {
            val expirationTimeLong = expirationTime.toLong()
            val createdDate = Date()
            val expirationDate = Date(createdDate.time + expirationTimeLong * 1000)

            return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact()
        }

        /**
         * トークンが有効か検証する
         */
        fun validateToken(token: String) = !isTokenExpired(token)

        private fun isTokenExpired(token: String): Boolean {
            val expiration = getExpirationDateFromToken(token) ?: run {
                return true
            }
            return expiration.before(Date())
        }
    }
}