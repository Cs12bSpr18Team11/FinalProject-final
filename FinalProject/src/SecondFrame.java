
import javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;
import  java.util.ArrayList;
import java.util.*;
import java.io.*;


public class SecondFrame extends JFrame{

  

  SecondFrame()  throws FileNotFoundException{
	if(LoginFrame.newAccount){
		addAccount();
	}
	JTextField name1=new JTextField("");
	JTextField name2=new JTextField("");
	JTextField account1=new JTextField("");
	JTextField account2=new JTextField("");
	JTextField money=new JTextField("          ");
    JMenuBar menubar=new JMenuBar();

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setJMenuBar(menubar);
	JMenu myaccount = new JMenu("My Account");
	menubar.add(myaccount);
	JMenuItem itembalance=new JMenuItem("My balance");
	myaccount.add(itembalance);
	MouseDrawDemo signature = new MouseDrawDemo();

	JPanel content=new JPanel();
	content.setLayout(new BorderLayout());
	JLabel title = new JLabel("<html><h1>Money Transfer</h1></html>");
	title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	content.add(title,BorderLayout.PAGE_START);
	JPanel mid=new JPanel();
	mid.setLayout(new GridLayout(2,0));

	JPanel top = new JPanel();
	top.setLayout(new GridLayout(0,2));
	JPanel topleft=new JPanel();
	topleft.setLayout(new GridLayout(3,3));
	topleft.add(new JLabel("    "));
	topleft.add(new JLabel("Name"));
	topleft.add(new JLabel("Bank"));
	JLabel from = new JLabel("From: ");
	topleft.add(from);
	from.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	topleft.add(name1);
	JComboBox bankname1 = new JComboBox(new String[]{"BOA","Citi","Chase","Santander","PNC","Wells Fargo","HSBC"});
	topleft.add(bankname1);
	JLabel to = new JLabel("To: ");
	topleft.add(to);
	to.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	topleft.add(name2);
	JComboBox bankname2 = new JComboBox(new String[]{"BOA","Citi","Chase","Santander","PNC","Wells Fargo","HSBC"});
	topleft.add(bankname2);
	top.add(topleft);
	JPanel topright=new JPanel();
	topright.setLayout(new GridLayout(3,0));
	topright.add(new JLabel("Account Number"));
	topright.add(account1);
	topright.add(account2);
	top.add(topright);

	mid.add(top);
	JPanel low=new JPanel();
	low.setLayout(new BorderLayout());
	JPanel middle=new JPanel();
	middle.setLayout(new FlowLayout());
	middle.add(new JLabel("Amount:"));
	middle.add(money);
	low.add(middle,BorderLayout.PAGE_START);
	JTextArea sign= new JTextArea("Sign your name in the box:  "
			+"\n"+"\n"+"(release the mouse you could resign)"
			);

	low.add(sign,BorderLayout.LINE_START);
	low.add(signature,BorderLayout.CENTER);
	mid.add(low);
	content.add(mid,BorderLayout.CENTER);
	JPanel bot = new JPanel();
	bot.setLayout(new FlowLayout());
	JButton submit=new JButton("Submit");
	submit.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			try {
				String fromNumber = account1.getText();
			  String toNumber = account2.getText();
			  String transferMoney = money.getText();
			  Double a = Double.parseDouble(transferMoney);
			  Double b = LoginFrame.balanceMap.get(toNumber);
			  Double c = LoginFrame.balanceMap.get(fromNumber);
			  Double total = b+a;
			  Double total2 = c-a;
			  setBalance(toNumber,total);
			  setBalance(fromNumber,total2);
				JOptionPane.showMessageDialog(null, "Transfer Success!");
		  } catch (FileNotFoundException e){
				System.out.println("Problem reading map from file "+e);
			}

		}
	});
	JButton cancel=new JButton("Cancel");
	cancel.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent event){
			name1.setText("");
			name2.setText("");
			account1.setText("");
			account2.setText("");
			money.setText("");
		}
	});
	bot.add(submit); bot.add(cancel);
	content.add(bot,BorderLayout.PAGE_END);

	setContentPane(content);
	pack();
	setLocation(100,100);
	setSize(500,300);
	setVisible(true);

	itembalance.addActionListener(new ActionListener(){
	     public void actionPerformed(ActionEvent e) {

	    	Mybalance mb=new Mybalance();
				mb.setVisible(true);
				mb.setLocation(700,100);
				mb.setSize(300,150);
				mb.setTitle("My Balance");

	     }
});
}

void addAccount() throws FileNotFoundException{
	Scanner sc = new Scanner(new File("Account.txt"));
	String tmp = "";
	while(sc.hasNextLine()){
		tmp += sc.nextLine();
		tmp += "\n";
	}
	String n = LoginFrame.newAccountNumber;
	String name = LoginFrame.newName;
	String password = LoginFrame.newPassword;
	PrintStream out = new PrintStream(new File("Account.txt"));
	out.print(tmp);
	out.println(n + " " + name + " " + password + " 0.00");
}

void setBalance(String accountNumber,double n) throws FileNotFoundException{
	LoginFrame.balanceMap.remove(accountNumber);
	LoginFrame.balanceMap.put(accountNumber, n);
	Scanner sc = new Scanner(new File("Account.txt"));
	String tmp = "";
	while(sc.hasNextLine()){
		String line = sc.nextLine();
		Scanner lineScan = new Scanner(line);
		if(lineScan.next().equals(accountNumber)){
			String name = lineScan.next();
			String password = lineScan.next();
			tmp += accountNumber + " " + name + " " + password + " " + n;
		} else {
			tmp += line;
		}
		tmp += "\n";
	}
	PrintStream out = new PrintStream(new File("Account.txt"));
	out.print(tmp);
}
}
