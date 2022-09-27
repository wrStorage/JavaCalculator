import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;

public class CalculatorController {
    private CalculatorModel calculatorModel;
    private CalculatorView calculatorView;

    CalculatorController(CalculatorModel model, CalculatorView view) {
        calculatorModel = model;
        calculatorView = view;

        calculatorView.addNumberButtonListener(new NumberListener(), new NumberKeyboardAction());
        calculatorView.addOperatorListener(new OperatorListener(), new OperatorKeyboardAction());
        calculatorView.addEqualListener(new EqualListener(), new EqualKeyBoardAction());
        calculatorView.addClearListener(new ClearListener(), new ClearKeyboardAction());
    }

    private void setNumberInput(ActionEvent number) {
        if(calculatorView.getNewOutput()) {
            calculatorView.setNewOutput();
            calculatorView.clearOutput();
        }
        
        calculatorView.setOutput(number.getActionCommand());

        if(calculatorModel.getOperator() == null)
            calculatorModel.setFirstNumber(Integer.valueOf(calculatorView.getOutput()));
        else
            calculatorModel.setSecondNumber(Integer.valueOf(calculatorView.getOutput()));
    }

    class NumberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setNumberInput(e);
        }
    }

    class NumberKeyboardAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            setNumberInput(e);
        }
    }

    class OperatorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            calculatorModel.setOperator(e.getActionCommand());
            calculatorView.setNewOutput();
        }
    }

    class OperatorKeyboardAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            calculatorModel.setOperator(e.getActionCommand());
            calculatorView.setNewOutput();
        }
    }

    class EqualListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!("Err".equals(calculatorView.getOutput())) && calculatorModel.getOperator() != null) {
                try {
                    calculatorView.clearOutput();
                    calculatorView.setOutput(String.valueOf(calculatorModel.getAnswer()));
                } catch (ArithmeticException error) {
                    calculatorView.setOutput("Err");
                }
            }
        }
    }

    class EqualKeyBoardAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!("Err".equals(calculatorView.getOutput())) && calculatorModel.getOperator() != null) {
                try {
                    calculatorView.clearOutput();
                    calculatorView.setOutput(String.valueOf(calculatorModel.getAnswer()));
                } catch (ArithmeticException error) {
                    calculatorView.setOutput("Err");
                }
            }
        }
    }

    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            calculatorView.clearOutput();
            calculatorView.setOutput("0");
            calculatorModel.setFirstNumber(0);
            calculatorModel.setSecondNumber(0);
            calculatorModel.setOperator(null);
        }
    }

    class ClearKeyboardAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            calculatorView.clearOutput();
            calculatorView.setOutput("0");
            calculatorModel.setFirstNumber(0);
            calculatorModel.setSecondNumber(0);
            calculatorModel.setOperator(null);
        }
    }
}