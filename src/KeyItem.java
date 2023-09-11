import java.awt.event.KeyEvent;


class KeyItem {
    private String label;
    private int keyCode;

    public KeyItem(String label, int keyCode) {
        this.label = label;
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    @Override
    public String toString() {
        return label;
    }
    public class KeyItems {
        public static final KeyItem[] KEYS = {
                new KeyItem("A", KeyEvent.VK_A),
                new KeyItem("B", KeyEvent.VK_B),
                new KeyItem("C", KeyEvent.VK_C),
                new KeyItem("D", KeyEvent.VK_D),
                new KeyItem("E", KeyEvent.VK_E),
                new KeyItem("F", KeyEvent.VK_F),
                new KeyItem("G", KeyEvent.VK_G),
                new KeyItem("H", KeyEvent.VK_H),
                new KeyItem("I", KeyEvent.VK_I),
                new KeyItem("J", KeyEvent.VK_J),
                new KeyItem("K", KeyEvent.VK_K),
                new KeyItem("L", KeyEvent.VK_L),
                new KeyItem("M", KeyEvent.VK_M),
                new KeyItem("N", KeyEvent.VK_N),
                new KeyItem("O", KeyEvent.VK_O),
                new KeyItem("P", KeyEvent.VK_P),
                new KeyItem("Q", KeyEvent.VK_Q),
                new KeyItem("R", KeyEvent.VK_R),
                new KeyItem("S", KeyEvent.VK_S),
                new KeyItem("T", KeyEvent.VK_T),
                new KeyItem("U", KeyEvent.VK_U),
                new KeyItem("V", KeyEvent.VK_V),
                new KeyItem("W", KeyEvent.VK_W),
                new KeyItem("X", KeyEvent.VK_X),
                new KeyItem("Y", KeyEvent.VK_Y),
                new KeyItem("Z", KeyEvent.VK_Z),
                new KeyItem("0", KeyEvent.VK_0),
                new KeyItem("1", KeyEvent.VK_1),
                new KeyItem("2", KeyEvent.VK_2),
                new KeyItem("3", KeyEvent.VK_3),
                new KeyItem("4", KeyEvent.VK_4),
                new KeyItem("5", KeyEvent.VK_5),
                new KeyItem("6", KeyEvent.VK_6),
                new KeyItem("7", KeyEvent.VK_7),
                new KeyItem("8", KeyEvent.VK_8),
                new KeyItem("9", KeyEvent.VK_9),
                new KeyItem("SPACE", KeyEvent.VK_SPACE),
                new KeyItem("ENTER", KeyEvent.VK_ENTER),
                new KeyItem("ESCAPE", KeyEvent.VK_ESCAPE),
                new KeyItem("UP", KeyEvent.VK_UP),
                new KeyItem("DOWN", KeyEvent.VK_DOWN),
                new KeyItem("LEFT", KeyEvent.VK_LEFT),
                new KeyItem("RIGHT", KeyEvent.VK_RIGHT),
                new KeyItem("F1", KeyEvent.VK_F1),
                new KeyItem("F2", KeyEvent.VK_F2),
                new KeyItem("F3", KeyEvent.VK_F3),
                new KeyItem("F4", KeyEvent.VK_F4),
                new KeyItem("F5", KeyEvent.VK_F5),
                new KeyItem("F6", KeyEvent.VK_F6),
                new KeyItem("F7", KeyEvent.VK_F7),
                new KeyItem("F8", KeyEvent.VK_F8),
                new KeyItem("F9", KeyEvent.VK_F9),
                new KeyItem("F10", KeyEvent.VK_F10),
                new KeyItem("F11", KeyEvent.VK_F11),
                new KeyItem("F12", KeyEvent.VK_F12),
                new KeyItem("TAB", KeyEvent.VK_TAB),
                new KeyItem("BACKSPACE", KeyEvent.VK_BACK_SPACE),
                new KeyItem("SHIFT", KeyEvent.VK_SHIFT),
                new KeyItem("CTRL", KeyEvent.VK_CONTROL),
                new KeyItem("ALT", KeyEvent.VK_ALT),
                new KeyItem("DELETE", KeyEvent.VK_DELETE),
                new KeyItem("INSERT", KeyEvent.VK_INSERT),
                new KeyItem("HOME", KeyEvent.VK_HOME),
                new KeyItem("END", KeyEvent.VK_END),
                new KeyItem("PAGE UP", KeyEvent.VK_PAGE_UP),
                new KeyItem("PAGE DOWN", KeyEvent.VK_PAGE_DOWN),
                new KeyItem("NUMPAD 0", KeyEvent.VK_NUMPAD0),
                new KeyItem("NUMPAD 1", KeyEvent.VK_NUMPAD1),
                new KeyItem("NUMPAD 2", KeyEvent.VK_NUMPAD2),
                new KeyItem("NUMPAD 3", KeyEvent.VK_NUMPAD3),
                new KeyItem("NUMPAD 4", KeyEvent.VK_NUMPAD4),
                new KeyItem("NUMPAD 5", KeyEvent.VK_NUMPAD5),
                new KeyItem("NUMPAD 6", KeyEvent.VK_NUMPAD6),
                new KeyItem("NUMPAD 7", KeyEvent.VK_NUMPAD7),
                new KeyItem("NUMPAD 8", KeyEvent.VK_NUMPAD8),
                new KeyItem("NUMPAD 9", KeyEvent.VK_NUMPAD9),
                new KeyItem("MULTIPLY", KeyEvent.VK_MULTIPLY),
                new KeyItem("ADD", KeyEvent.VK_ADD),
                new KeyItem("SUBTRACT", KeyEvent.VK_SUBTRACT),
                new KeyItem("DIVIDE", KeyEvent.VK_DIVIDE),
                new KeyItem("DECIMAL", KeyEvent.VK_DECIMAL),
                new KeyItem("NUM LOCK", KeyEvent.VK_NUM_LOCK),
                new KeyItem("CAPS LOCK", KeyEvent.VK_CAPS_LOCK),
                new KeyItem("SCROLL LOCK", KeyEvent.VK_SCROLL_LOCK),
                new KeyItem("PRINT SCREEN", KeyEvent.VK_PRINTSCREEN),
                new KeyItem("PAUSE", KeyEvent.VK_PAUSE),
                new KeyItem("SEMICOLON", KeyEvent.VK_SEMICOLON),
                new KeyItem("EQUALS", KeyEvent.VK_EQUALS),
                new KeyItem("COMMA", KeyEvent.VK_COMMA),
                new KeyItem("PERIOD", KeyEvent.VK_PERIOD),
                new KeyItem("SLASH", KeyEvent.VK_SLASH),
                new KeyItem("BACKQUOTE", KeyEvent.VK_BACK_QUOTE),
                new KeyItem("OPEN BRACKET", KeyEvent.VK_OPEN_BRACKET),
                new KeyItem("CLOSE BRACKET", KeyEvent.VK_CLOSE_BRACKET),
                new KeyItem("BACKSLASH", KeyEvent.VK_BACK_SLASH),
                new KeyItem("MINUS", KeyEvent.VK_MINUS)
        };

    }


}