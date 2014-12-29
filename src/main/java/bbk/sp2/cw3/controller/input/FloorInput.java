package bbk.sp2.cw3.controller.input;

import bbk.sp2.cw3.service.ValidationService;

import java.util.Scanner;

public class FloorInput extends UserInput {

    private ValidationService validationService;

    public FloorInput(ValidationService validationService, Scanner input) {
        super(input);
        this.validationService = validationService;
    }

    @Override
    protected boolean isValidInput(int inputValue) {
        return validationService.isNumberOfFloorsValid(inputValue);
    }
}