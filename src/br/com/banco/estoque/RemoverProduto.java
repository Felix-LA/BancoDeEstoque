package br.com.banco.estoque;

import br.com.banco.estoque.database.ProdutoDao;
import br.com.banco.estoque.entity.Produto;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RemoverProduto {

  ProdutoDao produtoDao = new ProdutoDao();


  Scanner sc = new Scanner(System.in);


  public void removerProduto() {

  List<Produto> produtos = produtoDao.buscarProdutos(null);

    Integer indice = null;

    System.out.println("Informe o ID do Produto a ser Removido");
    String remover = sc.nextLine();

    if (remover == null || remover.isEmpty()) {
      System.out.println("Favor informar um Id. \n");
      removerProduto();
      return;
    }

    if (Pattern.compile("\\D").matcher(remover).find()) {
      System.out.println("Favor informar um Id valido.\n");
      removerProduto();
      return;
    }

    Long removerProduto = Long.parseLong(remover);

    for (int index = 0; index < produtos.size(); index++) {
      if (removerProduto.equals(produtos.get(index).getId())) {
        indice = index;
        break;
      }
    }


    if (indice == null) {
      System.out.println("Produto Nao encontrado");
    } else {
      produtoDao.deleteProduto(removerProduto);
      System.out.println("Produto Removido");
    }
  }
}
