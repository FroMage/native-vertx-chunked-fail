package org.nat.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	Vertx vertx = Vertx.vertx();
    	vertx.createHttpServer().requestHandler(req -> {
    		req.response().setChunked(true);
    		req.response().write(Buffer.buffer("Hi from buffer 2"));
    		req.response().end();
    	}).listen(9000);
    }
}
