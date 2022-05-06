package com.vheal;

import com.vheal.dao.DoctorRepository;
import com.vheal.dao.DrugRepository;
import com.vheal.dao.PatientRepository;
import com.vheal.entity.Doctor;
import com.vheal.entity.Drug;
import com.vheal.entity.Patient;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientTests {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    @Rollback(value = false)
    @Order(1)
    public void testCreatePatient(){
        Patient patient = new Patient("Viswa","9876543210","23","male","Chennai","Tamil nadu","India");
        Patient savedPatient = patientRepository.save(patient);
        assertNotNull(savedPatient);
    }

    @Test
    @Order(2)
    public void testFindPatientByNameExist(){
        String patientName = "Viswa";
        Patient patient = patientRepository.findByPatientName(patientName);
        assertThat(patient.getPatientName()).isEqualTo(patientName);
    }

    @Test
    @Order(3)
    public void testFindPatientByNameNotExist(){
        String patientName = "Nathan";
        Patient patient = patientRepository.findByPatientName(patientName);
        assertNull(patient);
    }

    @Test
    @Order(4)
    public void testUpdatePatient(){
        String patientName = "Kasi";
        Patient patient = new Patient(patientName,"9876543210","23","male","Chennai","Tamil nadu","India");
        patient.setAge("24");
        patientRepository.save(patient);
        Patient updatedPatient = patientRepository.findByPatientName(patientName);
        assertThat(updatedPatient.getPatientName()).isEqualTo(patientName);
    }

    @Test
    @Rollback(value = false)
    @Order(5)
    public void testListPatient(){
        List<Patient> patients = patientRepository.findAll();

        for (Patient patient : patients){
            System.out.println(patient);
        }
        assertThat(patients).size().isGreaterThan(0);
    }

    @Test
    @Rollback(value = false)
    @Order(6)
    public void testDeletePatient(){
        String patientName = "Viswa";
        Patient patient = patientRepository.findByPatientName(patientName);
        Integer id = patient.getId();
        boolean isExistBeforeDelete = patientRepository.findById(id).isPresent();
        patientRepository.deleteById(id);
        boolean notExistAfterDelete = patientRepository.findById(id).isPresent();
        assertTrue(isExistBeforeDelete);
        assertFalse(notExistAfterDelete);
    }
}
