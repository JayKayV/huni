package univer.program.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import univer.program.entity.Room;

@Component
public class RoomValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Room.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Room room = (Room) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"number", "number field cannot be null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"bed_cnt", "bed_cnt field cannot be null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"type", "type field cannot be null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"price", "price field cannot be null");
    }
}
