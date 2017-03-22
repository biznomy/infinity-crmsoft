package com.ednomy.crm.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Component;

import com.ednomy.crm.entity.EdBaseEntity;

@Component
public class ReflectionUtil {

	public String getDataTypeOfEntity(String entityName, String fieldName) {

		String suffix = "com.ednomy.crm.entity.";

		entityName = WordUtils.capitalize(entityName) + "Entity";
		Class<?> c = null;
		try {
			c = Class.forName(suffix + entityName);
			
			Field f = c.getDeclaredField(fieldName);
			if (!f.getType().isInterface()) {

				return f.getType().getCanonicalName();
			} else {

				return f.getGenericType().toString();
			}

			// production code should handle these exceptions more gracefully
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (NoSuchFieldException x) {
			return getDataTypeOfBaseEntity(c, fieldName);
		}

		return "";

	}
	
	
	public String getDataTypeOfBaseEntity(Class<?> c, String fieldName) {

		if (!EdBaseEntity.class.isAssignableFrom(c)) {
			return "";
		}

		try {
			c = Class.forName(EdBaseEntity.class.getName());
			
			Field f = c.getDeclaredField(fieldName);
			if (!f.getType().isInterface()) {

				return f.getType().getCanonicalName();
			} else {

				return f.getGenericType().toString();
			}

			// production code should handle these exceptions more gracefully
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (NoSuchFieldException x) {
			x.printStackTrace();
		}

		return "";

	}

	public boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		return (obj.toString().trim().isEmpty());
	}

	@SuppressWarnings("rawtypes")
	public Object valueOf(Object obj, String fieldPath) {
		try {
			Class clazz = obj.getClass();
			StringTokenizer st = new StringTokenizer(fieldPath, ".");

			String fieldName = st.nextToken();

			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);

			Object value = field.get(obj);
			String newFieldPath = fieldPath.replace(fieldName + ".", "");

			if (value == null) {
				return null;
			} else if (!st.hasMoreTokens()) {
				return value;
			} else {
				return valueOf(value, newFieldPath);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	public Map<String, Object> getPublicMap(Object obj, List<String> names)
	        throws IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
	    List<String> gettedFields = new ArrayList<String>();
	    Map<String, Object> values = new HashMap<String, Object>();
	    for (Method getter: obj.getClass().getMethods()) {
	        if (getter.getName().startsWith("get") && (getter.getName().length() > 3)) {
	            String name0 = getter.getName().substring(3);
	            String name = name0.substring(0, 1).toLowerCase().concat(name0.substring(1));
	            gettedFields.add(name);
	            if ((names == null) || names.isEmpty() || names.contains(name)) {
	                values.put(name, getter.invoke(obj));
	            }
	        }
	    }
	    for (Field field: obj.getClass().getFields()) {
	    	String name = field.getName();
	        if ((! gettedFields.contains(name)) && ((names == null) || names.isEmpty() || names.contains(name))) {
	            values.put(name, field.get(obj));
	        }
	    }
	    return values;
	}
	
	public Object getSelected(Object obj, List<String> names)
	        throws IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
	    List<String> gettedFields = new ArrayList<String>();
	    Map<String, Object> values = new HashMap<String, Object>();
	    for (Method getter: obj.getClass().getMethods()) {
	        if (getter.getName().startsWith("get") && (getter.getName().length() > 3)) {
	            String name0 = getter.getName().substring(3);
	            String name = name0.substring(0, 1).toLowerCase().concat(name0.substring(1));
	            gettedFields.add(name);
	            if ((names == null) || names.isEmpty() || names.contains(name)) {
	                values.put(name, getter.invoke(obj));
	            }
	        }
	    }
	    for (Field field: obj.getClass().getFields()) {
	        String name = field.getName();
	        if ((! gettedFields.contains(name)) && ((names == null) || names.isEmpty() || names.contains(name))) {
	            values.put(name, field.get(obj));
	        }

	    	/*Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, fieldValue);*/
	        
	    }
	    
	    return values;
	}

}
