public class CalculatorModel {
    private int firstNumber = 0;
    private int secondNumber = 0;
    private String operator = null;

    public void setFirstNumber(int number) {
        firstNumber = number;
    }

    public void setSecondNumber(int number) {
        secondNumber = number;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public int getAnswer() {
        if(operator.equals("+"))
            return firstNumber+= secondNumber;
        else if(operator.equals("-"))
            return firstNumber-= secondNumber;
        else if(operator.equals("x"))
            return firstNumber*= secondNumber;
        else if(operator.equals("/")) {
            if(secondNumber == 0)
                throw new ArithmeticException();
            return firstNumber/= secondNumber;
        }
        throw new ArithmeticException();
    }
}
