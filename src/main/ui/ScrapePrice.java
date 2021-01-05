package ui;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ScrapePrice {

    public static float amazonPrice(String url) throws Exception {
        try {
            return amazonDeal(url);

        } catch (Exception e) {
            Document document = Jsoup.connect(url).userAgent("Chrome/75.0").get();
            Elements price = document.select("#priceblock_ourprice");
            String priceString = price.html();
            String[] parts = priceString.split(";| ");
            System.out.println("Price: CDN$ " + parts[1]);
            return Float.parseFloat(parts[1]);
        }
    }

    public static float amazonDeal(String url) throws Exception {
        Document document = Jsoup.connect(url).userAgent("Chrome/75.0").get();
        Elements price = document.select("#priceblock_dealprice");
        String priceString = price.html();
        String[] parts = priceString.split(";| | -");
        System.out.println("Price: CDN$ " + parts[1]);
        return Float.parseFloat(parts[1]);
    }

}
