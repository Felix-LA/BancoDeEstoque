package br.com.banco.estoque;

import br.com.banco.estoque.database.ProdutoDao;
import br.com.banco.estoque.entity.Produto;

import java.util.List;

public class VisualizarProduto {

  public void VisualizarProdutos() {

    ProdutoDao produtoDao = new ProdutoDao();
    List<Produto> produtos = produtoDao.buscarProdutos(null);

    System.out.printf("| %-20s | %-15s | %-15s | %-15s | %-15s | %-20s|%n",
            "Nome", "Id", "Código", "Preço", "Quantidade", "Descrição");

    for (int index = 0; index < produtos.size(); index++) {

      String nomeImprimir = produtos.get(index).getNome();
      String descricaoImprimir = produtos.get(index).getDescricao();

      if (nomeImprimir != null && nomeImprimir.length() > 20) {
        nomeImprimir = nomeImprimir.substring(0, 17) + "...";
      }

      if (descricaoImprimir != null && descricaoImprimir.length() > 20) {
        descricaoImprimir = descricaoImprimir.substring(0, 17) + "...";
      }

      System.out.printf("| %-20s | %-15s | %-15s | %-15s | %-15s | %-20s|%n",
              nomeImprimir,
              produtos.get(index).getId(),
              produtos.get(index).getCodigo(),
              produtos.get(index).getPreco(),
              produtos.get(index).getQuantidade(),
              descricaoImprimir
      );
    }
  }
}
