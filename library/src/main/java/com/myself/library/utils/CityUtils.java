package com.myself.library.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 关于城市的工具类
 */

public class CityUtils {

    private static String provinceId;
    private static String cityId;
    private static String areaId;

    /**
     *
     * @param context
     * @param province  省名称
     * @return  省编码
     */
    public static String convertProvinceCode(Context context, String province) {
        String JsonData = getJson(context, "city.json");//获取assets目录下的json文件数据
        ArrayList<CityBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            String provinceName = jsonBean.get(i).getAreaName();
            if (province.equals(provinceName)) {
                provinceId = jsonBean.get(i).getAreaId();
            }
        }
        return provinceId;

    }

    /**
     * @param context
     * @param province
     * @param city   市名称
     * @return   市编码
     */
    public static String convertCityCode(Context context, String province, String city) {
        String JsonData = getJson(context, "city.json");//获取assets目录下的json文件数据
        ArrayList<CityBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            String provinceName = jsonBean.get(i).getAreaName();
            if (province.equals(provinceName)) {
                List<CityBean.CitiesBean> cities = jsonBean.get(i).getCities();
                for (int j = 0; j < cities.size(); j++) {
                    String cityName = cities.get(j).getAreaName();
                    if (city.equals(cityName)) {
                        cityId = cities.get(j).getAreaId();
                    }
                }
            }
        }
        return cityId;

    }

    /**
     * @param context
     * @param province
     * @param city
     * @param area
     * @return  区编码
     */
    public static String convertAreaCode(Context context, String province, String city, String area) {
        String JsonData = getJson(context, "city.json");//获取assets目录下的json文件数据
        ArrayList<CityBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            String provinceName = jsonBean.get(i).getAreaName();
            if (province.equals(provinceName)) {
                List<CityBean.CitiesBean> cities = jsonBean.get(i).getCities();
                for (int j = 0; j < cities.size(); j++) {
                    String cityName = cities.get(j).getAreaName();
                    if (city.equals(cityName)) {
                        List<CityBean.CitiesBean.CountiesBean> counties = cities.get(j).getCounties();
                        for (int y = 0; y < counties.size(); y++) {
                            String areaName = counties.get(y).getAreaName();
                            if (area.equals(areaName)) {
                                areaId = counties.get(y).getAreaId();
                            }
                        }
                    }
                }
            }
        }
        return areaId;

    }

    public static ArrayList<CityBean> parseData(String result) {//Gson 解析
        ArrayList<CityBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                CityBean entity = gson.fromJson(data.optJSONObject(i).toString(), CityBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("xx", e.toString());
        }
        return detail;
    }

    /**
     * @param context
     * @param provinceCode  省编码
     * @return  省名称
     */
    public static String getProvinceName(Context context, String provinceCode) {
        String JsonData = getJson(context, "city.json");//获取assets目录下的json文件数据
        ArrayList<CityBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            String code = jsonBean.get(i).getAreaId();
            if (provinceCode.equals(code)) {
                return jsonBean.get(i).getAreaName();
            }
        }
        return "未找到省";
    }


    /**
     * @param context
     * @param provinceCode
     * @param cityCode   市编码
     * @return   市名称
     */
    public static String getCityName(Context context, String provinceCode, String cityCode) {
        String JsonData = getJson(context, "city.json");//获取assets目录下的json文件数据
        ArrayList<CityBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            String proCode = jsonBean.get(i).getAreaId();
            if (proCode.equals(provinceCode)) {
                List<CityBean.CitiesBean> cities = jsonBean.get(i).getCities();
                for (int j = 0; j < cities.size(); j++) {
                    String code = cities.get(j).getAreaId();
                    if (code.equals(cityCode)) {
                        return cities.get(j).getAreaName();
                    }
                }
            }
        }
        return "未找到城市";
    }

    /**
     * @param context
     * @param provinceCode
     * @param cityCode
     * @param zoneCode   区编码
     * @return   区名称
     */
    public static String getZoneName(Context context, String provinceCode, String cityCode, String zoneCode) {
        String JsonData = getJson(context, "city.json");//获取assets目录下的json文件数据
        ArrayList<CityBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            String pCode = jsonBean.get(i).getAreaId();
            if (pCode.equals(provinceCode)) {
                List<CityBean.CitiesBean> cities = jsonBean.get(i).getCities();
                for (int j = 0; j < cities.size(); j++) {
                    String cCode = cities.get(j).getAreaId();
                    if (cityCode.equals(cCode)) {
                        List<CityBean.CitiesBean.CountiesBean> counties = cities.get(j).getCounties();
                        for (int y = 0; y < counties.size(); y++) {
                            String zCode = counties.get(y).getAreaId();
                            if (zoneCode.equals(zCode)) {
                                return counties.get(y).getAreaName();
                            }
                        }
                    }
                }
            }
        }
        return "其他";
    }


    public static String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
