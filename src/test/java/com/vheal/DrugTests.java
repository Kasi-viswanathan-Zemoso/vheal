package com.vheal;

import com.vheal.dao.DrugRepository;
import com.vheal.entity.Drug;
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
public class DrugTests {

    @Autowired
    private DrugRepository drugRepository;

    @Test
    @Rollback(value = false)
    @Order(1)
    public void testCreateDrug(){
        Drug drug = new Drug("metacin255","tablet");
        Drug savedDrug = drugRepository.save(drug);
        assertNotNull(savedDrug);
    }

    @Test
    @Order(2)
    public void testFindProductByNameExist(){
        String drugName = "metacin255";
        Drug drug = drugRepository.findByDrugName(drugName);
        assertThat(drug.getDrugName()).isEqualTo(drugName);
    }

    @Test
    @Order(3)
    public void testFindProductByNameNotExist(){
        String drugName = "covishield";
        Drug drug = drugRepository.findByDrugName(drugName);
        assertNull(drug);
    }

    @Test
    @Order(4)
    public void testUpdateDrug(){
        String drugName = "paracetamol";
        Drug drug = new Drug(drugName,"tablet");
        drug.setType("pill");
        drugRepository.save(drug);
        Drug updatedDrug = drugRepository.findByDrugName(drugName);
        assertThat(updatedDrug.getDrugName()).isEqualTo(drugName);
    }

    @Test
    @Rollback(value = false)
    @Order(5)
    public void testListDrug(){
        List<Drug> drugs = drugRepository.findAll();

        for (Drug drug : drugs){
            System.out.println(drug);
        }
        assertThat(drugs).size().isGreaterThan(0);
    }

    @Test
    @Rollback(value = false)
    @Order(6)
    public void testDeleteDrug(){
        String drugName = "metacin255";
        Drug drug = drugRepository.findByDrugName(drugName);
        Integer id = drug.getId();
        boolean isExistBeforeDelete = drugRepository.findById(id).isPresent();
        drugRepository.deleteById(id);
        boolean notExistAfterDelete = drugRepository.findById(id).isPresent();
        assertTrue(isExistBeforeDelete);
        assertFalse(notExistAfterDelete);
    }
}
