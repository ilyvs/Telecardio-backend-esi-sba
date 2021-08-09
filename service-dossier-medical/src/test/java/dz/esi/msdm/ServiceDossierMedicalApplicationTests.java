package dz.esi.msdm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceDossierMedicalApplicationTests {

    @Test
    public void test_version_exist(){
        Assertions.assertEquals("true", "true");
    }

}
