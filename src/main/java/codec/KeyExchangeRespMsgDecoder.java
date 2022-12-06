package codec;

import io.netty.buffer.ByteBuf;
import msg.KeyExchangeReqMsg;
import msg.Msg;

public class KeyExchangeRespMsgDecoder extends Decoder{


    @Override
    public Msg decode(ByteBuf in) {
        KeyExchangeReqMsg keyExchangeReqMsg=new KeyExchangeReqMsg();
        byte[] payload=new byte[in.readInt()];
        in.readBytes(payload);
        keyExchangeReqMsg.setPayLoad(payload);
        byte[] keyEnc=new byte[in.readInt()];
        in.readBytes(keyEnc);
        keyExchangeReqMsg.setAesKeyEnc(keyEnc);
        return keyExchangeReqMsg;
    }
}
