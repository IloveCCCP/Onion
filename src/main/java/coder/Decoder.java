package coder;

import io.netty.buffer.ByteBuf;
import msg.Msg;

public abstract class Decoder {


    public abstract Msg decode(ByteBuf in);
}
