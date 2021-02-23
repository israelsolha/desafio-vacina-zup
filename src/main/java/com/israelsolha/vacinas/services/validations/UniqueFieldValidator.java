package com.israelsolha.vacinas.services.validations;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, String> {
    private String field;
    private Class<?> aClass;

    @Autowired
    public EntityManager entityManager;

    @Override
    public void initialize(UniqueField ann) {
        field = ann.fieldName();
        aClass = ann.domainClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Boolean notFound = true;

        if (field == null){
            return true;
        }
        Query customQuery = entityManager.createQuery("select 1 from " + aClass.getName() + " where " + field + " = :value");
        customQuery.setParameter("value", value);
        List<?> resultQuery = customQuery.getResultList();
        if (!resultQuery.isEmpty()){
            notFound = false;
        }

        return notFound;
    }
}
