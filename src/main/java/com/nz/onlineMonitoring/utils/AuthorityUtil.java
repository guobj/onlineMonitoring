/**
 * 根据省市区设置权限
 *
 * 省账号可以查看所有市区县的设备
 *
 * 市账号可以查看本市所有区县的设备
 *
 * 区账号只能查看本区的设备
 *
 * @AuthorityUtil
 * guobj
 */
package com.nz.onlineMonitoring.utils;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.nz.onlineMonitoring.login.model.Login;
import com.nz.onlineMonitoring.realData.model.RealData;

public class AuthorityUtil {

	private static AuthorityUtil authorityUtil = null;
	private AuthorityUtil() {}
	public static AuthorityUtil getInstance(){
		if(authorityUtil == null){
			synchronized(AuthorityUtil.class){
				if(authorityUtil == null){
					authorityUtil = new AuthorityUtil();
				}
			}
		}
		return authorityUtil;
	}

	/**
	 * 此方法用于按照省市区进去查询，设置不可越级访问，亦不可同级访问。
	 * @param citys 城市范围
	 * @param request
	 * @param object 类的实例化对象
	 * @throws Exception
	 * @author guobj
	 */
	public void assignPermissions(String[] citys, HttpServletRequest request, Object object) throws Exception{

		//反射得到object的类
		Class clz = object.getClass();
		//取到类成名ms_code
		Field field = clz.getDeclaredField("ms_code");
		//private成员变量无法为其赋值，所以设置其可见性为true，使其可以被赋值
		field.setAccessible(true);
		HttpSession session = request.getSession();
		Login user = (Login) session.getAttribute("user");
		String  account = user.getAccount().toString();
		if(account != null && !account.equals("")){
			if(account.endsWith("0000")){
				if (citys != null) {
					if (citys[1] != null && citys[1] != "") {
						//为成员变量ms_code赋值
						field.set(object, citys[1]);
					}else if (citys[0] != null && citys[0] != "") {
						field.set(object, citys[0]);
					}else {
					}
				}
			}else if(account.endsWith("00")){
				if (citys != null && citys.length > 0) {
					if (citys[0] != null && citys[0] != "") {
						field.set(object, citys[0]);
					}
				}else{
					Integer res = user.getAccount() / 100;
					field.set(object, res.toString());
				}
			}else{
				field.set(object, account);
			}
		}
//		System.out.println("测试："+field.get(object));
	}

	public void assignPermissionsRealData(String[] citys, HttpServletRequest request, RealData realData){
        HttpSession session = request.getSession();
        Login user = (Login) session.getAttribute("user");
        String  account = user.getAccount().toString();
        if(account != null && !account.equals("")){
            if(account.endsWith("0000")){
                if (citys != null) {
                    if (citys[1] != null && citys[1] != "") {
                        realData.setMs_code(citys[1]);
                    }else if (citys[0] != null && citys[0] != "") {
                        realData.setMs_code(citys[0]);
                    }else {
                        realData.setMs_code("37");
                    }
                }
            }else if(account.endsWith("00")){
                if (citys != null) {
                    if (citys[0] != null && citys[0] != "") {
                        realData.setMs_code(citys[0]);
                    }
                }else{
                    Integer res = user.getAccount() / 100;
                    realData.setMs_code(res.toString());
                }
            }else{
                realData.setMs_code(account);
            }
        }
    }
}
