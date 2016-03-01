package com.thebubblenetwork.api.global.bubblepackets.messaging.messages.request;

import com.google.common.io.ByteArrayDataInput;
import com.thebubblenetwork.api.global.bubblepackets.messaging.AbstractMessageObject;
import com.thebubblenetwork.api.global.type.ServerType;

import java.io.DataOutputStream;
import java.io.IOException;

public class ServerListRequest extends AbstractMessageObject {
    private String servertype;

    public ServerListRequest(ServerType servertype) {
        this.servertype = servertype.getName();
    }

    public ServerListRequest(byte[] bytes) {
        super(bytes);
    }

    public void serialize(ByteArrayDataInput in) {
        servertype = in.readUTF();
    }

    public void parse(DataOutputStream out) throws IOException {
        out.writeUTF(servertype);
    }

    public ServerType getServertype() {
        return ServerType.getType(servertype);
    }
}
