public class CalculatorModel {
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operator = null;
    private boolean subsequentCal = false;

    public void setFirstNumber(double number) {
        firstNumber = number;
    }

    public void setSecondNumber(double number) {
        secondNumber = number;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void resetSubsequent() {
        subsequentCal = false;
    }

    public boolean isSubsequent() {
        return subsequentCal;
    }

    public double getAnswer() {
        subsequentCal = true;
        if(operator.equals("+"))
            return firstNumber+= secondNumber;
        else if(operator.equals("-"))
            return firstNumber-= secondNumber;
        else if(operator.equals("*"))
            return firstNumber*= secondNumber;
        else if(operator.equals("/")) {
            if(secondNumber == 0)
                throw new ArithmeticException();
            return firstNumber/= secondNumber;
        }
        throw new ArithmeticException();
    }
}