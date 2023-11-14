package pictures.reisinger.demo

import assertk.assertFailure
import assertk.assertions.isInstanceOf
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.TestProfile
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.NotFoundException
import org.junit.jupiter.api.Test

@QuarkusTest
@TestProfile(DevTestProfile::class)
class LocalOnlyDevTest {

    @Inject
    private lateinit var testInterface: LocalTestInterface

    @Test
    fun callAllowed() {
        testInterface.methodToIntercept()
    }
}

@QuarkusTest
@TestProfile(ProdTestProfile::class)
class LocalOnlyProdTest {

    @Inject
    private lateinit var testInterface: LocalTestInterface

    @Test
    fun callNotAllowed() {
        assertFailure {
            testInterface.methodToIntercept()
        }.isInstanceOf(NotFoundException::class)
    }
}


internal interface LocalTestInterface {

    fun methodToIntercept() {}
}

@LocalOnly
@ApplicationScoped
internal class LocalTestInterfaceImpl : LocalTestInterface
