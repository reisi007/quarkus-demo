package pictures.reisinger.demo

import io.smallrye.jwt.build.Jwt
import java.time.Duration

fun main() {
    Jwt.issuer("https://local.dev")
        .upn("jdoe@quarkus.io")
        .groups(setOf("User", "Admin"))
        .expiresIn(Duration.ofHours(15))
        .sign()
        .apply { println(this) }
}
