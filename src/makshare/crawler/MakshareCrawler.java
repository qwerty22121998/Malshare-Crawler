/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package makshare.crawler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author qwert
 */
public class MakshareCrawler {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        File dir = new File("D:\\malshare");
        dir.mkdir();
        LinkGetter lg = new LinkGetter("https://malshare.com/daily/");
        ArrayList<String> arr = lg.getLinks();
        for(String link : arr) {
            CrawlerClass crawler = new CrawlerClass(link);
            crawler.exec();
        }
    }

}
