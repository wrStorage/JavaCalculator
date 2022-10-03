import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.Action;

public class CalculatorView extends JFrame {
    private JPanel panel = new JPanel();
    private JButton numberButtons[] = new JButton[10];
    private JButton calculationButtons[] = new JButton[5];
    private JButton clearButton = new JButton("CLR");
    private JButton decimalButton = new JButton(".");
    private JButton backspaceButton = new JButton("<-");
    private JButton plusMinusButton = new JButton("+/-");
    private JButton percentButton = new JButton("%");
    private JTextField output = new JTextField("0");
    private boolean newOutput = true;
    
    CalculatorView() {
        this.setLayout(null);
        this.setSize(600, 600);
        this.setResizable(false);
        output.setBounds(50, 25, 500, 50);
        output.setEditable(false);
        panel.setBounds(50, 100, 500, 400);
        panel.setLayout(new GridLayout(5,4, 10, 10));

        calculationButtons[0] = new JButton("+");
        calculationButtons[1] = new JButton("-");
        calculationButtons[2] = new JButton("*");
        calculationButtons[3] = new JButton("/");
        calculationButtons[4] = new JButton("=");

        for(int i = 0; i < 10; i++)
            numberButtons[i] = new JButton(Integer.toString(i));

        panel.add(percentButton);
        panel.add(clearButton);
        panel.add(backspaceButton);
        panel.add(calculationButtons[3]);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(calculationButtons[2]);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(calculationButtons[1]);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(calculationButtons[0]);
        panel.add(plusMinusButton);
        panel.add(numberButtons[0]);
        panel.add(decimalButton);
        panel.add(calculationButtons[4]);

        this.add(output);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setOutput(String output) {
        String oldOutput = getOutput();
        if(!oldOutput.isEmpty()) {
            if(output.equals(".") && oldOutput.contains("."))
                return;
            else if(oldOutput.length() == 1 && oldOutput.startsWith("0") && output.equals("0"))
                return;
            else if(oldOutput.length() == 1 && oldOutput.startsWith("0") && !output.equals("0") && !output.equals("."))
                this.output.setText(output);
            else
                this.output.setText(this.output.getText() + output);
        }
        else {
            if(output.equals("."))
                this.output.setText(".0");
            else
                this.output.setText(this.output.getText() + output);
        }   
    }

    public void deleteCharacter() {
        String oldOutput = getOutput();
        if(oldOutput.length() == 1)
            this.output.setText("0");
        else
            this.output.setText(oldOutput.substring(0, oldOutput.length() - 1));
    }

    public String getOutput() {
        return output.getText();
    }

    public void clearOutput() {
        this.output.setText("");
    }

    public boolean getNewOutput() {
        return newOutput;
    }

    public void setNewOutput() {
        if(newOutput)
            newOutput = false;
        else
            newOutput = true;
    }

    public void changeToPercent() {
        double oldOutput = Double.valueOf(this.output.getText());
        oldOutput *= .01;
        this.output.setText(String.valueOf(oldOutput));
    }

    public void changeSign() {
        double oldOutput = Double.valueOf(this.output.getText());
        oldOutput *= -1;
        this.output.setText(String.valueOf(oldOutput));
    }

    public void addNumberButtonListener(ActionListener listenButtonPress, Action numberAction) {
        for(int i = 0; i < 10; i++) {
            numberButtons[i].addActionListener(listenButtonPress);
            numberButtons[i].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(String.valueOf(i)), "numberAction");
            numberButtons[i].getActionMap().put("numberAction", numberAction);
        }
    }

    public void addOperatorListener(ActionListener listenButtonPress, Action operatorAction) {
        for(int i = 0; i < 4; i++)
            calculationButtons[i].addActionListener(listenButtonPress);

        calculationButtons[0].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("shift EQUALS"), "plusAction");
        calculationButtons[0].getActionMap().put("plusAction", operatorAction);

        calculationButtons[1].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("MINUS"), "minusAction");
        calculationButtons[1].getActionMap().put("minusAction", operatorAction);

        calculationButtons[2].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("shift 8"), "timesAction");
        calculationButtons[2].getActionMap().put("timesAction", operatorAction);

        calculationButtons[3].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SLASH"), "divideAction");
        calculationButtons[3].getActionMap().put("divideAction", operatorAction);
    }

    public void addEqualListener(ActionListener listenButtonPress, Action equalAction) {
        calculationButtons[4].addActionListener(listenButtonPress);
        calculationButtons[4].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("EQUALS"), "equalAction");
        calculationButtons[4].getActionMap().put("equalAction", equalAction);
        calculationButtons[4].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "equalAction");
        calculationButtons[4].getActionMap().put("equalAction", equalAction);
    }

    public void addClearListener(ActionListener clearButtonPress, Action clearAction) {
        clearButton.addActionListener(clearButtonPress);
        clearButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "clearAction");
        clearButton.getActionMap().put("clearAction", clearAction);
    }

    public void addDecimalListener(ActionListener decimalButtonPress, Action decimalAction) {
        decimalButton.addActionListener(decimalButtonPress);
        clearButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("PERIOD"), "decimalAction");
        clearButton.getActionMap().put("decimalAction", decimalAction);
    }

    public void addBackspaceListener(ActionListener backspaceButtonPress) {
        backspaceButton.addActionListener(backspaceButtonPress);
    }

    public void addPlusMinusListener(ActionListener plusMinusButtonPress) {
        plusMinusButton.addActionListener(plusMinusButtonPress);
    }

    public void addPercentListener(ActionListener percentButtonPress) {
        percentButton.addActionListener(percentButtonPress);
    }
}