package bmicalculator.base.display;

import bmicalculator.base.engine.BMICalculator;
import java.awt.*;  
import java.awt.event.*;  
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class BMICalDisplay extends JFrame implements ActionListener{

    protected JPanel topPanel;
    protected JPanel whitePanel;
    protected JPanel bluePanel;

    protected JPanel midPanel;
    protected JPanel buttonPanel;

    protected JPanel weightPanel;
    protected JPanel heightPanel;
    
    protected JPanel flowPanel;
    protected JPanel inputPanel;

    protected JLabel heightLabel;
    protected JLabel weightLabel;

    protected JLabel feetLabel;
    protected JLabel inchLabel;

    protected Object buttonPressed;
    protected JLabel bmiLabel1;
    protected JLabel bmiLabel2;
    protected JLabel invalidInch;

    protected JComboBox<String> heightToggle;
    protected JComboBox<String> weightToggle;
    protected ItemListener toggleItemListener;

    protected String[] heightMetrics = {"cm", "ft"};
    protected String[] weightMetrics = {"kg", "ib"};

    protected JTextField heightTextField;
    protected JTextField cmTextField;
    protected JTextField feetTextField;
    protected JTextField inchTextField;
    protected JTextField weightTextField;
    protected JButton calButton;
    protected JButton clearButton;

    protected Border blackline;

    private float weight;
    private float cmHeight;
    protected  int feet; 
    protected  int inch; 
        

    public BMICalDisplay(){
        setTitle("BMI Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(3,1));
      
        getContentPane().add(topPanel());
        getContentPane().add(midPanel());
        getContentPane().add(buttonPanel());
        setBounds(100,100, 400, 300);

        
        calButton.addActionListener(this);
        clearButton.addActionListener(this);
        
        heightToggle.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent event) {

                if(event.getStateChange() == event.SELECTED) {
                    Object selected = heightToggle.getSelectedItem();
        
                    if(selected == "ft"){
                        inputPanel.remove(heightPanel);
                        heightPanel = ftPanel();
                        inputPanel.add(heightPanel, 1);
                        
                        inputPanel.validate();
                        inputPanel.repaint();
        
                    }
        
                    if (selected == "cm"){
        
                        inputPanel.remove(heightPanel);
                        heightPanel = cmPanel();
                        inputPanel.add(heightPanel, 1);
                        
                        inputPanel.validate();
                        inputPanel.repaint();
        
                    }          
        
                }
        
            }
        });
     

        setVisible(true); 

    }

  
    @Override
    public void actionPerformed(ActionEvent e){
        buttonPressed = e.getSource();
        bmiLabel1 = new JLabel();
        bmiLabel2 = new JLabel();
        invalidInch = new JLabel();

        if (buttonPressed == clearButton){
            try{
            
            whitePanel.removeAll();
            this.validate();
            this.repaint();

            weightTextField.setText("");
            heightTextField.setText("");
            inchTextField.setText("");
            feetTextField.setText("");
            this.validate();
            this.repaint();

            }catch(Exception error){
                
            }     

        }      
       
        if (buttonPressed == calButton){

            try{            
                if(heightToggle.getSelectedItem() == "cm" && weightToggle.getSelectedItem() == "kg"){

                    weight = Float.parseFloat(weightTextField.getText()); 
                    cmHeight = Float.parseFloat(heightTextField.getText()); 
                        
                    Float bmi = BMICalculator.kgCmBMICal(weight, cmHeight);  
                    
                    if (bmi <= 18.5){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the Underweight range.");
                        
                    }
            
                    if (bmi > 18.5 && bmi < 24.9){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the normal range.");
                    }
            
                    if (bmi > 24.9 && bmi < 29.9){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the overweight range.");
                    }
            
                    if (bmi >= 30){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the Obese range.");
                    }
                    
                    whitePanel.removeAll();
                    whitePanel.add(bmiLabel1);
                    whitePanel.add(bmiLabel2);

                    topPanel.validate();
                    topPanel.repaint();

                
                }

                if (heightToggle.getSelectedItem() == "ft" && weightToggle.getSelectedItem() == "kg"){
            

                    if (inchTextField.getText().isEmpty()){

                        inchTextField.setText("0"); 

                    }

                    weight = Float.parseFloat(weightTextField.getText()); 
                    feet = Integer.parseInt(feetTextField.getText()); 
                    inch = Integer.parseInt(inchTextField.getText());  
                  

                    if (inch < 0 || inch > 11){

                        invalidInch.setText("Please enter inches between 0 and 11");
                        whitePanel.removeAll();
                        whitePanel.add(invalidInch);

                        topPanel.validate();
                        topPanel.repaint();


                    }

                    else{
                        
                    Float bmi = BMICalculator.kgFtBMICal(weight, feet, inch);

                    if (bmi <= 18.5){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the Underweight range.");
                        
                    }
            
                    if (bmi > 18.5 && bmi < 24.9){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the normal range.");
                    }
            
                    if (bmi > 24.9 && bmi < 29.9){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the overweight range.");
                    }
            
                    if (bmi >= 30){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the Obese range.");
                    }

                    whitePanel.removeAll();
                    whitePanel.add(bmiLabel1);
                    whitePanel.add(bmiLabel2);

                    topPanel.validate();
                    topPanel.repaint();
                    }

                }

                if(heightToggle.getSelectedItem() == "ft" && weightToggle.getSelectedItem() == "ib"){

                    if (inchTextField.getText().isEmpty()){

                        inchTextField.setText("0"); 

                    }

                    weight = Float.parseFloat(weightTextField.getText()); 
                    feet = Integer.parseInt(feetTextField.getText());  
                    inch = Integer.parseInt(inchTextField.getText()); 
                    
                    if (inch < 0 || inch > 11){

                        invalidInch.setText("Please enter inches between 0 and 11");
                        whitePanel.removeAll();
                        whitePanel.add(invalidInch);

                        topPanel.validate();
                        topPanel.repaint();

                    }

                    else{
                    Float bmi = BMICalculator.ibFtBMICal(weight, feet, inch);

                    if (bmi <= 18.5){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the Underweight range.");
                        
                    }
            
                    if (bmi > 18.5 && bmi < 24.9){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the normal range.");
                    }
            
                    if (bmi > 24.9 && bmi < 29.9){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the overweight range.");
                    }
            
                    if (bmi >= 30){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the Obese range.");
                    }



                    whitePanel.removeAll();
                    whitePanel.add(bmiLabel1);
                    whitePanel.add(bmiLabel2);

                    topPanel.validate();
                    topPanel.repaint();
                    }
                    
                }

                if(heightToggle.getSelectedItem() == "cm" && weightToggle.getSelectedItem() == "ib"){

        
                    weight = Float.parseFloat(weightTextField.getText()); 
                    cmHeight = Float.parseFloat(heightTextField.getText()); 
                    
                    Float bmi = BMICalculator.ibCmBMICal(weight, cmHeight);

                    if (bmi <= 18.5){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the Underweight range.");
                        
                    }
            
                    if (bmi > 18.5 && bmi < 24.9){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the normal range.");
                    }
            
                    if (bmi > 24.9 && bmi < 29.9){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the overweight range.");
                    }
            
                    if (bmi >= 30){
                        bmiLabel1.setText("Your BMI is: " + String.format("%.2f",bmi));
                        bmiLabel2.setText("You are in the Obese range.");
                    }

                    whitePanel.removeAll();
                    whitePanel.add(bmiLabel1);
                    whitePanel.add(bmiLabel2);

                    topPanel.validate();
                    topPanel.repaint();

                    
                }
            }catch(Exception error){
                    invalidInch.setText("Something went Wrong: Try adding valid measurements");
                    whitePanel.removeAll();
                    whitePanel.add(invalidInch);

                    topPanel.validate();
                    topPanel.repaint();

            }
          
        }

    }

    public JPanel topPanel(){
        topPanel = new JPanel(new GridLayout());
        bluePanel = new JPanel(new GridLayout());
        whitePanel = new JPanel();
        blackline = BorderFactory.createLineBorder(Color.black);
    
        bluePanel.setBackground(new Color(173,216,230));
        whitePanel.setBackground(Color.white);
        
        bluePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
       // whitePanel.setBorder(blackline);

        bluePanel.add(whitePanel);

        topPanel.add(bluePanel);
        

        return topPanel;
    }

    public JPanel midPanel() {
        midPanel = new JPanel(new GridLayout());
        midPanel.add(inputPanel());
       
        return midPanel;
    }



    public JPanel inputPanel() {
        inputPanel = new JPanel(new GridLayout(2,1));        
              
        weightPanel = new JPanel();
        weightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        weightLabel = new JLabel("Weight");
        weightTextField = new JTextField(5);
        weightToggle = new JComboBox<String>(weightMetrics);

        heightPanel = new JPanel();
        heightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        heightLabel = new JLabel("Height");
        heightTextField = new JTextField(5);
        heightToggle = new JComboBox<String>(heightMetrics);
       
        weightPanel.add(weightLabel);
        weightPanel.add(weightTextField);
        weightPanel.add(weightToggle);

        heightPanel.add(heightLabel);
        heightPanel.add(heightTextField);
        heightPanel.add(heightToggle);
        
        weightPanel.setBackground(new Color(173,216,230));
        heightPanel.setBackground(new Color(173,216,230));

        inputPanel.add(weightPanel);
        inputPanel.add((heightPanel));
        
        return inputPanel;
    }

    public JPanel cmPanel() {
        
        heightPanel = new JPanel();
        heightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        cmTextField = new JTextField(5);

        heightPanel.add(heightLabel);
        heightPanel.add(heightTextField);
        heightPanel.add(heightToggle);
        
        heightPanel.setBackground(new Color(173,216,230));
    
        return heightPanel;
}

    public JPanel ftPanel() {
        
        heightPanel = new JPanel();
        heightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        feetTextField = new JTextField(2);
        inchTextField = new JTextField(2);

        feetLabel = new JLabel("ft");
        inchLabel = new JLabel("inches");

       

        heightPanel.add(heightLabel);
        heightPanel.add(feetTextField);
        heightPanel.add(feetLabel);
        heightPanel.add(inchTextField);
        heightPanel.add(inchLabel);
        heightPanel.add(heightToggle);
        
        heightPanel.setBackground(new Color(173,216,230));
    
        return heightPanel;
}


    public JPanel buttonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
      

        calButton = new JButton("Calculate");
        clearButton = new JButton("clear");
        
        flowPanel = new JPanel(new GridLayout(2, 1));
        flowPanel.setBackground(new Color(173,216,230));
     
        
        flowPanel.add(calButton);
        flowPanel.add(clearButton);
        
        buttonPanel.add(flowPanel);
        buttonPanel.setBackground(new Color(173,216,230));
        
        return buttonPanel;
    }

    
    
}
