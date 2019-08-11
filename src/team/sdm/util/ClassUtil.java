package team.sdm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {
	private ClassUtil() {
	};

	/*
	 * �Ӷ����л�ȡ������
	 */
	public static String[] getFiledName(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
            //System.out.println(fields[i].getType());  
			fieldNames[i] = fields[i].getName();
//			System.out.println(fieldNames[i]);
		}
		return fieldNames;
	}
	
	/* ������������ȡ����ֵ  ��ͨ��get������
     * */  
    public static Object getFieldValueByName(String fieldName, Object obj) {  
        try {    
            String firstLetter = fieldName.substring(0, 1).toUpperCase();    
            String getter = "get" + firstLetter + fieldName.substring(1);    
            Method method = obj.getClass().getMethod(getter, new Class[] {});    
            Object value = method.invoke(obj, new Object[] {});    
            //System.out.println(value.toString());
            return value; 
        } catch (Exception e) {    
            return null;    
        }    
    }


}
