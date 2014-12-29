package bbk.sp2.cw3.repository;

import bbk.sp2.cw3.service.ValidationService;

public abstract class Repository {

    private ValidationService validationService;

    public Repository(ValidationService validationService) {
        this.validationService = validationService;
    }

    protected ValidationService getValidationService() {
        return validationService;
    }
}