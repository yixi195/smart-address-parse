package com.ysl.smart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText etContent;
    private TextView tv_result;

    private TextView tv_province,tv_province_code;
    private TextView tv_city,tv_city_code;
    private TextView tv_area,tv_area_code;
    private TextView tv_addr;
    private TextView tv_name;
    private TextView tv_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etContent = findViewById(R.id.et_content);
        tv_result = findViewById(R.id.tv_result);
        tv_province = findViewById(R.id.tv_province);
        tv_province_code = findViewById(R.id.tv_province_code);
        tv_city = findViewById(R.id.tv_city);
        tv_area = findViewById(R.id.tv_area);
        tv_addr = findViewById(R.id.tv_addr);
        tv_name = findViewById(R.id.tv_name);
        tv_phone = findViewById(R.id.tv_phone);
        tv_city_code = findViewById(R.id.tv_city_code);
        tv_area_code = findViewById(R.id.tv_area_code);
    }


    /**
     * 智能解析
     * @param text
     * @return
     */
    private SmartObj smartAddressParse(String text) {
        SmartObj smartObj = new SmartObj();
        ArrayList<City> data = new ArrayList<>();
        try {
            String json = ConverUtils.toString(getAssets().open("city.json"));
            data.addAll(JSON.parseArray(json, City.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("ysl", "data size===" + data.size());
        text = stripscript(text);
        Log.i("ysl","text===" + text);
        String[] textArr = text.split("\\s+");
        List<String> dataList = new ArrayList<>(Arrays.asList(textArr));
        if (dataList != null && !dataList.isEmpty()) {
            tv_result.setText(dataList.toString());
            Log.i("ysl", "textArr===" + dataList);
            for (int i = 0; i < dataList.size(); i++) {
                String address = stripscript(dataList.get(i));
                Log.i("ysl", "i====" + i + " | " + address);


                //电话匹配
                if (ConverUtils.isPhoneNumber(address)) {
                    smartObj.setPhone(address);
                    Log.i("ysl", "phone===" + address);
                    continue;
                }

                if (!ConverUtils.isEmpty(smartObj.getProvince())){
                    smartObj.setName(address);
                    Log.i("ysl","name====" + address);
                    continue;
                }

                String matchAddress = "";
                //省匹配 比如输入北京市朝阳区，会用北  北京  北京市 北京市朝 以此类推在addressList里的province中做匹配，会得到北京市  河北省 天津市等等；
                List<City> matchProvince = new ArrayList<>(); //粗略匹配上的省份
                for (int endIndex = 0; endIndex < address.length(); endIndex++) {
                    if (endIndex + 1 <= address.length()){
                        matchAddress = address.subSequence(0,endIndex + 1).toString();
                        Log.i("ysl","省匹配==" + endIndex + "| " + matchAddress);
                        for (City city : data) {
                            if (city.getAreaName().indexOf(matchAddress) != -1){ //第一次出现的位置 若没有则为-1
                                city.setMatchValue(matchAddress);
                                String province = city.getAreaName();
                                matchProvince.add(city);
                                Log.i("ysl","初略省匹配===" + province);
                            }
                        }
                    }
                }
                Log.i("ysl","累积省匹配成功的数量==" + matchProvince.size());

                //统计筛选初略统计出的省份
                for (City res : matchProvince){
                    res.index = 0;
                    for (City el : matchProvince){
                        if (res.getAreaName().equals(el.getAreaName())) {
                            el.index++;
                            if (res.getMatchValue().length() > el.getMatchValue().length()) {
                                el.setMatchValue(res.getMatchValue());
                            }
                        }
                    }
                }

                if (!matchProvince.isEmpty()){
                    //从低到高排序
                    Comparator<City> comparator = new Comparator<City>() {
                        public int compare(City s1, City s2) {
                            int result = s1.index - s2.index;
                            return result;
                        }
                    };
                    Collections.sort(matchProvince,comparator);
                    City rsCity = matchProvince.get(matchProvince.size() - 1);
                    String province = rsCity.getAreaName();
                    String provinceCode = rsCity.getAreaId();
                    smartObj.setProvince(province);
                    smartObj.setProvinceCode(provinceCode);
                    Log.i("ysl", "【province】===" + province + "|" + provinceCode);

                    address = address.replaceFirst(rsCity.getMatchValue(),"");
                    Log.i("ysl","address去除省以后====" + address);
                }

                //市查找
                matchAddress = "";
                List<SmartObj> matchCity = new ArrayList<>(); //粗略匹配上的市
                for (int endIndex = 0; endIndex < address.length(); endIndex++) {
                    if (endIndex + 1 <= address.length()){
                        matchAddress = address.subSequence(0,endIndex + 1).toString();
                        Log.i("ysl","市匹配==" + endIndex + "| " + matchAddress);
                        for (City city : data) { //省
                            String smartProvinceCode = smartObj.getProvinceCode();
                            String smartProvice = smartObj.getProvince();
//                            if (!ConverUtils.isEmpty(smartProvinceCode) || city.getAreaId().equals(smartProvinceCode)){
                                //特殊市
                                if ("北京市".equals(smartProvice) || "天津市".equals(smartProvice) ||
                                        "上海市".equals(smartProvice) || "重庆市".equals(smartProvice)){
                                    for (City.CitiesBean item : city.getCities()){ //市
                                        for (City.CitiesBean.CountiesBean res : item.getCounties()){ //区
                                            if (res.getAreaName().indexOf(matchAddress) != -1){  //第一次出现的位置 若没有则为-1
                                                SmartObj st = new SmartObj();
                                                st.setArea(res.getAreaName());
                                                st.setAreaCode(res.getAreaId());
                                                st.setCity(item.getAreaName());
                                                st.setCityCode(item.getAreaId());
                                                st.setMatchValue(matchAddress);
                                                st.setProvince(city.getAreaName());
                                                st.setProvinceCode(city.getAreaId());
                                                matchCity.add(st);
                                            }
                                        }
                                    }
                                }else{
                                    for (City.CitiesBean res : city.getCities()){
                                        if (res.getAreaName().indexOf(matchAddress) != -1){
                                            SmartObj st = new SmartObj();
                                            st.setCity(res.getAreaName());
                                            st.setCityCode(res.getAreaId());
                                            st.setMatchValue(matchAddress);
                                            st.setProvince(city.getAreaName());
                                            st.setProvinceCode(city.getAreaId());
                                            matchCity.add(st);
                                        }
                                    }
                                }
                        }
                    }
                }
                Log.i("ysl","累积市匹配成功的数量==" + matchCity.size());
                //统计筛选初略统计出的市
                for (SmartObj res : matchCity){
                    res.index = 0;
                    for (SmartObj el : matchCity){
                        if (res.getCity().equals(el.getCity())){
                            el.index++;
                            if (res.getMatchValue().length() > el.getMatchValue().length()){
                                el.setMatchValue(res.getMatchValue());
                            }
                        }
                    }
                }
                if (!matchCity.isEmpty()){
                    //从低到高排序
                    Comparator<SmartObj> comparator = new Comparator<SmartObj>() {
                        public int compare(SmartObj s1, SmartObj s2) {
                            int result = s1.index - s2.index;
                            return result;
                        }
                    };
                    Collections.sort(matchCity,comparator);
                    SmartObj rsCity = matchCity.get(matchCity.size() - 1);
                    smartObj.setCityCode(rsCity.getCityCode());
                    smartObj.setCity(rsCity.getCity());
                    smartObj.setAreaCode(rsCity.getAreaCode());
                    smartObj.setArea(rsCity.getArea());
                    if (ConverUtils.isEmpty(smartObj.getProvince()) && !ConverUtils.isEmpty(rsCity.getProvince())){
                        smartObj.setProvince(rsCity.getProvince());
                        smartObj.setProvinceCode(rsCity.getProvinceCode());
                    }
                    Log.i("ysl", "【city】===" + smartObj.getCity() + "|" + smartObj.getCityCode());

                    address = address.replaceFirst(rsCity.getMatchValue(),"");
                    Log.i("ysl","address去除市以后====" + address);
                }


                //区县查找
                matchAddress = "";
                List<SmartObj> matchCounty = new ArrayList<>();
                for (int endIndex = 1; endIndex < address.length(); endIndex++) {
                    if (endIndex + 1 <= address.length()){
                        matchAddress = address.subSequence(0,endIndex + 1).toString();
                        Log.i("ysl","区匹配==" + endIndex + "| " + matchAddress);
                        for (City city : data) { //省
                            String smartProvinceCode = smartObj.getProvinceCode();
                            String smartProvice = smartObj.getProvince();
//                            if (!ConverUtils.isEmpty(smartProvinceCode) || city.getAreaId().equals(smartProvinceCode)){
                                //特殊市
                                if ("北京市".equals(smartProvice) || "天津市".equals(smartProvice) ||
                                        "上海市".equals(smartProvice) || "重庆市".equals(smartProvice)){
                                    //nothing
                                }else{
                                    for (City.CitiesBean item : city.getCities()){ //市
                                        for (City.CitiesBean.CountiesBean res : item.getCounties()){ //区
                                            if (res.getAreaName().indexOf(matchAddress) != -1){  //匹配到区了===
                                                //省/市  || 省
                                                if (ConverUtils.isEmpty(smartObj.getProvince())){ //省为空
                                                    if (ConverUtils.isEmpty(smartObj.getCityCode())){//市为空
                                                        SmartObj st = new SmartObj();
                                                        st.setArea(res.getAreaName());
                                                        st.setAreaCode(res.getAreaId());
                                                        st.setCity(item.getAreaName());
                                                        st.setCityCode(item.getAreaId());
                                                        st.setMatchValue(matchAddress);
                                                        st.setProvinceCode(city.getAreaId());
                                                        st.setProvince(city.getAreaName());
                                                        matchCounty.add(st);
                                                    }else{//市不为空
                                                        String areaId = res.getAreaId().substring(0,4);
                                                        Log.i("ysl","areaId===" + areaId);
                                                        if (res.getAreaId().substring(0,4).equals(smartObj.getCityCode().substring(0,4))){
                                                            SmartObj st = new SmartObj();
                                                            st.setArea(res.getAreaName());
                                                            st.setAreaCode(res.getAreaId());
                                                            st.setCity(item.getAreaName());
                                                            st.setCityCode(item.getAreaId());
                                                            st.setMatchValue(matchAddress);
                                                            st.setProvinceCode(city.getAreaId());
                                                            st.setProvince(city.getAreaName());
                                                            matchCounty.add(st);
                                                        }
                                                    }
                                                }else{ //省不为空
                                                    if (ConverUtils.isEmpty(smartObj.getCityCode())){ //市为空
                                                        if (res.getAreaId().substring(0,3).equals(smartObj.getProvinceCode().substring(0,3))){
                                                            SmartObj st = new SmartObj();
                                                            st.setArea(res.getAreaName());
                                                            st.setAreaCode(res.getAreaId());
                                                            st.setCity(item.getAreaName());
                                                            st.setCityCode(item.getAreaId());
                                                            st.setMatchValue(matchAddress);
                                                            st.setProvinceCode(city.getAreaId());
                                                            st.setProvince(city.getAreaName());
                                                            matchCounty.add(st);
                                                        }
                                                    }else{ //市不为空
                                                        if (res.getAreaId().substring(0,4).equals(smartObj.getCityCode().substring(0,4))){
                                                            SmartObj st = new SmartObj();
                                                            st.setArea(res.getAreaName());
                                                            st.setAreaCode(res.getAreaId());
                                                            st.setCity(item.getAreaName());
                                                            st.setCityCode(item.getAreaId());
                                                            st.setMatchValue(matchAddress);
                                                            st.setProvinceCode(city.getAreaId());
                                                            st.setProvince(city.getAreaName());
                                                            matchCounty.add(st);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
//                            }
                        }
                    }
                }

                Log.i("ysl","累积市区配成功的数量==" + matchCounty.size());
                //统计筛选初略统计出的区县
                for (SmartObj res : matchCounty){
                    res.index = 0;
                    for (SmartObj el : matchCounty){
                        if (res.getCity().equals(el.getCity())){
                            el.index++;
                            if (res.getMatchValue().length() > el.getMatchValue().length()){
                                el.setMatchValue(res.getMatchValue());
                            }
                        }
                    }
                }
                if (!matchCounty.isEmpty()){
                    //从低到高排序
                    Comparator<SmartObj> comparator = new Comparator<SmartObj>() {
                        public int compare(SmartObj s1, SmartObj s2) {
                            int result = s1.index - s2.index;
                            return result;
                        }
                    };
                    Collections.sort(matchCounty,comparator);
                    SmartObj rsCity = matchCounty.get(matchCounty.size() - 1);
                    smartObj.setAreaCode(rsCity.getAreaCode());
                    smartObj.setArea(rsCity.getArea());
                    if (ConverUtils.isEmpty(smartObj.getProvince()) && !ConverUtils.isEmpty(rsCity.getProvince())){
                        smartObj.setProvince(rsCity.getProvince());
                        smartObj.setProvinceCode(rsCity.getProvinceCode());
                    }
                    if (ConverUtils.isEmpty(smartObj.getCity()) && !ConverUtils.isEmpty(rsCity.getCity())){
                        smartObj.setCity(rsCity.getCity());
                        smartObj.setCityCode(rsCity.getCityCode());
                    }

                    Log.i("ysl", "【area】===" + smartObj.getArea() + "|" + smartObj.getArea());
                    address = address.replaceFirst(rsCity.getMatchValue(),"");
                    Log.i("ysl","address去除区以后====" + address);
                    smartObj.setAddr(address);
                    Log.i("ysl","addr=====" + address);
                    continue;
                }

                //姓名查找
                if (ConverUtils.isEmpty(smartObj.getProvince())){
                    smartObj.setName(address);
                    Log.i("ysl","name====" + address);
                }
            }
        }
        return smartObj;
    }


    /**
     * 过滤特殊字符
     *
     * @param s
     */
    private String stripscript(String s) {
        Log.i("ysl","s 处理前===" + s);
        Pattern p = Pattern.compile("(\\d{3})\\-(\\d{4})\\-(\\d{4})$*");
        Matcher m = p.matcher(s);
        s = m.replaceAll("$1$2$3");
        Log.i("ysl","s 处理中===" + s);

        String pattern = "[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“’。，、？-]";
        String rs = "";
        for (int i = 0; i < s.length(); i++) {
            rs = rs + s.substring(i, i + 1);
            rs = ConverUtils.regReplace(rs, pattern, " ");
        }
        rs = ConverUtils.regReplace(rs, "/[\\r\\n]/g", "");

        Log.i("ysl","s 处理后==" + rs);
        return rs;
    }



    public void onButtonClick(View view) {
        SmartObj result = smartAddressParse(etContent.getText().toString());

        tv_province.setText("省: " + result.getProvince());
        tv_province_code.setText("省编码: " + result.getProvinceCode());
        tv_city.setText("市: " + result.getCity());
        tv_city_code.setText("市编码: " + result.getCityCode());
        tv_area.setText("区: " + result.getArea());
        tv_area_code.setText("区编码: " + result.getAreaCode());
        tv_addr.setText("地址: " + result.getAddr());
        tv_name.setText("姓名: " + result.getName());
        tv_phone.setText("电话: " + result.getPhone());
    }
}
