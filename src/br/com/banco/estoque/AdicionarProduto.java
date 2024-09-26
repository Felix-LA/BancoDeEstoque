package br.com.banco.estoque;

import br.com.banco.estoque.database.ProdutoDao;
import br.com.banco.estoque.entity.Produto;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AdicionarProduto {

  Scanner sc = new Scanner(System.in);

  ProdutoDao produtoDao = new ProdutoDao();

  public void adicionarProduto() {

    Produto novoProduto = new Produto();

    System.out.println("Informe o nome do produto");
    String nome = sc.nextLine();

    System.out.println("Informe o codigo do produto");
    String codigo = sc.nextLine();

    System.out.println("Informe a quantidade de produtos");
    String quantidade = sc.nextLine();

    System.out.println("Informe o Preço do produto");
    String preco = sc.nextLine();

    System.out.println("Informe a Descrição do produto");
    String descricao = sc.nextLine();

    final String erro = validarProdutoAdicionar(nome, codigo, quantidade, preco, descricao);

    if (!erro.isEmpty()) {
      System.out.println(erro);
      adicionarProduto();
      return;
    }
    novoProduto.setNome(nome);
    novoProduto.setCodigo(Integer.parseInt(codigo));
    novoProduto.setQuantidade(Integer.parseInt(quantidade));
    novoProduto.setPreco(Long.parseLong(preco));
    novoProduto.setDescricao(descricao);

    boolean sucesso;

    sucesso = produtoDao.insertProduto(novoProduto);

    if (sucesso) {
      System.out.println("Produto adicionado com Sucesso");
    } else {
      System.out.println("Não foi possível inserir o produto");
      System.out.println("Favor entrar em contato com o Suporte");
    }

  }

  private String validarProdutoAdicionar(final String nome, final String codigo,
                                         final String quantidade, final String preco, final String descricao) {


    final Pattern nonNumericPattern = Pattern.compile("\\D");
    String erro = "";


    if (nome == null || nome.isEmpty()) {
      erro += "Precisa ter um nome. Favor tentar novamente. \n";
    }

    if (codigo == null || nonNumericPattern.matcher(codigo).find()) {
      erro += "O código precisa ser inteiramente numérico. Favor tente novamente.\n";
    } else {
      List<Produto> produtos = produtoDao.buscarProdutos(Integer.parseInt(codigo));
      if (produtos.size() > 0) {
        erro += "Esse Codigo já existe, Favor colocar um Codigo diferente. \n";
      }
    }

    if (preco == null || nonNumericPattern.matcher(preco).find()) {
      erro += "O preço precisa ser inteiramente numérico. Favor tente novamente.\n";
    }

    if (quantidade == null || nonNumericPattern.matcher(quantidade).find()) {
      erro += "A quantidade precisa ser inteiramente numérico. Favor tente novamente.\n";
    }

    if (descricao == null || descricao.isEmpty()) {
      erro += "Precisa ter uma descrição. Favor tentar novamente. \n";
    }

    return erro;

  }
}
