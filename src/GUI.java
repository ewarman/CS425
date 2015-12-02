import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener
{
	User user = new User();
	
	//Test Variables
	String username = "test user";
	String password = "test pw";
	String name = "test name";
	String ccn = "123465789";
	String phone = "123-456-7890";
	String email = "test@email.com";
	int points = 123;
	String status = "test status";
	String thread1 = "thread1";
	String thread2 = "thread2";
	String comment1 = "comment1";
	String comment2 = "comment2";
	Vector<Vector<String>> movieThreads = new Vector<Vector<String>>();
	Vector<Vector<String>> starThreads = new Vector<Vector<String>>();
	
	//Main
	public static void main(String args[])
	{
		GUI app = new GUI();
	}

	//Frame
	JFrame frame;
	
	//Panels
	JPanel contentPanel;
	JPanel loginPanel;
	JPanel userTicketTab;
	JPanel userForumTab;
	JPanel userInfoTab;
	JPanel forumCreatePanel;
	JTabbedPane userTabbedPane;
	
	//Text
	JTextField ccvText;
	JTextField ccNameText;
	JTextField ccTypeText;
	JTextField ccExpText;
	JTextField street1Text;
	JTextField street2Text;
	JTextField cityText;
	JTextField stateText;
	JTextField zipText;
	JTextField usernameText;
	JTextField nameText;
	JTextField passwordText;
	JTextField ccnText;
	JTextField phoneText;
	JTextField emailText;
	JTextField pointsText;
	JTextField statusText;
	JTextField createThreadText;
	JTextField createCommentText;
	JTextArea commentText;
	JPasswordField pwField;
	JTextField loginField;
	
	//Buttons
	JButton userLoginButton;
	JButton empLoginButton;
	JButton createUserButton;
	JButton guestLoginButton;
	JButton updateUserButton;
	JButton createCommentButton;
	JButton createThreadButton;
	JButton threadSubmitButton;
	JButton commentSubmitButton;
	JRadioButton movieRadioButton;
	JRadioButton starRadioButton;
	JRadioButton createMovieThreadButton;
	JRadioButton createStarThreadButton;
	JRadioButton selectMovieRadioButton;
	JRadioButton selectLocationRadioButton;
	
	//Combo Boxes
	JComboBox<String> threadList;
	JComboBox<String> commentList;
	JComboBox<String> theaterList;
	JComboBox<String> movieList;
	JComboBox<String> dateList;
	
	//Constructor
	GUI()
	{
		createFrame();
		createLoginPanel();
		
		//This is for testing purposes only.  Remove once DAOs are complete
		movieThreads.add(new Vector<String>());
		movieThreads.add(new Vector<String>());
		movieThreads.add(new Vector<String>());
		starThreads.add(new Vector<String>());
		starThreads.add(new Vector<String>());
		starThreads.add(new Vector<String>());
		
		movieThreads.get(0).add(thread1);
		movieThreads.get(0).add(thread2);
		starThreads.get(0).add(thread1);
		starThreads.get(0).add(thread2);
		
		movieThreads.get(1).add(comment1);
		movieThreads.get(2).add(comment2);
		starThreads.get(1).add(comment1);
		starThreads.get(2).add(comment2);
	}
	
	//Creates main frame object
	private void createFrame()
	{
		frame = new JFrame("CS 425 Final Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPanel = new JPanel(new CardLayout());
		
		contentPanel.setVisible(true);
		frame.setVisible(true);
		
		frame.add(contentPanel);
	}
	
	//creates and displays login panel
	private void createLoginPanel()
	{
		loginPanel = new JPanel(new BorderLayout());
		
		JLabel panelLabel = new JLabel("Login or Click Guest Access");
		panelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		createLoginButtons();
		createLoginField();
		createPWField();
		
		loginPanel.add(panelLabel,BorderLayout.NORTH);

		panelLabel.setVisible(true);
		
		loginPanel.setVisible(true);
		
		contentPanel.add(loginPanel, "Login");
		frame.pack();
	}
	
	//creates login field portion of login panel
	private void createLoginField()
	{
		JPanel loginFieldPanel = new JPanel(new GridLayout(2,1));
		loginField = new JTextField(50);
	
		JLabel loginLabel = new JLabel("Username: ");
		loginLabel.setLabelFor(loginField);
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		loginFieldPanel.add(loginLabel);
		loginFieldPanel.add(loginField);
		
		loginField.setVisible(true);
		loginLabel.setVisible(true);
		loginFieldPanel.setVisible(true);		
		
		loginPanel.add(loginFieldPanel,BorderLayout.WEST);
	}
	
	//creates pw field portion of login panel
	private void createPWField()
	{	
		JPanel pwFieldPanel = new JPanel(new GridLayout(2,1));
		pwField = new JPasswordField(50);
		
		JLabel pwLabel = new JLabel("Password: ");
		pwLabel.setLabelFor(pwField);
		pwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		pwFieldPanel.add(pwLabel);
		pwFieldPanel.add(pwField);

		pwFieldPanel.setVisible(true);
		pwField.setVisible(true);
		pwLabel.setVisible(true);
		
		loginPanel.add(pwFieldPanel,BorderLayout.EAST);
	}
	
	//creates button portion of login panel
	private void createLoginButtons()
	{
		userLoginButton = new JButton("User Login");
		empLoginButton = new JButton("Employee Login");
		createUserButton = new JButton("Create Account");
		guestLoginButton = new JButton("Enter as Guest");
		
		userLoginButton.addActionListener(this);
		empLoginButton.addActionListener(this);
		createUserButton.addActionListener(this);
		guestLoginButton.addActionListener(this);
		
		userLoginButton.setActionCommand("user login");
		empLoginButton.setActionCommand("emp login");
		createUserButton.setActionCommand("create user");
		guestLoginButton.setActionCommand("guest login");
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		buttonPanel.add(userLoginButton);
		buttonPanel.add(empLoginButton);
		buttonPanel.add(createUserButton);
		buttonPanel.add(guestLoginButton);
	
		userLoginButton.setVisible(true);
		empLoginButton.setVisible(true);
		createUserButton.setVisible(true);
		guestLoginButton.setVisible(true);
		buttonPanel.setVisible(true);
		
		loginPanel.add(buttonPanel,BorderLayout.SOUTH);
	}
	
	//creates tabbed pane for user login
	private void createUserTabs()
	{
		userTabbedPane = new JTabbedPane();
		contentPanel.add(userTabbedPane,"User Tabs");
		
		
		userInfoTab = new JPanel(new GridLayout(2,1));
		createUserInfoTab();
		
		userTicketTab = new JPanel();
		createUserTicketTab();
		
		userForumTab = new JPanel(new GridLayout(2,1));
		createUserForumTab();
		
		userTabbedPane.setVisible(false);
	}

	//creates user ticket tab
	private void createUserTicketTab()
	{
		userTabbedPane.addTab("Tickets", userTicketTab);
		userTicketTab.setVisible(true);
		
		userTicketTab.setLayout(new GridLayout(1,2));
		JPanel ticketChoicesPanel = new JPanel(new GridLayout(4,1));
		JPanel ticketRadioPanel = new JPanel(new GridLayout(2,1));
		
		selectMovieRadioButton = new JRadioButton("Select By Movie");
		selectLocationRadioButton = new JRadioButton("Select By Theater");
		ButtonGroup group = new ButtonGroup();
		group.add(selectMovieRadioButton);
		group.add(selectLocationRadioButton);
		
		theaterList = new JComboBox<String>();
		movieList = new JComboBox<String>();
		dateList = new JComboBox<String>();
		
		ticketChoicesPanel.setVisible(true);
		ticketRadioPanel.setVisible(true);
		ticketRadioPanel.add(selectMovieRadioButton);
		ticketRadioPanel.add(selectLocationRadioButton);
		ticketChoicesPanel.add(ticketRadioPanel);
		
		userTicketTab.add(ticketChoicesPanel);
	}
	
	//creates user forum tab
	private void createUserForumTab()
	{
		JPanel forumChoicesPanel = new JPanel(new GridLayout(4,1));
		JPanel forumButtonPanel = new JPanel();
		JPanel forumPartsPanel = new JPanel(new GridLayout(1,2));
		
		commentText = new JTextArea();
		commentText.setEditable(false);
		JScrollPane scrollText = new JScrollPane(commentText);
		
		threadList = new JComboBox<String>();
		threadList.setActionCommand("thread selected");
		threadList.addActionListener(this);
		threadList.setEnabled(false);
		threadList.setSelectedIndex(-1);
		
		commentList = new JComboBox<String>();
		commentList.setActionCommand("comment selected");
		commentList.addActionListener(this);
		commentList.setEnabled(false);
		
		JScrollPane threadScroll = new JScrollPane(threadList);
		JScrollPane commentScroll = new JScrollPane(commentList);
		
		movieRadioButton = new JRadioButton("Movie Threads");
		movieRadioButton.setActionCommand("movie threads");
		movieRadioButton.addActionListener(this);
		
		starRadioButton = new JRadioButton("Star Threads");
		starRadioButton.setActionCommand("star threads");
		starRadioButton.addActionListener(this);
		
		ButtonGroup threadsGroup = new ButtonGroup();
		threadsGroup.add(movieRadioButton);
		threadsGroup.add(starRadioButton);
		
		forumChoicesPanel.add(movieRadioButton);
		forumChoicesPanel.add(starRadioButton);
		forumChoicesPanel.add(threadScroll);
		forumChoicesPanel.add(commentScroll);
		
		createThreadButton = new JButton("Create Thread");
		createThreadButton.setActionCommand("create thread");
		createThreadButton.addActionListener(this);
		
		createCommentButton = new JButton("Create Comment");
		createCommentButton.setActionCommand("create comment");
		createCommentButton.addActionListener(this);
		
		forumButtonPanel.add(createThreadButton);
		forumButtonPanel.add(createCommentButton);
		
		movieRadioButton.setVisible(true);
		starRadioButton.setVisible(true);
		commentText.setVisible(true);
		createThreadButton.setVisible(true);
		createCommentButton.setVisible(true);
		
		forumChoicesPanel.setVisible(true);
		forumButtonPanel.setVisible(true);
		
		forumPartsPanel.add(forumChoicesPanel);
		forumPartsPanel.add(scrollText);
		userForumTab.add(forumPartsPanel);
		userForumTab.add(forumButtonPanel);
		
		userTabbedPane.addTab("Forum", userForumTab);	
		userForumTab.setVisible(true);
	}
	
	//creates user info tab
	private void createUserInfoTab()
	{
		//add credit card information fields -- cvv, name on credit card, cardtype, exp date, street1, street2, city, state, zip
		JPanel userInfoMain = new JPanel(new GridLayout(8,4));
		JPanel userInfoButton = new JPanel();
		
		JButton updateUserButton = new JButton("Update Info");
		updateUserButton.setActionCommand("user updates info");
		updateUserButton.addActionListener(this);
		
		JLabel usernameLabel = new JLabel("Username: ");
		usernameText = new JTextField(user.username);
		usernameText.setEnabled(false);
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordText = new JTextField(user.password);
		
		JLabel nameLabel = new JLabel("Name: ");
		nameText = new JTextField(user.name);
		nameText.setEnabled(false);
		
		JLabel phoneLabel = new JLabel("Phone: ");
		phoneText = new JTextField(user.phone);
		
		JLabel emailLabel = new JLabel("Email: ");
		emailText = new JTextField(user.email);
		
		JLabel pointsLabel = new JLabel("Reward Points: ");
		pointsText = new JTextField(String.valueOf(user.curPoints));
		pointsText.setEnabled(false);
		
		JLabel statusLabel = new JLabel("Reward Level: ");
		statusText = new JTextField(user.rewardLevel);
		statusText.setEnabled(false);
		
		JLabel ccnLabel = new JLabel("CCN: ");
		ccnText = new JTextField(user.ccn);
		
		JLabel ccvLabel = new JLabel("CCV: ");
		ccvText = new JTextField("Need DAO w/this field");
		
		JLabel ccNameLabel = new JLabel("Name on CC: ");
		ccNameText = new JTextField("Need DAO w/this field");
		
		JLabel ccExpLabel = new JLabel("CC Exp Date: ");
		ccExpText = new JTextField("Need DAO w/this field");
		
		JLabel street1Label = new JLabel("Street 1: ");
		street1Text = new JTextField("Need DAO w/this field");
		
		JLabel street2Label = new JLabel("Street 2: ");
		street2Text = new JTextField("Need DAO w/this field");
		
		JLabel cityLabel = new JLabel("City: ");
		cityText = new JTextField("Need DAO w/this field");
		
		JLabel stateLabel = new JLabel("State: ");
		stateText = new JTextField("Need DAO w/this field");
		
		JLabel zipLabel = new JLabel("Zip Code: ");
		zipText = new JTextField("Need DAO w/this field");
		
		userInfoMain.add(usernameLabel);
		userInfoMain.add(usernameText);
		userInfoMain.add(ccnLabel);
		userInfoMain.add(ccnText);
		userInfoMain.add(passwordLabel);
		userInfoMain.add(passwordText);
		userInfoMain.add(ccvLabel);
		userInfoMain.add(ccvText);
		userInfoMain.add(nameLabel);
		userInfoMain.add(nameText);
		userInfoMain.add(ccNameLabel);
		userInfoMain.add(ccNameText);
		userInfoMain.add(phoneLabel);
		userInfoMain.add(phoneText);
		userInfoMain.add(ccExpLabel);
		userInfoMain.add(ccExpText);
		userInfoMain.add(emailLabel);
		userInfoMain.add(emailText);
		userInfoMain.add(zipLabel);
		userInfoMain.add(zipText);
		userInfoMain.add(pointsLabel);
		userInfoMain.add(pointsText);
		userInfoMain.add(statusLabel);
		userInfoMain.add(statusText);
		userInfoMain.add(street1Label);
		userInfoMain.add(street1Text);
		userInfoMain.add(street2Label);
		userInfoMain.add(street2Text);
		userInfoMain.add(cityLabel);
		userInfoMain.add(cityText);
		userInfoMain.add(stateLabel);
		userInfoMain.add(stateText);

		
		userInfoButton.add(updateUserButton,SwingConstants.CENTER);
		
		updateUserButton.setVisible(true);
		usernameLabel.setVisible(true);
		usernameText.setVisible(true);
		passwordLabel.setVisible(true);
		passwordText.setVisible(true);
		nameLabel.setVisible(true);
		nameText.setVisible(true);
		ccnLabel.setVisible(true);
		ccnText.setVisible(true);
		phoneLabel.setVisible(true);
		phoneText.setVisible(true);
		emailLabel.setVisible(true);
		emailText.setVisible(true);
		pointsLabel.setVisible(true);
		pointsText.setVisible(true);
		statusLabel.setVisible(true);
		statusText.setVisible(true);
		ccvLabel.setVisible(true);
		ccvText.setVisible(true);
		ccNameLabel.setVisible(true);
		ccNameText.setVisible(true);
		ccExpLabel.setVisible(true);
		ccExpText.setVisible(true);
		street1Label.setVisible(true);
		street1Text.setVisible(true);
		street2Label.setVisible(true);
		street2Text.setVisible(true);
		cityLabel.setVisible(true);
		cityText.setVisible(true);
		stateLabel.setVisible(true);
		stateText.setVisible(true);
		zipLabel.setVisible(true);
		zipText.setVisible(true);
		
		
		userInfoMain.setVisible(true);
		userInfoButton.setVisible(true);
		
		userInfoTab.add(userInfoMain);
		userInfoTab.add(userInfoButton);
		userTabbedPane.addTab("Info", userInfoTab);

		userInfoTab.setVisible(true);
	}
	
	//creates new thread from create thread panel
	private void createThread()
	{
		forumCreatePanel = new JPanel(new GridLayout(5,1));
		JPanel forumCreateButtonPanel = new JPanel();
		
		JLabel newLabel = new JLabel("Create a New Thread");
		newLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		createStarThreadButton = new JRadioButton("Star Thread");
		createMovieThreadButton = new JRadioButton("Movie Thread");
		ButtonGroup createGroup = new ButtonGroup();
		createGroup.add(createStarThreadButton);
		createGroup.add(createMovieThreadButton);
		createStarThreadButton.setSelected(true);
		
		forumCreateButtonPanel.add(createStarThreadButton);
		forumCreateButtonPanel.add(createMovieThreadButton);
		
		createThreadText = new JTextField("Thread Title", 50);
		createCommentText = new JTextField("Comment Text", 50);
		
		threadSubmitButton = new JButton("Submit");
		threadSubmitButton.setActionCommand("submit thread");
		threadSubmitButton.addActionListener(this);
		
		createStarThreadButton.setVisible(true);
		createMovieThreadButton.setVisible(true);
		forumCreateButtonPanel.setVisible(true);
		createThreadText.setVisible(true);
		createCommentText.setVisible(true);
		threadSubmitButton.setVisible(true);
		forumCreatePanel.setVisible(true);
		userTabbedPane.setVisible(false);
		
		forumCreatePanel.add(newLabel);
		forumCreatePanel.add(forumCreateButtonPanel);
		forumCreatePanel.add(createThreadText);
		forumCreatePanel.add(createCommentText);
		forumCreatePanel.add(threadSubmitButton);
		contentPanel.add(forumCreatePanel,"Forum Create");
		
		starRadioButton.setSelected(true);
		starRadioButton.setSelected(false);
		movieRadioButton.setSelected(false);
	}
	
	//creates new comment from create thread panel
	private void createComment()
	{		
		createThread();
	
		createStarThreadButton.setEnabled(false);
		createStarThreadButton.setSelected(starRadioButton.isSelected());
		createMovieThreadButton.setEnabled(false);
		createMovieThreadButton.setSelected(movieRadioButton.isSelected());
		
		createThreadText.setEnabled(false);
		createThreadText.setText((String) threadList.getSelectedItem()); 
		
		threadSubmitButton.setActionCommand("submit comment");
	}
	
	//handles user actions on GUI
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		CardLayout layoutManager = (CardLayout) contentPanel.getLayout();
		
		if(loginPanel.isShowing()) 
		{
			if(e.getActionCommand() == "user login")
			{
				username = loginField.getText();
				password = new String(pwField.getPassword());
				
				if(user.login(username, password) == true) {
					user.getPointsInfo();
					createUserTabs();
					layoutManager.show(contentPanel, "User Tabs");
					userForumTab.setVisible(false);
					userTicketTab.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(frame,"Invalid username/password.","Login Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(userInfoTab.isShowing())
		{
			if(e.getActionCommand() == "user updates info")
			{
				 password = passwordText.getText();
				 ccn = ccnText.getText();
				 phone = phoneText.getText();
				 email = emailText.getText();
				 
				 user.update(password, ccn, phone, email);
				 JOptionPane.showMessageDialog(frame,"Update Success","User Profile",JOptionPane.PLAIN_MESSAGE);
			}	
		}
		else if(userForumTab.isShowing())
		{
			threadList.removeActionListener(this);
			commentList.removeActionListener(this);
			if(e.getActionCommand() == "movie threads")
			{
				threadList.removeAllItems();
				threadList.setEnabled(true);
				for(int i=0; i<movieThreads.get(0).size(); i++)
				{
					threadList.addItem(movieThreads.get(0).get(i));
				}

				commentList.removeAllItems();
				commentList.setEnabled(false);
				commentText.setText("");
				threadList.setSelectedIndex(-1);
			}
			else if(e.getActionCommand() == "star threads")
			{
				threadList.removeAllItems();
				threadList.setEnabled(true);
				for(int i=0; i<starThreads.get(0).size(); i++)
				{
					threadList.addItem(starThreads.get(0).get(i));
				}

				commentList.removeAllItems();
				commentList.setEnabled(false);
				commentText.setText("");
				threadList.setSelectedIndex(-1);
			}
			else if(e.getActionCommand() == "thread selected" && threadList.getSelectedIndex() != -1)
			{
				commentList.setEnabled(true);
				commentList.removeAllItems();
				if(movieRadioButton.isSelected()) 
				{
					int i = movieThreads.get(0).indexOf((String) threadList.getSelectedItem());
					System.out.println(i);
					
					for(int j=0;j<movieThreads.get(i+1).size();j++)
					{
						commentList.addItem(movieThreads.get(i+1).get(j));
					}
					
				}
				else if(starRadioButton.isSelected())
				{
					{
						int i = starThreads.get(0).indexOf((String) threadList.getSelectedItem());
						System.out.println(i);

						for(int j=0;j<starThreads.get(i+1).size();j++)
						{
								commentList.addItem(starThreads.get(i+1).get(j));
						}
						
					}
				}
				commentList.setSelectedIndex(-1);
				commentText.setText("");
			}
			else if(e.getActionCommand() == "comment selected" && commentList.getSelectedIndex() != -1)
			{
				commentText.setText((String)((JComboBox) e.getSource()).getSelectedItem());
				System.out.println("test");
				System.out.println(commentList.getSelectedIndex());
			}
			else if(e.getActionCommand() == "create thread")
			{
				createThread();
				layoutManager.show(contentPanel, "Forum Create");
			}
			else if(e.getActionCommand() == "create comment")
			{
				if((movieRadioButton.isSelected() || starRadioButton.isSelected()) && threadList.getSelectedIndex() != -1)
				{
					createComment();
					layoutManager.show(contentPanel, "Forum Create");
				}
				else JOptionPane.showMessageDialog(frame,"You must select a thread first.","Create Comment Error",JOptionPane.ERROR_MESSAGE);
			}
			threadList.addActionListener(this);
			commentList.addActionListener(this);
		}
		else if(forumCreatePanel.isShowing())
		{
			if(e.getActionCommand() == "submit thread")
			{
				if(createMovieThreadButton.isSelected())
				{
					movieThreads.add(new Vector<String>());
					movieThreads.get(0).add(createThreadText.getText());
					movieThreads.get(movieThreads.size()-1).add(createCommentText.getText());
				}
				else
				{
					starThreads.add(new Vector<String>());
					starThreads.get(0).add(createThreadText.getText());
					starThreads.get(starThreads.size()-1).add(createCommentText.getText());

				}

				layoutManager.show(contentPanel, "User Tabs");
			}
			else if(e.getActionCommand() == "submit comment")
			{
				if(createMovieThreadButton.isSelected())
				{
					int i = movieThreads.get(0).indexOf(createThreadText.getText());
					movieThreads.get(i+1).add(createCommentText.getText());
				}
				else
				{
					int i = starThreads.get(0).indexOf(createThreadText.getText());
					starThreads.get(i+1).add(createCommentText.getText());
				}
				layoutManager.show(contentPanel, "User Tabs");
			}
			
			if(createMovieThreadButton.isSelected())
			{
				movieRadioButton.doClick();
			}
			else starRadioButton.doClick();
		}
		frame.pack();
	}
}
