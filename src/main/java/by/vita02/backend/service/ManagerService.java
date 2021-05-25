package by.vita02.backend.service;

import by.vita02.backend.dao.ClientDao;
import by.vita02.backend.dao.OrderDao;
import by.vita02.backend.dto.QueryDTO;
import by.vita02.backend.dto.MatrixDTO;
import by.vita02.backend.enums.ProjectType;
import by.vita02.backend.order.Order;
import by.vita02.backend.result.ITProject;
import by.vita02.backend.users.Client;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.List;

public class ManagerService {
  public void start(Socket socket) {
    try {
      BufferedWriter bufferedWriter =
          new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      bufferedWriter.flush();
      BufferedReader bufferedReader =
          new BufferedReader(new InputStreamReader(socket.getInputStream()));
      while (true) handle(bufferedWriter, bufferedReader);
    } catch (Exception e) {
      System.out.println("Errors with connection");
    }
  }

  private void handle(BufferedWriter bufferedWriter, BufferedReader bufferedReader)
      throws IOException {
    ClientDao clientDao = new ClientDao();
    OrderDao orderDao = new OrderDao();
    Gson gson = new Gson();
    String query = bufferedReader.readLine();
    QueryDTO queryDTO = gson.fromJson(query, QueryDTO.class);
    switch (queryDTO.getQuery()) {
      case ("signIn"):
        {
          String nickName = bufferedReader.readLine();
          String password = bufferedReader.readLine();
          Client client = clientDao.findByNick(nickName);
          if (client != null && client.getPassword().equals(password)) {
            bufferedWriter.write(gson.toJson(client) + "\n");
          } else {
            bufferedWriter.write("refuse" + "\n");
          }
          bufferedWriter.flush();
        }
        break;
      case ("saveClient"):
        {
          Client client = gson.fromJson(bufferedReader.readLine(), Client.class);
          client.setOrders(null);
          clientDao.save(client);
        }
        break;
      case ("updateClient"):
        {
          Client client = gson.fromJson(bufferedReader.readLine(), Client.class);
          clientDao.update(client);
        }
        break;
      case ("updateClientDate"):
        {
          Client client = gson.fromJson(bufferedReader.readLine(), Client.class);
          client.getOrders().get(client.getOrders().size() - 1).setDate(new Date());
          clientDao.update(client);
        }
        break;

      case ("methodCount"):
        {
          MatrixDTO matrixDTO = gson.fromJson(bufferedReader.readLine(), MatrixDTO.class);
          MethodRankService methodRankService = new MethodRankService();
          ProjectType projectType = methodRankService.calculate(matrixDTO.getMatrix());
          ITProject itProject = new ITProject();
          itProject.setProjectType(projectType);
          bufferedWriter.write(gson.toJson(itProject) + "\n");
          bufferedWriter.flush();
        }
        break;
      case ("findById"):
        {
          Client client = clientDao.findById(queryDTO.getClientID());
          bufferedWriter.write(gson.toJson(client) + "\n");
          bufferedWriter.flush();
        }
        break;
      case ("exists"):
        {
          String nickName = bufferedReader.readLine();
          Client client = clientDao.findByNick(nickName);
          if (client != null) {
            bufferedWriter.write("YES" + "\n");
          } else {
            bufferedWriter.write("NO" + "\n");
          }
          bufferedWriter.flush();
        }
        break;
      case ("getAllOrders"):{
        List<Order> orders = orderDao.getAll();
        bufferedWriter.write(gson.toJson(orders) + "\n");
        bufferedWriter.flush();
      }
    }
  }
}
