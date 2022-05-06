package com.vheal.entity.validation;

import com.vheal.dao.UserRepository;
import com.vheal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueEmailContraintValidator implements ConstraintValidator<UniqueEmail, String> {

    private UserRepository userRepository;

    @Autowired
    public UniqueEmailContraintValidator(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    @Override
    public void initialize(UniqueEmail constraint) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(email == null){
            return false;
        }
        if(userRepository.count() == 0){
            return true;
        }
        List<User> users = userRepository.findAll();
        for(User tempUser : users){
            if(tempUser.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }
}
