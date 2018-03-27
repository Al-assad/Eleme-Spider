package site.assad;

import java.io.*;

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2018/3/27 20:41
 * Description: 保存为 CSV 文件
 */
public class CSVSaver {

    private final String savePath;
    private PrintWriter out ;

    public CSVSaver(String savePath) throws IOException {
        this.savePath = savePath;
        out = new PrintWriter(savePath,"UTF-8");
        out.println("店铺id,店铺名称,风格标签,店铺评分,月销量,配送费用（元）,配置起步价（元）,配送时长（分钟）,店铺地址");
    }

    public void save(Restaurant restaurant){
        String line = restaurant.getId() + ","
                + restaurant.getName() + ","
                + restaurant.getFalvors() + ","
                + restaurant.getRating() + ","
                + restaurant.getOrderNum() + ","
                + restaurant.getDeliverFee() + ","
                + restaurant.getDeliverMinPrice() + ","
                + restaurant.getDeliverTime() + ","
                + restaurant.getAddress();
        out.println(line);
        out.flush();
    }







}
