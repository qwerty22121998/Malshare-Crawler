/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package makshare.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qwert
 */
public class Crawler {

    URL myURL;
    Scanner sc;
    ArrayList<String> md5, sha1, sha256, ssdeep;

    public Crawler(String url) {
        System.out.println(url);

        try {
            myURL = new URL(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex); 
        }

        try {
            sc = new Scanner(myURL.openStream());
        } catch (IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }

        md5 = new ArrayList<>();
        sha1 = new ArrayList<>();
        sha256 = new ArrayList<>();
        ssdeep = new ArrayList<>();
    }

    public String[] getContent() {
        if (md5.isEmpty()) {
            return null;
        }
        String[] res = new String[4 * md5.size()];
        for (int i = 0; i < md5.size(); i++) {
            res[4 * i] = "md5 : " + md5.get(i);
            res[4 * i + 1] = "sha1 : " + sha1.get(i);
            res[4 * i + 2] = "sha256 : " + sha256.get(i);
            res[4 * i + 3] = "ssdeep : " + ssdeep.get(i);
        }
        md5.clear();
        sha1.clear();
        sha256.clear();
        ssdeep.clear();
        return res;
    }

    public void classify() {
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] hash = line.split("	");
            //System.out.println(hash.length);
            if (hash.length != 4) {
                continue;
            }
            md5.add(hash[0]);
            sha1.add(hash[1]);
            sha256.add(hash[2]);
            ssdeep.add(hash[3]);
        }
    }
}
