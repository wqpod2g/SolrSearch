package nju.iip.solr;

import java.io.IOException;
import java.util.List;

import nju.iip.model.Weibo;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class QueryTest {

	private static String url = "http://localhost:8983/solr/collection";
	private static SolrClient solr = new HttpSolrClient(url);

	public static void main(String[] args) throws SolrServerException,IOException {
		SolrQuery query = new SolrQuery();
		query.setQuery("南京大学");
		QueryResponse rsp = solr.query(query);
		SolrDocumentList list = rsp.getResults();
		DocumentObjectBinder binder = new DocumentObjectBinder();
		List<Weibo> weibo_list = binder.getBeans(Weibo.class, list);
		for (int i = 0; i < weibo_list.size(); i++) {
			System.out.println(weibo_list.get(i).getAuthor());
		}
	}
}
