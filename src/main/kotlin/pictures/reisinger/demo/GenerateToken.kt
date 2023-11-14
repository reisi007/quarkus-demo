package pictures.reisinger.demo

import io.smallrye.jwt.build.Jwt
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import java.time.Duration

@LocalOnly
@Path("/local")
class GenerateTokenResource {

    @POST()
    @Path("/token")
    @Produces(MediaType.TEXT_PLAIN)
    fun devToken() = Jwt.issuer("https://local.dev")
        .upn("jdoe@quarkus.io")
        .groups(setOf("User", "Admin"))
        .expiresIn(Duration.ofHours(15))
        .sign()

}
