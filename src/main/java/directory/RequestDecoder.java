package directory;

import java.nio.charset.Charset;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import msg.DirectoryMsg;

public class RequestDecoder extends ReplayingDecoder<DirectoryMsg> {

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        DirectoryMsg data = new DirectoryMsg();
        int length=in.readInt();
        byte[] msgByte=new byte[length];
        in.readBytes(msgByte);
        String json= new String(msgByte);
        ByteBuf msgBuf=in.readBytes(in.readInt());
//        msgBuf.readBytes()
//        String json=new String( msgBuf.());
        data.setPort(in.readInt());
        int strLen = in.readInt();
        data.setIp(in.readCharSequence(strLen, charset).toString());
        out.add(data);
    }
}