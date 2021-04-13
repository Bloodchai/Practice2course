package com.company;

//import com.comsol.model.util.ModelUtil;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class SimpleGUI extends JPanel {
    private String comsol_jars;
    private String java_jars;
    String[] coil1_param = {
            "Radius",
            "Height",
            "Number of turns",
            "Ox position",
            "Oy position",
            "Oz position"
    };
    String[] coil1_inputParam = {
            "0.25",
            "0.025",
            "11",
            "0",
            "0",
            "-0.0125"
    };
    String[] coil2_param = {
            "Radius",
            "Height",
            "Number of turns",
            "Ox position",
            "Oy position",
            "Oz position"
    };
    String[] coil2_inputParam = {
            "0.25",
            "0.025",
            "11",
            "0",
            "0",
            "-0.0125 + 0.04"
    };
    String ComsolPath = "/home/knifer/comsol52a/multiphysics";
    String currentPattern;
    int iter1;
    int iter2;
    private int port = 2037;
    private JButton RunButton = new JButton("                   Solve                 ");
    private JButton choosePathToCOMSOLButton = new JButton("Choose path to COMSOL");
    private JButton runCOMSOLButton = new JButton("Run COMSOL Server");
    private JTextField input1 = new JTextField("", 5);
    private JTextField input2 = new JTextField("", 5);
    private JTextField comsolPathTextField = new JTextField("", 30);
    private JTextArea textAreaRunWork = new JTextArea(10, 30);
    private JComboBox Combobox1 = new JComboBox(coil1_param);
    private JComboBox Combobox2 = new JComboBox(coil2_param);
    private JLabel label1 = new JLabel("Coil 1 Params:");
    private JLabel label2 = new JLabel("Coil 2 Params:");
    private JLabel comsolPathLabel = new JLabel("Put you Comsol directory here");
    private final JFileChooser fc = new JFileChooser();

    ActionListener actionListener1 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox)e.getSource();
            String newSelection = (String)box.getSelectedItem();
            for (int i = 0; i < coil1_param.length; i ++){
                if(coil1_param[i] == newSelection){
                    iter1 = i;
                    break;
                }
            }
            input1.setText(coil1_inputParam[iter1]);
            currentPattern = newSelection;
        }
    };

    ActionListener actionListener2 = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JComboBox box = (JComboBox)e.getSource();
            String newSelection = (String)box.getSelectedItem();
            for (int i = 0; i < coil2_param.length; i ++){
                if(coil2_param[i] == newSelection){
                    iter2 = i;
                    break;
                }
            }
            input2.setText(coil2_inputParam[iter2]);
            currentPattern = newSelection;
        }
    };

    DocumentListener dl1 = new DocumentListener() {
        public void insertUpdate(DocumentEvent e) {
            coil1_inputParam[iter1] = input1.getText();
        }

        public void removeUpdate(DocumentEvent e) {
        }

        public void changedUpdate(DocumentEvent e) {
        }
    };

    DocumentListener dl2 = new DocumentListener() {
        public void insertUpdate(DocumentEvent e) {
            coil2_inputParam[iter2] = input2.getText();
        }

        public void removeUpdate(DocumentEvent e) {
        }

        public void changedUpdate(DocumentEvent e) {
        }
    };

    public static GridBagConstraints position(int x, int y){
        GridBagConstraints layConstraints = new GridBagConstraints();
        layConstraints.fill = GridBagConstraints.BOTH; // заполняет ячейку целиком
        layConstraints.gridx = x; // координаты ячейки, в которую помещается кнопка
        layConstraints.gridy = y;
        return layConstraints;
    }

    public SimpleGUI() {
        choosePathToCOMSOLButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fc.showOpenDialog(choosePathToCOMSOLButton.getParent());
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    comsolPathTextField.setText(file.getAbsolutePath());
                }
            }
        });
        runCOMSOLButton.addMouseListener (new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                try {
                    String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
                    if (OS.indexOf("nux") >= 0) {
                        String cmd = "/usr/bin/x-terminal-emulator -e " + comsolPathTextField.getText() + "/bin/comsol mphserver -port " + String.valueOf(port);
                        Runtime.getRuntime().exec(cmd);
                        port += 1;
                    }
                    RunButton.setEnabled(true);
                    runCOMSOLButton.setEnabled(false);
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
        RunButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try{
                    RunWork();
                    RunButton.setEnabled(false);
                    runCOMSOLButton.setEnabled(true);
                }
                catch (InterruptedException e1){
                    e1.printStackTrace();
                }
            }
        });

        try {
            comsol_jars = new String(Files.readAllBytes(Paths.get("comsol_jars.txt")));
            java_jars = new String(Files.readAllBytes(Paths.get("java_jars.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(label1, position(0, 0));
        Combobox1.setEditable(true);
        Combobox1.addActionListener(actionListener1);
        add(Combobox1, position(1, 0));
        input1.getDocument().addDocumentListener(dl1);
        add(input1, position(2, 0));
        add(label2, position(0, 1));
        Combobox2.setEditable(true);
        Combobox2.addActionListener(actionListener2);
        add(Combobox2, position(1, 1));
        input2.getDocument().addDocumentListener(dl2);
        add(input2, position(2, 1));
        add(comsolPathLabel, position(0, 2));
        add(comsolPathTextField, position(0, 3));
        comsolPathTextField.setText(ComsolPath);
        add(choosePathToCOMSOLButton, position(0, 4));
        add(runCOMSOLButton, position(0, 5));
        add(textAreaRunWork, position(0, 6));
        add(RunButton, position(0, 7));
    }

    private void RunWork() throws InterruptedException{
        String[] mas = {"/com", "/company"};
        LocalDateTime dt = LocalDateTime.now();
        String dirname = Integer.toString(Math.abs(dt.hashCode()));
        File dir = new File(dirname);
        dir.mkdir();
        for (String p:mas){
            dirname += p;
            dir = new File(dirname);
            dir.mkdir();
        }
        String path = dir.getAbsolutePath();
        String java_path = System.getProperty("java.home");
        String comsol_path = comsolPathTextField.getText() + "/plugins";
        String[] c_jars = comsol_jars.split("\n");
        String[] j_jars = java_jars.split("\n");

        String classpath = "";
        for (String s:j_jars
                ) {
            classpath += java_path + "/" + s + ":";
        }

        for (String s:c_jars
                ) {
            classpath += comsol_path + "/" + s + ":";
        }
        classpath += path.replace("/com/company", "");

        String run_str = java_path + "/bin/java -Dfile.encoding=UTF-8 -Duser.dir=" + path + " -classpath " + "\"" + classpath + "\"" + " com.company.Main " + String.valueOf(port - 1);

        String coil1_Text = "";
        for(String s:coil1_inputParam){
            coil1_Text += s + "\n";
        }

        String coil2_Text = "";
        for(String s:coil2_inputParam){
            coil2_Text += s + "\n";
        }

        try {
            Files.write(Paths.get(path + "/coil1_param.txt"), coil1_Text.getBytes());
            Files.write(Paths.get(path + "/coil2_param.txt"), coil2_Text.getBytes());
            Files.write(Paths.get(path + "/run.sh"), run_str.getBytes());
            Files.copy(Paths.get("comsol_jars.txt"), Paths.get(path + "/comsol_jars.txt"), REPLACE_EXISTING);
            Files.copy(Paths.get("java_jars.txt"), Paths.get(path + "/java_jars.txt"), REPLACE_EXISTING);
            Files.copy(Paths.get("SimpleGUI.class"), Paths.get(path + "/SimpleGUI.class"), REPLACE_EXISTING);
            Files.copy(Paths.get("SimpleGUI$1.class"), Paths.get(path + "/SimpleGUI$1.class"), REPLACE_EXISTING);
            Files.copy(Paths.get("SimpleGUI$2.class"), Paths.get(path + "/SimpleGUI$2.class"), REPLACE_EXISTING);
            Files.copy(Paths.get("SimpleGUI$3.class"), Paths.get(path + "/SimpleGUI$3.class"), REPLACE_EXISTING);
            Files.copy(Paths.get("SimpleGUI$4.class"), Paths.get(path + "/SimpleGUI$4.class"), REPLACE_EXISTING);
            Files.copy(Paths.get("SimpleGUI$5.class"), Paths.get(path + "/SimpleGUI$5.class"), REPLACE_EXISTING);
            Files.copy(Paths.get("SimpleGUI$6.class"), Paths.get(path + "/SimpleGUI$6.class"), REPLACE_EXISTING);
            Files.copy(Paths.get("SimpleGUI$7.class"), Paths.get(path + "/SimpleGUI$7.class"), REPLACE_EXISTING);
            Files.copy(Paths.get("Main.class"), Paths.get(path + "/Main.class"), REPLACE_EXISTING);
            Files.copy(Paths.get("Induction.class"),
                    Paths.get(path + "/Induction.class"), REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            String line;
            OutputStream stdin = null;
            InputStream stderr = null;
            InputStream stdout = null;

            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if (OS.indexOf("nux") >= 0) {
                textAreaRunWork.append("Run at " + dt.toString() + "\nWorking directory: " + path + "\n");
                Runtime.getRuntime().exec("/bin/chmod 777 " + path + "/run.sh");
                String cmd = "/usr/bin/x-terminal-emulator -e ";// + path + "/run.sh";
                Process process = Runtime.getRuntime().exec(cmd);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SimpleGUI app = new SimpleGUI();
        JFrame jf = new JFrame();
        jf.setTitle("Mutual inductance");
        jf.setSize(350, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.add(app);
    }
}
