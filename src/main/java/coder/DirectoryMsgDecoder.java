package coder;

import io.netty.buffer.ByteBuf;
import msg.DirectoryMsg;
import msg.Msg;

public class DirectoryMsgDecoder extends Decoder{


    @Override
    public DirectoryMsg decode(ByteBuf in) {
        DirectoryMsg msg=new DirectoryMsg();
        msg.setPort(in.readInt());
        byte[] keyBytes=new byte[in.readInt()];
        in.readBytes(keyBytes);
        msg.setPublicKey(keyBytes);
        return msg;
    }
}
