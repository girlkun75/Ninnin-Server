package com.girlkun.ninnin.entities.item.template;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class OptionTemplate {

    private static Map<Integer, OptionTemplate> ITEM_OPTION_TEMPLATES = new HashMap<>();

    public static void addOptionTemplate(int id, String name) {
        OptionTemplate itemTemplate = new OptionTemplate();
        itemTemplate.id = id;
        itemTemplate.name = name;
        ITEM_OPTION_TEMPLATES.put(id, itemTemplate);
    }

    public static OptionTemplate findById(int id) {
        Object o = ITEM_OPTION_TEMPLATES.get(id);
        if (o == null) {
            return null;
        }
        return (OptionTemplate) o;
    }

    public int id;
    public String name;

    private OptionTemplate() {

    }
}






















/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức.
 * Hãy tôn trọng tác giả của mã nguồn này.
 * Xin cảm ơn! - Girlkun75
 */
