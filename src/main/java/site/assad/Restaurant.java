package site.assad;

import java.util.List;

/**
 * Author: Al-assad 余林颖
 * E-mail: yulinying_1994@outlook.com
 * Date: 2018/3/27 14:16
 * Description: 店铺储存 POJO
 */
public class Restaurant {

    //对对对没错，这就是明目张胆的偷懒，懒得做类型转换
    private String id;                //店铺id
    private String name;              //店铺名称
    private String falvors;           //风格标签
    private String rating;            //店铺评分
    private String orderNum;          //最近月销量
    private String deliverFee;        //配送费用
    private String deliverMinPrice;   //配置起步价
    private String deliverTime;       //配送时长：单位分钟
    private String address;           //店铺地址

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFalvors() {
        return falvors;
    }

    public void setFalvors(String falvors) {
        this.falvors = falvors;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getDeliverFee() {
        return deliverFee;
    }

    public void setDeliverFee(String deliverFee) {
        this.deliverFee = deliverFee;
    }

    public String getDeliverMinPrice() {
        return deliverMinPrice;
    }

    public void setDeliverMinPrice(String deliverMinPrice) {
        this.deliverMinPrice = deliverMinPrice;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", falvors='" + falvors + '\'' +
                ", rating=" + rating +
                ", orderNum=" + orderNum +
                ", deliverFee=" + deliverFee +
                ", deliverMinPrice=" + deliverMinPrice +
                ", deliverTime=" + deliverTime +
                ", address='" + address + '\'' +
                '}';
    }
}
