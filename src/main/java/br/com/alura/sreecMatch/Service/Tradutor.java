package br.com.alura.sreecMatch.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Tradutor {
    public static String traduzir(String txt){
        try {
            String url = "https://translate.astian.org/translate";
            String json = """
                {
                "q": "%s",
                "source": "en",
                "target": "pt",
                "format": "text"
                }
                """.formatted(txt);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpClient cliente = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();

            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

//            System.out.println(response.body());
//            System.out.println(response.statusCode());

            JsonObject obj = JsonParser
                    .parseString(response.body())
                    .getAsJsonObject();

            return obj.get("translatedText").getAsString();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
