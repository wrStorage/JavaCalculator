import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;

public class CalculatorView extends JFrame {
    private JPanel panel = new JPanel();
    private JButton numberButtons[] = new JButton[10];
    private JButton calculationButtons[] = new JButton[5];
    private JTextField output = new JTextField("0");
    private boolean newOutput = true;
    
    CalculatorView() {
        this.setLayout(null);
        this.setSize(600, 600);
        this.setResizable(false);
        output.setBounds(50, 25, 500, 50);
        output.setEditable(false);
        panel.setBounds(50, 100, 500, 400);
        panel.setLayout(new GridLayout(5,3, 10, 10));

        for(int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(Integer.toString(i));
            panel.add(numberButtons[i]);
        }

        calculationButtons[0] = new JButton("+");
        calculationButtons[1] = new JButton("-");
        calculationButtons[2] = new JButton("*");
        calculationButtons[3] = new JButton("/");
        calculationButtons[4] = new JButton("=");

        for(int i = 0; i < 5; i++)
            panel.add(calculationButtons[i]);

        this.add(output);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setOutput(String output) {
        if(!getOutput().isEmpty()) {
            if(getOutput().charAt(0) != '0')
                this.output.setText(this.output.getText() + output);
            else
                if(!output.equals("0")) {
                    clearOutput();
                    this.output.setText(this.output.getText() + output);
                }
        }
        else
            this.output.setText(this.output.getText() + output);
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

        calculationButtons[0].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.SHIFT_DOWN_MASK), "plusAction");
        calculationButtons[0].getActionMap().put("plusAction", operatorAction);

        calculationButtons[1].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "minusAction");
        calculationButtons[1].getActionMap().put("minusAction", operatorAction);

        calculationButtons[2].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_8, InputEvent.SHIFT_DOWN_MASK), "timesAction");
        calculationButtons[2].getActionMap().put("timesAction", operatorAction);

        calculationButtons[3].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0), "divideAction");
        calculationButtons[3].getActionMap().put("divideAction", operatorAction);
    }

    public void addEqualListener(ActionListener listenButtonPress, Action equalAction) {
        calculationButtons[4].addActionListener(listenButtonPress);
        calculationButtons[4].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0), "equalAction");
        calculationButtons[4].getActionMap().put("equalAction", equalAction);
        calculationButtons[4].getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "equalAction");
        calculationButtons[4].getActionMap().put("equalAction", equalAction);
    }
}