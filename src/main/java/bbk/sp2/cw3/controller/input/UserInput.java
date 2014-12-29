package bbk.sp2.cw3.controller.input;

import java.util.Scanner;

public abstract class UserInput {

    private Scanner input;

    public UserInput(Scanner input) {
        this.input = input;
    }

    public int getNumericValue(final String prompt,
            final String validationErrorMessage) {

        int numberOfFloors = 0;
        boolean firstTime = true;

        do {

            if (!firstTime) {
                printError(validationErrorMessage);
            }

            System.out.print(prompt + ": ");

            if (input.hasNextInt()) {

                numberOfFloors = input.nextInt();
            } else {
                printError("Please enter an integer value");
            }

            firstTime = false;

        } while (!isValidInput(numberOfFloors));

        return numberOfFloors;
    }

    private void printError(String validationErrorMessage) {
        System.out.println("\tERROR: " + validationErrorMessage);
    }

    protected abstract boolean isValidInput(int inputValue);
}