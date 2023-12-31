package com.girlkun.ninnin.templates;

import com.girlkun.ninnin.entities.map.Waypoint;
import java.util.List;
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
public class MapTemplate {

    private int id;
    private String name;
    private int zones;
    
    private List<Waypoint> waypoints;
    
}






















/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức.
 * Hãy tôn trọng tác giả của mã nguồn này.
 * Xin cảm ơn! - Girlkun75
 */
