package by.vita02.backend;

import by.vita02.backend.service.ManagerService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Popa {
  private static ServerSocket serverSocket;
  private static Socket clientSocket;

  public static void main(String[] args) {
    System.out.println("Старт сервера...");
    try {
      serverSocket = new ServerSocket(readPortFromConfig());
      while (true) {
        clientSocket = serverSocket.accept();
        System.out.println("Новое подключение. IP: " + clientSocket.getInetAddress());
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

  static int readPortFromConfig() {
    Path path = Paths.get("../resources/serverConfig.txt");
    try {
      List<String> lines = Files.lines(path).toList();
      String portLine = lines.get(0);
      String[] separatedLine = portLine.split("\\s+");
      return Integer.parseInt(separatedLine[1]);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return -1;
  }
}
