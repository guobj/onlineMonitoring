package com.nz.onlineMonitoring.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class JavaPage implements PageProcessor {

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me()
			.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
			.addHeader("Referer","http://www.sdqx.gov.cn/")
			.setDomain("sd.weather.com.cn")
			.setRetryTimes(3).setSleepTime(100);

	public void process(Page page) {

		List<String> urls = page.getHtml().xpath("//div[@class='navbox']/span").links().regex(".+?\\.shtml").all();
		page.addTargetRequests(urls);

		System.out.println(urls);
		/*page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
		page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
		page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
		if (page.getResultItems().get("name")==null){
			//skip this page
			page.setSkip(true);
		}
		page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));*/
		List list = new ArrayList();
//		System.out.println(page.getUrl().toString().matches(".+?/weather/\\d.*"));
		if(page.getUrl().toString().matches(".+?/weather.*")){
			String url = page.getUrl().toString().replace("/weather/","/weather1d/");
//			page.setUrl();
//			System.out.println("第二次爬取:"+page.getHtml());
			list.add(page.getUrl());

		}
//		System.out.println("测试是否遗漏："+list);
//		System.out.println("测试："+page.getUrl());
	}

	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new JavaPage()).addUrl("http://sd.weather.com.cn/skjc/index.shtml").thread(5).run();
	}
}
