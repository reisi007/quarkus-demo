package pictures.reisinger.demo

import io.quarkus.test.junit.QuarkusTestProfile

class DevTestProfile : QuarkusTestProfile {
    override fun getConfigProfile() = "dev,test"
}

class ProdTestProfile : QuarkusTestProfile {
    override fun getConfigProfile() = "prod,test"
}
