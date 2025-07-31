import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame {
    private JTextField inputField;
    private JComboBox<String> fromCombo;
    private JComboBox<String> toCombo;
    private JLabel resultLabel;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Create components
        JLabel inputLabel = new JLabel("Enter Temperature:");
        inputField = new JTextField();

        JLabel fromLabel = new JLabel("From:");
        fromCombo = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});

        JLabel toLabel = new JLabel("To:");
        toCombo = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});

        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ", JLabel.CENTER);

        // Add components to frame
        add(inputLabel);
        add(inputField);
        add(fromLabel);
        add(fromCombo);
        add(toLabel);
        add(toCombo);
        add(new JLabel()); // empty placeholder
        add(convertButton);
        add(new JLabel()); // empty placeholder
        add(resultLabel);

        // Button action
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double inputTemp = Double.parseDouble(inputField.getText());
                    String fromScale = (String) fromCombo.getSelectedItem();
                    String toScale = (String) toCombo.getSelectedItem();

                    double converted = convertTemperature(inputTemp, fromScale, toScale);
                    resultLabel.setText(String.format("Result: %.2f %s", converted, toScale));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }
        });

        setLocationRelativeTo(null); // center window
        setVisible(true);
    }

    public static double convertTemperature(double value, String fromScale, String toScale) {
        fromScale = fromScale.toLowerCase();
        toScale = toScale.toLowerCase();

        double celsius;
        switch (fromScale) {
            case "celsius":
                celsius = value;
                break;
            case "fahrenheit":
                celsius = (value - 32) * 5 / 9;
                break;
            case "kelvin":
                celsius = value - 273.15;
                break;
            default:
                throw new IllegalArgumentException("Invalid from scale");
        }

        switch (toScale) {
            case "celsius":
                return celsius;
            case "fahrenheit":
                return (celsius * 9 / 5) + 32;
            case "kelvin":
                return celsius + 273.15;
            default:
                throw new IllegalArgumentException("Invalid to scale");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemperatureConverterGUI());
    }
}
