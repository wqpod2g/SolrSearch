package nju.iip.solr;

import nju.iip.controller.SearchController;
import nju.iip.util.Config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Solr
 * @author wangqiang
 *
 */
@Service
public class SolrImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	
	/**
	 * 查询
	 * @param query_words 查询词
	 * @param start 起始文档位置
	 * @param rows  每页文档数
	 * @return
	 */
	public QueryResponse queryDocuments(String query_words, int start,String collection){
		try {
			SolrClient solr = new HttpSolrClient(Config.getValue("SolrHost")+collection);
			SolrQuery query = new SolrQuery();
			query.setQuery(query_words);
			query.setRows(Integer.valueOf(Config.getValue("countPerPage")));// 每次取多少条
			query.setStart(start);// 从第几条开始查询
			query.setHighlight(true); // 开启高亮组件
			query.addHighlightField("text");
			query.addHighlightField("title");
			query.setHighlightSimplePre("<font color='red'>");// 标记
			query.setHighlightSimplePost("</font>");
			QueryResponse response = solr.query(query);
			solr.close();
			return response;
		}catch(Exception e) {
			logger.info("queryDocuments error",e);
		}
		return null;
	}

}
