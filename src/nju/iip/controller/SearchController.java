package nju.iip.controller;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nju.iip.dto.Article;
import nju.iip.dto.Wechat;
import nju.iip.dto.Weibo;
import nju.iip.solr.SolrImpl;
import nju.iip.util.Config;
import nju.iip.util.Page;

@Controller
public class SearchController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	
	@Autowired
	private SolrImpl solrImpl;
	
	/**
	 * 根据type 选择特定搜索
	 * @param query
	 * @param page
	 * @param model
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/CoreQuery")
	public String CoreQuery(String query,Page page,Model model,String type) {
		if (query == null || query.equals("")) {
			return "Search.jsp";
		}
		logger.info("type="+type);
		logger.info("query="+query);
		logger.info("page.no="+page.getPageNo());
		if(type==null) {
			return WeiboQuery(query,page,model);
		}
		else if(type.equals("weibo")) {
			return WeiboQuery(query,page,model);
		}
		else if(type.equals("wechat")) {
			return WechatQuery(query,page,model);
		}
		else if(type.equals("website")) {
			return WebsiteQuery(query,page,model);
		}
		return WeiboQuery(query,page,model);
	}
	
	
	/**
	 * 微博搜索
	 * @param query 查询关键字
	 * @param page 分页对象
	 * @param model model数据
	 * @return
	 */
	@RequestMapping(value = "/WebsiteQuery")
	public String WebsiteQuery(String query,Page page,Model model) {
		if (query == null || query.equals("")) {
			return "Search.jsp";
		}
		page.setPageSize(Integer.valueOf(Config.getValue("countPerPage")));
		QueryResponse rsp =  solrImpl.queryDocuments(query,page.getPageNo()*page.getPageSize(),"collection2");
		SolrDocumentList list = rsp.getResults();
		Map<String, Map<String, List<String>>> highlight_map = rsp.getHighlighting();
		DocumentObjectBinder binder = new DocumentObjectBinder();
		List<Article> article_list = binder.getBeans(Article.class, list);
		for(int i=0;i<article_list.size();i++) {
			String id = (String)list.get(i).getFieldValue("id");
			String highlight_text = highlight_map.get(id).get("text").get(0);
			article_list.get(i).setText(highlight_text);
			if (highlight_map.get(id).containsKey("title")) {
				String highlight_title = highlight_map.get(id).get("title").get(0);
				article_list.get(i).setTitle(highlight_title);
			}
			else {
				article_list.get(i).setTitle(list.get(i).getFieldValue("title").toString());
			}
		}
		page.setTotalRecords(new Long(list.getNumFound()).intValue());
		model.addAttribute("article_list", article_list);
		model.addAttribute("qTime", rsp.getQTime());
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "website.jsp";
	}
	
	/**
	 * 微博搜索
	 * @param query 查询关键字
	 * @param page 分页对象
	 * @param model model数据
	 * @return
	 */
	@RequestMapping(value = "/WeiboQuery")
	public String WeiboQuery(String query,Page page,Model model) {
		if (query == null || query.equals("")) {
			return "Search.jsp";
		}
		page.setPageSize(Integer.valueOf(Config.getValue("countPerPage")));
		QueryResponse rsp =  solrImpl.queryDocuments(query,page.getPageNo()*page.getPageSize(),"collection1");
		SolrDocumentList list = rsp.getResults();
		Map<String, Map<String, List<String>>> highlight_map = rsp.getHighlighting();
		DocumentObjectBinder binder = new DocumentObjectBinder();
		List<Weibo> weibo_list = binder.getBeans(Weibo.class, list);
		for(int i=0;i<weibo_list.size();i++) {
			String id = (String)list.get(i).getFieldValue("id");
			String highlight_text = highlight_map.get(id).get("text").get(0);
			weibo_list.get(i).setText(highlight_text);
		}
		page.setTotalRecords(new Long(list.getNumFound()).intValue());
		model.addAttribute("weibo_list", weibo_list);
		model.addAttribute("qTime", rsp.getQTime());
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "weiboResult.jsp";
	}
	
	
	@RequestMapping(value = "/WechatQuery")
	public String WechatQuery(String query,Page page,Model model) {
		if (query == null || query.equals("")) {
			return "Search.jsp";
		}
		page.setPageSize(Integer.valueOf(Config.getValue("countPerPage")));
		QueryResponse rsp =  solrImpl.queryDocuments(query,page.getPageNo()*page.getPageSize(),"collection2");
		SolrDocumentList list = rsp.getResults();
		Map<String, Map<String, List<String>>> highlight_map = rsp.getHighlighting();
		DocumentObjectBinder binder = new DocumentObjectBinder();
		List<Wechat> wechat_list = binder.getBeans(Wechat.class, list);
		for(int i=0;i<wechat_list.size();i++) {
			String id = (String)list.get(i).getFieldValue("id");
			String highlight_text = highlight_map.get(id).get("text").get(0);
			wechat_list.get(i).setText(highlight_text);
			if (highlight_map.get(id).containsKey("title")) {
				String highlight_title = highlight_map.get(id).get("title").get(0);
				wechat_list.get(i).setTitle(highlight_title);
			}
			else {
				wechat_list.get(i).setTitle(list.get(i).getFieldValue("title").toString());
			}
		}
		page.setTotalRecords(new Long(list.getNumFound()).intValue());
		model.addAttribute("wechat_list", wechat_list);
		model.addAttribute("qTime", rsp.getQTime());
		model.addAttribute("page", page);
		model.addAttribute("query", query);
		return "wechatResult.jsp";
	}
	

}
