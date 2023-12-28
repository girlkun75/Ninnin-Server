package com.girlkun.ninnin.server;

import com.girlkun.ninnin.entities.map.Map;
import com.girlkun.ninnin.entities.map.Zone;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class ManagerService {

    private static ManagerService I;

    public static ManagerService gI() {
        if (ManagerService.I == null) {
            ManagerService.I = new ManagerService();
        }
        return ManagerService.I;
    }

    public Map findMapById(int id) {
        for (Map map : Manager.MAPS) {
            if (map.getMapTemplate().getId() == id) {
                return map;
            }
        }
        return null;
    }

    public Zone findZoneByMapIdAndZoneId(int mapId, int zoneId) {
        Map map = this.findMapById(mapId);
        if (map == null) {
            return null;
        }
        for (Zone zone : map.getZones()) {
            if (zone.getId() == zoneId) {
                return zone;
            }
        }
        return null;
    }

}






















/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức.
 * Hãy tôn trọng tác giả của mã nguồn này.
 * Xin cảm ơn! - Girlkun75
 */
