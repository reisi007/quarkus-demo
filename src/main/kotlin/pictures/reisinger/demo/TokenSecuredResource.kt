package pictures.reisinger.demo

import jakarta.annotation.security.PermitAll
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.InternalServerErrorException
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.SecurityContext
import org.eclipse.microprofile.jwt.JsonWebToken

@Path("/secured")
class TokenSecuredResource {
    @Inject
    lateinit var jwt: JsonWebToken

    @GET
    @Path("permit-all")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(@Context ctx: SecurityContext): String {
        return getResponseString(ctx)
    }

    private fun getResponseString(ctx: SecurityContext, moreInfo: StringBuilder.() -> Unit = {}): String {
        val name = getNameFromSecurityContext(ctx)
        return buildString {
            append("""hello ${name}, isHttps: ${ctx.isSecure}, authScheme: ${ctx.authenticationScheme}, hasJWT: ${jwt.isPresent}""")
            moreInfo()
        }
    }

    private fun getNameFromSecurityContext(ctx: SecurityContext): String = if (ctx.userPrincipal == null) {
        "anonymous"
    } else if (ctx.userPrincipal.name != jwt.name) {
        throw InternalServerErrorException("Principal and JsonWebToken names do not match")
    } else {
        ctx.userPrincipal.name
    }

    private val JsonWebToken.isPresent
        get() = claimNames != null
}
