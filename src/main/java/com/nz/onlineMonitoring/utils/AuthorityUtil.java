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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.nz.onlineMonitoring.login.model.Login;
import com.nz.onlineMonitoring.realData.model.RealData;
import com.nz.onlineMonitoring.stationManage.model.Manage;

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

	public void assignPermissions(String[] citys, HttpServletRequest request, Manage realData){
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
