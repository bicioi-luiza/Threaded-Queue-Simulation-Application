package GUI;

import BusinessLogic.SelectionPolicy;
import BusinessLogic.SimulationManager;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InterfataCozi {

    public JFrame frame;
    private JTextField txtClienti;
    private JTextField txtMinST;
    private JTextField txtMaxST;
    private JTextField txtMinAT;
    private JTextField txtMaxAT;
    private JTextField txtCozi;
    private JTextField textField;

    private JTextArea textArea;
    private JButton btnNewButton;

    /**
     * Launch the application.
     */
    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfataCozi window = new InterfataCozi();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    /**
     * Create the application.
     */
    public InterfataCozi() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(241, 236, 199));
        frame.setBounds(100, 100, 1400, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lbTitlu = new JLabel("QUEUES MANAGEMENT APPLICATION \r\n");
        lbTitlu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        lbTitlu.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitlu.setBounds(265, 24, 800, 94);
        frame.getContentPane().add(lbTitlu);

        JLabel lblNewLabel = new JLabel("Despre clienti");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(105, 190, 101, 18);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Introduceti numarul de clienti");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1.setBounds(65, 237, 195, 13);
        frame.getContentPane().add(lblNewLabel_1);

        txtClienti = new JTextField();
        txtClienti.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setLabelFor(txtClienti);
        txtClienti.setBounds(61, 249, 199, 19);
        frame.getContentPane().add(txtClienti);
        txtClienti.setColumns(10);

        JLabel lblNewLabel_1_1 = new JLabel("Introduceti min service time");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_1.setBounds(65, 293, 195, 13);
        frame.getContentPane().add(lblNewLabel_1_1);

        txtMinST = new JTextField();
        txtMinST.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setLabelFor(txtMinST);
        txtMinST.setColumns(10);
        txtMinST.setBounds(61, 305, 199, 19);
        frame.getContentPane().add(txtMinST);

        JLabel lblNewLabel_1_2 = new JLabel("Introduceti max service time");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_2.setBounds(65, 344, 195, 13);
        frame.getContentPane().add(lblNewLabel_1_2);

        txtMaxST = new JTextField();
        txtMaxST.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_2.setLabelFor(txtMaxST);
        txtMaxST.setColumns(10);
        txtMaxST.setBounds(61, 356, 199, 19);
        frame.getContentPane().add(txtMaxST);

        JLabel lblNewLabel_1_3 = new JLabel("Introduceti min arrival time");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_3.setBounds(65, 398, 195, 13);
        frame.getContentPane().add(lblNewLabel_1_3);

        txtMinAT = new JTextField();
        txtMinAT.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3.setLabelFor(txtMinAT);
        txtMinAT.setColumns(10);
        txtMinAT.setBounds(61, 410, 199, 19);
        frame.getContentPane().add(txtMinAT);

        JLabel lblNewLabel_1_4 = new JLabel("Introduceti max arrival time");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_4.setBounds(65, 443, 195, 13);
        frame.getContentPane().add(lblNewLabel_1_4);

        txtMaxAT = new JTextField();
        txtMaxAT.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_4.setLabelFor(txtMaxAT);
        txtMaxAT.setColumns(10);
        txtMaxAT.setBounds(61, 455, 199, 19);
        frame.getContentPane().add(txtMaxAT);

        JLabel lblInformatiiSuplimentare = new JLabel("Informatii suplimentare");
        lblInformatiiSuplimentare.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblInformatiiSuplimentare.setBounds(77, 553, 173, 18);
        frame.getContentPane().add(lblInformatiiSuplimentare);

        JLabel lblNewLabel_1_5 = new JLabel("Introduceti numarul de cozi");
        lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_5.setBounds(65, 602, 195, 13);
        frame.getContentPane().add(lblNewLabel_1_5);

        txtCozi = new JTextField();
        txtCozi.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_5.setLabelFor(txtCozi);
        txtCozi.setColumns(10);
        txtCozi.setBounds(61, 614, 199, 19);
        frame.getContentPane().add(txtCozi);

        JLabel lblNewLabel_1_6 = new JLabel("Introduceti o limita de timp");
        lblNewLabel_1_6.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1_6.setBounds(65, 656, 195, 13);
        frame.getContentPane().add(lblNewLabel_1_6);

        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setColumns(10);
        textField.setBounds(61, 668, 199, 19);
        frame.getContentPane().add(textField);

        JLabel lblLogOfEvents = new JLabel("Log of events");
        lblLogOfEvents.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblLogOfEvents.setBounds(821, 190, 101, 18);
        frame.getContentPane().add(lblLogOfEvents);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(450, 237, 838, 450);
        frame.getContentPane().add(scrollPane);

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);

        btnNewButton = new JButton("START");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton.setBounds(293, 488, 108, 43);
        frame.getContentPane().add(btnNewButton);
        InterfataCozi noua =this;
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(txtClienti.getText().isBlank()||txtCozi.getText().isBlank()||txtMinST.getText().isBlank()||txtMaxST.getText().isBlank()||txtMinAT.getText().isBlank()||txtMaxAT.getText().isBlank()||textField.getText().isBlank()) JOptionPane.showMessageDialog(null, "Trebuie sa completati toate spatiile");
                else {

                    int noClients = checkIfNotInt(txtClienti.getText());
                    if (noClients == 2147483647)
                        JOptionPane.showMessageDialog(null, "Ati introdus gresit numarul de clienti");

                    int noServers = checkIfNotInt(txtCozi.getText());
                    if (noServers == 2147483647)
                        JOptionPane.showMessageDialog(null, "Ati introdus gresit numarul de cozi");

                    int limitTime = checkIfNotInt(textField.getText());
                    if (limitTime == 2147483647)
                        JOptionPane.showMessageDialog(null, "Ati introdus gresit limita de timp");

                    int minSTime = checkIfNotInt(txtMinST.getText());
                    if (minSTime == 2147483647)
                        JOptionPane.showMessageDialog(null, "Ati introdus gresit min service time");

                    int maxSTime = checkIfNotInt(txtMaxST.getText());
                    if (maxSTime == 2147483647 || maxSTime >= limitTime || maxSTime <= minSTime)
                        JOptionPane.showMessageDialog(null, "Ati introdus gresit max service time");

                    int minATime = checkIfNotInt(txtMinAT.getText()); System.out.println(minATime);
                    if (minATime == 2147483647)
                        JOptionPane.showMessageDialog(null, "Ati introdus gresit min arrival time");

                    int maxATime = checkIfNotInt(txtMaxAT.getText());
                    if (maxATime == 2147483647 || maxATime >= limitTime || maxATime <= minATime)
                        JOptionPane.showMessageDialog(null, "Ati introdus gresit max arrival time");


                    if(maxATime!=2147483647&&minATime!=2147483647&&maxSTime!=2147483647&&minSTime!=2147483647&&limitTime!=2147483647&&noClients!=2147483647&&noServers!=2147483647&&maxSTime <= limitTime&&maxATime <= limitTime&&maxATime >= minATime&&maxSTime >= minSTime) {
                        SimulationManager gen = new SimulationManager(noua, limitTime, maxSTime, minSTime, noServers, noClients, minATime, maxATime, SelectionPolicy.SHORTEST_TIME);
                        Thread t = new Thread(gen);
                        t.start();
                    }
                }

            }
        });
    }

    public int checkIfNotInt(String x) {
        int rez;
        try {
            rez = Integer.parseInt(x);
        } catch (NumberFormatException e) {
            // Display an error message to the user
            return 2147483647;
        }
        return rez;
    }

    public void setTextArea(String text) {
        this.textArea.setText(text);
    }
    public void actionLst(){

    }
}

