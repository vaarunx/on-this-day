package onThisDay.onThisDay;

import java.util.*;

//All these classes are created with the same schema as the API Response 
//https://api.wikimedia.org/wiki/API_reference/Feed/On_this_day

//Main Class that contains all the articles
class TodaysHistoryInfo {

	ArrayList<Selected> selected_array = new ArrayList<Selected>();
	ArrayList<Birth> births_array = new ArrayList<Birth>();
	ArrayList<Death> deaths_array = new ArrayList<Death>();
	ArrayList<Holiday> holidays_array = new ArrayList<Holiday>();
	ArrayList<Event> events_array = new ArrayList<Event>();
	private short year;
	
	public short getYear() { return year; }
	public void setYear(short year) {
	       this.year = year;
	   }
	public ArrayList<Selected> getSelected() {
	       return selected_array;
	   }
	public void setSelected(ArrayList<Selected> S) {
		this.selected_array = S;
	}
	public ArrayList<Event> getEvents() {
	       return events_array;
	   }
	public void setEvents(ArrayList<Event> E) {
		this.events_array = E;
	}
	public ArrayList<Birth> getBirths() {
	       return births_array;
	   }
	public void setBirths(ArrayList<Birth> B) {
		this.births_array = B;
	}
	public ArrayList<Death> getDeaths() {
	       return deaths_array;
	   }
	public void setDeaths(ArrayList<Death> D) {
		this.deaths_array = D;
	}
	public ArrayList<Holiday> getHolidays() {
	       return holidays_array;
	   }
	public void setHolidays(ArrayList<Holiday> H) {
		this.holidays_array = H;
	}
		   
	}

//Java's default date class is used in the pages class, so this class had to be renamed.
class CustomDate {
private int month;
private int day;
private int year;
public int getMonth() { return month; }
public void setMonth(int month) { this.month = month; }
public int getDay() { return day; }
public void setDay(int day) { this.day = day; }
public int getYear() { return year; }
public void setYear(int year) { this.year = year; }
}


class Namespace{
    public int id;
    public String text;
    public int getId() { return this.id;}
    public String getText() { return this.text; }
    public void setId(int id) { this.id=id; }
    public void setText(String text) { this.text=text; }
    Namespace(){
    	this.id=0;
    	this.text=null;
    }
    Namespace(int id, String text){
    	this.id=id;
    	this.text=text;
    }
    Namespace(Namespace obj){
    	this.id=obj.id;
    	this.text=obj.text;
    }
}

class Titles{
    public String canonical;
    public String normalized;
    public String display;
    public String getCanonical() { return this.canonical; }
    public String getNormalized() { return this.normalized; }
    public String getDisplay() { return this.display; }
    public void setCanonical(String canonical) { this.canonical=canonical; }
    public void setNormalized(String normalized) { this.normalized=normalized; }
    public void setDisplay(String display) { this.display=display; }
    Titles(){
    	this.canonical=null;
    	this.normalized=null;
    	this.display=null;
    }
    Titles(String canonical, String normalized, String display){
    	this.canonical=canonical;
    	this.normalized=normalized;
    	this.display=display;
    }
    Titles(Titles obj){
    	this.canonical=obj.canonical;
    	this.normalized=obj.normalized;
    	this.display=obj.display;
    }
}

class Originalimage{
    public String source;
    public int width;
    public int height;
    public String getSource() { return this.source; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    public void setSource(String source) { this.source=source; }
    public void setWidth(int width) { this.width=width; }
    public void setHeight(int height) { this.height=height; }
    Originalimage(){
    	this.source=null;
    	this.width=0;
    	this.height=0;
    }
    Originalimage(String source, int width, int height){
    	this.source=source;
    	this.width=width;
    	this.height=height;
    }
    Originalimage(Originalimage obj){
    	this.source=obj.source;
    	this.width=obj.width;
    	this.height=obj.height;
    }
}

class Thumbnail extends Originalimage{
    Thumbnail(){
    	this.source=null;
    	this.width=0;
    	this.height=0;
    }
    Thumbnail(String source, int width, int height){
    	this.source=source;
    	this.width=width;
    	this.height=height;
    }
    Thumbnail(Originalimage obj){
    	this.source=obj.source;
    	this.width=obj.width;
    	this.height=obj.height;
    }
}

class Desktop{
    public String page;
    public String revisions;
    public String edit;
    public String talk;
    public String getPage() { return this.page; }
    public String getRevisions() { return this.revisions; }
    public String getEdit() { return this.edit; }
    public String getTalk() { return this.talk; }
    public void setPage(String page) { this.page=page; }
    public void setRevisions(String revisions) { this.revisions=revisions; }
    public void setEdit(String edit) { this.edit=edit; }
    public void setTalk(String talk) { this.talk=talk; }
    Desktop(){
    	this.page=null;
    	this.revisions=null;
    	this.edit=null;
    	this.talk=null;
    }
    Desktop(String page, String revisions, String edit, String talk){
    	this.page=page;
    	this.revisions=revisions;
    	this.edit=edit;
    	this.talk=talk;
    }
    Desktop(Desktop obj){
    	this.page=obj.page;
    	this.revisions=obj.revisions;
    	this.edit=obj.edit;
    	this.talk=obj.talk;
    }
}

class Mobile extends Desktop{
    Mobile(){
    	this.page=null;
    	this.revisions=null;
    	this.edit=null;
    	this.talk=null;
    }
    Mobile(String page, String revisions, String edit, String talk){
    	this.page=page;
    	this.revisions=revisions;
    	this.edit=edit;
    	this.talk=talk;
    }
    Mobile(Desktop obj){
    	this.page=obj.page;
    	this.revisions=obj.revisions;
    	this.edit=obj.edit;
    	this.talk=obj.talk;
    }
}

class ContentUrls{
    public Desktop desktop;
    public Mobile mobile;
    public Desktop getDesktop() { return this.desktop; }
    public Mobile getMobile() { return this.mobile; }
    public void setDesktop(Desktop obj) { this.desktop = new Desktop(obj); }
    public void setMobile(Mobile obj) { this.mobile = new Mobile(obj); }
    ContentUrls(){
    	this.desktop = new Desktop();
    	this.mobile = new Mobile();
    }
    ContentUrls(Desktop obj1, Mobile obj2){
    	this.desktop = new Desktop(obj1);
    	this.mobile = new Mobile(obj2);
    }
    ContentUrls(ContentUrls obj){
    	this.desktop = new Desktop(obj.desktop);
    	this.mobile = new Mobile(obj.mobile);
    }
}

class Coordinates{
    public double lat;
    public double lon;
    public double getLat() { return this.lat; }
    public double getLon() { return this.lon; }
    public String getCoords() { return Double.toString(lat)+"N, "+Double.toString(lon)+"E."; }
    public void setLat(double lat) { this.lat=lat; }
    public void setLon(double lon) { this.lon=lon; }
    Coordinates(){
    this.lat=0.0;
    this.lon=0.0;
    }
    Coordinates(double lat, double lon){
        this.lat=lat;
        this.lon=lon;
    }
    Coordinates(Coordinates obj){
        this.lat=obj.lat;
        this.lon=obj.lon;
    }    
}


class Page{
    public String type;
    public String title;
    public String displaytitle;
    public Namespace namespace;
    public String wikibase_item;
    public Titles titles;
    public int pageid;
    public Thumbnail thumbnail;
    public Originalimage originalimage;
    public String lang;
    public String dir;
    public String revision;
    public String tid;
    public Date timestamp;
    public String description;
    public String description_source;
    public ContentUrls content_urls;
    public String extract;
    public String extract_html;
    public String normalizedtitle;
    public Coordinates coordinates;
    
    public String getType() { return this.type; }
    public String getTitle() { return this.title; }
    public String getDisplaytitle() { return this.displaytitle; }
    public Namespace getNamespace() { return this.namespace; }
    public String getWikibaseItem() { return this.wikibase_item; }
    public Titles getTitles() {return this.titles; }
    public int getPageId() { return this.pageid; }
    public Thumbnail getThumbnail() { return this.thumbnail; }
    public Originalimage getOriginalImage() { return this.originalimage; }
    public String getLang() { return this.lang; }
    public String getDir() { return this.dir; }
    public String getRevision() { return this.revision; }
    public String getTid() { return this.tid; }
    public Date getTimestamp() { return this.timestamp; }
    public String getTimestampString() { return this.timestamp.toString(); }
    public String getDescription() { return this.description; }
    public String getDescription_source() { return this.description_source; }
    public ContentUrls getContentUrls() { return this.content_urls; }
    public String getExtract() { return this.extract; }
    public String getExtarctHtml() { return this.extract_html; }
    public String getNormalizedTitle() { return this.normalizedtitle; }
    public Coordinates getLocation() { return this.coordinates; }
    
    public void setType(String type) { this.type=type; }
    public void setTitle(String title) {this.title=title; }
    public void setDisplaytitle(String displaytitle) { this.displaytitle=displaytitle; }
    public void setNamespace(Namespace obj) { this.namespace = new Namespace(obj); }
    public void setWikibaseItem(String wikibase_item) { this.wikibase_item=wikibase_item; }
    public void setTitles(Titles obj) { this.titles = new Titles(obj); }
    public void setPageId(int pageid) { this.pageid=pageid; }
    public void setThumbnail(Thumbnail obj) { this.thumbnail = new Thumbnail(obj); }
    public void setOriginalImage(Originalimage obj) { this.originalimage = new Originalimage(obj); }
    public void setLang(String lang) { this.lang=lang; }
    public void setDir(String dir) { this.dir=dir; }
    public void setRevision(String revision) { this.revision=revision; }
    public void setTid(String tid) { this.tid=tid; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public void setDescription(String description) { this.description=description; }
    public void setDescription_source(String description_source) { this.description_source=description_source; }
    public void setContentUrls(ContentUrls obj) { this.content_urls = new ContentUrls(obj); }
    public void setExtract(String extract) { this.extract=extract; }
    public void setExtarctHtml(String extract_html) { this.extract_html=extract_html; }
    public void setNormalizedTitle(String normalizedtitle) { this.normalizedtitle=normalizedtitle; }
    public void setLocation(Coordinates obj) { this.coordinates = new Coordinates(obj); }
}


class Selected{
    public String text;
    public ArrayList<Page> pages;
    public int year;
    public String getText() { return this.text; }
    public ArrayList<Page> getArticles() { return this.pages; }
    public int getYear() { return this.year; }
    public void setText(String text) { this.text=text; }
    public void setArticles(ArrayList<Page> obj) { this.pages = obj; }
    public void setYear(int year) { this.year=year; }
    Selected(){
    	this.text=null;
    	this.pages=null;
    	this.year=0;
    }
    Selected(String text, ArrayList<Page> pages, int year){
    	this.text=text;
    	this.pages=pages;
    	this.year=year;
    }
    Selected(Selected obj){
    	this.text=obj.text;
    	this.pages=obj.pages;
    	this.year=obj.year;
    }
}

class Birth extends Selected {
    Birth(){
    	this.text=null;
    	this.pages=null;
    	this.year=0;
    }
    Birth(String text, ArrayList<Page> pages, int year){
    	this.text=text;
    	this.pages=pages;
    	this.year=year;
    }
    Birth(Birth obj){
    	this.text=obj.text;
    	this.pages=obj.pages;
    	this.year=obj.year;
    }
}

class Death extends Selected {
    Death(){
    	this.text=null;
    	this.pages=null;
    	this.year=0;
    }
    Death(String text, ArrayList<Page> pages, int year){
    	this.text=text;
    	this.pages=pages;
    	this.year=year;
    }
    Death(Death obj){
    	this.text=obj.text;
    	this.pages=obj.pages;
    	this.year=obj.year;
    }
}

class Event extends Selected {
    Event(){
    	this.text=null;
    	this.pages=null;
    	this.year=0;
    }
    Event(String text, ArrayList<Page> pages, int year){
    	this.text=text;
    	this.pages=pages;
    	this.year=year;
    }
    Event(Event obj){
    	this.text=obj.text;
    	this.pages=obj.pages;
    	this.year=obj.year;
    }
}

class Holiday extends Selected {
    Holiday(){
    	this.text=null;
    	this.pages=null;
    }
    Holiday(String text, ArrayList<Page> pages, int year){
    	this.text=text;
    	this.pages=pages;
    }
    Holiday(Holiday obj){
    	this.text=obj.text;
    	this.pages=obj.pages;
    }
}






public class PojoClasses {}