package hotelgui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class HotelGUI extends JFrame {

	// Fields
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField apiEndpointTextField3;
    private JTextField apiEndpointTextField4a;
    private JTextField apiEndpointTextField4b;
    private JTextField apiEndpointTextField5a;
    private JTextField apiEndpointTextField5b;
    private JTextField apiEndpointTextField6a;
    private JTextField apiEndpointTextField6b;
    private JTextField apiEndpointTextField6c;
    private JTextArea resultTextArea = new JTextArea();
    private String base_url;
    private HotelApiCaller apiCaller;
    
    // Constructor
    public HotelGUI(String base_url) {
    	this.base_url = base_url;
    	this.apiCaller =  new HotelApiCaller(this.base_url);
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 800);
        setTitle("Hotel Booking App");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Get all rooms
        JButton btnCallApi1 = createButton("Get all rooms", 680, 10);
        btnCallApi1.addActionListener(e -> this.resultTextArea.setText(this.apiCaller.getAllRoomsCaller()));

        // Get all available rooms
        JButton btnCallApi2 = createButton("Get all available rooms", 680, 50);
        btnCallApi2.addActionListener(e -> this.resultTextArea.setText(this.apiCaller.getAllAvailableRoomsCaller()));

        // Get all available rooms by room type
        JLabel labelApi3 = createLabel("Room type: ", 10, 90);
        apiEndpointTextField3 = createTextField(110, 90, 200);
        JButton btnCallApi3 = createButton("Get available rooms by type", 680, 90);
        btnCallApi3.addActionListener(e -> this.resultTextArea.setText(this.apiCaller.getAllAvailableRoomsByTypeCaller(
        		apiEndpointTextField3.getText()
        		)));

        // Update occupied status of room in a hotel
        JLabel labelApi4a = createLabel("Hotel: ", 10, 130);
        apiEndpointTextField4a = createTextField(110, 130, 200);
        JLabel labelApi4b = createLabel("Room num: ", 340, 130);
        apiEndpointTextField4b = createTextField(440, 130, 200);
        JButton btnCallApi4 = createButton("Update occupied status by hotel and room", 680, 130);
        btnCallApi4.addActionListener(e -> this.resultTextArea.setText(this.apiCaller.updateOccupiedStatusByHotelAndRoomNumberCaller(
        		apiEndpointTextField4a.getText(),
        		apiEndpointTextField4b.getText()
        		)));

        // Update unoccupied status of room in a hotel
        JLabel labelApi5a = createLabel("Hotel: ", 10, 170);
        apiEndpointTextField5a = createTextField(110, 170, 200);
        JLabel labelApi5b = createLabel("Room num: ", 340, 170);
        apiEndpointTextField5b = createTextField(440, 170, 200);
        JButton btnCallApi5 = createButton("Update unoccupied status by hotel and room", 680, 170);
        btnCallApi5.addActionListener(e -> this.resultTextArea.setText(this.apiCaller.updateUnoccupiedStatusByHotelAndRoomNumberCaller(
        		apiEndpointTextField5a.getText(),
        		apiEndpointTextField5b.getText()
        		)));
        
        // Update price of rooms by type and hotel
        JLabel labelApi6a = createLabel("Hotel: ", 10, 210);
        apiEndpointTextField6a = createTextField(110, 210, 200);
        JLabel labelApi6b = createLabel("Room type: ", 340, 210);
        apiEndpointTextField6b = createTextField(440, 210, 200);
        JLabel labelApi6c = createLabel("Rate: ", 10, 250);
        apiEndpointTextField6c = createTextField(110, 250, 200);
        JButton btnCallApi6 = createButton("Update rate by hotel and room type", 680, 210);
        btnCallApi6.addActionListener(e -> this.resultTextArea.setText(this.apiCaller.updateRateByHotelAndRoomTypeCaller(
        		apiEndpointTextField6a.getText(),
        		apiEndpointTextField6b.getText(),
        		apiEndpointTextField6c.getText()
        		)));

        resultTextArea.setEditable(false);
        JScrollPane scrollPane = createScrollableTextArea(resultTextArea, 10, 290, 1040, 600);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        contentPane.add(btnCallApi1);
        contentPane.add(btnCallApi2);
        contentPane.add(labelApi3);
        contentPane.add(apiEndpointTextField3);
        contentPane.add(btnCallApi3);
        contentPane.add(labelApi4a);
        contentPane.add(apiEndpointTextField4a);
        contentPane.add(labelApi4b);
        contentPane.add(apiEndpointTextField4b);
        contentPane.add(btnCallApi4);
        contentPane.add(labelApi5a);
        contentPane.add(apiEndpointTextField5a);
        contentPane.add(labelApi5b);
        contentPane.add(apiEndpointTextField5b);
        contentPane.add(btnCallApi5);
        contentPane.add(labelApi6a);
        contentPane.add(apiEndpointTextField6a);
        contentPane.add(labelApi6b);
        contentPane.add(apiEndpointTextField6b);
        contentPane.add(labelApi6c);
        contentPane.add(apiEndpointTextField6c);
        contentPane.add(btnCallApi6);
        contentPane.add(scrollPane);
    }
    
    // Methods
    private JButton createButton(String label, int x, int y) {
        JButton button = new JButton(label);
        button.setBounds(x, y, 350, 25);
        return button;
    }

    private JTextField createTextField(int x, int y, int width) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, 25);
        return textField;
    }
    
    private JLabel createLabel(String labelText, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 80, 20);
        return label;
    }
    
    private JScrollPane createScrollableTextArea(JTextArea textArea, int x, int y, int width, int height) {
    	JScrollPane scrollPane = new JScrollPane(textArea);
    	scrollPane.setBounds(x, y, width, height);
    	return scrollPane;
    }
    
}