package ru.sbt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NotesApp {
    private List<Note> notes = new ArrayList<>();
    private JFrame frame;
    private JTextField titleField;
    private JTextArea contentArea;
    private DefaultListModel<Note> listModel;
    private JList<Note> noteList;

    public NotesApp() {
        frame = new JFrame("Notes Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        titleField = new JTextField(20);
        contentArea = new JTextArea(10, 40);

        listModel = new DefaultListModel<>();
        noteList = new JList<>(listModel);

        JButton addButton = new JButton("Add Note");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNote();
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Title: "));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Content: "));
        inputPanel.add(contentArea);
        inputPanel.add(addButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(noteList), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void addNote() {
        String title = titleField.getText();
        String content = contentArea.getText();

        if (!title.isEmpty() && !content.isEmpty()) {
            Note note = new Note(title, content);
            notes.add(note);
            listModel.addElement(note);
            titleField.setText("");
            contentArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NotesApp();
            }
        });
    }
}