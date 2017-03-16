package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Дмитрий on 01.01.2017.
 */
public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args)
    {
        ConsoleHelper.writeMessage("Input servers port: ");
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port);)
        {
            ConsoleHelper.writeMessage("Server start...");
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Socket error.");
        }

    }

    public static void sendBroadcastMessage(Message message) {
        try
        {
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
                pair.getValue().send(message);
            }
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Error send message.");
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run()
        {
            String userName = "";
            ConsoleHelper.writeMessage("New connection with " + socket.getRemoteSocketAddress());
            try (Connection connection = new Connection(socket);)
            {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            }
            catch (IOException e){
                ConsoleHelper.writeMessage("Error data transfer.");
            }
            catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Class not found.");
            }
            finally
            {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                ConsoleHelper.writeMessage("Connection close.");
            }

        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true)
            {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message != null && message.getType() != null && message.getType() == MessageType.USER_NAME)
                {
                    String userName = message.getData();
                    if (userName != null && userName != "")
                    {
                        if (!connectionMap.containsKey(userName))
                        {
                            connectionMap.put(userName, connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return userName;
                        }

                    }
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
                if (!userName.equals(pair.getKey()))
                    connection.send(new Message(MessageType.USER_ADDED, pair.getKey()));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message message = connection.receive();
                if (message.getType() != MessageType.TEXT)
                    ConsoleHelper.writeMessage("Error message.");
                else
                {
                    String messageText = userName + ": " + message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, messageText));
                }
            }
        }
    }
}
