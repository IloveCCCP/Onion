package codec;

import io.netty.buffer.ByteBuf;
import msg.KeyExchangeReqMsg;
import msg.Msg;

public class KeyExchangeRespMsgDecoder extends Decoder{


    @Override
    public Msg decode(ByteBuf in) {
        KeyExchangeReqMsg keyExchangeReqMsg=new KeyExchangeReqMsg();
        byte[] keyEnc=new byte[in.readInt()];
        in.readBytes(keyEnc);
        keyExchangeReqMsg.setKeyEnc(keyEnc);

        return keyExchangeReqMsg;
    }
}
