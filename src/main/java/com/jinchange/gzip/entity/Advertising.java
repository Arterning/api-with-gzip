package com.jinchange.gzip.entity;

import lombok.Data;

/**
 * @ClassName: Project
 * @Author zhangjin
 * @Date 2022/3/24 20:42
 * @Description: adTag是广告渲染的HTML代码 超级大的数据库都是用text类型存放的
 * 太大的数据传输的弊端
 * 1. 占用网络贷款 云产品费用飙升
 * 2. 网络传输耗时加大
 * 思路
 * 先在接口调用方对数据进行压缩 使用GZIP
 * 接口拿到数据后进行解压缩
 * 这样做的结果
 * 1. 额外更多CPU计算资源 对于这种情况 用掉一些CPU资源换来更快的网络传输是划算的
 * 2. 可能会影响原有的其他接口
 */
@Data
public class Advertising {
    private String adName;
    private String adTag;
}
