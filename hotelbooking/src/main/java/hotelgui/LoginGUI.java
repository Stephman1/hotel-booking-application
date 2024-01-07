package hotelgui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class LoginGUI extends JFrame {
	
	// Fields
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
    private JTextField passwordField;
    private String email;
    private List<LoginListener> loginListeners = new ArrayList<>();
    
    // Constructor
    public LoginGUI() {
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setBounds(100, 100, 400, 300);
        setTitle("Login to Hotel Booking App");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel emailLabel = createLabel("Email: ", 10, 10);
        emailField = createTextField(100, 10, 250);
        JLabel passwordLabel = createLabel("Password: ", 10, 50);
        passwordField = createTextField(100, 50, 250);
        JButton loginButton = createButton("Login", 10, 90);
        loginButton.addActionListener(e -> this.setEmail(emailField.getText().strip()));
        
        contentPane.add(emailLabel);
        contentPane.add(emailField);
        contentPane.add(passwordLabel);
        contentPane.add(passwordField);
        contentPane.add(loginButton);
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

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
		// Notify listeners when the email is set
		notifyLoginListeners();
	}
    
	public void addLoginListener(LoginListener listener) {
	    loginListeners.add(listener);
	}

	private void notifyLoginListeners() {
	    for (LoginListener listener : loginListeners) {
	        listener.onLogin(email); // Notify each listener about the email
	    }
	}
    
}
