package com.feldjoshuanoah.basketballstats.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * The main client that handles a connection to the server.
 */
public class BasketballStatsClient {

    /**
     * The logger used for logging messages and exceptions to the console.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger("Client");

    /**
     * The endpoint for communication between this client and the server.
     */
    private Socket socket;

    /**
     * The reader for the character-input stream. It buffers characters so as to
     * provide for the efficient reading of characters, arrays, and lines.
     */
    private BufferedReader input;
    /**
     * The writer for the character-output stream. It uses the auto-flush mode,
     * which means that {@code println} will flush the output buffer.
     */
    private PrintWriter output;

    /**
     * Creates a new client instance. The underlying stream socket is created
     * and connected to the specified port number on the named host.
     *
     * @param host The host name, or {@code null} for the loopback address.
     * @param port The port number.
     *
     * @throws IllegalStateException If the creation of the socket fails.
     */
    public BasketballStatsClient(final String host, final int port) {
        try {
            socket = new Socket(host, port);
            input = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (final IOException exception) {
            LOGGER.error("Failed to create the socket.", exception);
        }
    }
}
