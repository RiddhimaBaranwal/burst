// A simple loan calculator applet.   
import java.awt.*;   
import java.awt.event.*;   
 import javax.swing.*; 
import java.text.*;   
/*   
  <applet code="RegPay" width=320 height=200>   
  </applet>   
*/   
   
public class RegPay extends JApplet   
  implements ActionListener {   
   
  JTextField amountText, paymentText, periodText, rateText;  
  JButton doIt;   
   
  double principal; // original princial  
  double intRate;   // interest rate  
  double numYears;  // length of loan in years  
  
  /* Number of payments per year. You could  
     allow this value to be set by the user. */  
  final int payPerYear = 12;  
  
  NumberFormat nf;  
  
  public void init() {   
    try {  
      SwingUtilities.invokeAndWait(new Runnable () {  
        public void run() {  
          makeGUI(); // initialize the GUI  
        }  
      });  
    } catch(Exception exc) {  
      System.out.println("Can't create because of "+ exc);  
    }  
  } 
 
  // Setup and initialize the GUI.   
  private void makeGUI() {  
 
    // Use a grid bag layout.  
    GridBagLayout gbag = new GridBagLayout();  
    GridBagConstraints gbc = new GridBagConstraints();  
    setLayout(gbag);  
  
    JLabel heading = new   
           JLabel("Compute Monthly Loan Payments");  
   
    JLabel amountLab = new JLabel("Principal ");  
    JLabel periodLab = new JLabel("Years ");  
    JLabel rateLab = new JLabel("Interest Rate ");  
    JLabel paymentLab = new JLabel("Monthly Payments ");  
  
    amountText = new JTextField(10);   
    periodText = new JTextField(10);   
    paymentText = new JTextField(10);   
    rateText = new JTextField(10);  
  
    // Payment field for display only.  
    paymentText.setEditable(false);  
  
    doIt = new JButton("Compute");   
  
    // Define the grid bag.  
    gbc.weighty = 1.0; // use a row weight of 1  
    gbc.gridwidth = GridBagConstraints.REMAINDER;  
    gbc.anchor = GridBagConstraints.NORTH;  
    gbag.setConstraints(heading, gbc);  
  
    // Anchor most components to the right.  
    gbc.anchor = GridBagConstraints.EAST;  
  
    gbc.gridwidth = GridBagConstraints.RELATIVE;  
    gbag.setConstraints(amountLab, gbc);  
    gbc.gridwidth = GridBagConstraints.REMAINDER;  
    gbag.setConstraints(amountText, gbc);  
  
    gbc.gridwidth = GridBagConstraints.RELATIVE;  
    gbag.setConstraints(periodLab, gbc);  
    gbc.gridwidth = GridBagConstraints.REMAINDER;  
    gbag.setConstraints(periodText, gbc);  
  
    gbc.gridwidth = GridBagConstraints.RELATIVE;  
    gbag.setConstraints(rateLab, gbc);  
    gbc.gridwidth = GridBagConstraints.REMAINDER;  
    gbag.setConstraints(rateText, gbc);  
  
    gbc.gridwidth = GridBagConstraints.RELATIVE;  
    gbag.setConstraints(paymentLab, gbc);  
    gbc.gridwidth = GridBagConstraints.REMAINDER;  
    gbag.setConstraints(paymentText, gbc);  
  
    gbc.anchor = GridBagConstraints.CENTER;  
    gbag.setConstraints(doIt, gbc);  
  
    // Add all the components.  
    add(heading);   
    add(amountLab);   
    add(amountText);   
    add(periodLab);   
    add(periodText);  
    add(rateLab);  
    add(rateText);  
    add(paymentLab);   
    add(paymentText);   
    add(doIt);   
   
    // Register to receive action events.  
    amountText.addActionListener(this);   
    periodText.addActionListener(this);   
    rateText.addActionListener(this);   
    doIt.addActionListener(this);       
  
    // Create a number format. 
    nf = NumberFormat.getInstance();  
    nf.setMinimumFractionDigits(2);  
    nf.setMaximumFractionDigits(2);  
  }   
   
  /* User pressed Enter on a text field or  
     pressed Compute. Display the result if all 
     fields are completed. */  
  public void actionPerformed(ActionEvent ae) {   
    double result = 0.0;  
  
    String amountStr = amountText.getText();   
    String periodStr = periodText.getText();   
    String rateStr = rateText.getText();  
  
    try {   
      if(amountStr.length() != 0 &&  
         periodStr.length() != 0 &&  
         rateStr.length() != 0) {  
  
        principal = Double.parseDouble(amountStr);  
        numYears = Double.parseDouble(periodStr);       
        intRate = Double.parseDouble(rateStr) / 100;  
  
        result = compute();    
   
        paymentText.setText(nf.format(result));   
      }  
   
      showStatus(""); // erase any previous error message   
    } catch (NumberFormatException exc) {   
      showStatus("Invalid Data");  
      paymentText.setText("");   
    }   
  }  
  
  // Compute the loan payment.  
  double compute() {  
    double numer;  
    double denom;  
    double b, e;  
  
    numer = intRate * principal / payPerYear;    
     
    e = -(payPerYear * numYears);    
    b = (intRate / payPerYear) + 1.0;    
    
    denom = 1.0 - Math.pow(b, e);    
  
    return numer / denom;    
  }      
}




// Find the remaining balance on a loan.  
import java.awt.*;   
import java.awt.event.*;   
import javax.swing.*;   
import java.text.*;   
/*   
  <applet code="RemBal" width=340 height=260>   
  </applet>   
*/   
   
public class RemBal extends JApplet   
  implements ActionListener {   
   
  JTextField orgPText, paymentText, remBalText,  
             rateText, numPayText;  
  JButton doIt;   
   
  double orgPrincipal; // original princial  
  double intRate;      // interest rate  
  double payment;      // amount of each payment  
  double numPayments;  // number of payments made  
  
  /* Number of payments per year.  You could  
     allow this value to be set by the user. */  
  final int payPerYear = 12;  
  
  NumberFormat nf;  
  
  public void init() {   
    try {  
      SwingUtilities.invokeAndWait(new Runnable () {  
        public void run() {  
          makeGUI(); // initialize the GUI  
        }  
      });  
    } catch(Exception exc) {  
      System.out.println("Can't create because of "+ exc);  
    }  
  } 
 
  // Setup and initialize the GUI.   
  private void makeGUI() {  
 
    // Use a grid bag layout.  
    GridBagLayout gbag = new GridBagLayout();  
    GridBagConstraints gbc = new GridBagConstraints();  
    setLayout(gbag);  
  
    JLabel heading = new   
           JLabel("Find Loan Balance ");  
   
    JLabel orgPLab = new JLabel("Original Principal ");  
    JLabel paymentLab = new JLabel("Amount of Payment ");  
    JLabel numPayLab = new JLabel("Number of Payments Made ");  
    JLabel rateLab = new JLabel("Interest Rate ");  
    JLabel remBalLab = new JLabel("Remaining Balance ");  
  
    orgPText = new JTextField(10);   
    paymentText = new JTextField(10);   
    remBalText = new JTextField(10);  
    rateText = new JTextField(10);  
    numPayText = new JTextField(10);  
  
    // Payment field for display only.  
    remBalText.setEditable(false);  
  
    doIt = new JButton("Compute");   
  
    // Define the grid bag.  
     gbc.weighty = 1.0; // use a row weight of 1  
  
    gbc.gridwidth = GridBagConstraints.REMAINDER;  
    gbc.anchor = GridBagConstraints.NORTH;  
    gbag.setConstraints(heading, gbc);  
  
    // Anchor most components to the right.  
    gbc.anchor = GridBagConstraints.EAST;  
  
    gbc.gridwidth = GridBagConstraints.RELATIVE;  
    gbag.setConstraints(orgPLab, gbc);  
    gbc.gridwidth = GridBagConstraints.REMAINDER;  
    gbag.setConstraints(orgPText, gbc);  
  
    gbc.gridwidth = GridBagConstraints.RELATIVE;  
    gbag.setConstraints(paymentLab, gbc);  
    gbc.gridwidth = GridBagConstraints.REMAINDER;  
    gbag.setConstraints(paymentText, gbc);  
  
    gbc.gridwidth = GridBagConstraints.RELATIVE;  
    gbag.setConstraints(rateLab, gbc);  
    gbc.gridwidth = GridBagConstraints.REMAINDER;  
    gbag.setConstraints(rateText, gbc);  
  
    gbc.gridwidth = GridBagConstraints.RELATIVE;  
    gbag.setConstraints(numPayLab, gbc);  
    gbc.gridwidth = GridBagConstraints.REMAINDER;  
    gbag.setConstraints(numPayText, gbc);  
  
    gbc.gridwidth = GridBagConstraints.RELATIVE;  
    gbag.setConstraints(remBalLab, gbc);  
    gbc.gridwidth = GridBagConstraints.REMAINDER;  
    gbag.setConstraints(remBalText, gbc);  
  
    gbc.anchor = GridBagConstraints.CENTER;  
    gbag.setConstraints(doIt, gbc);  
  
    // Add all the components.   
    add(heading);   
    add(orgPLab);   
    add(orgPText);   
    add(paymentLab);   
    add(paymentText);  
    add(numPayLab);   
    add(numPayText);  
    add(rateLab);  
    add(rateText);  
    add(remBalLab);   
    add(remBalText);   
    add(doIt);   
   
    // Register to receive action events.  
    orgPText.addActionListener(this);   
    numPayText.addActionListener(this);   
    rateText.addActionListener(this);   
    paymentText.addActionListener(this);   
    doIt.addActionListener(this);       
  
    // Create a number format. 
    nf = NumberFormat.getInstance();  
    nf.setMinimumFractionDigits(2);  
    nf.setMaximumFractionDigits(2);  
  }   
   
  /* User pressed Enter on a text field  
     or pressed Compute. Display the result if all 
     fields are completed. */  
  public void actionPerformed(ActionEvent ae) {   
    double result = 0.0;  
  
    String orgPStr = orgPText.getText();   
    String numPayStr = numPayText.getText();   
    String rateStr = rateText.getText();  
    String payStr = paymentText.getText();  
  
    try {   
      if(orgPStr.length() != 0 &&  
         numPayStr.length() != 0 &&  
         rateStr.length() != 0 &&  
         payStr.length() != 0) {  
  
        orgPrincipal = Double.parseDouble(orgPStr);  
        numPayments = Double.parseDouble(numPayStr);       
        intRate = Double.parseDouble(rateStr) / 100;  
        payment = Double.parseDouble(payStr);  
  
        result = compute();    
   
        remBalText.setText(nf.format(result));   
      }  
   
      showStatus(""); // erase any previous error message   
    } catch (NumberFormatException exc) {   
      showStatus("Invalid Data");  
      remBalText.setText("");   
    }   
  }  
  
  // Compute the loan balance.  
  double compute() {  
    double bal = orgPrincipal;  
    double rate = intRate / payPerYear;  
  
    for(int i = 0; i < numPayments; i++)  
      bal -= payment - (bal * rate);  
  
    return bal;  
  }  
}
