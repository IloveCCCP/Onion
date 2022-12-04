package codec;

import io.netty.buffer.ByteBuf;
import msg.ErrorCode;
import msg.Msg;
import msg.RespMsg;

import java.nio.charset.StandardCharsets;

public class RespMsgDecoder extends Decoder{
    @Override
    public Msg decode(ByteBuf in) {
        RespMsg respMsg=new RespMsg();
        respMsg.setErrorCode(ErrorCode.getEnumByValue(in.readInt()));
        byte[] msgBytes=new byte[in.readInt()];
        in.readBytes(msgBytes);
        respMsg.setMsg(new String(msgBytes, StandardCharsets.UTF_8));
        return respMsg;
    }
}
