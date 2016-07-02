package nio;

import java.util.logging.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

//	private final ByteBuf firstMessage;

    private int counter;

    private byte[] req;
    
    static final String ECHO_REQ = "Hi, Lilinfeng. Welcome to Netty.$_";
    
	/**
	 * Creates a client-side handler.
	 */
	public TimeClientHandler() {
//		byte[] req = "QUERY TIME ORDER".getBytes();
//		firstMessage = Unpooled.buffer(req.length);
//		firstMessage.writeBytes(req);
		
//		req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
//		ctx.writeAndFlush(firstMessage);
		
//		ByteBuf message = null;
//		for (int i = 0; i < 1000; i++) {
//		    message = Unpooled.buffer(req.length);
//		    message.writeBytes(req);
//		    ctx.writeAndFlush(message);
//		}
		
		for (int i = 0; i < 1; i++) {
		    ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
//		ByteBuf buf = (ByteBuf) msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
//		String body = new String(req, "UTF-8");
//		System.out.println("Now is : " + body);
		
//		String body = (String) msg;
//		System.out.println("Now is : " + body + " ; the counter is : "
//			+ ++counter);
		
		System.out.println("This is " + ++counter + " times receive server : ["
				+ msg + "]");
	}

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	ctx.flush();
    }

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		// ÊÍ·Å×ÊÔ´
		logger.warning("Unexpected exception from downstream : " + cause.getMessage());
		ctx.close();
	}
}
