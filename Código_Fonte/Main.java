import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AssinaturaService assinaturaService = new AssinaturaService();
        PagamentoService pagamentoService = new PagamentoService();
        AssinaturaController controller =
                new AssinaturaController(assinaturaService, pagamentoService);

        System.out.println("=== FEIRA EM CASA ===");

        System.out.print("Nome do assinante: ");
        String nome = sc.nextLine();

        System.out.print("Celular: ");
        String celular = sc.nextLine();

        System.out.print("E-mail: ");
        String email = sc.nextLine();

        Assinante assinante = new Assinante(1L, nome, celular, email);

        System.out.println("\nEnviando código por SMS (mock)...");
        String codigoGerado = "123456";
        System.out.print("Informe o código recebido: ");
        String codigoDigitado = sc.nextLine();

        if (!assinaturaService.validarCodigoSms(codigoDigitado, codigoGerado)) {
            System.out.println("Código inválido. Encerrando.");
            sc.close();
            return;
        }

        System.out.println("\nCelular validado!");

        System.out.println("\nPlanos disponíveis:");
        List<Plano> planos = controller.obterPlanos();
        for (Plano p : planos) {
            System.out.println(p);
        }

        System.out.print("Escolha o ID do plano: ");
        long idPlano = Long.parseLong(sc.nextLine());

        Plano planoEscolhido = planos.stream()
                .filter(p -> p.getIdPlano() == idPlano)
                .findFirst()
                .orElse(null);

        if (planoEscolhido == null) {
            System.out.println("Plano inválido.");
            sc.close();
            return;
        }

        Cesta cesta = controller.criarCesta();

        selecionarItens(sc, controller, TipoProduto.FRUTA, cesta);
        selecionarItens(sc, controller, TipoProduto.LEGUME, cesta);
        selecionarItens(sc, controller, TipoProduto.VERDURA, cesta);

        System.out.println("\nCesta final:");
        System.out.println(cesta);

        System.out.println("\nEndereço de entrega:");
        System.out.print("Logradouro: ");
        String logradouro = sc.nextLine();
        System.out.print("Número: ");
        String numero = sc.nextLine();
        System.out.print("Complemento: ");
        String complemento = sc.nextLine();
        System.out.print("Bairro: ");
        String bairro = sc.nextLine();
        System.out.print("Cidade: ");
        String cidade = sc.nextLine();
        System.out.print("CEP: ");
        String cep = sc.nextLine();

        Endereco endereco = new Endereco(1L, logradouro, numero,
                complemento, bairro, cidade, cep);

        BigDecimal valorTotal = controller.calcularValor(planoEscolhido);
        System.out.println("\nValor total: R$ " + valorTotal);

        System.out.println("\nDados do cartão:");
        System.out.print("Nome impresso: ");
        String nomeCartao = sc.nextLine();
        System.out.print("Número do cartão: ");
        String numeroCartao = sc.nextLine();
        System.out.print("Bandeira: ");
        String bandeira = sc.nextLine();
        System.out.print("Validade mês (MM): ");
        int mes = Integer.parseInt(sc.nextLine());
        System.out.print("Validade ano (AAAA): ");
        int ano = Integer.parseInt(sc.nextLine());

        Cartao cartao = new Cartao(nomeCartao, numeroCartao, bandeira, mes, ano);

        Pagamento pagamento = controller.realizarPagamento(cartao, valorTotal);

        if (pagamento.getStatus() != Pagamento.StatusPagamento.APROVADO) {
            System.out.println("Pagamento recusado!");
            sc.close();
            return;
        }

        Assinatura assinatura = controller.finalizarAssinatura(
                assinante, planoEscolhido, cesta, endereco, pagamento);

        System.out.println("\nAssinatura criada com sucesso!");
        System.out.println(assinatura);

        sc.close();
    }

    private static void selecionarItens(Scanner sc,
                                        AssinaturaController controller,
                                        TipoProduto tipo,
                                        Cesta cesta) {

        System.out.println("\nSelecione itens do tipo " + tipo + ":");

        List<Produto> produtos = controller.obterProdutosPorTipo(tipo);
        for (Produto p : produtos) {
            System.out.println(p);
        }

        while (true) {
            System.out.print("Digite o ID do produto (0 para sair): ");
            long idProd = Long.parseLong(sc.nextLine());
            if (idProd == 0) break;

            Produto escolhido = produtos.stream()
                    .filter(p -> p.getIdProduto() == idProd)
                    .findFirst()
                    .orElse(null);

            if (escolhido == null) {
                System.out.println("ID inválido!");
                continue;
            }

            System.out.print("Quantidade: ");
            int qtd = Integer.parseInt(sc.nextLine());

            cesta.adicionarItem(escolhido, qtd);
        }
    }
}
