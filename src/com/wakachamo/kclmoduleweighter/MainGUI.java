package com.wakachamo.kclmoduleweighter;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Joaquim
 */
public class MainGUI extends JFrame implements ActionListener {
    
    private HashMap<String, JTextField> textFields;
    
    private JLabel resultsLabel;
    private JButton startButton;
    private final boolean masters;
    
    public MainGUI(String[] modules, boolean masters) {
        super();
        this.masters = masters;
        this.setTitle("KCL Computer Science Weighted Grade Average");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.initialiseComponents(modules);
    }
    
    private void initialiseComponents(String[] modules) {
        this.textFields = new HashMap<>();
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(modules.length + 1, 2));
        
        for (String module : modules) {
            JLabel label = new JLabel(module + ":");
            label.setHorizontalAlignment(SwingConstants.RIGHT);
            
            JTextField textField = new JTextField();
            textField.setPreferredSize(new Dimension(100, 30));
            
            mainPanel.add(label);
            mainPanel.add(textField);
            
            this.textFields.put(module, textField);
        }
        
        this.resultsLabel = new JLabel("Click to calculate");
        mainPanel.add(this.resultsLabel);
        
        this.startButton = new JButton("Calculate");
        this.startButton.addActionListener(this);
        mainPanel.add(this.startButton);
        
        this.setContentPane(mainPanel);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.startButtonClicked(e);
    }

    private void startButtonClicked(ActionEvent e) {
        WeightedAverageCalculator calc = new WeightedAverageCalculator(this.masters);
        for (String module : this.textFields.keySet()) {
            String gradeString = this.textFields.get(module).getText();
            int credits = 15;
            if (module.equalsIgnoreCase("5CCS2SEG") || module.equalsIgnoreCase("6CCS3PRJ") || module.equalsIgnoreCase("7CCS3PRJ")) {
                credits = 30;
            }
            calc.setModule(module, credits, Integer.parseInt(gradeString));
        }
        this.resultsLabel.setText("" + calc.getWeightedAverage());
    }
    
}
