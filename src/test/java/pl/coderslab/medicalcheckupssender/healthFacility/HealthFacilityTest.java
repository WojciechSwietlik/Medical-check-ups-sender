package pl.coderslab.medicalcheckupssender.healthFacility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.coderslab.medicalcheckupssender.HealthFacility.HealthFacility;
import pl.coderslab.medicalcheckupssender.HealthFacility.HealthFacilityRepository;
import pl.coderslab.medicalcheckupssender.HealthFacility.HealthFacilityService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(JUnitPlatform.class)
@SelectClasses({HealthFacilityService.class})
public class HealthFacilityTest {

    HealthFacilityRepository repository;

    @BeforeEach
    void setup() {
        repository = new HealthFacilityRepository() {
            @Override
            public List<HealthFacility> findHealthFacilityByCity(String city) {
                return null;
            }

            @Override
            public List<HealthFacility> findAll() {
                return null;
            }

            @Override
            public List<HealthFacility> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<HealthFacility> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public <S extends HealthFacility> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends HealthFacility> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends HealthFacility> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<HealthFacility> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public HealthFacility getOne(Long aLong) {
                return null;
            }

            @Override
            public HealthFacility getById(Long aLong) {
                return null;
            }

            @Override
            public HealthFacility getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends HealthFacility> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends HealthFacility> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<HealthFacility> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends HealthFacility> S save(S entity) {
                return null;
            }

            @Override
            public Optional<HealthFacility> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(HealthFacility entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends HealthFacility> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends HealthFacility> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends HealthFacility> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends HealthFacility> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends HealthFacility> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends HealthFacility, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };

    }

    @Test
    void shouldAddNewHealthFacilities() {
        //given
        HealthFacility healthFacility = new HealthFacility("DR TEST", "TestowaStreet", "1", "00-001", "Warsaw", "01 000 00 00");
        //when
        HealthFacility saved = repository.save(healthFacility);
        //then
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(healthFacility.getNameOfHealthFacility(), saved.getNameOfHealthFacility());
        assertEquals(healthFacility.getStreetName(), saved.getStreetName());
        assertEquals(healthFacility.getHouseNumber(), saved.getHouseNumber());
        assertEquals(healthFacility.getZipCode(), saved.getZipCode());
        assertEquals(healthFacility.getCity(), saved.getCity());
        assertEquals(healthFacility.getPhoneNumber(), saved.getPhoneNumber());
    }

    @Test
    void shouldAssignUniqueIdsToHealthFacility() {
        // given
        HealthFacility healthFacility1 = new HealthFacility(1L, "DR LUBICZ", "Zdrowa", "1", "00-001", "Cracow", "100 200 300");
        HealthFacility healthFacility2 = new HealthFacility(2L, "DR QUEEN", "Queens", "1", "00-111", "Warsaw", "111 222 333");
        // when
        HealthFacility saved = repository.save(healthFacility1);
        HealthFacility saved2 = repository.save(healthFacility2);
        // then
        assertNotNull(saved);
        assertNotNull(saved2);
        assertNotNull(saved.getId());
        assertNotNull(saved2.getId());
        assertNotEquals(saved.getId(), saved2.getId());
    }

    @Test
    void shouldDeleteHealthFacilityWhenExists() {
        // given
        Long id = Long.valueOf(1);
        HealthFacility healthFacility = new HealthFacility("DR BAD", "Bracka", "1", "21-233", "Warsaw", "12 333 44 55");
        // when
        HealthFacility saved = repository.save(healthFacility);
        assertNotNull(saved);
        assertNotNull(saved.getId());
        repository.deleteById(saved.getId());
        // then
        Optional<HealthFacility> deleted = repository.findById(saved.getId());
        assertTrue(deleted.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenHealthFacilityDoesntExist() {
        // given
        // no Health Facilities in the repository
        // when
        assertThrows(IllegalArgumentException.class, () -> repository.deleteById(Long.valueOf(1)));
    }
}