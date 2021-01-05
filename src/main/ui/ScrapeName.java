package ui;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ScrapeName {

    public static String amazonName(String url) throws Exception {
        Document document = Jsoup.connect(url).userAgent("Chrome/75.0").get();
        Elements name = document.select("#productTitle");
        String nameString = name.text();
        String[] parts = nameString.split(",| -| ,");
        System.out.println("item Name: " + parts[0]);
        return parts[0];
    }
}
