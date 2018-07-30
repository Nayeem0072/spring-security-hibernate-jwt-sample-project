package com.syn.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.syn.entities.TVShow;
@Controller
@RequestMapping("/scraper")
public class ScraperController {
	private HtmlPage page;
	private ArrayList<TVShow> tvShowList;

	@RequestMapping(value = "/create-dictionary", method = RequestMethod.GET)
	public ModelAndView createDictionary() {
		String scrapeUrl = "https://www.rottentomatoes.com/";
		WebClient client = new WebClient();  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false); 
		Map dictionary = new HashMap();

		try {
			page = client.getPage(scrapeUrl);
			HtmlDivision mainDiv = (HtmlDivision) page.getFirstByXPath(".//div[@class='body_main container']") ;
			if(mainDiv == null){  
				System.out.println("No text found!");
			}else{
				String[] words = mainDiv.asText().split("\\s+");
				for(String word: words) {					
					word = word.replace(".", "");
					word = word.replace(",", "");

					if(dictionary.containsKey(word)) {
						Integer val = (Integer) dictionary.get(word);
						dictionary.put(word, val + 1);
					}
					else
						dictionary.put(word, 1);
				}
				for(Object key: dictionary.keySet()){
					System.out.println(key + ": " + dictionary.get(key));
					
				}		
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		client.close();
		ModelAndView mv = new ModelAndView("tvshows");
		mv.addObject("tvShowList", tvShowList);

		return mv;
	}

	@RequestMapping(value = "/rated-tv", method = RequestMethod.GET)
	public ModelAndView fetchRatedShows() {
		String scrapeUrl = "https://www.rottentomatoes.com/user/id/941054499/tvratings/";
		tvShowList = new ArrayList<TVShow>();

		WebClient client = new WebClient();  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false); 
		try {
			page = client.getPage(scrapeUrl);

			List<HtmlDivision> ratedItems = (List<HtmlDivision>) page.getByXPath("//div[@class='bottom_divider media']") ;

			if(ratedItems.isEmpty()){  
				System.out.println("No shows found!");
			}else{
				for (HtmlElement items : ratedItems) {
					HtmlImage itemImage =  ((HtmlImage) items.getFirstByXPath(".//img[@class='media-object']"));
					HtmlSpan itemRelease =  ((HtmlSpan) items.getFirstByXPath(".//span[@class='subtle small']"));					
					HtmlAnchor itemUrl = ((HtmlAnchor) items.getFirstByXPath(".//a[@id='tvPosterLink']"));

					List<HtmlSpan> ratedStars = (List<HtmlSpan>) items.getByXPath("//span[@class='glyphicon glyphicon-star']") ;

					System.out.println("name: " + itemImage.getAltAttribute());
					System.out.println("image: " + itemImage.getSrcAttribute());
					System.out.println("release: " + itemRelease.asText());
					System.out.println("url: " + itemUrl.getHrefAttribute());
					System.out.println("star: " + ratedStars.size());

					for (HtmlElement star : ratedStars) {
						System.out.println(star.asXml());
					}

					TVShow show = new TVShow();
					show.setName(itemImage.getAltAttribute());
					show.setUrl(itemUrl.getHrefAttribute());
					show.setThumbImg(itemImage.getSrcAttribute());

				}
			}

			//System.out.println(page.asXml());
		}catch(Exception e){
			e.printStackTrace();
		}

		client.close();

		ModelAndView mv = new ModelAndView("tvshows");
		mv.addObject("tvShowList", tvShowList);

		return mv;
	}


	@RequestMapping(value = "/latest-tv", method = RequestMethod.GET)
	public ModelAndView fetchLatestTvData() {		
		String scrapeUrl = "https://www.rottentomatoes.com/browse/tv-list-2/";
		tvShowList = new ArrayList<TVShow>();

		WebClient client = new WebClient();  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false);  

		try {
			page = client.getPage(scrapeUrl);		  
			List<HtmlTableRow> popularItems = (List<HtmlTableRow>) page.getByXPath("//tr[@class='tv_show_tr tvTopListTitle']") ;

			if(popularItems.isEmpty()){  
				System.out.println("No shows found!");
			}else{
				int count = 0;
				for (HtmlElement items : popularItems) {
					HtmlTableDataCell middleCol  = items.getFirstByXPath(".//td[@class='middle_col']");			
					HtmlAnchor middleColAnchor =  ((HtmlAnchor) middleCol.getFirstByXPath(".//a"));

					HtmlElement leftCol  = items.getFirstByXPath(".//td[@class='left_col']");
					HtmlSpan leftColSpan =  ((HtmlSpan) leftCol.getFirstByXPath(".//span[@class='tMeterScore']"));

					try{
						TVShow show = new TVShow();
						show.setName(middleColAnchor.asText());
						show.setUrl(middleColAnchor.getHrefAttribute()) ;
						if(leftColSpan != null){
							show.setScore(leftColSpan.asText());
						}
						else {
							show.setScore("No Score Yet");
						}

						tvShowList.add(show);

						//System.out.println("Popular Show "+ ++count + ": " + show.getName() + " (" + show.getUrl() + ") " + show.getScore());
					}catch(Exception e){
						System.out.println("Casting err");
						e.printStackTrace();
					}

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		client.close();

		ModelAndView mv = new ModelAndView("tvshows");
		mv.addObject("tvShowList", tvShowList);

		return mv;
	}

}
