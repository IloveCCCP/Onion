package directory;

import coder.Decoder;
import coder.DirectoryMsgDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import msg.DirectoryMsg;
import msg.Msg;
import msg.MsgType;

import java.util.List;

public class MsgDecoder extends ByteToMessageDecoder {
    Decoder decoder;

    Msg msg;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        int type= in.readInt();
        if(MsgType.REGISTER.getValue()==type){
            decoder=new DirectoryMsgDecoder();
            msg=decoder.decode(in);

        }

//        DirectoryMsg data = new DirectoryMsg();
//        int length=in.readInt();
//        byte[] msgByte=new byte[length];
//        in.readBytes(msgByte);
//        String json= new String(msgByte);
//        ByteBuf msgBuf=in.readBytes(in.readInt());
////        msgBuf.readBytes()
////        String json=new String( msgBuf.());
//        data.setPort(in.readInt());
//        int strLen = in.readInt();
        //data.setIp(in.readCharSequence(strLen, charset).toString());
        out.add(msg);
    }
}
