package com.fireant.oschat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by zhangdeyi on 15/7/30.
 */
@XStreamAlias("active")
public class Active extends BaseBean {

    /**
     * <active>
         <id>6173165</id>
         <portrait>
         http://static.oschina.net/uploads/user/126/253900_50.jpeg?t=1422505979000
         </portrait>
         <author>
         <![CDATA[ 火蚁 ]]>
         </author>
         <authorid>253900</authorid>
         <catalog>3</catalog>
         <objecttype>100</objecttype>
         <objectcatalog>0</objectcatalog>
         <objecttitle>
         <![CDATA[ ]]>
         </objecttitle>
         <appclient>1</appclient>
         <url/>
         <objectID>6173165</objectID>
         <message>
         <![CDATA[ 头号内存小吃货，好吃好喝的养得白白胖胖 ]]>
         </message>
         <commentCount>1</commentCount>
         <pubDate>2015-07-29 21:05:31</pubDate>
         <tweetimage>
         <![CDATA[
         http://static.oschina.net/uploads/space/2015/0729/210429_c6Qc_253900_thumb.png
         ]]>
         </tweetimage>
         <tweetattach/>
         </active>
     **/

    @XStreamAlias("portrait")
    private String portrait;
    @XStreamAlias("author")
    private String author;
    @XStreamAlias("authorid")
    private int authorid;
    @XStreamAlias("catalog")
    private int catalog;
    @XStreamAlias("objecttype")
    private int objecttype;
    @XStreamAlias("objectcatalog")
    private int objectcatalog;
    @XStreamAlias("objecttitle")
    private String objecttitle;
    @XStreamAlias("appclient")
    private int appclient;
    @XStreamAlias("url")
    private String url;
    @XStreamAlias("objectID")
    private int objectID;
    @XStreamAlias("message")
    private String message;
    @XStreamAlias("commentCount")
    private int commentCount;
    @XStreamAlias("pubDate")
    private String pubDate;
    @XStreamAlias("tweetimage")
    private String tweetimage;
    @XStreamAlias("tweetattach")
    private String tweetattach;

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
    }

    public int getCatalog() {
        return catalog;
    }

    public void setCatalog(int catalog) {
        this.catalog = catalog;
    }

    public int getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(int objecttype) {
        this.objecttype = objecttype;
    }

    public int getObjectcatalog() {
        return objectcatalog;
    }

    public void setObjectcatalog(int objectcatalog) {
        this.objectcatalog = objectcatalog;
    }

    public String getObjecttitle() {
        return objecttitle;
    }

    public void setObjecttitle(String objecttitle) {
        this.objecttitle = objecttitle;
    }

    public int getAppclient() {
        return appclient;
    }

    public void setAppclient(int appclient) {
        this.appclient = appclient;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTweetimage() {
        return tweetimage;
    }

    public void setTweetimage(String tweetimage) {
        this.tweetimage = tweetimage;
    }

    public String getTweetattach() {
        return tweetattach;
    }

    public void setTweetattach(String tweetattach) {
        this.tweetattach = tweetattach;
    }
}
