package com.zed.topic.search.data.in.worm;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HTMLParserListener;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import com.zed.topic.search.core.configuration.Config;
import com.zed.topic.search.core.pojo.Paper;
import com.zed.topic.search.data.in.service.RepService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zed
 * @date 2019/12/9 下午11:05
 * @contact shadowl91@163.com
 */
@Component
@Slf4j
public class WebReptileCNKI {

    @Autowired
    private Config config;

    @Autowired
    private RepService repService;

    private ExecutorService executorService = Executors.newFixedThreadPool(5);


    private List<Paper> list = new Vector<>(128);

//    private List<Paper> list = new ArrayList<>(128);

    private String UA = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36";

    private static Pattern dbcode_pattern;

    private static Pattern dbname_pattern;

    private static Pattern filename_pattern;
    static {
        String dbcode = "DbCode=.*?&";
        String dbname = "DbName=.*?&";
        String filename = "FileName=.*?&";
        dbcode_pattern = Pattern.compile(dbcode);
        dbname_pattern = Pattern.compile(dbname);
        filename_pattern = Pattern.compile(filename);
    }

    public void clearList() {
        list.clear();
    }

    public List<Paper> getList() {
        return list;
    }

    /**
     * 1. use the keyword to search for papers
     * 2. get papers in first page
     * 3. get total pages of result of this search
     * 4. get papers in urls of list
     * @param keyword
     * @return
     */
    public Document getCNKIContent(String keyword) {
        List<String> nextPageList = new ArrayList<>();
        WebClient webClient = this.getWebClient();
        Document document = null;
        try {
            document = this.getContentPapers(keyword, webClient);
            // get table
            Elements content = document.getElementsByClass("pageBar_top").select("tr");
            // get each row
            Elements content_1 = document.getElementsByClass("GridTableContent").select("tr");
//            for (Element row: content_1)
//                this.getPaper(row);
            // get all page urls
            String total_temp = content.get(0).select("span.countPageMark").text();
//            int totalPages = Integer.parseInt(total_temp.substring(2));
//            log.info("keyword:{},totalPages:{}",keyword, totalPages);
//            this.addURLs(nextPageList, totalPages);
//            int count = 1;
//            for (String nextURL: nextPageList) {
//                log.info("this page is:{}", ++count);
//                this.getNextPagePaper(nextURL, webClient);
//            }
//            int num = totalPages / 20;
//            // if page numbers > 100 force webcrawler to get page from 1 to 100
//            if (num > 5)
//                num = 4;
            for (int i = 0; i < 15; i+=2) {
                String pageUrl = getPageUrl(i);
                getNextPagePaper(pageUrl, webClient, keyword);
            }
//            executorService.execute(new CNKIRunnable(0, keyword));


//            int start = 0;
//            while (start < num) {
//                executorService.execute(new CNKIRunnable(start, keyword));
//                start++;
//            }
//            Thread.sleep(100000L);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            webClient.close();
        }
        return document;
    }

    private Document getContentPapers(String keyword, WebClient webClient) throws IOException {
        this.searchAndClickBtn(webClient, keyword);
        String url = "https://kns.cnki.net/kns/brief/brief.aspx?pagename=ASP.brief_result_aspx&isinEn=1&dbPrefix=SCDB&dbCatalog=%e4%b8%ad%e5%9b%bd%e5%ad%a6%e6%9c%af%e6%96%87%e7%8c%ae%e7%bd%91%e7%bb%9c%e5%87%ba%e7%89%88%e6%80%bb%e5%ba%93&ConfigFile=SCDB.xml&research=off&t=";
        url += System.currentTimeMillis();
        url += "&keyValue=";
        url += URLEncoder.encode(keyword, "utf-8");
        url += "&S=1&sorttype=";
        log.info("url:{}", url);
        HtmlPage targetPage = (HtmlPage) webClient.getPage(url);
        return Jsoup.parse(targetPage.asXml());
    }

    // do click
    private void searchAndClickBtn(WebClient webClient, String keyword) throws IOException {
        HtmlPage htmlPage = webClient.getPage(config.cnkiURL);
        if (htmlPage == null)
            throw new NullPointerException("get no page");
        HtmlTextInput input = (HtmlTextInput) htmlPage.getElementById("txt_1_value1");
        input.setValueAttribute(keyword);
        DomElement btnSearch = htmlPage.getElementById("btnSearch");
        btnSearch.click();
        webClient.waitForBackgroundJavaScript(30000);
    }

    // add urls to list
    private void addURLs(List<String> urls, int total) {
        for (int i = 2; i < total; i++) {
            String url = "https://kns.cnki.net/kns/brief/brief.aspx?curpage=" + i +
                    "&RecordsPerPage=20&QueryID=3&ID=&turnpage=1&tpagemode=L&dbPrefix=SCDB&Fields=&DisplayMode=listmode&PageName=ASP.brief_result_aspx&isinEn=1&";
            urls.add(url);
        }
    }

    private String getPageUrl(int i) {

        return "https://kns.cnki.net/kns/brief/brief.aspx?curpage=" + i +
                "&RecordsPerPage=20&QueryID=3&ID=&turnpage=1&tpagemode=L&dbPrefix=SCDB&Fields=&DisplayMode=listmode&PageName=ASP.brief_result_aspx&isinEn=1&";

    }

    // get papers in next page
    private void getNextPagePaper(String url, WebClient webClient, String keyword) throws IOException {
        HtmlPage page = (HtmlPage) webClient.getPage(url);
        Document document = Jsoup.parse(page.asXml());
        Elements rows = document.getElementsByClass("GridTableContent").select("tr");
//        log.info("document:{}", document);
        for (Element row: rows)
            this.getPaper(row, keyword);
    }

//    private void getNextPagePaperSingle(WebClient webClient, String keyword) throws IOException {
//        getContentPapers(keyword, webClient);
//    }

    class CNKIRunnable implements Runnable {

        private int startPosition;

        private int offset = 15;

        private String keyword;

        public CNKIRunnable(int startPosition, String keyword) {
            this.startPosition = startPosition * 15;
            this.keyword = keyword;
        }

        @Override
        public void run() {
            WebClient webClient = getWebClient();
            try {
                Document document = getContentPapers(keyword, webClient);
                int count = 0;
                while (count < offset) {
                    count++;
                    String url = getPageUrl(count * 2 + startPosition);
                    log.info("[multiple threads]keyword={},page={}", keyword, count + startPosition);
                    getNextPagePaper(url, webClient, keyword);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                webClient.close();
            }
        }
    }

    private WebClient getWebClient(){
        // get webclient
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.waitForBackgroundJavaScript(30000);
        return webClient;
    }

    private Paper getPaper(Element row, String keyword) throws IOException {
        if (StringUtils.isBlank(row.attr("bgcolor")))
            return null;
        Elements td = row.getElementsByTag("td");
        Paper paper = new Paper();
        for (int i = 0; i < 8; i++) {
            if (!StringUtils.isNotBlank(td.get(i).text()))
                continue;
            switch (i) {
                case 1:
                    paper.setName(td.get(i).text());
                    String href = td.get(i).getElementsByTag("a").attr("href");
                    this.getSummary(href, paper);
                    break;
                case 2:
                    paper.setAuthor(td.get(i).text());
                    break;
                case 3:
                    paper.setSource(td.get(i).text());
                    break;
                case 4:
                    paper.setDate(td.get(i).text());
                    break;
                case 5:
                    paper.setDb(td.get(i).text());
                    break;
                case 6:
                    paper.setRef(Integer.valueOf(td.get(i).text()));
                    break;
                case 7:
                    paper.setDownloads(Integer.valueOf(td.get(i).text()));
                    break;
                default:
                    break;
            }
        }
        paper.setKeyword(keyword);
        log.info("{}",paper);
        if (!StringUtils.isEmpty(paper.getFilecode()) && !StringUtils.isEmpty(paper.getName()))
            repService.save(paper);
//        list.add(paper);
//        if (list.size()>=100) {
//            repService.batchInsertPaper(list);
//            list.clear();
//        }
        return paper;
    }

    private Paper cleanData(Papaer paper) {
        if (StringUtils.isEmpty(paper.getAuthor) ||
            StringUtils.isEmpty(paper.getSummary) ||
            StringUtils.isEmpty(paper.getDate) ||
            StringUtils.isEmpty(paper.getSource) ||
            paper.getDownloads == null 
           )
            return null;
        HtmlPage htmlPage = webClient.getPage(paper.getSource);
        if (htmlPage == null)
            renull;
        return paper;
    }

    private Paper getSummary(String href, Paper paper) throws IOException {
        String url = "https://kns.cnki.net/KCMS/detail/detail.aspx?";
        Matcher dbcode = dbcode_pattern.matcher(href);
        Matcher dbname = dbname_pattern.matcher(href);
        Matcher filename = filename_pattern.matcher(href);
        if (dbcode.find()) {
            String dbcode_temp = dbcode.group();
            String dbcode_str = dbcode_temp.substring(7, dbcode_temp.length() - 1);
            url = url + "&dbcode=" + dbcode_str;
        }
        if (dbname.find()) {
            String dbname_temp = dbname.group();
            String dbname_str = dbname_temp.substring(7, dbname_temp.length() - 1);
            url = url + "&dbname=" + dbname_str;
        }
        if (filename.find()) {
            String filename_temp = filename.group();
            String filename_str = filename_temp.substring(9, filename_temp.length() - 1);
            url = url + "&filename=" + filename_str;
            paper.setFilecode(filename_str);
        }
        log.info("detail url:{}",url);
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .userAgent(UA)
                    .timeout(10000)
                    .get();
        } catch (IOException e) {
//            e.printStackTrace();
            log.error("error url:{}", url);
        }
        // TODO if is null there seem to be post on other websites still need to process
        if (document == null || document.getElementById("ChDivSummary") == null)
            paper.setSummary(null);
        else
            paper.setSummary(document.getElementById("ChDivSummary").text());
        return paper;
    }
}
