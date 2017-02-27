package netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/10/31.
 */
public class HelloClient {
    public static void main(String args[]) {

        // Client服务启动器

        ClientBootstrap bootstrap = new ClientBootstrap(

                new NioClientSocketChannelFactory(

                        Executors.newCachedThreadPool(),

                        Executors.newCachedThreadPool()));

        // 设置一个处理服务端消息和各种消息事件的类(Handler)

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override

            public ChannelPipeline getPipeline() throws Exception {

                return Channels.pipeline(new HelloClientHandler());

            }

        });

        // 连接到本地的8000端口的服务端

        bootstrap.connect(new InetSocketAddress(

                "127.0.0.1", 9999));

    }



    private static class HelloClientHandler extends SimpleChannelHandler {



        /**

         * 当绑定到服务端的时候触发，打印"Hello world, I'm client."

         *

         * @alia OneCoder

         * @author lihzh

         */

        @Override

        public void channelConnected(ChannelHandlerContext ctx,

                                     ChannelStateEvent e) {
            String s = "client : Hello," +
                    " I'm client.";
            ChannelBuffer buffer = ChannelBuffers.buffer(s.length());
            buffer.writeBytes(s.getBytes());
            e.getChannel().write(buffer);
        }

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
            System.out.println("收到一个信息");
            System.out.println(buffer.toString(Charset.defaultCharset()));
        }
    }
}
