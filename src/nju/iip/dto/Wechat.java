package nju.iip.dto;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 微信内容实体类
 * @author wangqiang
 *
 */
public class Wechat {
	@Field
	private String id;
	@Field
	private String title;
	@Field
	private String text;
	@Field
	private String url;
	@Field
	private String sourceName;
	@Field
	private String postTime;
	

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	

}
