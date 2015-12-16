package nju.iip.model;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 微博内容实体类
 * @author wangqiang
 *
 */
public class Weibo {
	@Field
	private String id;
	@Field
	private String weiboUrl;
	@Field
	private String author;//作者
	@Field
	private String text;//正文
	@Field
	private String loves;//赞 数量
	@Field
	private String repost;//转发 数量
	@Field
	private String comment;//评论
	@Field
	private String postTime;//发布时间
	@Field
	private String device;//发布设备
	@Field
	private String crawlTime; //爬取时间
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoves() {
		return loves;
	}
	public void setLoves(String loves) {
		this.loves = loves;
	}
	public String getRepost() {
		return repost;
	}
	public void setRepost(String repost) {
		this.repost = repost;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCrawlTime() {
		return crawlTime;
	}
	public void setCrawlTime(String crawlTime) {
		this.crawlTime = crawlTime;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public String getWeiboUrl() {
		return weiboUrl;
	}
	public void setWeiboUrl(String weiboUrl) {
		this.weiboUrl = weiboUrl;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}

}
