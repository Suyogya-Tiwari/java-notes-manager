package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import controller.NotesController;
import model.Note;
import model.User;

public class Dashboard extends JFrame {

    private User user;
    private DefaultListModel<Note> listModel;
    private JList<Note> noteList;
    private JTextField searchField;

    private ArrayList<Note> allNotes = new ArrayList<>();

    public Dashboard(User user) {
        this.user = user;

        setTitle("My Notes");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));

        // 🔝 TOP PANEL
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel(" My Notes");
        title.setFont(new Font("Arial", Font.BOLD, 22));

        searchField = new JTextField();
        searchField.setToolTipText("Search notes...");

        topPanel.add(title, BorderLayout.NORTH);
        topPanel.add(searchField, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // 📄 NOTE LIST
        listModel = new DefaultListModel<>();
        noteList = new JList<>(listModel);

        // 🎨 CUSTOM CARD UI
        noteList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {

                Note note = (Note) value;

                JPanel panel = new JPanel(new BorderLayout(5, 5));
                panel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));

                JLabel title = new JLabel(note.getTitle());
                title.setFont(new Font("Arial", Font.BOLD, 14));

                String preview = note.getContent().length() > 50
                        ? note.getContent().substring(0, 50) + "..."
                        : note.getContent();

                JLabel content = new JLabel("<html><body style='width:200px'>" + preview + "</body></html>");
                content.setFont(new Font("Arial", Font.PLAIN, 12));

                panel.add(title, BorderLayout.NORTH);
                panel.add(content, BorderLayout.CENTER);

                if (isSelected) {
                    panel.setBackground(new Color(180, 205, 255));
                } else {
                    panel.setBackground(Color.WHITE);
                }

                panel.setOpaque(true);
                return panel;
            }
        });

        noteList.setFixedCellHeight(80);

        add(new JScrollPane(noteList), BorderLayout.CENTER);

        // 🔘 BUTTON PANEL
        JPanel bottomPanel = new JPanel();

        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View");
        JButton deleteBtn = new JButton("Delete");

        bottomPanel.add(addBtn);
        bottomPanel.add(viewBtn);
        bottomPanel.add(deleteBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        loadNotes();

        // 🔍 SEARCH
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filter(); }
            public void removeUpdate(DocumentEvent e) { filter(); }
            public void changedUpdate(DocumentEvent e) { filter(); }
        });

        // 👆 DOUBLE CLICK TO EDIT
        noteList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Note selected = noteList.getSelectedValue();
                    if (selected != null) {
                        openNote(selected);
                    }
                }
            }
        });

        // ➕ ADD
        addBtn.addActionListener(e -> addNote());

        // 👁 VIEW
        viewBtn.addActionListener(e -> viewNote());

        // 🗑 DELETE
        deleteBtn.addActionListener(e -> deleteNote());

        setVisible(true);
    }

    // LOAD NOTES
    private void loadNotes() {
        try {
            listModel.clear();
            allNotes = NotesController.getNotes(user.getId());

            for (Note n : allNotes) {
                listModel.addElement(n);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SEARCH FILTER
    private void filter() {
        String text = searchField.getText().toLowerCase();

        listModel.clear();
        for (Note n : allNotes) {
            if (n.getTitle().toLowerCase().contains(text) ||
                    n.getContent().toLowerCase().contains(text)) {
                listModel.addElement(n);
            }
        }
    }

    // ADD NOTE
    private void addNote() {
        JTextField titleField = new JTextField();
        JTextArea contentArea = new JTextArea(5, 20);

        int result = JOptionPane.showConfirmDialog(
                this,
                new Object[]{"Title:", titleField, "Content:", new JScrollPane(contentArea)},
                "New Note",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                NotesController.addNote(user.getId(),
                        titleField.getText(),
                        contentArea.getText());
                loadNotes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // VIEW NOTE
    private void viewNote() {
        Note selected = noteList.getSelectedValue();

        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Select a note first!");
            return;
        }

        JTextArea area = new JTextArea(selected.getContent());
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400, 250));

        JOptionPane.showMessageDialog(
                this,
                scroll,
                selected.getTitle(),
                JOptionPane.PLAIN_MESSAGE
        );
    }

    // EDIT NOTE
    private void openNote(Note note) {
        JTextField titleField = new JTextField(note.getTitle());
        JTextArea contentArea = new JTextArea(note.getContent());

        int result = JOptionPane.showConfirmDialog(
                this,
                new Object[]{"Edit Title:", titleField, "Edit Content:", new JScrollPane(contentArea)},
                "Edit Note",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                NotesController.updateNote(note.getId(),
                        titleField.getText(),
                        contentArea.getText());
                loadNotes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // DELETE NOTE
    private void deleteNote() {
        Note selected = noteList.getSelectedValue();

        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Select a note first!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete this note?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                NotesController.deleteNote(selected.getId());
                loadNotes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}