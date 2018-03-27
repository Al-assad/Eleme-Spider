# Eleme-Spider  
#### 饿了么商户数据爬虫  

#### 开发环境
* Itellij IDEA
* Java 1.8 
  
#### 测试环境
* Ubuntu 16.04
* Win10

#### 运行环境
* JRE 1.8 +
  
#### 采集数据项目
* 店铺id
* 店铺名称
* 风格标签
* 店铺评分
* 月销量
* 配送费用（元）
* 配置起步价（元）
* 配送时长（分钟）
* 店铺地址 
  
#### 数据采集说明
* 采集接口：https://h5.ele.me/restapi/shopping/v3/restaurants?latitude=22.533719&longitude=113.936091&keyword=&offset=0&limit=30&extras[]=activities&extras[]=tags&terminal=h5  
* 请求方法：GET  
* 响应正文格式：JSON
* 请求参数说明：
    * latitude：纬度
    * longitude：经度
    * offset：跳过分页数量
    * limit： 分页记录限制
* 数据保存格式：CSV
  
#### 样本数据
以深圳大学，南山区（22.533719，113.936091）为定位点，样本 csv 位于仓库根目录下的 eleme-data.csv
