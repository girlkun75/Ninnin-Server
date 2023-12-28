package com.girlkun.ninnin.entities.map;

import com.girlkun.ninnin.server.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
@Data
@AllArgsConstructor
public class Waypoint {

    private int id;
    private String name;
    private int x;
    private int y;
    private int w;
    private int h;
    private boolean isEnter;
    
    
    private int mapIdGo;
    private int xGo;
    private int yGo;
    
    public final boolean isOverlap(float x, float y){
        return true;
    }
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */

