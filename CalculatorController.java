import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;

public class CalculatorController {
    private CalculatorModel calculatorModel;
    private CalculatorView calculatorView;

    CalculatorController(CalculatorModel model, CalculatorView view) {
        calculatorModel = model;
        calculatorView = view;

        calculatorView.addNumberButtonListener(new NumberListener(), new OneAction());
        calculatorView.addCalculationListener(new OperatorListener());
        calculatorView.addEqualListener(new EqualListener());
    }

    class NumberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(calculatorView.getNewOutput()) {
                calculatorView.setNewOutput();
                calculatorView.clearOutput();
            }
            
            calculatorView.setOutput(e.getActionCommand());

            if(calculatorModel.getOperator() == null)
                calculatorModel.setFirstNumber(Integer.valueOf(calculatorView.getOutput()));
            else
                calculatorModel.setSecondNumber(Integer.valueOf(calculatorView.getOutput()));
        }
    }

    class OneAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(calculatorView.getNewOutput()) {
                calculatorView.setNewOutput();
                calculatorView.clearOutput();
            }
            
            calculatorView.setOutput(e.getActionCommand());

            if(calculatorModel.getOperator() == null)
                calculatorModel.setFirstNumber(Integer.valueOf(calculatorView.getOutput()));
            else
                calculatorModel.setSecondNumber(Integer.valueOf(calculatorView.getOutput()));
        }
    }

    class OperatorListener implements ActionListener {
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
}