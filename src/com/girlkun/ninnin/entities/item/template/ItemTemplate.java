package com.girlkun.ninnin.entities.item.template;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class ItemTemplate {

    private static Map<Integer, ItemTemplate> ITEM_TEMPLATES = new HashMap<>();

    public static void addItemTemplate(int id, int typeItem, int typeClass, String name, String description, int iconId) {
        ItemTemplate itemTemplate = new ItemTemplate();
        itemTemplate.id = id;
        itemTemplate.typeItem = typeItem;
        itemTemplate.typeClass = typeClass;
        itemTemplate.name = name;
        itemTemplate.description = description;
        itemTemplate.iconId = iconId;
        ITEM_TEMPLATES.put(id, itemTemplate);
    }

    public static ItemTemplate findById(int id) {
        Object o = ITEM_TEMPLATES.get(id);
        if (o == null) {
            return null;
        }
        return (ItemTemplate) o;
    }

    public int id;
    public int typeItem;
    public int typeClass;
    public String name;
    public String description;
    public int iconId;

    private ItemTemplate() {

    }
}






















/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức.
 * Hãy tôn trọng tác giả của mã nguồn này.
 * Xin cảm ơn! - Girlkun75
 */
