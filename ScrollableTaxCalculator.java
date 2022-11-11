import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.DecimalFormat;

public class ScrollableTaxCalculator extends JFrame {
    // declare variables for the input and output labels and text fields
    JLabel inputLabel, outputLabel;
    JTextField inputTextField, outputTextField;
    JPanel inputPanel, outputPanel;
    JLabel sliderLabel;
    JSlider slider;
    JPanel sliderPanel;
    // windows width and height
    final int WIDTH = 300;
    final int HEIGHT = 150;
    // sliders minimum and maximum figures and starting position
    final int MIN = 0;
    final int MAX = 10;
    final int START = 0;

    // constructor
    public ScrollableTaxCalculator() {
        setTitle("Scrollable Tax Calculator");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set layout
        setLayout(new BorderLayout());
        // build the necessary panels
        buildInputPanel();
        buildSliderPanel();
        buildOutputPanel();

        // set the border layout
        add(inputPanel, BorderLayout.NORTH);
        add(outputPanel, BorderLayout.CENTER);
        add(sliderPanel, BorderLayout.SOUTH);
    }

    // creating the build input panel
    public void buildInputPanel() {
        inputLabel = new JLabel("Enter the amount of purchase: ");
        inputTextField = new JTextField(10);
        inputPanel = new JPanel();
        inputPanel.add(inputLabel);
        inputPanel.add(inputTextField);
    }

    // creating the build output panel
    public void buildOutputPanel() {
        outputLabel = new JLabel("Sales Tax:");
        outputTextField = new JTextField("0.0", 10);
        outputTextField.setEditable(false);
        outputPanel = new JPanel();
        outputPanel.add(outputLabel);
        outputPanel.add(outputTextField);
    }

    // creating the slider panel
    public void buildSliderPanel() {
        sliderLabel = new JLabel("Sales Tax Slider");
        slider = new JSlider(JSlider.HORIZONTAL, MIN, MAX, START);
        slider.setMajorTickSpacing(1);// spacing for the values
        slider.setPaintTicks(true);// dispalying the ticks
        slider.setPaintLabels(true);// displaying the labels
        // building slider change events
        slider.addChangeListener(new SliderListener());
        // add the components to the panel
        sliderPanel = new JPanel();
        sliderPanel.add(sliderLabel);
        sliderPanel.add(slider);
    }

    // SliderLisnter class implements change listener interface
    private class SliderListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            DecimalFormat dollar = new DecimalFormat("#,##0.00");

            if (inputTextField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Enter the purchase amount");
            } else {
                double taxRate;
                double salesTax;
                String input;
                input = inputTextField.getText();
                taxRate = Double.parseDouble(input) / 100;
                salesTax = slider.getValue() * taxRate;
                outputTextField.setText(dollar.format(salesTax));
            }
        }
    }

    public static void main(String[] args) {
        new ScrollableTaxCalculator();
    }
}