package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Administrator on 2016/12/14.
 */
public final class SimpleServer {
    public static void  main(String[] args)  throws Exception{

        //EventLoopGroup --> 死循环,不断的检测IO事件,处理IO事件,执行任务
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务器,启动辅助类,设置端口,管道,中间元组
            ServerBootstrap b = new ServerBootstrap();

            //服务器的分组和管道使用 task -> Channel -> bossHandler(NioEventLoopGroup) -> workerHander(NioEventLoopGroup)
            b.group(bossGroup,workerGroup) //->boss接活,worker干活
                        .channel(NioServerSocketChannel.class) // ->管道 ->8888
                        .handler(new SimpleServerHandler())    // -->bossGroup中间元组,
                        .childHandler(new ChannelInitializer<SocketChannel>() {   //->workerGroup中间元组
                            @Override
                            public void initChannel(SocketChannel ch) throws Exception {
                                //worker logic
                                System.out.print("连接异常");
                            }
                        });

            //绑定端口 -> 真正的启动服务器
            //用户调用方法 Bootstrap.bind(port) 第一步就是通过反射的方式new一个NioServerSocketChannel对象，并且在new的过程中创建了一系列的核心组件，仅此而已
            ChannelFuture f = b.bind(8888).sync();
            //等到服务端关闭socket
            f.channel().closeFuture().sync();
        }catch (Exception e){
            System.out.print("连接异常");
        }finally {
            //关闭两组死循环,测试要关,上线不用关?
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }


    private static class SimpleServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channelActive");
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("channelRegistered");
        }

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            System.out.println("handlerAdded");
        }

    }
}
