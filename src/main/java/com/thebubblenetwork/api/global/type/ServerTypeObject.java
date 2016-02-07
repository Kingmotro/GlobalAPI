package com.thebubblenetwork.api.global.type;

import com.thebubblenetwork.api.global.plugin.BubbleHubObject;

import java.util.HashSet;
import java.util.Set;

/**
 * The Bubble Network 2016
 * BubbleBungee
 * 09/01/2016 {11:12}
 * Created January 2016
 */

public class ServerTypeObject implements ServerType {
    private static Set<ServerType> types = new HashSet<>();

    public static ServerType registerType(ServerType type){
        types.add(type);
        BubbleHubObject.getInstance().logInfo("Registered servertype: " + type.getName());
        return type;
    }

    public static ServerType getType(String name){
        for(ServerType wrapper:types){
            if(wrapper.getName().equals(name))return wrapper;
        }
        throw new IllegalArgumentException(name + " is not a correct servertype");
    }

    public static Set<ServerType> getTypes() {
        return types;
    }

    private String name,prefix;
    private int maxplayers;

    public ServerTypeObject(String name, String prefix, int maxplayers) {
        this.name = name;
        this.prefix = prefix;
        this.maxplayers = maxplayers;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getMaxPlayers(){
        return maxplayers;
    }
}