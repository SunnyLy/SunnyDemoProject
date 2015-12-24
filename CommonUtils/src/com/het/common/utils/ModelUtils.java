package com.het.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by weatherfish on 2015/1/7.
 */
public class ModelUtils {

	public static TreeMap<String, String> Json2Map(String json) {
		Type type = new TypeToken<TreeMap<String, String>>() {
		}.getType();
		Gson gson = GsonUtil.getGsonInstance();
		TreeMap<String, String> map = gson.fromJson(json, type);
		return map;
	}

	public static String Map2Json(TreeMap<String, String> map) {
		Gson gson = GsonUtil.getGsonInstance();
		String json = gson.toJson(map);
		return json;
	}

	public static <T> String Model2Json(T model) {
		Gson gson = GsonUtil.getGsonInstance();
		String json = gson.toJson(model);
		return json;
	}

	public static <T> String Model2Json2(T model) {
		StringBuilder json = new StringBuilder("{");
		Field[] fileds = model.getClass().getDeclaredFields();
		StringBuilder filedStr;
		Method method;
		try {// serialVersionUID
			for (Field field : fileds) {
				if (field.getName().equals("back")
						|| field.getName().equals("serialVersionUID")) {
					continue;
				}
				filedStr = new StringBuilder(field.getName());
				json.append("\"").append(field.getName()).append("\":");
				filedStr.setCharAt(0, (field.getName().charAt(0) + "")
						.toUpperCase().charAt(0));
				method = model.getClass().getMethod("get" + filedStr);
				if (method.invoke(model) != null) {
					json.append("\"").append(method.invoke(model)).append("\"");
				}
				json.append(",");
			}
			json.deleteCharAt(json.lastIndexOf(","));
			json.append("}");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println("json==== " + json.toString());
		return json.toString();
	}

	public static <T> String Model2JsonIgnoreDeviceId(T model) {
		Gson gson = GsonUtil.getGsonInstance();
		String json = gson.toJson(model);
		return json;
	}

	public static <T> T Json2Model(String json, Class<T> clazz) {
		Gson gson = GsonUtil.getGsonInstance();
		T model = gson.fromJson(json, clazz);
		return model;
	}

	public static TreeMap<String, String> Model2Map(Object model) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		Field[] fileds = model.getClass().getDeclaredFields();
		Method method;
		try {
			for (Field field : fileds) {
				if (field.getName().equals("serialVersionUID")) {
					continue;
				}
				if (field.getName().equals("status")) {
					continue;
				}
				if (field.getName().equals("id")) {
					continue;
				}
				StringBuilder nameBuilder = new StringBuilder(field.getName());
				nameBuilder.setCharAt(0, (nameBuilder.charAt(0) + "")
						.toUpperCase().charAt(0));
				method = model.getClass().getMethod("get" + nameBuilder);
				if (method.invoke(model) != null) {
					map.put(field.getName(), method.invoke(model) + "");
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return map;
	}

}
