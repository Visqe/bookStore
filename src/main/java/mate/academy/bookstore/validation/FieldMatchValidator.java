package mate.academy.bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstObjName;
    private String secondObjName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstObjName = constraintAnnotation.field();
        this.secondObjName = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        Object firstObj = new BeanWrapperImpl(object).getPropertyValue(firstObjName);
        Object secondObj = new BeanWrapperImpl(object).getPropertyValue(secondObjName);
        return Objects.equals(firstObj, secondObj);
    }
}
