import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;



public class RepeatKeyPress implements NativeKeyListener{

    boolean golobalListened;
    private boolean isTimerRunning;
    private boolean pauseForChat = true;
    private Robot robot;
    private Timer timer;
    private int repeatKey;
    private JLabel intervalLabel;
    private JTextField intervalField;
    private JLabel keyLabel;
    private JLabel toggleKeyShortcutLabel;
    private JComboBox<KeyItem> keyComboBox;
    private JButton startButton;
    private JLabel randomIntervalLabel;
    private JTextField randomIntervalField;
    private KeyItem toggleKeyShortcut;
    private JComboBox<KeyItem> toggleKeyComboBox;

    public RepeatKeyPress() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        intervalField = new JTextField(5);
        intervalField.setText("100"); // default value 100ms
        intervalLabel = new JLabel("Interval (ms):");

        randomIntervalLabel = new JLabel("+ random Interval (ms):");
        randomIntervalField = new JTextField(5);
        randomIntervalField.setText("100");  // default value

        keyComboBox = new JComboBox<>(KeyItem.KeyItems.KEYS);
        keyComboBox.setPreferredSize(new Dimension(100, 25));
        keyLabel = new JLabel("Key to Press:");

        toggleKeyComboBox = new JComboBox<>(KeyItem.KeyItems.KEYS);
        toggleKeyComboBox.setPreferredSize(new Dimension(100, 25));
        toggleKeyShortcutLabel = new JLabel("Shortcut:");
        toggleKeyShortcutLabel.setPreferredSize(new Dimension(60, 25));
        toggleKeyComboBox.setSelectedItem(new KeyItem("A", KeyEvent.VK_A));  // 예: Q 키를 기본값으로 설정
        toggleKeyShortcut = (KeyItem) toggleKeyComboBox.getSelectedItem();

        startButton = new JButton("Start");
        isTimerRunning = false;

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!isTimerRunning) {
                    repeatKey = ((KeyItem) keyComboBox.getSelectedItem()).getKeyCode();
                    startRepeating();
                    startButton.setText("Stop");  // 버튼의 텍스트를 "Stop"으로 변경
                    isTimerRunning = true;
                } else {
                    stopRepeating();
                    startButton.setText("Start");  // 버튼의 텍스트를 "Start"로 변경
                    isTimerRunning = false;
                }
            }
        });

        toggleKeyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleKeyShortcut = (KeyItem) toggleKeyComboBox.getSelectedItem();
            }
        });
    }

    public void startRepeating() {
        if (timer == null) {
            timer = new Timer(getRandomizedInterval(), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    robot.keyPress(repeatKey);
                    robot.keyRelease(repeatKey);
                    // 매번 액션 수행 후에 타이머의 딜레이를 랜덤하게 바꿔줍니다.
                    timer.setDelay(getRandomizedInterval());
                }
            });
            timer.start();
        }
    }

    private int getRandomizedInterval() {
        int randomInterval = (int) (Math.random() * Integer.parseInt(randomIntervalField.getText()));
        return Integer.parseInt(intervalField.getText()) + randomInterval;
    }
    private void stopRepeating() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }



    public JButton getStartButton() {
        return startButton;
    }

    public JComboBox<KeyItem> getKeyComboBox() {
        return keyComboBox;
    }

    public JLabel getIntervalLabel() {
        return intervalLabel;
    }

    public JLabel getKeyLabel() {
        return keyLabel;
    }

    public JLabel getRandomIntervalLabel() {
        return randomIntervalLabel;
    }

    public JTextField getIntervalField() {
        return intervalField;
    }

    public JTextField getRandomIntervalField() {
        return randomIntervalField;
    }

    public JComboBox<KeyItem> getToggleKeyComboBox() {
        return toggleKeyComboBox;
    }

    public JLabel getToggleKeyShortcutLabel() {
        return toggleKeyShortcutLabel;
    }
    public KeyItem getToggleKeyShortcut() {
        return toggleKeyShortcut;
    }
    public boolean getPauseForChat(){
        return pauseForChat;
    }
    public void setGolobalListened(boolean golobalListened) {
        this.golobalListened = golobalListened;
    }
}
