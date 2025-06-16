import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import org.json.JSONObject;

public class ViaCep {
	
	private String estado;
    private String uf;
    private String cidade;
    private String bairro;
    private String rua;

    public String buscarCep(String cep) {
        try {
            // Monta a URL da requisição
            String enderecoUrl = "https://viacep.com.br/ws/" + cep + "/json/";
            URL url = new URL(enderecoUrl);

            // Abre a conexão HTTP
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            // Lê a resposta da requisição
            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            StringBuilder resposta = new StringBuilder();

            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }
            leitor.close();

            // Converte a resposta para JSON
            JSONObject json = new JSONObject(resposta.toString());
            
            if (json.has("erro") && json.getBoolean("erro")) {
                return "Erro ao pesquisar o CEP.";
            }

            // Extrai informações do JSON
            this.estado = json.optString("estado");
            this.uf = json.optString("uf");
            this.cidade = json.optString("localidade");
            this.bairro = json.optString("bairro");
            this.rua = json.optString("logradouro");
            


        } catch (Exception erro) {
            erro.printStackTrace();
            System.out.println("Erro ao buscar o CEP: " + erro.getMessage());
        }
		return null;
    }
    
    
    public String getEstado() { return estado; }
    public String getUf() { return uf; }
    public String getCidade() { return cidade; }
    public String getBairro() { return bairro; }
    public String getRua() { return rua; }
}
