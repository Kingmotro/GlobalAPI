package com.thebubblenetwork.api.global.bubblepackets.messaging.messages.response;

import com.google.common.io.ByteArrayDataInput;
import com.thebubblenetwork.api.global.bubblepackets.messaging.AbstractMessageObject;
import com.thebubblenetwork.api.global.type.ServerType;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerListResponse extends AbstractMessageObject {
    public static EncapsulatedServer createServer(int id, int playercount, boolean connect){
        EncapsulatedServer server = new EncapsulatedServer();
        server.id = id;
        server.playercount = playercount;
        server.connect = connect;
        return server;
    }

    private String servertype;
    private List<EncapsulatedServer> serverList;

    public ServerListResponse(byte[] bytes) {
        super(bytes);
    }

    public ServerListResponse(ServerType servertype, List<EncapsulatedServer> serverList) {
        this.servertype = servertype.getName();
        this.serverList = serverList;
    }

    public void serialize(ByteArrayDataInput in) {
        servertype = in.readUTF();
        serverList = new ArrayList<>();
        int size = in.readInt();
        for(int current = 0;current < size; current++){
            EncapsulatedServer server = new EncapsulatedServer();
            server.id = in.readInt();
            server.playercount = in.readInt();
            server.connect = in.readBoolean();
            serverList.add(server);
        }
    }

    public void parse(DataOutputStream out) throws IOException {
        out.writeUTF(servertype);
        out.writeInt(serverList.size());
        for(EncapsulatedServer server: serverList){
            out.writeInt(server.id);
            out.writeInt(server.playercount);
            out.writeBoolean(server.connect);
        }
    }

    public List<EncapsulatedServer> getServerList(){
        return serverList;
    }

    public ServerType getServertype() {
        return ServerType.getType(servertype);
    }

    public static class EncapsulatedServer{
        private int id, playercount;
        private boolean connect;

        public int getId() {
            return id;
        }

        public boolean isConnect() {
            return connect;
        }

        public int getPlayercount() {
            return playercount;
        }
    }
}
