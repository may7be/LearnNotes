
package com.zg.android_utils.bean;

import android.support.v4.util.ArrayMap;

import java.util.Map;

public enum SignalType {

    /**
     * 1boolean，2float
     */
    BOOLEAN(1), FLOAT(2),

    /**
     * 读取指令：桶液位
     */
    GET_BUCKET_WATER_FLOW(1093),
    /**
     * 读取指令：桶开度
     */
    GET_BUCKET_WATER_OPENING(121),
    /**
     * 读取指令：PID自动手动状态  0自动 ，1手动
     */
    GET_BUCKET_AM(226),
    /**
     * 读取指令：密度PID自动手动状态
     */
    GET_DENSITY_AM(148),
    /**
     * 读取指令：流量PID自动手动状态
     */
    GET_FLOW_AM(163),
    /**
     * 读取指令：流量
     */
    GET_FLOW(133),
    /**
     * 读取指令：阀开度
     */
    GET_VALVE_OPENING(1101),
    /**
     * 读取指令：频率
     */
    GET_RATE(1054),
    /**
     * 读取指令：集中就地,0就地，1集控
     */
    GET_CONTROL(227),

    /**
     * 读取指令：TCS分选机 实测密度
     */
    GET_DENSITY_SHICE(1094),

    /**
     * 读取指令：TCS分选机 桶下属标签当前流量
     */
    GET_BARREL_FLUID(1352),

    /**
     * 读取指令：TCS分选机 分选密度
     */
    GET_DENSITY_FENXUAN(1118),
    /**
     * 读取指令：原煤瞬时量
     */
    GET_COAL_MINUTE(141),
    /**
     * 读取指令：原煤累积量
     */
    GET_COAL_TOTAL(115),

    /**
     * 读取指令：电流
     */
    GET_ELECTRIC(128),

    GET_FREQUENCY(1140,"读取频率"),

    /**
     * 读取指令：关到位
     */
    GET_CLOSED(132),
    /**
     * 读取指令：开到位
     */
    GET_OPENED(112),

    /**
     * 读取指令：设定的频率
     */
    GET_EDIT_RATE(173),
    /**
     * 读取指令：设定的密度
     */
    GET_EDIT_DENSITY(174),
    /**
     * 读取指令：设定的开度
     */
    GET_EDIT_OPENED(175),
    /**
     * 读取指令：设定的流量
     */
    GET_EDIT_FLOW(321),
    /**
     * 读取指令：设定的液位
     */
    GET_EDIT_WATER_FLOW(322),
    GET_EDIT_PRESSURE(1053),
    /**
     * 读取指令：压力
     */
    GET_PRESSURE(1052),
    GET_RULIAOPRESSURE(140),
    /**
     * 读取指令：油压
     */
    GET_OIL_PRESSURE(188),
    GET_NIUJU(1061),
    /**
     * 读取指令：设定高桶位
     */
    GET_EIDT_HIGHT_POSITION(323),
    /**
     * 读取指令：设定低桶位
     */
    GET_EIDT_LOW_POSITION(324),
    /**
     * 读取指令：设定高桶位
     */
    GET_EIDT_HIGHT_DENSITY_POSITION(189),
    /**
     * 读取指令：设定低桶位
     */
    GET_EIDT_LOW_DENSITY_POSITION(190),

    /**
     * 读取指令：月累计带煤量
     */
    GET_MONTH_BAND_COAL_AMOUNT(1047),
    GET_QIANSU_BAOHU1(1058),
    GET_QIANSU_BAOHU2(335),
    GET_PAOPIAN_BAOHU(243),
    GET_DUOMEI_BAOHU(233),
    GET_SILIE_BAOHU(235),
    GET_YANWU_BAOHU(230),
    GET_PENYOU_BAOHU(343),
    GET_WARNING(198),
    GET_WENDU1(1108),
    GET_WENDU2(1109),
    GET_WENDU3(1110),
    GET_WENDU4(1111),
    GET_WENDU5(1112),
    GET_JITOUWENDU1(118),
    GET_JITOUWENDU2(119),
    GET_JITOUWENDU3(1115),
    GET_JITOUWENDU4(1116),
    GET_ZHUZHOUCHENGCHILUNXIANGLIULIANG1(308),
    GET_FUZHOUWOLUNLIULIANG(309),
    GET_FUZHOUPIDAILUNDUANLIULIANG(310),
    GET_CHILUNXIANGLIULIANG(311),
    GET_ZHUZHOUCHENGQUDUANLIULIANG(312),
    GET_ZHUZHOUCHENGCHILUNXIANGLIULIANG2(1060),
    GET_ZHOUCHENGWENDU1(119),
    GET_ZHOUCHENGWENDU2(118),
    GET_ZHOUCHENGWENDU3(1115),
    GET_ZHOUCHENGWENDU4(1116),
    GET_DINGZIWENDU(123),
    GET_FANZHUANG(213),
    GET_ZHENGZHUAN(215),
    GET_HUIFENN(1098),
    GET_YOUYAGUZHANG(188),
    GET_PRESURE_ZIDONG(995),
    GET_DENSITY_ZIDONG(996),// 密度pid选择自动
    GET_YEWEI_ZIDONG(997),
    GET_PIDAICHENG(1050),
    GET_BANLEIJILIANG(1049),
    GET_PID_FLUID_CONTROL(1351),    // 流量PID选择自动
    //GET_DANGQIANJISHI(0),
    //GET_GUOCHENGJISHI(0),
    GET_DANGQIANMOSHI(0),
    GET_GUANDAOWEI(132),
    GET_KAIDAOWEI(112),
    GET_YANWUGUZHANG(267),
    GET_QIANSUGUZHANG(271),
    GET_QIANSUGUZHANG2(336),
    GET_DUOMEIGUZHANG(272),
    GET_SILIEGUZHANG(274),
    GET_PENYOUGUZHANG(274),
    GET_PAOPIANGUZHANG(268),
    GET_QINGSAOQI(1662),
    GET_DANGQIANYALI(1052),
    GET_SHEDINGYALI(1053),
    GET_LASHENGGUZHANG(270),
    GET_LASHENGGUZHANG1(352),
    GET_LASHENGGUZHANG2(353),
    GET_LASHENGGUZHANG3(354),
    GET_LASHENGGUZHANG4(355),
    GET_LASHENGGUZHANG5(356),
    GET_LASHENGGUZHANG6(357),
    GET_LASHENGGUZHANG7(358),
    GET_QINGPAOPIANGUZHANG1(344),
    GET_QINGPAOPIANGUZHANG2(345),
    GET_QINGPAOPIANGUZHANG3(346),
    GET_QINGPAOPIANGUZHANG4(347),
    GET_DEVICESTATUS(1200),
    GET_TINGGIGHFLOWEDIT(1103),
    GET_TINGLOWFLOWEDIT(1104),
    GET_YEWEIXUANZELIANSUO(244),
    /**
     * 平均顶水流量
     */
    GET_AVERAGE_FLUID(1525),

    /**
     * 控制指令：桶位，PID自动设定液位
     */
    SET_BUCKET_LEVEL(177),
    /**
     * 控制指令：桶位，PID手动设定开度
     */
    SET_BUCKET_OPENING(175),
    /**
     * 控制指令：桶位，0自动1手动切换
     */
    SET_BUCKET_AM_CHANGE(145),

    /**
     * 控制指令：流量自动设定(流量)
     */
    SET_FLOW_AUTO(150),
    /**
     * 控制指令：流量手动设定(开度)
     */
    SET_FLOW_MANUAL(148),
    /**
     * 控制指令：流量自动手动切换
     */
    SET_FLOW_AM_CHANGE(161),

    /**
     * 控制指令：密度自动设定(密度)
     */
    SET_DENSITY_AUTO(155),
    /**
     * 控制指令：密度手动设定(频率)
     */
    SET_DENSITY_MANUAL(161),
    /**
     * 控制指令：密度手动自动切换
     */
    SET_DENSITY_AM_CHANGE(147),

    /**
     * 控制指令：集中就地切换,0就地，1集控
     */
    SET_CONTROL(227),

    /**
     * 控制指令：集控启动
     */
    SET_START(156),
    /**
     * 控制指令：集控停止
     */
    SET_STOP(158),

    /**
     * 控制指令：故障复位
     */
    SET_RESET(159),
    /**
     * 控制指令：检修
     */
    SET_REPAIR(164),

    /**
     * 控制指令：密度校正
     */
    SET_DENSITY_REGULATE(171),

    /**
     * 控制指令：集控关阀
     */
    SET_CLOSE_VALVE(239),
    /**
     * 控制指令：集控开阀
     */
    SET_OPEN_VALVE(238),

    /**
     * 控制指令：设定频率;频率给定
     */
    SET_RATE(173),
    SET_PRESURE(1053),
    /**
     * 控制指令：设定高桶位
     */
    SET_HIGHT_POSITION(323),
    /**
     * 控制指令：设定低桶位
     */
    SET_LOW_POSITION(324),
    SET_FANZHUANG(229),
    SET_ZHENGZHUAN(1057),
    SET_KAIFA(238),
    SET_GUANFA(239),
    /**
     * 设定平均顶水流量
     */
    SET_BARREL_FLUID(176),

    RUN_STATE(1406, "设备运行状态"),//1运行，0停止

    /**
     * 压滤机设定
     */
    FILTER_SKB(1152, "松开"),
    FILTER_QBB(1153, "取板"),
    FILTER_LBB(1154, "拉板"),
    FILTER_YJB(1155, "压紧"),
    FILTER_JLB(1157, "进料"),
    FILTER_CFB(1162, "吹风"),
    FILTER_JLB_END(1158, "进料结束"),
    FILTER_PAUSE(1168, "暂停"),
    FILTER_SDZD(1169, "手动自动"),
    FILTER_JT(1170, "急停"),
    FILTER_CX(1201, "次序"),
    //获取服务器计算的板数
    FILTER_BS(1202, "板数"),
    //从PLC获取板数逻辑，比如：FILTER_BS_TAG_1信号的值为1就读取FILTER_BS_1做为真实板数；
    // FILTER_BS_TAG_2的值为1就读取FILTER_BS_2做为真实板数。三个TAG同时只会有一个值为1.
    FILTER_BS_1(1399, "板数"),
    FILTER_BS_2(1400, "板数"),
    FILTER_BS_3(1401, "板数"),
    FILTER_BS_TAG_1(1403, "板数"),
    FILTER_BS_TAG_2(1404, "板数"),
    FILTER_BS_TAG_3(1405, "板数"),

    FILTER_ZT(1203, "状态"),
    FILTER_DQJS(1288, "当前计时"),
    FILTER_YCJD(1172, "远程/就地"),//1是远程0为就地
    FILTER_GCJS(1289, "过程计时"),
    FILTER_SMART(1243,"智能/手动"),//1是智能，0是手动

    //压滤故障
    FILTER_SKCS(1175,"松开超时"),
    FILTER_QBCS(1176, "取板超时"),
    FILTER_LBCS(1177, "拉板超时"),
    FILTER_YJCS(1178, "压紧超时或超限"),
    FILTER_JLCS(1179, "进料超时"),
    FILTER_YZCS(1180, "压榨超时"),
    FILTER_PKCS(1181, "排空超时"),
    FILTER_CFCS(1182, "吹风超时"),
    FILTER_JLF1_OPEN_CS(1183, "进料阀1打开超时"),
    FILTER_JLF1_CLOSE_CS(1184, "进料阀1关闭超时"),
    FILTER_JLF2_OPEN_CS(1185, "进料阀2打开超时"),
    FILTER_JLF2_CLOSE_CS(1186, "进料阀2关闭超时"),
    FILTER_YZF_OPEN_CS(1187, "压榨阀打开超时"),
    FILTER_YZF_CLOSE_CS(1188, "压榨阀关闭超时"),
    FILTER_PKF_OPEN_CS(1189, "排空阀打开超时"),
    FILTER_PKF_CLOSE_CS(1190, "排空阀关闭超时"),
    FILTER_CFF_OPEN_CS(1191, "吹风阀打开超时"),
    FILTER_CFF_CLOSE_CS(1192, "吹风阀关闭超时"),
    FILTER_HLF_OPEN_CS(1193, "回流阀打开超时"),
    FILTER_HLF_CLOSE_CS(1194, "回流阀关闭超时"),
    FILTER_BREAKDOWN(1245, "故障信号"),

    FILTER_FEED_OVER_ELECTRICITY(1371, "进料结束判断值"),

    NONG_SUO_ELECTRIC_LIMIT(1237, "浓缩机驱动电机电流阈值"),

    PROPORTIONAL_VALVE_KAI_DU(1117,"比例阀开度");



    SignalType(int code) {
        this.code = code;
    }

    SignalType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private static Map<Integer, SignalType> mapping = new ArrayMap<>();

    static {
        for (SignalType type : values()) {
            mapping.put(type.getCode(), type);
        }
    }

    public static SignalType valueOf(int value) {
        return mapping.get(value);
    }
}
