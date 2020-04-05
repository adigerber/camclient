package com.adigerber.camclient.core.http.models;

import java.util.HashMap;
import java.util.Map;

public enum ServerType {
    NewCard(4),
    DVRDVS(5),
    DVR3(9, 6),
    IPCamera(7),
    DVR4(8),
    NVMS(10);

    private int[] ids;
    private static Map<Integer, ServerType> types;

    static {
        types = new HashMap<>();

        for (ServerType type : ServerType.values()) {
            for (int id : type.ids) {
                types.put(id, type);
            }
        }
    }

    ServerType(int... ids) {
        this.ids = ids;
    }

    public static ServerType byId(int id) {
        return types.get(id);
    }

    public int getId() {
        return ids[0];
    }
}
