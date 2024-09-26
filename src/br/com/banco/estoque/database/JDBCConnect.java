package br.com.banco.estoque.database;

import br.com.banco.estoque.entity.Produto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.Properties;

public class JDBCConnect {

  private static String URL = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=123&ssl=false";

  private static Connection createConnection() {
    try {
//      String url = "jdbc:postgresql://localhost:5432/postgres";
//      Properties props = new Properties();
//      props.setProperty("user", "postgres");
//      props.setProperty("password", "123");
//      props.setProperty("ssl", "false");
//      Connection conn = DriverManager.getConnection(url, props);


      Connection conn = DriverManager.getConnection(URL);

      if (conn.isValid(1500)) {
        System.out.println("Conectado!!");
      }

      return conn;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static ResultSet executeQuery(String query, Integer codigo) {
    try {
      Connection connection = createConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      if (codigo != null) {
        preparedStatement.setInt(1, codigo);
      }

      ResultSet resultSet = preparedStatement.executeQuery();

      connection.close();

      return resultSet;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static boolean executeUpdate(String update, Produto produto) {
    try {
      Connection connection = createConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(update);

      preparedStatement.setString(1, produto.getNome());
      preparedStatement.setInt(2, produto.getCodigo());
      preparedStatement.setLong(3, produto.getPreco());
      preparedStatement.setInt(4, produto.getQuantidade());
      preparedStatement.setString(5, produto.getDescricao());

      int affectRows = preparedStatement.executeUpdate();

      connection.close();

      return affectRows > 0;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean executeDelete(String delete, Long id) {
    try {
      Connection connection = createConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(delete);

      preparedStatement.setLong(1, id);

      int deleteRows = preparedStatement.executeUpdate();

      connection.close();

      return deleteRows > 0;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean executeEditar(String editar, Produto produto) {
    try {
      Connection connection = createConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(editar);

      preparedStatement.setString(1, produto.getNome());
      preparedStatement.setInt(2, produto.getCodigo());
      preparedStatement.setDouble(3, produto.getPreco());
      preparedStatement.setInt(4, produto.getQuantidade());
      preparedStatement.setString(5, produto.getDescricao());

      preparedStatement.setLong(6, produto.getId());


      int affectRows = preparedStatement.executeUpdate();

      connection.close();

      return affectRows > 0;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}


