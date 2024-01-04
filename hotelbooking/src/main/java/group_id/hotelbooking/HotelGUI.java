package group_id.hotelbooking;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class HotelGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField apiEndpointTextField3;
    private JTextField apiEndpointTextField4;
    private JTextField apiEndpointTextField5;
    private JTextArea resultTextArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HotelGUI frame = new HotelGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public HotelGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Get all rooms
        JButton btnCallApi1 = createButton("Get all rooms", 420, 10);
        btnCallApi1.addActionListener(e -> resultTextArea.setText(makeApiCall("")));

        // Get all available rooms
        JButton btnCallApi2 = createButton("Get all available rooms", 420, 50);
        btnCallApi2.addActionListener(e -> resultTextArea.setText(makeApiCall("")));

        // Get all available rooms by room type
        apiEndpointTextField3 = createTextField(10, 90, 400);
        JButton btnCallApi3 = createButton("Get available rooms by type", 420, 90);
        btnCallApi3.addActionListener(e -> resultTextArea.setText(makeApiCall(apiEndpointTextField3.getText())));

        // Update occupied status of room in a hotel
        apiEndpointTextField4 = createTextField(10, 130, 400);
        JButton btnCallApi4 = createButton("Update room occupied status", 420, 130);
        btnCallApi4.addActionListener(e -> resultTextArea.setText(makeApiCall(apiEndpointTextField4.getText())));

        // Update price of rooms by type and hotel
        apiEndpointTextField5 = createTextField(10, 170, 400);
        JButton btnCallApi5 = createButton("Update price of room type", 420, 170);
        btnCallApi5.addActionListener(e -> resultTextArea.setText(makeApiCall(apiEndpointTextField5.getText())));

        resultTextArea = createTextArea(10, 210, 760, 240);

        contentPane.add(btnCallApi1);
        contentPane.add(btnCallApi2);
        contentPane.add(apiEndpointTextField3);
        contentPane.add(btnCallApi3);
        contentPane.add(apiEndpointTextField4);
        contentPane.add(btnCallApi4);
        contentPane.add(apiEndpointTextField5);
        contentPane.add(btnCallApi5);
        contentPane.add(resultTextArea);
    }

    private JButton createButton(String label, int x, int y) {
        JButton button = new JButton(label);
        button.setBounds(x, y, 250, 25);
        return button;
    }

    private JTextField createTextField(int x, int y, int width) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, 25);
        return textField;
    }

    private JTextArea createTextArea(int x, int y, int width, int height) {
        JTextArea textArea = new JTextArea();
        textArea.setBounds(x, y, width, height);
        return textArea;
    }

    private String makeApiCall(String apiEndpoint) {
        // Implement your actual API call logic here
        // For demonstration purposes, returning a dummy response
        return "API response for endpoint: " + apiEndpoint;
    }
}
