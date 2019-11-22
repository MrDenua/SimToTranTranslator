package com.dengzii.plugin.converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class ConverterDialog extends JDialog {
    private JPanel contentPane;
    private JTextArea sim;
    private JTextArea tra;
    private JButton toTraditionalButton;
    private JButton toSimplifyButton;


    public ConverterDialog() {
        setContentPane(contentPane);

        setModal(false);
        setModalityType(ModalityType.APPLICATION_MODAL);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        toSimplifyButton.addActionListener(e -> toSim());
        toTraditionalButton.addActionListener(e -> toTra());
    }

    public void create(){
        create("");
    }

    public void create(String src){
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int w = screen.width / 3;
        int h = 300;
        int x = screen.width / 2 - w / 2;
        int y = screen.height / 2 - h;
        setLocation(x, y);
        setPreferredSize(new Dimension(w, h));

        setTitle("Simplify&Traditional Converter");
        if (!src.trim().isEmpty()){
            sim.setText(src);
            toTra();
        }
        pack();
        setAlwaysOnTop(true);
        setVisible(true);
    }

    private void toTra(){
        tra.setText(ConvertUtils.INSTANCE.simToTra(sim.getText()));
        tra.requestFocus();
        tra.selectAll();
    }

    private void toSim(){
        sim.setText(ConvertUtils.INSTANCE.traToSim(tra.getText()));
        sim.requestFocus();
        sim.selectAll();
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        new ConverterDialog().create();
    }
}
