package com.girlkun.ninnin.templates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
@Data
@ToString
@AllArgsConstructor
public class ObjectMapTemplate {

    private int id;
    private String name;
    private int type;
    private int[] fps;
    private int[] icons;
    private Body mainBody;
    private Body[] extraBodies;
    private int dx;
    private int dy;

    @Data
    @ToString
    public static class Body {

        private String type;
        private int w;
        private int h;
        private int r;
        private int filterId;
        private int dx;
        private int dy;
    }

}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
