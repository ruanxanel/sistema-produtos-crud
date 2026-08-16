import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner sc = new Scanner(System.in);
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final int senhaAdm = 123;
    private final String nomeAdm = "Sla";

    public int lerOpcao(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                int op = sc.nextInt();
                sc.nextLine();
                return op;
            } catch (Exception e) {
                System.out.println("Opção invalida, digite apenas números");
                sc.nextLine();
            }
        }
    }

    public void ui() {
        System.out.println("==========================================");
        System.out.println("------------------LOGIN-------------------");
        System.out.println("==========================================");
        System.out.println("1 - ADMIN");
        System.out.println("2 - USUARIO");
        System.out.println("0 - SAIR");
        System.out.println("==========================================");
    }

    public void uiAdm() {
        System.out.println("\n========================================");
        System.out.println("          CONTROLE DE PRODUTOS          ");
        System.out.println("========================================");
        System.out.println("1 - Inserir produto");
        System.out.println("2 - Atualizar produto");
        System.out.println("3 - Deletar produto");
        System.out.println("4 - Listar todos os produtos");
        System.out.println("5 - Buscar Produtos por ID");
        System.out.println("100 - Deletar Dados da tabela");
        System.out.println("0 - Voltar");
        System.out.println("========================================");
    }

    public void uiUsuario() {
        System.out.println("\n========================================");
        System.out.println("        SISTEMA DE PRODUTOS - LOJA       ");
        System.out.println("========================================");
        System.out.println("1 - Listar todos os produtos");
        System.out.println("2 - Buscar Produtos por ID");
        System.out.println("0 - Voltar");
        System.out.println("========================================");
    }

    public void executarLogin(int op) {

        switch (op) {
            case 1:
                System.out.println("\n========================================");
                System.out.println("                 ADMIN                  ");
                System.out.println("========================================");
                System.out.print("Nome (Digite 0 pra sair): ");
                String nome = sc.nextLine();

                if (nome.equals("0")) {
                    System.out.println();
                    break;
                }

                System.out.print("Senha: ");
                int senha = sc.nextInt();
                sc.nextLine();

                if (nome.equals(nomeAdm) && senha == senhaAdm) {
                    sistemaAdm();
                } else {
                    System.out.println("Nome ou senha está incorreto");
                }
                break;

            case 2:
                sistemaUsuario();
                break;

            case 0:
                System.out.println("Saindo....");
                break;

            default:
                System.out.println("Opção invalida");
        }
    }

    public void sistemaAdm() {

        Produto p;
        int op = -1, id;
        while (op != 0) {

            uiAdm();

            System.out.print("Escolha a opção: ");
            op = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (op) {
                case 1:

                    System.out.println("====Cadastro de produto====");
                    System.out.print("Nome do produto: ");
                    String nome = sc.nextLine();

                    System.out.print("Preço do produto: ");
                    double preco = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Estoque do produto: ");
                    int estoque = sc.nextInt();
                    sc.nextLine();

                    Produto produto = new Produto(0, nome, preco, estoque);
                    produtoDAO.inserir(produto);
                    System.out.println("Produto cadastrado com sucesso!");
                    break;

                case 2:

                    System.out.println("====Atualizar produto====");
                    System.out.print("Id do produto: ");
                    id = sc.nextInt();
                    sc.nextLine();

                    p = produtoDAO.buscarPorId(id);

                    System.out.print("Novo nome do : ");
                    String newName = sc.nextLine();
                    p.setNome(newName);

                    System.out.print("Novo preço do produto: ");
                    double newPreco = sc.nextDouble();
                    sc.nextLine();
                    p.setPreco(newPreco);

                    System.out.print("Nova quantidade do produto: ");
                    int newQtd = sc.nextInt();
                    sc.nextLine();
                    p.setEstoque(newQtd);

                    produtoDAO.atualizar(p);
                    System.out.println("Produto atualizado com sucesso");
                    break;

                case 3:

                    System.out.println("====Deletar produto====");
                    System.out.print("Digite o Id do produto: ");
                    int idDeletar = sc.nextInt();
                    sc.nextLine();

                    produtoDAO.deletar(idDeletar);
                    System.out.println("Produto deletado com sucesso");
                    break;

                case 4:

                    List<Produto> produtos = produtoDAO.listarTodos();
                    System.out.printf("%-5s %-20s %-10s %-10s%n", "ID", "NOME", "PRECO", "ESTOQUE");

                    for (Produto i : produtos) {
                        System.out.printf("%-5d %-20s %-10.2f %-10d%n", i.getId(), i.getNome(), i.getPreco(), i.getEstoque());
                    }
                    break;

                case 5:

                    System.out.print("Id do produto: ");
                    id = sc.nextInt();
                    sc.nextLine();

                    p = produtoDAO.buscarPorId(id);

                    if (p != null) {

                        System.out.printf("%-5s %-20s %-10s %-10s%n", "ID", "NOME", "PRECO", "ESTOQUE");
                        System.out.printf("%-5d %-20s %-10.2f %-10d%n", p.getId(), p.getNome(), p.getPreco(), p.getEstoque());

                    } else {
                        System.out.println("Produto não encontrado");
                    }
                    break;

                case 100:

                    System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println("!!            AVISO DE SEGURANÇA        !!");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println("Você está prestes a APAGAR TODOS os produtos");
                    System.out.println("da tabela. Essa ação NÃO PODE SER DESFEITA!");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.print("Digite CONFIRMAR para prosseguir, ou qualquer outra coisa para cancelar: ");

                    String confirmacao = sc.nextLine();

                    if (confirmacao.equals("CONFIRMAR")) {

                        System.out.print("Digite o nome: ");
                        String nomeADM = sc.nextLine();

                        System.out.print("Digite a senha: ");
                        int senha = sc.nextInt();
                        sc.nextLine();

                        if (nomeADM.equals(nomeAdm) && senha == senhaAdm) {
                            produtoDAO.deletarTudo();
                            System.out.println("Dados apagado com sucesso");
                        } else {
                            System.out.println("Nome ou Senha incorreto(a)");
                        }
                    } else {
                        System.out.println("Operação cancelada. Nenhum dado foi alterado.");
                    }
                    break;
                case 0:
                    System.out.println("Voltando....");
                    break;

                default:
                    System.out.println("Opção invalida");
                    break;
            }
        }
    }

    public void sistemaUsuario() {

        int op = -1;
        while (op != 0) {

            uiUsuario();
            System.out.print("Escolha a opção: ");
            op = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (op) {
                case 1:

                    List<Produto> produtos = produtoDAO.listarTodos();
                    System.out.printf("%-5s %-20s %-10s %-10s%n", "ID", "NOME", "PRECO", "ESTOQUE");

                    for (Produto p : produtos) {
                        System.out.printf("%-5d %-20s %-10.2f %-10d%n", p.getId(), p.getNome(), p.getPreco(), p.getEstoque());
                    }
                    break;

                case 2:

                    System.out.print("Id do produto: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Produto p = produtoDAO.buscarPorId(id);

                    if (p != null) {
                        System.out.printf("%-5s %-20s %-10s %-10s%n", "ID", "NOME", "PRECO", "ESTOQUE");
                        System.out.printf("%-5d %-20s %-10.2f %-10d%n", p.getId(), p.getNome(), p.getPreco(), p.getEstoque());
                    } else {
                        System.out.println("Produto não encontrado");
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção invalida");
                    break;
            }
        }
    }
}