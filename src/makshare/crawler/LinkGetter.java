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
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author qwert
 */
public class LinkGetter {

    String url;

    public LinkGetter(String url) {
        this.url = url;
    }

    public boolean isDate(String date) {
        return date.split("-").length == 3;
    }

    public ArrayList<String> getLinks() throws MalformedURLException, IOException {
        URL myUrl = new URL(url);
        Scanner sc = new Scanner(myUrl.openConnection().getInputStream()).useDelimiter("\\Z");
        String html = sc.next();
        Pattern linkPattern = Pattern.compile("<a[^>]+href=[\"']?([\"'>]+)[\"']?[^>]*>");
        Matcher pageMatcher = linkPattern.matcher(html);
        ArrayList<String> links = new ArrayList<>();
        while (pageMatcher.find()) {
            String tmp = (pageMatcher.group().replace("<a href=\"", "").replace("/\">", ""));
            if (isDate(tmp)) {
                links.add(tmp);
            }
        }
        return links;
    }
}
