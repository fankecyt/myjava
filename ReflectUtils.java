import java.lang.reflect.*;
public class ReflectUtils{
	/**
	*通过类名创建出无参的实例对象
	*@param className 类名
	*@return Object 返回创建好的实例对象
	*/
	public static Object getInstance(String className)throws Exception{	
		Class clazz=Class.forName(className);//1、通过className创建出Class对象	
		return clazz.newInstance();//2、通过newInstance字节创建出无参的实例对象
	}
	/**
	*通过类名和参数创建出有参的实例对象
	*@param className 类名
	*@param types 所有参数的类型
	*@param params 所有参数的值
	*@return Object 返回创建好的实例对象
	*/
	public static Object getInstance(String className,Class[] types,Object[] params)throws Exception{	
		Class clazz=Class.forName(className);//1、通过className创建出Class对象		
		Constructor con=clazz.getConstructor(types);//2、通过getConstructor()获取构造函数		
		return con.newInstance(params);//3、通过构造函数创建出有参的实例对象
	}
	/**
	*通过类名和属性名获取属性值
	*@param className 类名
	*@param filedName 属性名
	*/
	public static Field getField(String className,String fieldName)throws Exception{
		Class clazz=Class.forName(className);
		Object o=clazz.newInstance();
		return clazz.getDeclaredField(fieldName);
	}
	/**
	*通过类名和方法名获取方法
	*@param className 类名
	*@param methodName 方法名
	*@param className 参数类型
	*@param value 参数值
	*/
	public static Method getMethod(String className,String methodName,Class...className,Object...value)throws Exception{
		Class clazz=Class.forName(className);
		Object o=clazz.newInstance();
		return clazz.getDeclaredMethod(methodName,className);
	}
}