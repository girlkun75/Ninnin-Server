package com.girlkun.ninnin.utils;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class Utils {

    public static void genSingleton(Class clazz) {
        String name = clazz.getSimpleName();
        System.out.println("private static " + name + " I;");
        System.out.println("public static " + name + " gI(){");
        System.out.println("if(" + name + ".I == null){");
        System.out.println(name + ".I = new " + name + "();");
        System.out.println("}");
        System.out.println("return " + name + ".I;");
        System.out.println("}");
    }

    public static float distance(float x1, float y1, float x2, float y2) {
        float deltaX = x2 - x1;
        float deltaY = y2 - y1;
        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
