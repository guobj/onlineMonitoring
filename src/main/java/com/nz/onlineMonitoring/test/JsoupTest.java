package com.nz.onlineMonitoring.test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsoupTest {

	public static void test() throws IOException {

		Map<String,String> headersMap = new HashMap<String, String>();
		headersMap.put("Host","sd.weather.com.cn");
		headersMap.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headersMap.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		headersMap.put("Accept-Encoding","gzip, deflate");
		headersMap.put("Referer","http://www.sdqx.gov.cn/");
		Document doc = Jsoup.connect("http://sd.weather.com.cn/skjc/index.shtml")
				.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
				.headers(headersMap)
				.header("Connection", "close")
				.get();
		Elements cityLinks = doc.select("div.navbox span a");
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("城市："+cityLinks.size());
		for(Element cityLink : cityLinks){

			String cityUrl = cityLink.attr("href");
			String cityName =  cityLink.text();
			Document doc2 = Jsoup.connect(cityUrl)
					.data("query", "Java")
					.userAgent("Mozilla")
					.header("Connection", "close")
					.maxBodySize(0)
					.get();
			Elements areaLinks = doc2.select("div#forecastID dl dt a");
			System.out.println("市区："+areaLinks.size());
			for(Element areaLink : areaLinks){

				String areaUrl = areaLink.attr("href");
				String areaName = areaLink.text();

				String oneDayUrl = areaUrl.replaceAll("/weather/","/weather1d/");
				System.out.println(areaName);
				System.out.println(oneDayUrl);
				map.put(areaName,oneDayUrl);
			}
		}

		//防止HtmlUnit疯狂控制台打印日志
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		//HtmlUnit解析Html
		WebClient wc = new WebClient();
		wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true
		wc.getOptions().setCssEnabled(false); //禁用css支持
		wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常
		wc.getOptions().setTimeout(10000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待
		/*WebRequest wr = new WebRequest();
		wr.setAdditionalHeaders();*/
		String pageXml;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			HtmlPage page = wc.getPage(entry.getValue().toString());
			pageXml = page.asXml(); //以xml的形式获取响应文本
			Document resDoc = Jsoup.parse(pageXml);
			Elements humidityEmt = resDoc.select("div.sk");
			System.out.println(entry.getKey()+":"+humidityEmt.text());
		}
	}

	public static void main(String[] args) throws IOException {
		test();
	}
}
