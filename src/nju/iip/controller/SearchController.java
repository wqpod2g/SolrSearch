package nju.iip.controller;

import java.util.List;
import java.util.Map;

import nju.iip.model.Weibo;
import nju.iip.solr.SolrImpl;
import nju.iip.util.Config;
import nju.iip.util.Page;

import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	
	@Autowired
	private SolrImpl solrImpl;
	
	@RequestMapping(value = "/WeiboQuery")
	public String WeiboQuery(String query,Page page,Model model) {
		if (query == null || query.equals("")) {
			return "Search.jsp";
		}
		logger.info("query="+query);
		logger.info("page.no="+page.getPageNo());
		page.setPageSize(Integer.valueOf(Config.getValue("countPerPage")));
		QueryResponse rsp =  solrImpl.queryDocuments(query,page.getPageNo()*page.getPageSize(),"collection");
		SolrDocumentList list = rsp.getResults();
		Map<String, Map<String, List<String>>> highlight_map = rsp.getHighlighting();
		DocumentObjectBinder binder = new DocumentObjectBinder();
		List<Weibo> weibo_list = binder.getBeans(Weibo.class, list);
		for(int i=0;i<weibo_list.size();i++) {
			String id = (String)list.get(i).getFieldValue("id");
			String highlight_text = highlight_map.get(id).get("text").get(0);
			logger.info("highlight_text="+highlight_text);
			weibo_list.get(i).setText(highlight_text);
		}
		page.setTotalRecords(new Long(list.getNumFound()).intValue());
		model.addAttribute("weibo_list", weibo_list);
		model.addAttribute("qTime", rsp.getQTime());
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "result.jsp";
	}
	
	

}
