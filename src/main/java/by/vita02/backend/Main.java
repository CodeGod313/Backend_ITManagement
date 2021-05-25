package by.vita02.backend;

import by.vita02.backend.order.Order;
import by.vita02.backend.service.ManagerService;
import by.vita02.backend.users.Client;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  private static ServerSocket serverSocket;
  private static Socket clientSocket;

  public static void main(String[] args) {
    try {
      serverSocket = new ServerSocket(8080);
      while (true) {
        clientSocket = serverSocket.accept();
        Thread query =
            new Thread(
                () -> {
                  ManagerService managerService = new ManagerService();
                  managerService.start(clientSocket);
                },
                "Wow");
        query.start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
