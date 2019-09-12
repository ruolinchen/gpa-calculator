//Ruolin Chen, rlc8my
//Homework 5: GPA Calculator and Planner
//Notes: Formatting may appear too big on some screens because I have a high resolution screen and did not figure out how to scale
//everything up so hard coded some font, size, and placement so I could see an make it nice looking
/**Sources and web-sites used:
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html
 * https://docs.oracle.com/javase/tutorial/uiswing/components/index.html
 * http://www.seasite.niu.edu/cs580java/JList_Basics.htm
 * http://www.java2s.com/Code/Java/Swing-JFC/AnexampleofJListwithaDefaultListModel.htm
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GPACalculatorPlanner1 extends JFrame{
	private JLabel instructions, instruc1, instruc2, instruc3, credits, grade, name, target, 
	currentGPA, requiredGPA, listClasses, bigGPA, smallGPA, gpaInstruc, gpaInstruc1;
	private JButton add, remove, refresh, calc, calculate, blank;
	private JTextField courseCreditHours, courseGrade, courseName, targetGPA;
	private JPanel p1, p2, p3;
	private JList<String> allClasses;
	private ArrayList<String> creditHours, creditHoursWithoutGrades, grades, names;
	private DefaultListModel<String> classes;
	
	private double gpa;
	
	public static void main(String[] args) {
		new GPACalculatorPlanner1();
	}
	
	public GPACalculatorPlanner1() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//window size taken from size of the screen
		int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		this.setSize(width, height);
		System.out.println(width);
		System.out.println(height);
		this.setLocationRelativeTo(null);
		setTitle("GPA Calculator and Planner");
		
		gpa = 0.0; //default GPA is 0.0
		

		
		//initializing fields ---------------------------------------------------------------------------------------------------
		instructions = new JLabel("<html>Enter the required information to calculate your GPA. </html>");
		instruc1 = new JLabel("<html>To add a class: Enter information and click 'Add Class'.</html>");
		instruc2 = new JLabel("<html>To remove a class: Click on class you want to remove in the list and click 'Remove'.</html>");
		instruc3 = new JLabel("<html>To reset classes: Click on 'Refresh'.</html>");
		credits = new JLabel("<html>Credit Hours: </html>");
		grade = new JLabel("<html> Letter Grade Earned (Optional): </html>");
		name = new JLabel("<html>Name of Course (Optional): </html>");
		target = new JLabel("<html>Target GPA: </html>");
		currentGPA = new JLabel("<html>Current GPA: " + gpa + "</html>");
		requiredGPA = new JLabel("<html>Required GPA: </html>");
		listClasses = new JLabel("<html>List of Classes (Credits/Grade/Name):</html>");
		gpaInstruc = new JLabel("<html>To calculate GPA: Click on 'Calculate GPA'</html>");
		gpaInstruc1 = new JLabel("<html>To calculate required GPA: Enter targeted GPA and click 'Calculate Required GPA'</html>");
		
		courseCreditHours = new JTextField(100);
		courseGrade = new JTextField(100);
		courseName = new JTextField(100);
		targetGPA = new JTextField(100);
		
		add = new JButton("Add Class");
		remove = new JButton("Remove");
		refresh = new JButton("Refresh");
		blank = new JButton("Blank Credits");
		calc = new JButton("Calculate GPA");
		calculate = new JButton("Calculate Required GPA");
		//------------------------------------------------------------------------------------------------------------------------
		
		//set size of font for the input------------------------------------------------------------------------------------------
		int fontSize = width/80;
		Font textFont = new Font("Times New Roman", Font.PLAIN,  fontSize);
		courseCreditHours.setFont(textFont);
		courseGrade.setFont(textFont);
		courseName.setFont(textFont);
		Font textFont2 = new Font("Times New Roman", Font.PLAIN,  fontSize);
		targetGPA.setFont(textFont2);
		//------------------------------------------------------------------------------------------------------------------------
		
		//set font size for the instructions and buttons (for myself and my high resolution laptop)-------------------------------
		instructions.setFont(new Font("Times New Roman", Font.PLAIN,  fontSize));
		instruc1.setFont(new Font("Times New Roman", Font.PLAIN,  fontSize));
		instruc2.setFont(new Font("Times New Roman", Font.PLAIN,  fontSize));
		instruc3.setFont(new Font("Times New Roman", Font.PLAIN,  fontSize));
		listClasses.setFont(new Font("Times New Roman", Font.PLAIN,  fontSize));
		gpaInstruc.setFont(new Font("Times New Roman", Font.PLAIN,  fontSize));
		gpaInstruc1.setFont(new Font("Times New Roman", Font.PLAIN,  fontSize));
		Font f = new Font("Times New Roman", Font.PLAIN, 20);
		Font o = new Font("Times New Roman", Font.PLAIN, 20);
		credits.setFont(f);
		grade.setFont(f);
		name.setFont(f);
		
		add.setFont(o);
		remove.setFont(o);
		blank.setFont(o);
		refresh.setFont(o);
		calc.setFont(o);
		calculate.setFont(o);
		
		target.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		currentGPA.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		requiredGPA.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		//------------------------------------------------------------------------------------------------------------------------

		//set size and location of each text field and buttons--------------------------------------------------------------------
		instructions.setSize(width/3, height/18);
		instruc1.setSize(width/3, height/18);
		instruc2.setSize(width/2, height/18);
		instruc3.setSize(width/3, height/18);
		credits.setSize(width/16, height/18);
		grade.setSize(width/4, height/18);
		name.setSize(width/4, height/18);
		listClasses.setSize(width/2, height/18);
		
		gpaInstruc.setSize(width/3, height/18);
		gpaInstruc1.setSize(width/4, height/18);
		
		courseCreditHours.setSize(width/6, 70);
		courseGrade.setSize(width/6, 70);
		courseName.setSize(width/6, 70);
		
		add.setSize(width/12, height/18);
		remove.setSize(width/12, height/18);
		blank.setSize(width/12, height/18);
		refresh.setSize(width/12, height/18);
		calc.setSize(width/8, height/24);
		calculate.setSize(width/8, height/24);
		
		target.setSize(width/11, height/32);
		targetGPA.setSize(width/11, height/23);
		requiredGPA.setSize(width/3, height/32);
		currentGPA.setSize(width/3, height/32);
		
		instructions.setLocation(width/32, height/18);
		instruc1.setLocation(width/32, 2*height/18);
		instruc2.setLocation(width/32, 3*height/18);
		instruc3.setLocation(width/32, 4*height/18);
		credits.setLocation(width/17, height/3);
		grade.setLocation(width/17, 5*height/12);
		name.setLocation(width/17, 6*height/12);
		listClasses.setLocation(4*width/9, height/18);
		
		gpaInstruc.setLocation(5*width/7, 2*height/18);
		gpaInstruc1.setLocation(5*width/7, 3*height/18);
		
		courseCreditHours.setLocation(width/5, height/3);
		courseGrade.setLocation(width/5, 5*height/12);
		courseName.setLocation(width/5, 6*height/12);
		
		add.setLocation(width/30, 2*height/3);
		remove.setLocation(4*width/30, 2*height/3);
		blank.setLocation(7*width/30, 2*height/3);
		refresh.setLocation(10*width/30, 2*height/3);
		calc.setLocation(5*width/7, 7*height/24);
		calculate.setLocation(5*width/7, 6*height/12);
		
		target.setLocation(5*width/7, 9*height/24);
		targetGPA.setLocation(5*width/6, 9*height/24);
		requiredGPA.setLocation(5*width/7, 10*height/24);
		currentGPA.setLocation(5*width/7, 6*height/24);
		//-----------------------------------------------------------------------------------------------------------------------
		
		//panel to display the entered classes------------------------------------------------------------------------------------
		p1 = new JPanel();
		p1.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		p1.setSize(width/4, height/2);
		p1.setLocation(4*width/9, height/9);
		p1.setBackground(Color.WHITE);
		classes = new DefaultListModel<String>();
		allClasses = new JList<String>(classes);
		allClasses.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		p1.add(allClasses);
		
		p2 = new JPanel();
		p2.setSize(width/2, height/6);
		p2.setLocation(4*width/9, 3*height/4);
		bigGPA = new JLabel("Your required GPA is greater than 4.0. You should take/add more credits and then recalculate.");
		bigGPA.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		p2.add(bigGPA);
		p2.setVisible(false);
		
		p3 = new JPanel();
		p3.setSize(width/2, height/6);
		p3.setLocation(4*width/9, 3*height/4);
		smallGPA = new JLabel("Your required GPA is less than 2.0. You may take less credits if you wish.");
		smallGPA.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
		p3.add(smallGPA);
		p3.setVisible(false);
		//------------------------------------------------------------------------------------------------------------------------
		
		//takes inputs and puts them in a list------------------------------------------------------------------------------------
		creditHours = new ArrayList<String>();
		creditHoursWithoutGrades = new ArrayList<String>();
		grades = new ArrayList<String>();
		names = new ArrayList<String>();
		//------------------------------------------------------------------------------------------------------------------------
		
		//adding everything to the frame------------------------------------------------------------------------------------------
		this.add(instructions);
		this.add(instruc1);
		this.add(instruc2);
		this.add(instruc3);
		this.add(gpaInstruc);
		this.add(gpaInstruc1);
		this.add(credits);
		this.add(grade);
		this.add(name);
		this.add(listClasses);
		this.add(target);
		this.add(currentGPA);
		this.add(requiredGPA);
		this.add(courseCreditHours);
		this.add(courseGrade);
		this.add(courseName);
		this.add(targetGPA);
		this.add(add);
		this.add(refresh);
		this.add(blank);
		this.add(remove);
		this.add(calc);
		this.add(calculate);
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(new JLabel());
		setVisible(true);
		
		add.addActionListener(new addButtonListener());
		remove.addActionListener(new removeButtonListener());
		blank.addActionListener(new blankButtonListener());
		refresh.addActionListener(new refreshButtonListener());
		calc.addActionListener(new calculateGPAButtonListener());
		calculate.addActionListener(new targetGPAButtonListener());
		//-------------------------------------------------------------------------------------------------------------------------
	}
	
	//button actions---------------------------------------------------------------------------------------------------------------
	private class addButtonListener implements ActionListener {
		//adds information entered in the text fields to the list of classes
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//gets information from the text fields
			int creditHour = Integer.parseInt(courseCreditHours.getText());
			String grade = courseGrade.getText();
			String name = courseName.getText();
			if(grade.equals("")) {
				creditHoursWithoutGrades.add(courseCreditHours.getText());
			}
			else {
				creditHours.add(courseCreditHours.getText());
				grades.add(grade);
			}
			names.add(name);
			String info = creditHour + "     " + grade + "     " + name;
			//adds to the list of classes
			classes.addElement(info);
		}
	}
	
	private class removeButtonListener implements ActionListener {
		//removes a single class
		@Override
		public void actionPerformed(ActionEvent arg0) {
			classes.removeElement(allClasses.getSelectedValue());
		}
	}
	
	private class blankButtonListener implements ActionListener {
		//adds 15 blank credits
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//assuming adds five classes with 3 credits each
			classes.addElement("3");
			classes.addElement("3");
			classes.addElement("3");
			classes.addElement("3");
			classes.addElement("3");
			creditHoursWithoutGrades.add("3");
			creditHoursWithoutGrades.add("3");
			creditHoursWithoutGrades.add("3");
			creditHoursWithoutGrades.add("3");
			creditHoursWithoutGrades.add("3");
		}
	}
	
	private class refreshButtonListener implements ActionListener {
		//clears the list of classes
		@Override
		public void actionPerformed(ActionEvent arg0) {
			classes.clear();
			creditHours.clear();
			grades.clear();
			names.clear();
			creditHoursWithoutGrades.clear();
		}
	}
	
	private class calculateGPAButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			double n = 0;
			int allCredits = 0;
			double total = 0;
			double sum = 0;
			for(String g : grades) {
				//assumes user enters letter grades
				if(g.equals("A+") || g.equals("A")) {
					n = 4.0;
				}
				else if(g.equals("A-")) {
					n = 3.7;
				}
				else if(g.equals("B+")) {
					n = 3.3;
				}
				else if(g.equals("B")) {
					n = 3.0;
				}
				else if(g.equals("B-")) {
					n = 2.7;
				}
				else if(g.equals("C+")) {
					n = 2.3;
				}
				else if(g.equals("C")) {
					n = 2.0;
				}
				else if(g.equals("C-")) {
					n = 1.7;
				}
				else if(g.equals("D+")) {
					n = 1.3;
				}
				else if(g.equals("D")) {
					n = 1.0;
				}
				else if(g.equals("D-")) {
					n = 0.7;
				}
				else if(g.equals("F")) {
					n = 0.0;
				}
				int i = grades.indexOf(g);
				
				total = n * Double.parseDouble(creditHours.get(i));
				allCredits += Integer.parseInt(creditHours.get(i));
				sum += total;
				
			}
			float average = (float) (sum / allCredits);
			gpa = average;
			currentGPA.setText("<html>Current GPA: " + gpa + "</html>");
		}
	}
	private class targetGPAButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			double inputGPA;
			//gets target GPA from input entered
			inputGPA = Double.parseDouble(targetGPA.getText());
			int totcred = 0; //total credits that have grades
			int allcred = 0; //total credits that don't have grades
			int sumcred = 0;
			for(String c : creditHours) {
				int cred = Integer.parseInt(c);
				totcred += cred;
			}
			for(String h :creditHoursWithoutGrades) {
				int hour = Integer.parseInt(h);
				allcred += hour;
			}
			sumcred = totcred + allcred; //all credits including those without a grade
			double m = inputGPA * sumcred;
			int allCredits = 0;
			for(String g : grades) {
				int i = grades.indexOf(g);
				allCredits += Integer.parseInt(creditHours.get(i));
			}
			double j = gpa * allCredits; 
			double k = m - j;
			//divided by the number of total credits without a grade
			double reqGPA = k / allcred;
			requiredGPA.setText("<html>Required GPA: " + reqGPA + "</html>");
			if(reqGPA > 4.0) {
				p2.setVisible(true);
			}
			if(reqGPA < 2.0) {
				p3.setVisible(true);
			}
		}
	}
	public void scaleWindowDimensions(JFrame frame, GraphicsDevice gd) {
	    Rectangle bounds = gd.getDefaultConfiguration().getBounds();
	    int screenX = (int) bounds.getWidth();
	    int screenY = (int) bounds.getHeight();
	    // substitute this for however you're setting the size of the JFrame; this is simply how I sometimes do it
	    frame.getContentPane().setPreferredSize(new Dimension(screenX, screenY));
	}
}
