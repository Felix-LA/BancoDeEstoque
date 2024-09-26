package br.com.banco.estoque;

import br.com.banco.estoque.database.ProdutoDao;
import br.com.banco.estoque.entity.Produto;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditarProduto {
  ProdutoDao produtoDao = new ProdutoDao();

  Scanner sc = new Scanner(System.in);

  public void editarProduto() {

    List<Produto> produtos = produtoDao.buscarProdutos(null);

    System.out.println("Informe o ID do produto: ");

    String IdAProcurar = sc.nextLine();

    if (IdAProcurar == null || IdAProcurar.isEmpty()) {
      return;
    }

    if (Pattern.compile("\\D").matcher(IdAProcurar).find()) {
      System.out.println("Favor informar um ID valido.");
      editarProduto();
      return;
    }
    final Long procurarProduto = Long.parseLong(IdAProcurar);

    Produto produtoSelecionado = null;

    for (int index = 0; index < produtos.size(); index++) {
      Produto produtoCorrente = produtos.get(index);
      if (procurarProduto.equals(produtoCorrente.getId())) {
        produtoSelecionado = produtoCorrente;
        break;
      }
    }

    if (produtoSelecionado == null) {
      System.out.println("Produto de id " + procurarProduto + " não encontrado.");
      return;
    }

    System.out.println("Informe o Nome: ");
    String novoNome = sc.nextLine();

    System.out.println("Informe o codigo");
    String novoCodigo = sc.nextLine();

    System.out.println("Informe o Preço");
    String novoPreco = sc.nextLine();

    System.out.println("Informe a Quantidade");
    String novaQuantidade = sc.nextLine();

    System.out.println("Informe a Descrição");
    String novaDescricao = sc.nextLine();

    final String erro = validarProdutoEditar(novoNome, novoCodigo, novoPreco, novaQuantidade, novaDescricao);

    if (!erro.isEmpty()) {
      System.out.println(erro);
      editarProduto();

      return;
    }

    produtoSelecionado.setNome(novoNome);
    produtoSelecionado.setCodigo(Integer.parseInt(novoCodigo));
    produtoSelecionado.setPreco(Long.parseLong(novoPreco));
    produtoSelecionado.setQuantidade(Integer.parseInt(novaQuantidade));
    produtoSelecionado.setDescricao(novaDescricao);

    boolean sucesso = produtoDao.editarProduto(produtoSelecionado);

    if (sucesso) {
      System.out.println("Produto editado com sucesso!");
    } else {
      System.out.println("Não foi possível editar o produto");
      System.out.println("Favor entrar em contato com o Suporte");
    }
  }

  private String validarProdutoEditar(final String novoNome, final String novoCodigo, final String novoPreco,
                                      final String novaQuantidade, final String novaDescricao) {

    final Pattern nonNumericPattern = Pattern.compile("\\D");
    Matcher matcher;
    String erro = "";

    if (novoCodigo == null || nonNumericPattern.matcher(novoCodigo).find()) {
      erro += "O código precisa ser inteiramente numérico. Favor tente novamente.\n";
    }

    if (novoPreco == null || nonNumericPattern.matcher(novoPreco).find()) {
      erro += "O preço precisa ser inteiramente numérico. Favor tente novamente.\n";
    }

    if (novaQuantidade == null || nonNumericPattern.matcher(novaQuantidade).find()) {
      erro += "A quantidade precisa ser inteiramente numérico. Favor tente novamente.\n";
    }

    return erro;

  }
}
