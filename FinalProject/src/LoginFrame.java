
import javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;
import  java.util.ArrayList;
import java.util.*;
import java.io.*;

public class LoginFrame extends JFrame implements ActionListener {
	static class PicDemo extends JPanel{
   PicDemo(){
    setPreferredSize(new Dimension(50,100));
    ImageIcon image = new ImageIcon(getClass().getResource("/dog.jpg"));
    JLabel label = new JLabel(image);
    add(label);
   }
  }
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static JTextField text;
	public static JButton login;
	public static JButton register;
	public static JLabel number;
	public static JLabel password;
	public static JLabel title;
	public static JPasswordField  text2;
	public static JTextField text3;
	public static JPanel mid;
	public static JPanel right;
	public static HashMap<String,String> passwordMap = new HashMap<String,String>();
	public static HashMap<String,String> nameMap = new HashMap<String,String>();
	public static HashMap<String,Double> balanceMap = new HashMap<String,Double>();
	public static boolean newAccount = false;
	public static String newName;
	public static String newAccountNumber;
	public static String newPassword;

	LoginFrame()  throws Exception {
		importMap();
		number=new JLabel("Account number:");
		text=new JTextField("");
		text2 = new JPasswordField (20);
		password = new JLabel("Password: ");
		login=new JButton("Login");
		register=new JButton("Register");
		JLabel info = new JLabel("Enter name below to register");
		text3 = new JTextField("");
		title =new JLabel("<html><h1>Bank Account System</h1></html>");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new  BorderLayout());
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(1,2));
		add(titlePanel,BorderLayout.PAGE_START);
		titlePanel.add(title);
		JPanel pic = new PicDemo();
		titlePanel.add(pic);
		mid=new JPanel();
		mid.setLayout(new GridLayout(2,2));
		mid.add(number);
		mid.add(text);
		mid.add(password);
		mid.add(text2);
		add(mid,BorderLayout.CENTER);
		right=new JPanel();
		right.setLayout(new GridLayout(3,1));
		right.add(info);
		right.add(text3);
		right.add(register);
		add(right,BorderLayout.LINE_END);
		add(login,BorderLayout.PAGE_END);




		login.addActionListener(this);
		register.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				String password = text2.getText();
				String name = text3.getText();
				if(name.equals("")){
					JOptionPane.showMessageDialog(null, "Error, please enter name");
				} else {
					int n = passwordMap.size();
					newAccountNumber = n + "";
					newName = name;
					newPassword = password;
					newAccount = true;
					passwordMap.put("" + n,password);
					nameMap.put("" + n,name);
					balanceMap.put("" + n,0.0);
					info.setText(n + " is your account number");
					JOptionPane.showMessageDialog(null, "Success! " + n + " is your account number");
				}
			}
		});

	}
	public static void main (String[] args)  throws Exception {
		LoginFrame frame=new LoginFrame();
		frame.setVisible(true);
		frame.setSize(500,200);
		frame.setLocation(100,100);
		frame.setTitle("Login");


	}

	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("Login"))
		{

			String accountNumber = text.getText();
			String password = text2.getText();
			try {
				if(checkName(accountNumber, password)) {
					SecondFrame fr=new SecondFrame();
					fr.setVisible(true);
					fr.setLocation(100,100);
					fr.setSize(500,300);
					fr.setTitle("Transfer Frame");
					dispose();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void importMap()  throws Exception {
		Scanner sc = new Scanner(new File("Account.txt"));
		while(sc.hasNextLine()&&sc.hasNext()) {
			String accountNumber = sc.next();
			String name = sc.next();
			String password = sc.next();
			double balance = Double.parseDouble(sc.next());
			passwordMap.put(accountNumber,password);
			nameMap.put(accountNumber,name);
			balanceMap.put(accountNumber,balance);

		}
	}

	public boolean checkName(String accountNumber, String password){
		if(password.equals(passwordMap.get(accountNumber))){
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Wrong Password or Name!");
			return false;
		}
	}
}
