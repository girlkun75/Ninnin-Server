package com.girlkun.ninnin.consts;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public enum ConstEventPopup {

    NON_ACTION(-1),
    SWITCH_TO_MENU_SCREEN(0),
    SWITCH_TO_CREATE_PLAYER_SCREEN(1),
    SWITCH_TO_REGISTER_SCREEN(2),
    ;

    private ConstEventPopup(int id) {
        this.id = (byte) id;
    }

    public byte id;

    public static ConstEventPopup findById(int id) {
        for (ConstEventPopup eventPopup : ConstEventPopup.values()) {
            if (eventPopup.id == id) {
                return eventPopup;
            }
        }
        return null;
    }
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
