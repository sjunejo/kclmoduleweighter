package com.wakachamo.kclmoduleweighter;

/**
 *
 * @author Joaquim
 */
public class StartupGUI extends javax.swing.JFrame {

    /**
     * Creates new form StartupGUI
     */
    public StartupGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modulesInfoLabel = new javax.swing.JLabel();
        modulesScrollPane = new javax.swing.JScrollPane();
        modulesTextArea = new javax.swing.JTextArea();
        startButton = new javax.swing.JButton();
        mastersCheckbox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KCL Module Weighter");

        modulesInfoLabel.setText("Please type in your modules (line-separated):");

        modulesTextArea.setColumns(20);
        modulesTextArea.setLineWrap(true);
        modulesTextArea.setRows(5);
        modulesTextArea.setText("4CCS1CS1\n4CCS1DST\n4CCS1ELA\n4CCS1FC1\n4CCS1IAI\n4CCS1PEP\n4CCS1PRA\n4CCS1PRP\n5CCS02DB\n5CCS2CSL\n5CCS2FC2\n5CCS2OSC\n5CCS2OSD\n5CCS2PLD\n5CCS2SEG\n6CCS3AST\n6CCS3CIS\n6CCS3GRS\n6CCS3OME\n6CCS3PRJ\n6CCS3SAD\n6CCS3SMT");
        modulesTextArea.setWrapStyleWord(true);
        modulesScrollPane.setViewportView(modulesTextArea);

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        mastersCheckbox.setText("MSCi");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modulesScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modulesInfoLabel)
                        .addGap(0, 104, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(mastersCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(modulesInfoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modulesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(mastersCheckbox))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        MainGUI GUI = new MainGUI(this.modulesTextArea.getText().split("\n"), this.mastersCheckbox.isSelected());
        GUI.setVisible(true);
    }//GEN-LAST:event_startButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartupGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox mastersCheckbox;
    private javax.swing.JLabel modulesInfoLabel;
    private javax.swing.JScrollPane modulesScrollPane;
    private javax.swing.JTextArea modulesTextArea;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
