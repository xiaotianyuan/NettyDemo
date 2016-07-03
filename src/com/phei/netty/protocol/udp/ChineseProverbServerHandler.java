/*
 * Copyright 2013-2018 Lilinfeng.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phei.netty.protocol.udp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.ThreadLocalRandom;

/**
 * @author lilinfeng
 * @date 2014��2��14��
 * @version 1.0
 */
public class ChineseProverbServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

	// �����б�
	private static final String[] DICTIONARY = { "ֻҪ���������ĥ���롣", "��ʱ��л��ǰ�࣬����Ѱ�����ռҡ�", "�������������ʣ�һƬ�����������",
			"һ�����һ��𣬴������������", "����������־��ǧ���ʿĺ�꣬׳�Ĳ���!" };

	private String nextQuote() {
		int quoteId = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
		return DICTIONARY[quoteId];
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
		String req = packet.content().toString(CharsetUtil.UTF_8);
		System.out.println(req);
		if ("�����ֵ��ѯ?".equals(req)) {
			ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("�����ѯ���: " + nextQuote(), CharsetUtil.UTF_8),
					packet.sender()));
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		cause.printStackTrace();
	}
}
