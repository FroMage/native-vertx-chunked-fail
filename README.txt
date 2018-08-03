How to reproduce:

- mvn clean install
- native-image --report-unsupported-elements-at-runtime -H:ReflectionConfigurationFiles=.../native-vertx-chunked-fail/reflect.json \
  -Dio.netty.noUnsafe=true -H:EnableURLProtocols=http -Djava.net.preferIPv4Stack=true \
  -jar .../native-vertx-chunked-fail/target/native-vertx-chunked-fail-0.0.1-SNAPSHOT.jar
- ./native-vertx-chunked-fail-0.0.1-SNAPSHOT

Load http://localhost:9000/

Observe the exception:

```
Aug 03, 2018 4:52:08 PM io.vertx.core.net.impl.ConnectionBase
SEVERE: Unhandled exception
java.io.IOException: Bad address
    at java.lang.Throwable.<init>(Throwable.java:265)
    at java.lang.Exception.<init>(Exception.java:66)
    at java.io.IOException.<init>(IOException.java:58)
    at com.oracle.svm.core.posix.PosixJavaNIOSubstitutions.throwIOExceptionWithLastError(PosixJavaNIOSubstitutions.java:236)
    at com.oracle.svm.core.posix.PosixJavaNIOSubstitutions.convertLongReturnVal(PosixJavaNIOSubstitutions.java:304)
    at com.oracle.svm.core.posix.PosixJavaNIOSubstitutions.convertLongReturnVal(PosixJavaNIOSubstitutions.java:285)
    at com.oracle.svm.core.posix.PosixJavaNIOSubstitutions$Target_sun_nio_ch_FileDispatcherImpl.writev0(PosixJavaNIOSubstitutions.java:1401)
    at sun.nio.ch.SocketDispatcher.writev(SocketDispatcher.java:51)
    at sun.nio.ch.IOUtil.write(IOUtil.java:148)
    at sun.nio.ch.SocketChannelImpl.write(SocketChannelImpl.java:504)
    at io.netty.channel.socket.nio.NioSocketChannel.doWrite(NioSocketChannel.java:418)
    at io.netty.channel.AbstractChannel$AbstractUnsafe.flush0(AbstractChannel.java:934)
    at io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe.flush0(AbstractNioChannel.java:362)
    at io.netty.channel.AbstractChannel$AbstractUnsafe.flush(AbstractChannel.java:901)
    at io.netty.channel.DefaultChannelPipeline$HeadContext.flush(DefaultChannelPipeline.java:1321)
    at io.netty.channel.AbstractChannelHandlerContext.invokeFlush0(AbstractChannelHandlerContext.java:776)
    at io.netty.channel.AbstractChannelHandlerContext.invokeFlush(AbstractChannelHandlerContext.java:768)
    at io.netty.channel.AbstractChannelHandlerContext.flush(AbstractChannelHandlerContext.java:749)
    at io.netty.channel.ChannelOutboundHandlerAdapter.flush(ChannelOutboundHandlerAdapter.java:115)
    at io.netty.channel.AbstractChannelHandlerContext.invokeFlush0(AbstractChannelHandlerContext.java:776)
    at io.netty.channel.AbstractChannelHandlerContext.invokeFlush(AbstractChannelHandlerContext.java:768)
    at io.netty.channel.AbstractChannelHandlerContext.flush(AbstractChannelHandlerContext.java:749)
    at io.vertx.core.net.impl.ConnectionBase.endReadAndFlush(ConnectionBase.java:83)
    at io.vertx.core.net.impl.VertxHandler$$Lambda$632/1507352827.run(Unknown Source)
    at io.vertx.core.impl.ContextImpl.lambda$wrapTask$2(ContextImpl.java:337)
    at io.vertx.core.impl.ContextImpl$$Lambda$520/2004466600.run(Unknown Source)
    at io.vertx.core.impl.ContextImpl.executeFromIO(ContextImpl.java:195)
    at io.vertx.core.net.impl.VertxHandler.channelReadComplete(VertxHandler.java:136)
    at io.netty.channel.AbstractChannelHandlerContext.invokeChannelReadComplete(AbstractChannelHandlerContext.java:398)
    at io.netty.channel.AbstractChannelHandlerContext.invokeChannelReadComplete(AbstractChannelHandlerContext.java:380)
    at io.netty.channel.AbstractChannelHandlerContext.fireChannelReadComplete(AbstractChannelHandlerContext.java:373)
    at io.netty.handler.codec.ByteToMessageDecoder.channelReadComplete(ByteToMessageDecoder.java:324)
    at io.netty.channel.AbstractChannelHandlerContext.invokeChannelReadComplete(AbstractChannelHandlerContext.java:398)
    at io.netty.channel.AbstractChannelHandlerContext.invokeChannelReadComplete(AbstractChannelHandlerContext.java:380)
    at io.netty.channel.AbstractChannelHandlerContext.fireChannelReadComplete(AbstractChannelHandlerContext.java:373)
    at io.netty.channel.DefaultChannelPipeline$HeadContext.channelReadComplete(DefaultChannelPipeline.java:1364)
    at io.netty.channel.AbstractChannelHandlerContext.invokeChannelReadComplete(AbstractChannelHandlerContext.java:398)
    at io.netty.channel.AbstractChannelHandlerContext.invokeChannelReadComplete(AbstractChannelHandlerContext.java:380)
    at io.netty.channel.DefaultChannelPipeline.fireChannelReadComplete(DefaultChannelPipeline.java:941)
    at io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe.read(AbstractNioByteChannel.java:146)
    at io.netty.channel.nio.NioEventLoop.processSelectedKey(NioEventLoop.java:645)
    at io.netty.channel.nio.NioEventLoop.processSelectedKeysPlain(NioEventLoop.java:545)
    at io.netty.channel.nio.NioEventLoop.processSelectedKeys(NioEventLoop.java:499)
    at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:459)
    at io.netty.util.concurrent.SingleThreadEventExecutor$5.run(SingleThreadEventExecutor.java:886)
    at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
    at java.lang.Thread.run(Thread.java:748)
    at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:238)

```