package site.assad;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.utils.HttpConstant;

import java.io.IOException;


/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2018/3/27 16:01
 * Description: 程序主入口；
 *              实现 https://h5.ele.me/restapi/shopping/v3/restaurants 页面 json 抽取逻辑;
 *              定位点：深圳大学，南山区（22.533719，113.936091）
 */
public class RestaurantPageProcessor implements PageProcessor{

    private static volatile int count = 0;

    private CSVSaver csvSaver = null;

    public RestaurantPageProcessor(String csvFilePath) throws IOException {
        csvSaver = new CSVSaver(csvFilePath);
    }

    //模拟 firefox 浏览器请求头
    private Site site = Site.me()
            .setRetryTimes(3)
            .setTimeOut(30000)
            .setSleepTime(500)
            .setUseGzip(true)
            .addHeader("Accept","*/*")
            .addHeader("Accept-Encoding","gzip, deflate, br")
            .addHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
            .addHeader("Connection","keep-alive")
            .addHeader("Host","h5.ele.me")
            .addHeader("origin","https://h5.ele.me")
            .addHeader("Referer","https://h5.ele.me/msite/food/")
            .addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")
            .addHeader("Content-Type","application/x-www-form-urlencoded");

    private final static int PER_LIMIT = 20;  //每次抓取数据的限制数量
    private final static int PAGE_LIMIT = 1000; //抓取页面总数限制，经过试验 1000 为上限

    public final static String LATITUDE = "22.533719";    //定位纬度
    public final static String LONGITUDE = "113.936091";   //定位经度

    @Override
    public void process(Page page) {

        for(String rawText : new JsonPathSelector("$.items[*].restaurant").selectList(page.getRawText())){

            String id = new JsonPathSelector("$.id").select(rawText);
            String name = new JsonPathSelector("$.name").select(rawText);
            String falvors = new JsonPathSelector( "$.flavors[0].name").select(rawText);
            String rating = new JsonPathSelector( "$.rating").select(rawText);
            String orderNum = new JsonPathSelector("$.recent_order_num").select(rawText);
            String deliverFee = new JsonPathSelector("$.float_delivery_fee").select(rawText);
            String deliverMinPrice = new JsonPathSelector("$.float_minimum_order_amount").select(rawText);
            String deliverTime = new JsonPathSelector("$.order_lead_time").select(rawText);
            String address = new JsonPathSelector( "$.address").select(rawText);

            Restaurant restaurant = new Restaurant();
            restaurant.setId(id);
            restaurant.setName(name);
            restaurant.setFalvors(falvors);
            restaurant.setRating(rating);
            restaurant.setOrderNum(orderNum);
            restaurant.setDeliverFee(deliverFee);
            restaurant.setDeliverMinPrice(deliverMinPrice);
            restaurant.setDeliverTime(deliverTime);
            restaurant.setAddress(address);

            System.out.println(restaurant);
            csvSaver.save(restaurant);
            count++;
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    //程序运行入口
    public static void main(String[] args) throws InterruptedException, IOException {

        Spider spider = Spider.create(new RestaurantPageProcessor("./eleme-data.csv"));
        for(int offset = 0; offset <= PAGE_LIMIT; offset += PER_LIMIT){
            String url = "https://h5.ele.me/restapi/shopping/v3/restaurants?latitude="+ LATITUDE
                    +"&longitude="+ LONGITUDE
                    +"&keyword=&offset=" + offset
                    +"&limit=" + PER_LIMIT
                    +"&extras[]=activities&extras[]=tags&terminal=h5";

            Request request = new Request(url);
            request.setMethod(HttpConstant.Method.GET);
            spider.addRequest(request);
        }

        spider.run();

        System.out.println("items count: " + count);
    }
}
