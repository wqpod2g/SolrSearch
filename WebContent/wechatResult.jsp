<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link type="text/css" rel="stylesheet" href="css/result.css" />
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/index.css"/>
<link rel="stylesheet" href="css/common.css"/>
<title>${query}_IIP_SEARCH</title>
<style>
.s_btn_wr {
	width: 102px;
	height: 32px;
	border: 1px solid #38f;
	border-bottom: 1px solid #2e7ae5;
	background-color: #38f;
}

.btn_wr {
	position: absolute;
	left: 536px;
	top: 0;
}

.s-title-img .btn {
	cursor: pointer;
	width: 102px;
	height: 32px;
	padding: 0;
	border: 0;
	background-color: #38f;
	font-size: 16px;
	color: white;
}

.btn {
	color: white;
	background-color: #38f;
	width: 102px;
	height: 32px;
	font-size: 16px;
	border: 0;
}
</style>
</head>
<body>
  <div class="container-fluid">
    	<ul class="nav nav-pills pull-right header">
            <li><a href="./login-1.html" id="signin-btn"><span class="glyphicon glyphicon-user" aria-hidden="true" title="登录"></span>&nbsp;登录</a></li>            
            <li><a href="./Search.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true" title="实验室"></span>&nbsp;实验室</a></li>
            <li><a href="./Search.jsp"><span class="glyphicon glyphicon-question-sign" aria-hidden="true" title="帮助"></span>&nbsp;帮助</a></li>
        </ul>
    </div>
	<div id="wrapper" style="margin-left: 60px;">

		<div id="head">
			<div class="s_nav">
				<a href="http://iip.nju.edu.cn" class="s_logo"> <img
					alt="智能信息处理研究组" src="images/wechat.jpg" height="45px">
				</a>
				<div class="s_tab" id="s_tab">
				<a href="Search.jsp">首页</a>&nbsp;&nbsp;
					<a href="javascript:void(0)">更多»</a>
				</div>
			</div>
			<br>
			<form name="f" action="WechatQuery" class="fm" >
				<span class="bg s_ipt_wr"> <input name="query" id="kw"
					class="s_ipt" value='${query}' maxlength="200">
				</span> <input type="submit" value="搜索" class="btn btn-primary btn-large" id="mrs_qbutton">
			</form>
			<div class="well">
				<span>加 &quot;*&quot; 不拆分关键词，用 AND, OR, NOT 进行逻辑查询，例如
					“&quot;上海安硕&quot;&nbsp;&nbsp;AND&nbsp;&nbsp;&quot;未来&quot;</span>
			</div>
			<div>
				<span class="nums">为您找到相关结果约${page.totalRecords}个,
					耗时${qTime}毫秒</span>
			</div>
		</div>

		<div id="main">

			<div class="content">
				<c:forEach items="${wechat_list}" var="wechat">
					<table cellpadding="0" cellspacing="0" class="result">
						<tr>
							<td class=f>
								<h3 class="t">
									<a href="${wechat.url}" target="_blank">${wechat.title}</a>
								</h3> <font size=-1><div class="realtime">来源：&nbsp;${wechat.sourceName}&nbsp;</div>
									${wechat.text}<br> <font color="#008000"><span><a
											class="g" href="http://weixin.sogou.com${wechat.url}">http://weixin.sogou.com${wechat.url.substring(0,50)}</a>...&nbsp;${wechat.postTime}&nbsp;</span></font>-&nbsp;
									<a href="${wechat.url}"
									target="_blank" class="m">搜索快照</a> <br> </font>
							</td>
						</tr>
					</table>
					<br>
				</c:forEach>
			</div>


			<p id="page">
				<c:if test="${page.totalRecords!=0}">
					<a href='WechatQuery?query=${query}&pageNo=0'
						class="n">首页</a>
					<c:if test="${page.pageNo != 0}">
						<a
							href='WechatQuery?query=${query}&pageNo=${page.getPreviousPageNo()}'
							class="n">&lt;上一页</a>
					</c:if>
					<c:forEach var="item" begin="${page.getBegin()}"
						end="${page.getEnd()}" step="1">
						<c:choose>
							<c:when test="${item!=page.pageNo}">
								<a href='WechatQuery?query=${query}&pageNo=${item}'>
									<span class="fk"></span> <span class="pc">${item+1}</span>
								</a>
							</c:when>
							<c:otherwise>
								<strong> <span class="fk fk_cur"></span><span
									class="pc">${item+1}</span></strong>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${page.pageNo != page.getTotalPages()-1}">
						<a
							href='WechatQuery?query=${query}&pageNo=${page.getNextPageNo()}'
							class="n">下一页&gt;</a>
					</c:if>
					<a
						href='WechatQuery?query=${query}&pageNo=${page.getBottomPageNo()}'
						class="n">最后一页</a>
				</c:if>
			</p>
		</div>
		<br clear=all>
	</div>
	<br clear=all>
	<div class="footer navbar-fixed-bottom">
    <ul class="pull-right">
    	<li>2015</li>
    	<li>|</li>
        <li>Copyright © <a href="http://iip.nju.edu.cn" target="blank" style="color:blue;">NJU_IIP_Group</a></li>
        <li>|</li>
        <li>Powered by <a href="http://lucene.apache.org/solr/" target="blank" style="color:blue;">Solr</a></li>
    </ul>
</div>

</body>
</html>