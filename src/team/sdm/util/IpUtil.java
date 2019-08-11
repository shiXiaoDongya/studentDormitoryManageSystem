package team.sdm.util;



import javax.servlet.http.HttpServletRequest;



public class IpUtil {
    /**
     * 获取登录用户IP地址
     *
     * @param request
     * @return
     */
	public static String getIp(HttpServletRequest request){
		String ip = null;
		try {
			ip = request.getHeader("X-Real-IP");
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
				ip = request.getHeader("x-forwarded-for");
			}
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
				ip = request.getHeader("Proxy-Client-Ip");
			}
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
				ip = request.getHeader("WL-Proxy-Client-Ip");
			}
			if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
				ip = request.getRemoteAddr();
			}
			if(ip == null){
				ip = request.getLocalAddr();
			}
			if("0:0:0:0:0:0:0:1".equals(ip)){
				ip="127.0.0.1";
			}
		}catch(Exception ex) {
		
		}
		return ip;
	}
}