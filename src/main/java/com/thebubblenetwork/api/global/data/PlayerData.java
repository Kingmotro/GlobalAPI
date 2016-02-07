package com.thebubblenetwork.api.global.data;

import com.thebubblenetwork.api.global.plugin.BubbleHubObject;
import com.thebubblenetwork.api.global.sql.SQLUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Jacob on 31/12/2015.
 */
public class PlayerData extends DataObject {

    public static final String
            RANKBASE = "rank",
            STATSBASE = "stats",
            FRIENDSBASE = "friends",
            ITEMSBASE = "items",
            PACKS = "packs",
            KITBASE = "kits",
            CURRENCYBASE = "currency",
            TOKENS = "tokens",
            CRYSTALS = "crystals",
            MAINRANK = RANKBASE + ".mainrank",
            SUBRANKS = RANKBASE + ".subranks",
            FRIENDSLIST = FRIENDSBASE + ".current",
            FRIENDINCOMINGRQ = FRIENDSBASE + ".incoming",
            FRIENDOUTGOINGRQ = FRIENDSBASE + ".outgoing";

    public static String table = "playerdata";

    public PlayerData(Map loaded) {
        super(loaded);
    }

    public UUID[] getUUIDList(String indentifier) throws InvalidBaseException{
        String[] list = getString(indentifier).split(",");
        UUID[] uuids = new UUID[list.length];
        int i = 0;
        for(String s:list){
            uuids[i] = UUID.fromString(s);
            i++;
        }
        return uuids;
    }

    public Map<String, Integer> getMapRaw(String indentifier) {
        Map<String, Integer> map = new HashMap<>();
        for (Object o : getRaw().keySet()) {
            if (o instanceof String) {
                String s = (String) o;
                if (s.startsWith(indentifier)) {
                    int i;
                    try {
                        i = getNumber(s).intValue();
                    } catch (InvalidBaseException ex) {
                        continue;
                    }
                    map.put(s.replace(indentifier + ".", ""), i);
                }
            }
        }
        return map;
    }

    public Map<String, Integer> getMap(String id, String uid) {
        String indentifier = id + "." + uid.toLowerCase();
        return getMapRaw(indentifier);
    }
}