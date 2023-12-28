package com.girlkun.ninnin.managers;

import com.girlkun.ninnin.entities.map.Map;
import com.girlkun.ninnin.server.Manager;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class MapManager {

    public static Map findById(int mapId){
        for(Map map : Manager.MAPS){
            if(map.getMapTemplate().getId() == mapId){
                return map;
            }
        }
        return null;
    }
    
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */

