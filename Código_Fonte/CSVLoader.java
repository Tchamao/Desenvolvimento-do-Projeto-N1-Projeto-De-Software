import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {
    public static List<Plano> carregarPlanos(String caminho) {
        List<Plano> planos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            br.readLine(); // pula cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] partes = linha.split(",");
                if (partes.length >= 6) {
                    Long id = Long.parseLong(partes[0].trim());
                    String nome = partes[1].trim();
                    BigDecimal valor = new BigDecimal(partes[2].trim());
                    int frutas = Integer.parseInt(partes[3].trim());
                    int legumes = Integer.parseInt(partes[4].trim());
                    int verduras = Integer.parseInt(partes[5].trim());
                    planos.add(new Plano(id, nome, valor, frutas, legumes, verduras));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro CSV Planos: " + e.getMessage());
        }
        return planos;
    }

    public static List<Produto> carregarProdutos(String caminho) {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            br.readLine(); // pula cabeçalho
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] partes = linha.split(",");
                if (partes.length >= 3) {
                    Long id = Long.parseLong(partes[0].trim());
                    String nome = partes[1].trim();
                    String tipoStr = partes[2].trim().toUpperCase();
                    TipoProduto tipo = TipoProduto.FRUTA;
                    if (tipoStr.contains("LEGUME")) tipo = TipoProduto.LEGUME;
                    else if (tipoStr.contains("VERDURA")) tipo = TipoProduto.VERDURA;
                    produtos.add(new Produto(id, nome, tipo));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro CSV Produtos: " + e.getMessage());
        }
        return produtos;
    }
}
