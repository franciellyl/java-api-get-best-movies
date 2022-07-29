package br.org;

import br.org.parse.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class ApplicationMain {

    public static void main(String[] args) throws IOException, InterruptedException, IllegalAccessException {

        //fazer conexão
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        //extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}