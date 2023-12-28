package com.girlkun.ninnin.server;

import com.girlkun.GirlkunDBTool;
import com.girlkun.database.GirlkunDB;
import com.girlkun.ninnin.consts.ConstDB;
import com.girlkun.ninnin.entities.map.Map;
import com.girlkun.ninnin.templates.MapTemplate;
import com.girlkun.ninnin.templates.ObjectMapTemplate;
import com.girlkun.result.GirlkunResultSet;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author ❤Girlkun75❤
 * @copyright ❤Trần Lại❤
 */
public class Manager {

    private static Manager I;

    public static Manager gI() throws Exception {
        if (Manager.I == null) {
            Manager.I = new Manager();
        }
        return Manager.I;
    }

    public static int PORT = 7555;

    public static final List<ObjectMapTemplate> OBJECT_MAP_TEMPLATES = new ArrayList<>();
    public static final List<MapTemplate> MAP_TEMPLATES = new ArrayList<>();
    public static final List<Map> MAPS = new ArrayList<>();

    private Manager() throws Exception {
        this.loadDatabase();
    }

    private void loadDatabase() throws Exception {
        this.loadObjectMapTemplates();
        this.loadMapTemplates();
        this.initMaps();
    }

    private void loadObjectMapTemplates() throws Exception {
        GirlkunResultSet rs = GirlkunDB.executeQuery(ConstDB.NINNIN_RES.baseName, "select * from object_map_template");
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int type = rs.getInt("type");
            int fps[] = new int[2];
            int iconsId[] = null;
            ObjectMapTemplate.Body mainBody = new ObjectMapTemplate.Body();
            ObjectMapTemplate.Body extraBodies[] = null;
            int dx = rs.getInt("dx");
            int dy = rs.getInt("dy");

            JSONArray dataArr = (JSONArray) JSONValue.parse(rs.getString("fps"));
            fps[0] = Integer.parseInt(String.valueOf(dataArr.get(0)));
            fps[1] = Integer.parseInt(String.valueOf(dataArr.get(1)));
            dataArr = (JSONArray) JSONValue.parse(rs.getString("icons_id"));
            iconsId = new int[dataArr.size()];
            for (int i = 0; i < iconsId.length; i++) {
                iconsId[i] = Integer.parseInt(String.valueOf(dataArr.get(i)));
            }
            JSONObject dataObj = (JSONObject) JSONValue.parse(rs.getString("main_body"));
            mainBody.setType(String.valueOf(dataObj.get("type")));
            mainBody.setW(Integer.parseInt(String.valueOf(dataObj.get("w"))));
            mainBody.setH(Integer.parseInt(String.valueOf(dataObj.get("h"))));
            mainBody.setR(Integer.parseInt(String.valueOf(dataObj.get("r"))));
            mainBody.setFilterId(Integer.parseInt(String.valueOf(dataObj.get("filter_id"))));
            dataArr = (JSONArray) JSONValue.parse(rs.getString("extra_bodies"));

            extraBodies = new ObjectMapTemplate.Body[dataArr.size()];
            for (int i = 0; i < extraBodies.length; i++) {
                dataObj = (JSONObject) JSONValue.parse(String.valueOf(dataArr.get(i)));
                extraBodies[i] = new ObjectMapTemplate.Body();
                extraBodies[i].setType(String.valueOf(dataObj.get("type")));
                extraBodies[i].setW(Integer.parseInt(String.valueOf(dataObj.get("w"))));
                extraBodies[i].setH(Integer.parseInt(String.valueOf(dataObj.get("h"))));
                extraBodies[i].setR(Integer.parseInt(String.valueOf(dataObj.get("r"))));
                extraBodies[i].setFilterId(Integer.parseInt(String.valueOf(dataObj.get("filter_id"))));
                extraBodies[i].setDx(Integer.parseInt(String.valueOf(dataObj.get("dx"))));
                extraBodies[i].setDy(Integer.parseInt(String.valueOf(dataObj.get("dy"))));
            }
            OBJECT_MAP_TEMPLATES.add(new ObjectMapTemplate(id, name, type, fps, iconsId, mainBody, extraBodies, dx, dy));
        }
        rs.dispose();
    }

    private void loadMapTemplates() throws Exception {
        GirlkunResultSet rs = GirlkunDB.executeQuery(ConstDB.NINNIN_RES.baseName, "select * from map_template");
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int zones = rs.getInt("zones");
            MAP_TEMPLATES.add(new MapTemplate(id, name, zones));
        }
        rs.dispose();
    }

    private void initMaps() throws Exception {
        for (MapTemplate temp : MAP_TEMPLATES) {
            MAPS.add(new Map(temp));
        }
    }
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức. Hãy tôn trọng tác
 * giả của mã nguồn này. Xin cảm ơn! - Girlkun75
 */
