import javax.swing.*;
import  java.awt.*;
import  java.awt.event.*;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Mybalance extends JFrame{

	public Mybalance() {

		JPanel content=new JPanel();
		content.setLayout(new  BorderLayout());

		JLabel title=new JLabel("<html><h1>My Balance</h1></html>");
		title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		content.add(title,BorderLayout.PAGE_START);

		JPanel mid=new JPanel();
		mid.setLayout(new GridLayout(2,2));
		mid.add(new JLabel("Name: "));
		JTextField name=new JTextField("");
		mid.add(name);
		mid.add(new JLabel("Account Number: "));
		JTextField account=new JTextField("");
		mid.add(account);
		content.add(mid,BorderLayout.CENTER);

		JButton login=new JButton("View My Balance");
		login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				String myAccount = account.getText();
				Double myBalance = LoginFrame.balanceMap.get(myAccount);
				String answer = "Your balance is "+myBalance;
				JOptionPane.showMessageDialog(null, answer);
			}
		});

		content.add(login,BorderLayout.PAGE_END);



//		login.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//				String n = name.getText();
//				String ac = account.getText();
//
//			     int a=-1;
//				for(int i=0; i<accountArray.size();i++) {
//					if(n.equals(accountArray.get(i).getName())&&ac.equals(accountArray.get(i).getAccount())) {
//			       a=i;
//
//						}
//				}
//				if(a==-1) {
//					JOptionPane.showMessageDialog(null,"Invalid username or password");
//				}else {
//					JOptionPane.showMessageDialog(null,"Your balance is $"+accountArray.get(a).getBalance());
//
//					}
//			}
//		});





		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(700,100);
		setSize(300,150);
		setVisible(true);
		setContentPane(content);


	}
}
