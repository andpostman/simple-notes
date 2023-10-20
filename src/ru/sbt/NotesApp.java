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
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

// Create a top panel for input
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleField = new JTextField();
        titleField.setBorder(BorderFactory.createTitledBorder("Title"));
        contentArea = new JTextArea(10, 40);
        contentArea.setBorder(BorderFactory.createTitledBorder("Content"));

        JButton addButton = new JButton("Add Note");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNote();
            }
        });

        inputPanel.add(titleField, BorderLayout.NORTH);
        inputPanel.add(new JScrollPane(contentArea), BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.SOUTH);

// Create a list panel
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        listModel = new DefaultListModel<>();
        noteList = new JList<>(listModel);
        noteList.setBorder(BorderFactory.createTitledBorder("Notes List"));

        listPanel.add(new JScrollPane(noteList), BorderLayout.CENTER);

// Add panels to the frame
        frame.add(inputPanel, BorderLayout.WEST);
        frame.add(listPanel, BorderLayout.CENTER);

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