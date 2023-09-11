import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;


public class Window implements NativeKeyListener {
    private final String SAVE_FILE_PATH = "save.bin";
    Color color = new Color(67, 97, 127);
    JFrame frame;
    JPanel masterPanel = new JPanel();;
    JPanel repeatKeyPressPanel1 = new JPanel();
    JPanel repeatKeyPressPanel2 = new JPanel();
    JPanel skillProjectionPanel = new JPanel();
    JPanel repeatKeyPressPanel1_1 = new JPanel();;
    JPanel repeatKeyPressPanel1_2 = new JPanel();;
    JPanel repeatKeyPressPanel2_1 = new JPanel();;
    JPanel repeatKeyPressPanel2_2 = new JPanel();;
    JPanel skillProjectionPanel1 = new JPanel();;
    JPanel skillProjectionPanel2 = new JPanel();;
    JPanel skillProjectionPanel3 = new JPanel();;
    JPanel skillProjectionPanel4 = new JPanel();;
    JPanel[] mainPanels;
    JPanel[] subPanels;
    JSlider projectFrame;
    RepeatKeyPress keyPress1 = new RepeatKeyPress();;
    RepeatKeyPress keyPress2 = new RepeatKeyPress();;
    SkillProjection skillProjection = new SkillProjection();;
    JLabel infoLabel;
    JPanel infoPanel = new JPanel();;
    Image icon;
    Object[] globalKeyListenees;
    GlobalKeyListener[] globalKeyListeners;
    public Window() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("Failed to register native hook.");
            System.exit(1);
        }

        frame = new JFrame("SWAT_beta by 싱숑");

        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.setBounds(5, 5, 705, 800);

        mainPanels = new JPanel[]{repeatKeyPressPanel1,repeatKeyPressPanel2,skillProjectionPanel};
        for(JPanel mainPanel : mainPanels) {
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(BorderFactory.createLineBorder(color, 10, true));
            mainPanel.setBounds(0, 0, 500, 350);
        }
        subPanels = new JPanel[]{repeatKeyPressPanel1_1,repeatKeyPressPanel1_2,repeatKeyPressPanel2_1,
                repeatKeyPressPanel2_2,skillProjectionPanel1,skillProjectionPanel2,skillProjectionPanel3,skillProjectionPanel4};
        for(JPanel subPanel : subPanels) subPanel.setLayout(new FlowLayout());

        infoLabel = new JLabel("<html><font size='5'>첫번째엔 메디박스 넣고 두번째엔 힐링 젤리넣어서 먹으려고 두개 만듦" +
                "<br>조금 버그가 있을 수도 있는데 내킬때 고침" +
                "<br>단축키는 전부 다른걸로 해야됨. 고치기 너무 귀찬아" +
                "<br>프로그램 키면 시간부터 설정해야 아무거나 막눌리는 참사를 막을 수 있음</html>");
        infoPanel.setLayout(new BorderLayout());// 전체버튼 + 위치 지정
        infoPanel.setBounds(0, 0, 500, 700);

        projectFrame = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);

        frame.setLocation(100, 100); // 발생시 어디에 위치
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 윈도우 종료시 프로그램 같이 종료55
        frame.setLayout(null); // 레이아웃 매니저를 사용하지 않음


        icon = Toolkit.getDefaultToolkit().getImage("icon.jpg");// 아이콘 이미지 로드
        frame.setIconImage(icon);// 아이콘 설정

        frame.setPreferredSize(new Dimension(730, 500));//프레임 사이즈, 다른방법도있음
        frame.setResizable(false);
        frame.setFocusable(true); // 프레임이 키 이벤트를 받을 수 있도록 설정합니다.

        loadSettings();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveSettings();
            }
        });

        repeatKeyPressPanel1_1.add(keyPress1.getIntervalLabel());
        repeatKeyPressPanel1_1.add(keyPress1.getIntervalField());
        repeatKeyPressPanel1_1.add(keyPress1.getRandomIntervalLabel());
        repeatKeyPressPanel1_1.add(keyPress1.getRandomIntervalField());
        repeatKeyPressPanel1_2.add(keyPress1.getKeyLabel());
        repeatKeyPressPanel1_2.add(keyPress1.getKeyComboBox());
        repeatKeyPressPanel1_2.add(keyPress1.getToggleKeyShortcutLabel());
        repeatKeyPressPanel1_2.add(keyPress1.getToggleKeyComboBox());
        repeatKeyPressPanel1_2.add(keyPress1.getStartButton());
        repeatKeyPressPanel1.add(repeatKeyPressPanel1_1);
        repeatKeyPressPanel1.add(repeatKeyPressPanel1_2);

        repeatKeyPressPanel2_1.add(keyPress2.getIntervalLabel());
        repeatKeyPressPanel2_1.add(keyPress2.getIntervalField());
        repeatKeyPressPanel2_1.add(keyPress2.getRandomIntervalLabel());
        repeatKeyPressPanel2_1.add(keyPress2.getRandomIntervalField());
        repeatKeyPressPanel2_2.add(keyPress2.getKeyLabel());
        repeatKeyPressPanel2_2.add(keyPress2.getKeyComboBox());
        repeatKeyPressPanel2_2.add(keyPress2.getToggleKeyShortcutLabel());
        repeatKeyPressPanel2_2.add(keyPress2.getToggleKeyComboBox());
        repeatKeyPressPanel2_2.add(keyPress2.getStartButton());
        repeatKeyPressPanel2.add(repeatKeyPressPanel2_1);
        repeatKeyPressPanel2.add(repeatKeyPressPanel2_2);

        skillProjectionPanel1.add(skillProjection.getToggleKeyShortcutLabel());
        skillProjectionPanel1.add(skillProjection.getToggleKeyComboBox());
        skillProjectionPanel1.add(skillProjection.getStartButton());

        skillProjectionPanel2.add(skillProjection.getSkillLocationParamLabel());
        skillProjectionPanel2.add(skillProjection.getSkillLocationParamSilder());
        skillProjectionPanel2.add(skillProjection.getSkillLocationParamField());
        skillProjectionPanel2.add(skillProjection.getSkillLocationParamGuideLabel());

        skillProjectionPanel3.add(skillProjection.getFPS_Label());
        skillProjectionPanel3.add(skillProjection.getFPS_Slider());
        skillProjectionPanel3.add(skillProjection.getFPS_Field());
        skillProjectionPanel3.add(skillProjection.getFPS_GuideLabel());

        skillProjectionPanel4.add(skillProjection.getAlphaLabel());
        skillProjectionPanel4.add(skillProjection.getAlphaSlider());
        skillProjectionPanel4.add(skillProjection.getAlphaField());
        skillProjectionPanel4.add(skillProjection.getAlphaGuideLabel());

        skillProjectionPanel.add(skillProjectionPanel1);
        skillProjectionPanel.add(skillProjectionPanel2);
        skillProjectionPanel.add(skillProjectionPanel3);
        skillProjectionPanel.add(skillProjectionPanel4);

        masterPanel.add(repeatKeyPressPanel1);
        masterPanel.add(repeatKeyPressPanel2);
        masterPanel.add(skillProjectionPanel);

        infoPanel.add(infoLabel, BorderLayout.NORTH);
        masterPanel.add(infoPanel);

        frame.add(masterPanel);

        globalKeyListenees = new Object[]{keyPress1,keyPress2,skillProjection};
        for(Object globalKeyListenee : globalKeyListenees){


        }
        GlobalKeyListener globalKeyListenerKeyPress1 = new GlobalKeyListener(keyPress1);
        globalKeyListenerKeyPress1.setButton(keyPress1.getStartButton(),keyPress1.getToggleKeyShortcut());
        GlobalScreen.addNativeKeyListener(globalKeyListenerKeyPress1);

        GlobalKeyListener globalKeyListenerKeyPress2 = new GlobalKeyListener(keyPress2);
        globalKeyListenerKeyPress2.setButton(keyPress2.getStartButton(),keyPress2.getToggleKeyShortcut());
        GlobalScreen.addNativeKeyListener(globalKeyListenerKeyPress2);

        GlobalKeyListener globalKeyListenerSkillProjection = new GlobalKeyListener(skillProjection);
        globalKeyListenerSkillProjection.setButton(skillProjection.getStartButton(),skillProjection.getToggleKeyShortcut());
        GlobalScreen.addNativeKeyListener(globalKeyListenerSkillProjection);


        frame.pack();
        frame.setVisible(true); // 프레임이 보이게
    }

    private void loadSettings() {
        File file = new File(SAVE_FILE_PATH);
        if (file.exists()) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(SAVE_FILE_PATH));
                // 예: 첫 번째 줄에 intervalField의 값을 저장
                int i = 0;
                keyPress1.getIntervalField().setText(lines.get(i++));
                keyPress1.getRandomIntervalField().setText(lines.get(i++));
                keyPress1.getKeyComboBox().setSelectedItem(findKeyItemByKeyLabel(keyPress1.getKeyComboBox(), lines.get(i++)));
                keyPress1.getToggleKeyComboBox().setSelectedItem(findKeyItemByKeyLabel(keyPress1.getToggleKeyComboBox(), lines.get(i++)));

                keyPress2.getIntervalField().setText(lines.get(i++));
                keyPress2.getRandomIntervalField().setText(lines.get(i++));
                keyPress2.getKeyComboBox().setSelectedItem(findKeyItemByKeyLabel(keyPress2.getKeyComboBox(), lines.get(i++)));
                keyPress2.getToggleKeyComboBox().setSelectedItem(findKeyItemByKeyLabel(keyPress2.getToggleKeyComboBox(), lines.get(i++)));

                skillProjection.getToggleKeyComboBox().setSelectedItem(findKeyItemByKeyLabel(skillProjection.getToggleKeyComboBox(), lines.get(i++)));
                skillProjection.getSkillLocationParamSilder().setValue(Integer.parseInt(lines.get(i++)));
                skillProjection.getSkillLocationParamField().setText(lines.get(i++));
                skillProjection.getFPS_Slider().setValue(Integer.parseInt(lines.get(i++)));
                skillProjection.getFPS_Field().setText(lines.get(i++));
                skillProjection.getAlphaSlider().setValue(Integer.parseInt(lines.get(i++)));
                skillProjection.getAlphaField().setText(lines.get(i++));




                // 필요한 다른 설정값들도 여기서 로드
            } catch (IOException e) {
                showErrorDialog("Error loading settings: " + e.getMessage());
            }
        }
    }

    private void saveSettings() {
        try (FileWriter writer = new FileWriter(SAVE_FILE_PATH)) {
            // 예: 첫 번째 줄에 intervalField의 값을 저장
            writer.write(keyPress1.getIntervalField().getText() + "\n" +
                    keyPress1.getRandomIntervalField().getText() + "\n" +
                    keyPress1.getKeyComboBox().getSelectedItem().toString() + "\n" +
                    keyPress1.getToggleKeyComboBox().getSelectedItem().toString() + "\n" +

                    keyPress2.getIntervalField().getText() + "\n" +
                    keyPress2.getRandomIntervalField().getText() + "\n" +
                    keyPress2.getKeyComboBox().getSelectedItem().toString() + "\n" +
                    keyPress2.getToggleKeyComboBox().getSelectedItem().toString() + "\n" +

                    skillProjection.getToggleKeyComboBox().getSelectedItem().toString() + "\n" +
                    skillProjection.getSkillLocationParamSilder().getValue() + "\n" +
                    skillProjection.getSkillLocationParamField().getText() + "\n" +
                    skillProjection.getFPS_Slider().getValue() + "\n" +
                    skillProjection.getFPS_Field().getText() + "\n" +
                    skillProjection.getAlphaSlider().getValue() + "\n" +
                    skillProjection.getAlphaField().getText() + "\n"
            );
            // 필요한 다른 설정값들도 여기서 저장
        } catch (IOException e) {
            showErrorDialog("Error saving settings: " + e.getMessage());
        }
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private KeyItem findKeyItemByKeyLabel(JComboBox<KeyItem> comboBox, String label) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            KeyItem item = comboBox.getItemAt(i);
            if (item.toString().equals(label)) {
                return item;
            }
        }
        return null;
    }
}
