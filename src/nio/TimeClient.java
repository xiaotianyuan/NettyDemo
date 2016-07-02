package nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClient {

	public void connect(int port, String host) throws Exception {
		// ���ÿͻ���NIO�߳���
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
//							ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
//							ch.pipeline().addLast(new StringDecoder());
//							ch.pipeline().addLast(new TimeClientHandler());
							
							ByteBuf delimiter = Unpooled.copiedBuffer("$_"
								    .getBytes());
							    ch.pipeline().addLast(
								    new DelimiterBasedFrameDecoder(1024,
									    delimiter));
							    ch.pipeline().addLast(new StringDecoder());
							    ch.pipeline().addLast(new TimeClientHandler());
						}
					});

			// �����첽���Ӳ���
			ChannelFuture f = b.connect(host, port).sync();

			// �����ͻ�����·�ر�
			f.channel().closeFuture().sync();
		} finally {
			// �����˳����ͷ�NIO�߳���
			group.shutdownGracefully();
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int port = 8090;
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				// ����Ĭ��ֵ
			}
		}
		new TimeClient().connect(port, "127.0.0.1");
	}

}
