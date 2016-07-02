package nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	private int counter = 0;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		ByteBuf buf = (ByteBuf) msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
//		String body = new String(req, "UTF-8");
//		System.out.println("The time server receive order : " + body);
//		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
//				? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
//		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
//		ctx.write(resp);
		
//		// ½â¾öÕ³°ü
//		String body = (String) msg;
//		System.out.println("The time server receive order : " + body
//			+ " ; the counter is : " + ++counter);
//		String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(
//			System.currentTimeMillis()).toString() : "BAD ORDER";
//		currentTime = currentTime + System.getProperty("line.separator");
//		ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
//		ctx.writeAndFlush(resp);
		
//		String body = (String) msg;
//		System.out.println("This is " + ++counter + " times receive client : ["
//			+ body + "]");
//		body += "$_";
//		ByteBuf echo = Unpooled.copiedBuffer(body.getBytes());
//		ctx.writeAndFlush(echo);
		
		System.out.println("Receive client : [" + msg + "]");
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
