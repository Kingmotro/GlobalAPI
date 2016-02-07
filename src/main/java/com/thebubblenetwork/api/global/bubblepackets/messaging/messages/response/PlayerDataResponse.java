package com.thebubblenetwork.api.global.bubblepackets.messaging.messages.response;

import com.google.common.io.ByteArrayDataInput;
import com.thebubblenetwork.api.global.bubblepackets.messaging.messages.AbstractDataMapMessageObject;
import com.thebubblenetwork.api.global.bubblepackets.messaging.messages.PlayerMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Copyright Statement
 * ----------------------
 * Copyright (C) The Bubble Network, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Wrote by Jacob Evans <jacobevansminor@gmail.com>, 01 2016
 * <p>
 * <p>
 * Class information
 * ---------------------
 * Package: com.thebubblenetwork.bubblepackets.messaging.messages.request
 * Date-created: 25/01/2016 00:00
 * Project: BubblePackets
 */
public class PlayerDataResponse extends AbstractDataMapMessageObject implements PlayerMessage {
    private UUID u;

    public PlayerDataResponse(UUID u, Map data) {
        super(data);
        this.u = u;
    }

    public PlayerDataResponse(byte[] bytes){
        super(bytes);
    }

    public void serializeInfo(ByteArrayDataInput in){
        u = UUID.fromString(in.readUTF());
    }

    public void parseInfo(DataOutputStream out) throws IOException{
        out.writeUTF(String.valueOf(u));
    }

    public UUID getUUID(){
        return u;
    }
}