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
        this.firstObjName = constraintAnnotation.password();
        this.secondObjName = constraintAnnotation.repeatPassword();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object firstObj = new BeanWrapperImpl(o).getPropertyValue(firstObjName);
        Object secondObj = new BeanWrapperImpl(o).getPropertyValue(secondObjName);
        return Objects.equals(firstObj, secondObj);
    }
}
