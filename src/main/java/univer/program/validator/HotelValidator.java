package univer.program.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import univer.program.entity.Hotel;
import univer.program.service.HotelService;

@Component
public class HotelValidator implements Validator {
    private HotelService hotelService;

    @Autowired
    public HotelValidator(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Hotel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Hotel hotel = (Hotel) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "name field cannot be null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"country", "country field cannot be null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"city", "city field cannot be null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address", "address field cannot be null");

    }
}
