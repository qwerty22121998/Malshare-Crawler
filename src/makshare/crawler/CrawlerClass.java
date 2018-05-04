/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package makshare.crawler;

import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author qwert
 */
public class CrawlerClass {

    String url;
    String date;

    public CrawlerClass(String date) {
        this.date = date;
        url = "https://malshare.com/daily/" + date + "/malshare_fileList." + date + ".all.txt";

    }

    public void exec() {
        try {
            Crawler cr = new Crawler(url);
            cr.classify();
            String[] ct = cr.getContent();
            if (ct == null) {
                return;
            }
            File dir = new File("D:\\malshare\\" + date + ".txt");
            if (!dir.exists()) {
                dir.createNewFile();
            }
            FileWriter fw = new FileWriter(dir);
            for (String s : ct) {
                fw.write(s + "\n");
            }
            System.out.println(dir.getAbsolutePath() + " DONE");
            fw.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
