package com.feldjoshuanoah.basketballstats.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The main client that handles connections to multiple clients.
 */
public class BasketballStatsServer {

    /**
     * The logger used for logging messages and exceptions to the console.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger("Server");

    /**
     * The socket that waits for requests to come in over the network.
     */
    private ServerSocket serverSocket;

    /**
     * Creates a new server instance. The underlying server socket is bound to
     * the specified port. A port number of {@code 0} means that the port number
     * is automatically allocated, typically from an ephemeral port range.
     *
     * @param port The port number, or {@code 0} to use a port number that is
     *             automatically allocated.
     */
    public BasketballStatsServer(final int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (final IOException exception) {
            LOGGER.error("Failed to create the server socket.", exception);
        }
        // Starts the thread that listens for new connections to come in over
        // the server socket and accepts them.
        new Thread(new Runnable() {
            public void run() {
                while (!serverSocket.isClosed()) {
                    try {
                        Socket socket = serverSocket.accept();
                    } catch (final IOException exception) {
                        LOGGER.error("Failed to accept a connection.",
                                exception);
                    }
                }
            }
        }).start();
    }
}
