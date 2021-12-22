package ArtIdeaGenerator;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

import static ArtIdeaGenerator.Main.generateCharacter;
import static ArtIdeaGenerator.Main.generatePortrait;

public class GUI {

    public GUI() {
    }

    private final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 22);
    private final Font font2 = new Font(Font.SANS_SERIF, Font.BOLD, 20);
    private String generatedCharacterString;

    public void createGUI(Connection connection) {
        JFrame frame = createFrame();
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setTitle("Idea Generator");
        panel.setLayout(null);

        addCharacterTitleText(panel);

        setBackgroundColor(panel);

        JLabel generatedCharacter = createResultCharacterLabel(panel);

        createGenerateCharacterButton(connection, panel, generatedCharacter);

        addPortraitTitleText(panel, "Generate Portrait!", 250);

        JLabel generatedPortrait = generatedPortrait(panel);

        createGeneratePortraitButton(connection, panel, generatedPortrait);


        frame.setVisible(true);
    }

    private void createGeneratePortraitButton(Connection connection, JPanel panel, JLabel generatedPortrait) {
        JButton generateButton = new JButton("Generate!");
        generateButton.setFont(font);
        generateButton.setBounds(290, 300, 220, 50);
        generateButton.addActionListener(e -> {
            try {
                generatedPortrait.setText(generatePortrait(connection));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        panel.add(generateButton);
    }

    private JLabel generatedPortrait(JPanel panel) {
        JLabel generatedPortrait = new JLabel();
        generatedPortrait.setFont(font2);
        generatedPortrait.setBounds(200, 350, 700, 50);
        panel.add(generatedPortrait);
        return generatedPortrait;
    }

    private void addPortraitTitleText(JPanel panel, String s, int i) {
        JLabel title = new JLabel(s);
        title.setFont(font);
        title.setBounds(290, i, 500, 25);
        panel.add(title);
    }

    private void createGenerateCharacterButton(Connection connection, JPanel panel, JLabel generatedCharacter) {
        JButton generateButton = new JButton("Generate!");
        generateButton.setFont(font);
        generateButton.setBounds(290, 80, 220, 50);
        generateButton.addActionListener(e -> {
            try {
                setGeneratedCharacterString(generateCharacter(connection));
                generatedCharacter.setText(generatedCharacterString);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        panel.add(generateButton);
    }

    private JLabel createResultCharacterLabel(JPanel panel) {
        JLabel generatedCharacter = new JLabel();
        generatedCharacter.setFont(font2);
        generatedCharacter.setBounds(255, 120, 700, 50);
        panel.add(generatedCharacter);
        return generatedCharacter;
    }

    public void setGeneratedCharacterString(String generatedCharacterString) {
        this.generatedCharacterString = generatedCharacterString;
    }

    private void setBackgroundColor(JPanel panel) {
        Color backgroundColor = new Color(244, 213, 184);
        panel.setBackground(backgroundColor);
    }

    private void addCharacterTitleText(JPanel panel) {
        addPortraitTitleText(panel, "Generate Character!", 50);
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame();
        frame.setSize(800, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }
}
