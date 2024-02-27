import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.LinkedList;

public class SkillProjection {
    public final int SKILL_WIDTH = 48;
    public final int SKILL_HEIGHT = 61;

    public final int SKILL_1_CAPTURE_X = 764;
    public final int SKILL_2_CAPTURE_X = 827;
    public final int SKILL_3_CAPTURE_X = 889;
    public final int SKILL_4_CAPTURE_X = 954;
    public final int SKILL_5_CAPTURE_X = 1013;
    public final int SKILL_6_CAPTURE_X = 1075;
    public final int SKILL_CAPTURE_Y = 972;
    private final int CAPTURE_X = 100; 
    private final int CAPTURE_Y = 100; 
    private final int CAPTURE_WIDTH = 48; 
    private final int CAPTURE_HEIGHT = 61; 

    private final int FRAME_X = 650; 
    private final int FRAME_Y = 500; 

    private final int CONTROL_FRAME_X = 600;
    private final int CONTROL_FRAME_Y = 500;

    private int[] skillXCoords;

    private double center;

    private double skillLocationParam;

    private double radius;

    private int FPS;
    private JLabel FPS_Label;
    private JSlider FPS_Slider;
    private JTextField FPS_Field;
    private JLabel FPS_GuideLabel;
    private JSlider skillLocationParamSilder;

    private JLabel skillLocationParamLabel;

    private JTextField skillLocationParamField;

    private JLabel skillLocationParamGuideLabel;

    private JFrame[] skillFrames;

    private JPanel[] skillPanels;
    private JButton startButton;
    private BufferedImage skillCaptures[];
    private JLabel toggleKeyShortcutLabel;
    private JComboBox<KeyItem> keyComboBox;
    private KeyItem toggleKeyShortcut;
    private JComboBox<KeyItem> toggleKeyComboBox;

    private float alpha;
    private JLabel keyLabel;

    private JLabel alphaLabel;
    private JSlider alphaSlider;
    private JTextField alphaField;
    private JLabel alphaGuideLabel;


    boolean wasVisible;

    public SkillProjection() {

        skillXCoords = new int[]{SKILL_1_CAPTURE_X, SKILL_2_CAPTURE_X, SKILL_3_CAPTURE_X, SKILL_4_CAPTURE_X, SKILL_5_CAPTURE_X, SKILL_6_CAPTURE_X};
        skillFrames = new JFrame[skillXCoords.length];
        skillPanels = new JPanel[skillXCoords.length];
        skillCaptures = new BufferedImage[skillXCoords.length];
        skillLocationParam = 0.2;
        skillLocationParamSilder = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (skillLocationParam * 100));
        skillLocationParamField = new JTextField(5);
        skillLocationParamField.setText(String.valueOf(skillLocationParam));
        skillLocationParamGuideLabel = new JLabel("(0 ~ 1)");
        FPS = 30;
        FPS_Slider = new JSlider(JSlider.HORIZONTAL, 1, 100, (int) FPS);
        FPS_Field = new JTextField(5);
        FPS_Field.setText(String.valueOf(FPS));
        FPS_Label = new JLabel("FPS: ");
        FPS_GuideLabel = new JLabel("(1 ~ 100)");
        center = 1920 / 2;
        radius = center - FRAME_X;


        keyComboBox = new JComboBox<>(KeyItem.KeyItems.KEYS);
        keyComboBox.setPreferredSize(new Dimension(100, 25));
        keyLabel = new JLabel("Key to Press:");

        toggleKeyComboBox = new JComboBox<>(KeyItem.KeyItems.KEYS);
        toggleKeyComboBox.setPreferredSize(new Dimension(100, 25));
        toggleKeyShortcutLabel = new JLabel("Shortcut:");
        toggleKeyShortcutLabel.setPreferredSize(new Dimension(60, 25));
        toggleKeyComboBox.setSelectedItem(new KeyItem("A", KeyEvent.VK_A)); 
        toggleKeyShortcut = (KeyItem) toggleKeyComboBox.getSelectedItem();


        skillLocationParamSilder.setMajorTickSpacing(10); // large scale interval
        skillLocationParamSilder.setMinorTickSpacing(1);// small scale interval
        skillLocationParamSilder.setPaintTicks(true);     // interval visible
        skillLocationParamLabel = new JLabel("position:");

        alpha = 0.8f;
        alphaLabel = new JLabel("alpha: ");
        alphaGuideLabel = new JLabel("(0 ~ 1)");
        alphaSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) (alpha * 100));
        alphaField = new JTextField(5);
        alphaField.setText(String.valueOf(alpha));


        for (int i = 0; i < 6; i++) {
            JFrame newSkillFrame = createSkillFrame(skillXCoords[i], SKILL_CAPTURE_Y);
            skillFrames[i] = newSkillFrame;
        }


        startButton = new JButton("Start Projection");

        Timer timer = new Timer((1000 / FPS), e -> {
            try {
                Robot robot = new Robot();

                for (int i = 0; i < skillFrames.length; i++) {
                    skillCaptures[i] = robot.createScreenCapture(
                            new Rectangle(skillXCoords[i], SKILL_CAPTURE_Y, CAPTURE_WIDTH, CAPTURE_HEIGHT));
                    skillFrames[i].repaint();
                }
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (skillFrames[0].isVisible()) {
                    for (JFrame skillFrame : skillFrames) {
                        skillFrame.setVisible(false);
                        startButton.setText("Start Projection");  // Change the button's text to "Stop"
                        timer.stop();
                    }
                } else {
                    for (JFrame skillFrame : skillFrames) {
                        skillFrame.setVisible(true);
                        startButton.setText("Stop Projection");  // Change the button's text to "Start"
                        timer.start();
                    }
                }
            }
        });

        toggleKeyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleKeyShortcut = (KeyItem) toggleKeyComboBox.getSelectedItem();
            }
        });

        skillLocationParamSilder.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                wasVisible = skillFrames[0].isVisible();
                if (wasVisible) {
                    for (JFrame skillFrame : skillFrames) {
                        skillFrame.setVisible(false);

                    }
                }
                skillLocationParam = ((JSlider) e.getSource()).getValue() * 0.01;
                for (int i = 0; i < 6; i++) {
                    JFrame newSkillFrame = createSkillFrame(skillXCoords[i], SKILL_CAPTURE_Y);
                    skillFrames[i] = newSkillFrame;
                    skillLocationParamField.setText(String.valueOf(skillLocationParam));
                }
                if (wasVisible) {
                    for (JFrame skillFrame : skillFrames) {
                        skillFrame.setVisible(true);

                    }
                }
            }
        });

        skillLocationParamField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSlider();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSlider();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSlider();
            }

            private void updateSlider() {
                try {
                    double value = Double.parseDouble(skillLocationParamField.getText());
                    skillLocationParamSilder.setValue((int) (value * 100));  // Assuming the slider's range is from 0 to 100 for values between 0.00 to 1.00
                } catch (NumberFormatException e) {
                    // Handle invalid input in the text field if necessary
                }
            }
        });

        FPS_Slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                wasVisible = skillFrames[0].isVisible();

                if (wasVisible) {
                    for (JFrame skillFrame : skillFrames) {
                        skillFrame.setVisible(false);

                    }
                }
                FPS = ((JSlider) e.getSource()).getValue();
                timer.setDelay(1000 / FPS);
                FPS_Field.setText(String.valueOf(FPS));

                if (wasVisible) {
                    for (JFrame skillFrame : skillFrames) {
                        skillFrame.setVisible(true);

                    }
                }

            }
        });

        FPS_Field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSlider();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSlider();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSlider();
            }

            private void updateSlider() {
                try {
                    double value = Double.parseDouble(FPS_Field.getText());
                    FPS_Slider.setValue((int) value);  // Assuming the slider's range is from 0 to 100 for values between 0.00 to 1.00
                } catch (NumberFormatException e) {
                    // Handle invalid input in the text field if necessary
                }
            }
        });

        alphaSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                wasVisible = skillFrames[0].isVisible();
                if (wasVisible) {
                    for (JFrame skillFrame : skillFrames) {
                        skillFrame.setVisible(false);

                    }
                }
                alpha =  (float) (((JSlider) e.getSource()).getValue() * 0.01);
                alphaField.setText(String.valueOf(alpha));
                for (int i = 0; i < 6; i++) {
                    JFrame newSkillFrame = createSkillFrame(skillXCoords[i], SKILL_CAPTURE_Y);
                    skillFrames[i] = newSkillFrame;

                }
                if (wasVisible) {
                    for (JFrame skillFrame : skillFrames) {
                        skillFrame.setVisible(true);

                    }
                }
            }
        });

        alphaField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSlider();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSlider();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSlider();
            }

            private void updateSlider() {
                try {
                    double value = Double.parseDouble(alphaField.getText());
                    alphaSlider.setValue((int) (value * 100));  // Assuming the slider's range is from 0 to 100 for values between 0.00 to 1.00
                } catch (NumberFormatException e) {
                    // Handle invalid input in the text field if necessary
                }
            }
        });


        // Timer for capturing screen at regular intervals (500ms)


    }

    private JFrame createSkillFrame(int skillX, int skillY) {
        JFrame skillFrame = new JFrame();
        skillFrame.setUndecorated(true);
        skillFrame.setOpacity(alpha);

        skillFrame.setAlwaysOnTop(true);


        int index = indexOf(skillXCoords, skillX);
        JPanel skillPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);//Tasks required to draw the basic background of a component, etc.
                if (skillCaptures[index] != null) {//If screen capture was performed more than once
                    g.drawImage(skillCaptures[index], 0, 0, this);
                }
            }
        };


        switch (index) {//center radius
            case 0: 
                skillFrame.setLocation((int) (center - (radius * Math.cos((3.14 / 2) * skillLocationParam))), FRAME_Y - (int) (radius * Math.sin((3.14 / 2) * skillLocationParam)));
                break;
            case 1:
                skillFrame.setLocation((int) (center + (radius * Math.cos((3.14 / 2) * skillLocationParam))) - SKILL_WIDTH, FRAME_Y - (int) (radius * Math.sin((3.14 / 2) * skillLocationParam)));
                break;
            case 2: 
                skillFrame.setLocation(FRAME_X, FRAME_Y);
                break;
            case 3: 
                skillFrame.setLocation((int) (center + radius - SKILL_WIDTH), FRAME_Y);
                break;
            case 4: 
                skillFrame.setLocation((int) (center - (radius * Math.cos((3.14 / 2) * skillLocationParam))), FRAME_Y + (int) ((3.14 / 2) * radius * Math.sin(skillLocationParam)));
                break;
            case 5: 
                skillFrame.setLocation((int) (center + (radius * Math.cos((3.14 / 2) * skillLocationParam))) - SKILL_WIDTH, FRAME_Y + (int) ((3.14 / 2) * radius * Math.sin(skillLocationParam)));
                break;
            default:                 // Handle case where the value is not found in the array
                break;
        }
        skillPanel.setPreferredSize(new Dimension(SKILL_WIDTH, SKILL_HEIGHT));
        skillFrame.add(skillPanel);

        skillFrame.pack();

        skillFrame.setVisible(false);
        return skillFrame;
    }

    public static int indexOf(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public KeyItem getToggleKeyShortcut() {
        return toggleKeyShortcut;
    }

    public JComboBox<KeyItem> getToggleKeyComboBox() {
        return toggleKeyComboBox;
    }

    public JLabel getToggleKeyShortcutLabel() {
        return toggleKeyShortcutLabel;
    }

    public JComboBox<KeyItem> getKeyComboBox() {
        return keyComboBox;
    }

    public JSlider getSkillLocationParamSilder() {
        return skillLocationParamSilder;
    }

    public JLabel getSkillLocationParamLabel() {
        return skillLocationParamLabel;
    }

    public JTextField getSkillLocationParamField() {
        return skillLocationParamField;
    }

    public double getFPS() {
        return FPS;
    }

    public JSlider getFPS_Slider() {
        return FPS_Slider;
    }

    public JTextField getFPS_Field() {
        return FPS_Field;
    }

    public JLabel getFPS_Label() {
        return FPS_Label;
    }

    public JLabel getFPS_GuideLabel() {
        return FPS_GuideLabel;
    }

    public JLabel getSkillLocationParamGuideLabel() {
        return skillLocationParamGuideLabel;
    }

    public JLabel getAlphaLabel() {
        return alphaLabel;
    }

    public JSlider getAlphaSlider() {
        return alphaSlider;
    }

    public JTextField getAlphaField() {
        return alphaField;
    }

    public JLabel getAlphaGuideLabel() {
        return alphaGuideLabel;
    }
}
