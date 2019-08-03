package com.bzz.cloud.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : yang qianli
 * @email: 624003618@qq.com
 * @Date: 2019-05-30 13-25
 * @Modified by:
 * @Description:
 */
public class XiaoShouShouRuModel extends BaseRowModel {

   // private String id;

    @ExcelProperty(index = 1)
    private String gongchang;
    @ExcelProperty(index = 2)
    private String daqu;
    @ExcelProperty(index = 3)
    private String chengshiqun;
    @ExcelProperty(index = 4)
    private String yewuyuan;
    @ExcelProperty(index = 5)
    private String kehudaima;
    @ExcelProperty(index = 6)
    private String kehumingcheng;
    @ExcelProperty(index = 7)
    private String dapinleimiaoshu;
    @ExcelProperty(index = 8)
    private String yijipinleimiaoshu;
    @ExcelProperty(index = 9)
    private String erjipinleimiaoshu;
    @ExcelProperty(index = 10)
    private String sanjipinleimiaoshu;
    @ExcelProperty(index = 11)
    private String chanpinxianmiaoshu;
    @ExcelProperty(index = 12)
    private String wuliaobianma;

    @ExcelProperty(index = 13)
    private String wuliaomiaoshu;

    @ExcelProperty(index = 14)
    private Integer xiang;
    @ExcelProperty(index = 15)
    private BigDecimal dun;

    @ExcelProperty(index = 16)
    private BigDecimal xiaoshoushouru;
    @ExcelProperty(index = 17)
    private BigDecimal jingzhi;
    @ExcelProperty(index = 18)
    private BigDecimal shuie;
    @ExcelProperty(index = 19)
    private BigDecimal zhanluejiajine;
    @ExcelProperty(index = 20)
    private BigDecimal zhekoujine;
    @ExcelProperty(index = 21)
    private BigDecimal zhekoubaifenbi;
    @ExcelProperty(index = 22)
    private String shoudafangjincheng;
    @ExcelProperty(index = 23,format = "yyyy/MM/dd")
    private Date fapiaoriqi;
    @ExcelProperty(index = 24)
    private String dingdanhaoma;
    @ExcelProperty(index = 25,format = "yyyy/MM/dd")
    private Date danjuriqi;
    @ExcelProperty(index = 26)
    private String kuchengdidian;
    @ExcelProperty(index = 27)
    private String caigoudingdanhao;


   /* private String remarks;	// 备注
    private long createBy;	// 创建者
    private Date createDate;	// 创建日期
    private long updateBy;	// 更新者
    private Date updateDate;	// 更新日期
    private String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）*/


    public String getGongchang() {
        return gongchang;
    }

    public void setGongchang(String gongchang) {
        this.gongchang = gongchang;
    }

    public String getDaqu() {
        return daqu;
    }

    public void setDaqu(String daqu) {
        this.daqu = daqu;
    }

    public String getChengshiqun() {
        return chengshiqun;
    }

    public void setChengshiqun(String chengshiqun) {
        this.chengshiqun = chengshiqun;
    }

    public String getYewuyuan() {
        return yewuyuan;
    }

    public void setYewuyuan(String yewuyuan) {
        this.yewuyuan = yewuyuan;
    }

    public String getKehudaima() {
        return kehudaima;
    }

    public void setKehudaima(String kehudaima) {
        this.kehudaima = kehudaima;
    }

    public String getKehumingcheng() {
        return kehumingcheng;
    }

    public void setKehumingcheng(String kehumingcheng) {
        this.kehumingcheng = kehumingcheng;
    }

    public String getDapinleimiaoshu() {
        return dapinleimiaoshu;
    }

    public void setDapinleimiaoshu(String dapinleimiaoshu) {
        this.dapinleimiaoshu = dapinleimiaoshu;
    }

    public String getYijipinleimiaoshu() {
        return yijipinleimiaoshu;
    }

    public void setYijipinleimiaoshu(String yijipinleimiaoshu) {
        this.yijipinleimiaoshu = yijipinleimiaoshu;
    }

    public String getErjipinleimiaoshu() {
        return erjipinleimiaoshu;
    }

    public void setErjipinleimiaoshu(String erjipinleimiaoshu) {
        this.erjipinleimiaoshu = erjipinleimiaoshu;
    }

    public String getSanjipinleimiaoshu() {
        return sanjipinleimiaoshu;
    }

    public void setSanjipinleimiaoshu(String sanjipinleimiaoshu) {
        this.sanjipinleimiaoshu = sanjipinleimiaoshu;
    }

    public String getChanpinxianmiaoshu() {
        return chanpinxianmiaoshu;
    }

    public void setChanpinxianmiaoshu(String chanpinxianmiaoshu) {
        this.chanpinxianmiaoshu = chanpinxianmiaoshu;
    }

    public String getWuliaobianma() {
        return wuliaobianma;
    }

    public void setWuliaobianma(String wuliaobianma) {
        this.wuliaobianma = wuliaobianma;
    }

    public String getWuliaomiaoshu() {
        return wuliaomiaoshu;
    }

    public void setWuliaomiaoshu(String wuliaomiaoshu) {
        this.wuliaomiaoshu = wuliaomiaoshu;
    }

    public Integer getXiang() {
        return xiang;
    }

    public void setXiang(Integer xiang) {
        this.xiang = xiang;
    }

    public BigDecimal getDun() {
        return dun;
    }

    public void setDun(BigDecimal dun) {
        this.dun = dun;
    }

    public BigDecimal getXiaoshoushouru() {
        return xiaoshoushouru;
    }

    public void setXiaoshoushouru(BigDecimal xiaoshoushouru) {
        this.xiaoshoushouru = xiaoshoushouru;
    }

    public BigDecimal getJingzhi() {
        return jingzhi;
    }

    public void setJingzhi(BigDecimal jingzhi) {
        this.jingzhi = jingzhi;
    }

    public BigDecimal getShuie() {
        return shuie;
    }

    public void setShuie(BigDecimal shuie) {
        this.shuie = shuie;
    }

    public BigDecimal getZhanluejiajine() {
        return zhanluejiajine;
    }

    public void setZhanluejiajine(BigDecimal zhanluejiajine) {
        this.zhanluejiajine = zhanluejiajine;
    }

    public BigDecimal getZhekoujine() {
        return zhekoujine;
    }

    public void setZhekoujine(BigDecimal zhekoujine) {
        this.zhekoujine = zhekoujine;
    }

    public BigDecimal getZhekoubaifenbi() {
        return zhekoubaifenbi;
    }

    public void setZhekoubaifenbi(BigDecimal zhekoubaifenbi) {
        this.zhekoubaifenbi = zhekoubaifenbi;
    }

    public String getShoudafangjincheng() {
        return shoudafangjincheng;
    }

    public void setShoudafangjincheng(String shoudafangjincheng) {
        this.shoudafangjincheng = shoudafangjincheng;
    }

    public Date getFapiaoriqi() {
        return fapiaoriqi;
    }

    public void setFapiaoriqi(Date fapiaoriqi) {
        this.fapiaoriqi = fapiaoriqi;
    }

    public String getDingdanhaoma() {
        return dingdanhaoma;
    }

    public void setDingdanhaoma(String dingdanhaoma) {
        this.dingdanhaoma = dingdanhaoma;
    }

    public Date getDanjuriqi() {
        return danjuriqi;
    }

    public void setDanjuriqi(Date danjuriqi) {
        this.danjuriqi = danjuriqi;
    }

    public String getKuchengdidian() {
        return kuchengdidian;
    }

    public void setKuchengdidian(String kuchengdidian) {
        this.kuchengdidian = kuchengdidian;
    }

    public String getCaigoudingdanhao() {
        return caigoudingdanhao;
    }

    public void setCaigoudingdanhao(String caigoudingdanhao) {
        this.caigoudingdanhao = caigoudingdanhao;
    }

    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }*/
}
