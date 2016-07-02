package nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TimeServer {

	public void bind(int port) throws Exception {
		// ���÷���˵�NIO�߳���
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
					.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChildChannelHandler());

			// �󶨶˿ڣ�ͬ���ȴ��ɹ�
			ChannelFuture f = b.bind(port).sync();

			// �ȴ�����˼����˿ڹر�
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// �����˳����ͷ��̳߳���Դ
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			// TODO Auto-generated method stub
			// arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
			// arg0.pipeline().addLast(new StringDecoder());
			// arg0.pipeline().addLast(new TimeServerHandler());

			// ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
			// arg0.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,
			// delimiter));
			// arg0.pipeline().addLast(new StringDecoder());
			// arg0.pipeline().addLast(new TimeServerHandler());

			arg0.pipeline().addLast(new FixedLengthFrameDecoder(20));
			arg0.pipeline().addLast(new StringDecoder());
			arg0.pipeline().addLast(new TimeServerHandler());
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int port = 8090;
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		}

		new TimeServer().bind(port);
	}

}
