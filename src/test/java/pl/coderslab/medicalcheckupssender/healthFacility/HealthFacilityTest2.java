package pl.coderslab.medicalcheckupssender.healthFacility;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.coderslab.medicalcheckupssender.HealthFacility.HealthFacility;
import pl.coderslab.medicalcheckupssender.HealthFacility.HealthFacilityRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
public class HealthFacilityTest2 {

    @Autowired
    private HealthFacilityRepository healthFacilityRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void find_by_cityOfHealthFacility_then_return_healthFacility() {
        //given
        HealthFacility healthFacility = new HealthFacility();
        healthFacility.setCity("Warsaw");
        testEntityManager.persist(healthFacility);
        //when
        HealthFacility result = healthFacilityRepository.findHealthFacilityByCity("Warsaw");
        //then
        assertEquals(healthFacility.getCity(), result.getCity());
    }

    @Test
    public void given_pz_and_pzu_then_find_pz_should_return_two_elements() {
        //given
        HealthFacility pz = testEntityManager.persistAndFlush(new HealthFacility("pz"));
        HealthFacility pzu = testEntityManager.persistAndFlush(new HealthFacility("pzu"));
        //when
        List<HealthFacility> result = healthFacilityRepository.findHealthFacilityByNameOfHealthFacility("pz");
        //then
        assertThat(result).containsExactly(pz, pzu);
    }

    @Test
    public void find_by_nameOfHealthFacility_then_return_HealthFacility() {
        //given
        HealthFacility healthFacility = new HealthFacility();
        healthFacility.setNameOfHealthFacility("DR");
        testEntityManager.persist(healthFacility);
        //when
        HealthFacility result = healthFacilityRepository.findHealthFacilityByNameOfHealthFacility("Dr");
        //then
        assertEquals(healthFacility.getNameOfHealthFacility(), result.getNameOfHealthFacility());
    }

    @Test
    public void given_pzu_then_luxmed_shoul_be_null() {
        //given
        HealthFacility healthFacility = new HealthFacility();
        healthFacility.setNameOfHealthFacility("pzu");
        testEntityManager.persist(healthFacility);
        //when
        HealthFacility result = healthFacilityRepository.findHealthFacilityByNameOfHealthFacility("luxmed");
        //then
        assertNull(result);
    }

    @Test
    public void given_pz_and_pzu_then_find_pz_should_return_two_elements(){
        //given
        HealthFacility pz = testEntityManager.persistAndFlush(new HealthFacility("pz"));
        HealthFacility pzu = testEntityManager.persistAndFlush(new HealthFacility("pzu"));
        //when
        List<HealthFacility> result = healthFacilityRepository.findHealthFacilityByNameOfHealthFacility("pz");
        //then
        assertThat(result).containsExactly(pz, pzu);
    }
}
