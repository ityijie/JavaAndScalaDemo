package netty;



import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import static org.jboss.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by Administrator on 2016/10/31.
 */
public class HelloServer {
    public  static void main (String args[]){
        // Server服务启动器

        ServerBootstrap bootstrap = new ServerBootstrap(

                new NioServerSocketChannelFactory(

                        Executors.newCachedThreadPool(),

                        Executors.newCachedThreadPool()
                )
        );

        //设置网络参数
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.setOption("child.receiveBufferSize", 10485760);
        bootstrap.setOption("reuseAddress", true);
        bootstrap.setOption("child.connectTimeoutMillis", 10000);
        // 设置一个处理客户端消息和各种消息事件的类(Handler)

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override

            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new HelloServerHandler());

            }

        });

        // 开放8000端口供客户端访问。

        bootstrap.bind(new InetSocketAddress(9999));

    }


    private static class HelloServerHandler extends SimpleChannelHandler {



        /**

         * 当有客户端绑定到服务端的时候触发，打印"Hello world, I'm server."

         *

         * @alia OneCoder

         * @author lihzh

         */

        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("有一个Client连接");

        }

        /**
         * 有消息到的时候触发
         * @param ctx
         * @param e
         * @throws Exception
         */
        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
            System.out.println(buffer.toString(Charset.defaultCharset()));
            //结果返回.
            String ceshi = "server: 已看到你连接:一切顺利";
            e.getChannel().write(ChannelBuffers.wrappedBuffer(ceshi.getBytes()));
        }
    }
}
