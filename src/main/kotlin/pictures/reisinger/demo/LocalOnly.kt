package pictures.reisinger.demo

import io.quarkus.arc.profile.UnlessBuildProfile
import jakarta.interceptor.AroundInvoke
import jakarta.interceptor.Interceptor
import jakarta.interceptor.InterceptorBinding
import jakarta.interceptor.InvocationContext
import jakarta.ws.rs.NotFoundException


@InterceptorBinding
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.CLASS
)
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalOnly


@LocalOnly
@Interceptor
@UnlessBuildProfile("dev")
class LocalOnlyInterceptor {
    @AroundInvoke
    @Throws(Exception::class)
    fun checkProfile(ctx: InvocationContext?): Any {
        throw NotFoundException()
    }
}
