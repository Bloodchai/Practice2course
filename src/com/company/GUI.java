package com.company;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class GUI {
    String[] coil_param = {
            "Radius",
            "Height",
            "Number of turns",
            "Ox position",
            "Oy position",
            "Oz position"
    };
    private JPanel Main;
    private JButton runButton;
    private JTextField COMSOLPathTextField;
    private JButton choosePathToCOMSOLButton;
    private JTextArea textAreaRunWork;
    private JButton runCOMSOLButton;
    private JLabel Label1;
    private JComboBox coil1ParamsComboBox;
    private JTextField coil1ParamsTextField;
    private JComboBox coil2ParamsComboBox;
    private JTextField coil2ParamsTextField;
    private String comsol_jars;
    private String java_jars;
    private final JFileChooser fc = new JFileChooser();
    private int port = 2037;
    String currentPattern;
    int iter1;
    int iter2;
    String[] coil1_inputParam = {
            "0.25",
            "0.025",
            "11",
            "0",
            "0",
            "-0.0125"
    };
    String[] coil2_inputParam = {
            "0.25",
            "0.025",
            "11",
            "0",
            "0",
            "-0.0125 + 0.04"
    };

    public GUI(){
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
            COMSOLPathTextField.setText("/Applications/COMSOL52/Multiphysics");
        } else if (OS.indexOf("nux") >= 0) {
            COMSOLPathTextField.setText("/usr/local/comsol52/multiphysics");
        } else if (OS.indexOf("win") >= 0) {
            COMSOLPathTextField.setText("C:\\Program files\\COMSOL52\\Multiphysics");
        }

        try {
            comsol_jars = new String(Files.readAllBytes(Paths.get("comsol_jars.txt")));
            java_jars = new String(Files.readAllBytes(Paths.get("java_jars.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ActionListener actionListener1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                String newSelection = (String)box.getSelectedItem();
                for (int i = 0; i < coil_param.length; i ++){
                    if(coil_param[i] == newSelection){
                        iter1 = i;
                        break;
                    }
                }
                coil1ParamsTextField.setText(coil1_inputParam[iter1]);
                currentPattern = newSelection;
            }
        };

        ActionListener actionListener2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                String newSelection = (String)box.getSelectedItem();
                for (int i = 0; i < coil_param.length; i ++){
                    if(coil_param[i] == newSelection){
                        iter2 = i;
                        break;
                    }
                }
                coil2ParamsTextField.setText(coil2_inputParam[iter2]);
                currentPattern = newSelection;
            }
        };

        DocumentListener dl1 = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                coil1_inputParam[iter1] = coil1ParamsTextField.getText();
            }

            public void removeUpdate(DocumentEvent e) {
            }

            public void changedUpdate(DocumentEvent e) {
            }
        };

        DocumentListener dl2 = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                coil2_inputParam[iter2] = coil2ParamsTextField.getText();
            }

            public void removeUpdate(DocumentEvent e) {
            }

            public void changedUpdate(DocumentEvent e) {
            }
        };

        coil1ParamsComboBox.addActionListener(actionListener1);
        coil2ParamsComboBox.addActionListener(actionListener2);
        coil1ParamsTextField.getDocument().addDocumentListener(dl1);
        coil2ParamsTextField.getDocument().addDocumentListener(dl2);

        choosePathToCOMSOLButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fc.showOpenDialog(choosePathToCOMSOLButton.getParent());
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    COMSOLPathTextField.setText(file.getAbsolutePath());
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
                        String cmd = "/usr/bin/x-terminal-emulator -e " + COMSOLPathTextField.getText() + "/bin/comsol mphserver -port " + String.valueOf(port);
                        Runtime.getRuntime().exec(cmd);
                        port += 1;
                    }
                    runButton.setEnabled(true);
                    runCOMSOLButton.setEnabled(false);
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
        runButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try{
                    RunWork();
                    runButton.setEnabled(false);
                    runCOMSOLButton.setEnabled(true);
                }
                catch (InterruptedException e1){
                    e1.printStackTrace();
                }
            }
        });
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
        String comsol_path = COMSOLPathTextField.getText() + "/plugins";
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
            Files.copy(Paths.get("Main.class"), Paths.get(path + "/Main.class"), REPLACE_EXISTING);
            Files.copy(Paths.get("Induction.class"), Paths.get(path + "/Induction.class"), REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if (OS.indexOf("nux") >= 0) {
                textAreaRunWork.append("Run at " + dt.toString() + "\nWorking directory: " + path + "\n");
                Runtime.getRuntime().exec("/bin/chmod 777 " + path + "/run.sh");
                String cmd = "/usr/bin/x-terminal-emulator -e " + path + "/run.sh";
                Process process = Runtime.getRuntime().exec(cmd);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static  void main(String[] args){
        JFrame form = new JFrame();
        form.setContentPane(new GUI().Main);
        form.setBounds(100,100, 500, 500);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        form.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
