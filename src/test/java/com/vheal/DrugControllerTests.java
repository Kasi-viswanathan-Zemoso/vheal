package com.vheal;

import com.vheal.controller.DrugController;
import com.vheal.dao.DrugRepository;
import com.vheal.service.DrugService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrugControllerTests {

    @Autowired
    private DrugService drugService;

    @MockBean
    private DrugRepository drugRepository;

    private Model model;

    public DrugControllerTests(){
        model = new Model() {
            @Override
            public Model addAttribute(String attributeName, Object attributeValue) {
                return null;
            }

            @Override
            public Model addAttribute(Object attributeValue) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> attributeValues) {
                return null;
            }

            @Override
            public Model addAllAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public Model mergeAttributes(Map<String, ?> attributes) {
                return null;
            }

            @Override
            public boolean containsAttribute(String attributeName) {
                return false;
            }

            @Override
            public Object getAttribute(String attributeName) {
                return null;
            }

            @Override
            public Map<String, Object> asMap() {
                return null;
            }
        };
    }

    @Test
    public void testListDrug(){
        DrugController drugController = new DrugController(drugService);
        assertEquals("drugs/list-drugs",drugController.listDrugs(model));
    }

    @Test
    public void testShowFormForAdd(){
        DrugController drugController = new DrugController(drugService);
        assertEquals("drugs/drug-form",drugController.showFormForAdd(model));
    }
}
