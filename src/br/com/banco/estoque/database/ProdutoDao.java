package br.com.banco.estoque.database;

import br.com.banco.estoque.entity.Produto;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao {

  public List<Produto> buscarProdutos(Integer codigo) {
    try {
      String query = "select * from produto";
      if (codigo != null) {
        query = query + " where codigo = ?";
      }

      ResultSet resultSet = JDBCConnect.executeQuery(query, codigo);

      List<Produto> produtos = new ArrayList<>();

      while (resultSet.next()) {
        Produto p = new Produto();
        p.setId(resultSet.getLong("id"));
        p.setNome(resultSet.getString("nome"));
        p.setCodigo(resultSet.getInt("codigo"));
        p.setPreco(resultSet.getLong("preco"));
        p.setQuantidade(resultSet.getInt("quantidade"));
        p.setDescricao(resultSet.getString("descricao"));
        produtos.add(p);
      }

      return produtos;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public boolean insertProduto(Produto novoProduto) {

    try {
      String update = "insert into produto(nome,codigo,preco,quantidade,descricao)"
              + "values(?,?,?,?,?)";

      boolean sucesso = JDBCConnect.executeUpdate(update, novoProduto);

      return sucesso;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean deleteProduto(Long id) {
    try {
      String delete = "delete from produto "
              + "where id = ?";

      boolean sucesso = JDBCConnect.executeDelete(delete, id);

      return sucesso;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean editarProduto(Produto produto) {
    try {
      String editar = "update produto "
              + "set nome = ?, " +
              "codigo = ?, " +
              "preco = ?, " +
              "quantidade = ?, " +
              "descricao = ? "
              + "where id = ?";

      boolean sucesso = JDBCConnect.executeEditar(editar, produto);


      return sucesso;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
