package com.girlkun.ninnin.entities.map;

import com.girlkun.ninnin.interfaces.IUpdate;
import com.girlkun.ninnin.templates.MapTemplate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
@Data
public class Map implements Runnable, IUpdate {

    private MapTemplate mapTemplate;
    private List<Zone> zones;

    public Map(MapTemplate mapTemplate) {
        this.mapTemplate = mapTemplate;
        this.zones = new ArrayList<>();
        for (int i = 0; i < this.mapTemplate.getZones(); i++) {
            this.zones.add(new Zone(i, this));
        }
        new Thread(this, "Update map " + this.mapTemplate.getName()).start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.update();
                Thread.sleep(1);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void update() {
        for (Zone zone : this.zones) {
            zone.update();
        }
    }

}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
