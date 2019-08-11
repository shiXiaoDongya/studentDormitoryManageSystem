package team.sdm.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class IPUtils {
   /**
    * ��ȡIP������
    * @param content
    * @return
    * @throws IOException
    */
    public static String getAddresses(String content) throws IOException {
    	String encodingString = "utf-8";
    	content="ip="+content;
        // �������pconline�Ľӿ�
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        // ��http://whois.pconline.com.cnȡ��IP���ڵ�ʡ������Ϣ
        String returnStr = getResult(urlStr, content, encodingString);
        if (returnStr != null) {
            // �����ص�ʡ������Ϣ
//            System.out.println("IP=====" + returnStr);
            String[] temp = returnStr.split(",");
            if (temp.length < 3) {
                return "0"; // ��ЧIP������������
            }
            String region = (temp[4].split(":"))[1].replaceAll("\"", "");
            region = decodeUnicode(region); // ʡ
//            System.out.println("region = " + region);

            String country = "";
            String area = "";
            // String region = "";
            String city = "";
            String county = "";
            String isp = "";
//            System.out.println("temp�ĳ���=" + temp.length);
            for (int i = 0; i < temp.length; i++) {
                switch (i) {
                // ���ʹ�õ������˵Ľӿڣ����������Ҫ�޸ģ�case:3 4 5�ֱ��Ӧ���ң�ʡ������
                case 1:
                    country = (temp[i].split(":"))[2].replaceAll("\"", "");
                    country = decodeUnicode(country); // ����
                    break;
                case 3:
                    area = (temp[i].split(":"))[1].replaceAll("\"", "");
                    area = decodeUnicode(area); // ����
                    break;
                case 4:
                    region = (temp[i].split(":"))[1].replaceAll("\"", "");
                    region = decodeUnicode(region); // ʡ��
                    break;
                case 5:
                    city = (temp[i].split(":"))[1].replaceAll("\"", "");
                    city = decodeUnicode(city); // ����
                    break;
                case 6:
                    county = (temp[i].split(":"))[1].replaceAll("\"", "");
                    county = decodeUnicode(county); // ����
                    break;
                case 7:
                    isp = (temp[i].split(":"))[1].replaceAll("\"", "");
                    isp = decodeUnicode(isp); // ISP��˾
                    break;
                }
            }
//            System.out.println(country + "=" + area + "=" + region + "=" + city + "=" + county + "=" + isp);
            if ("XX".equals(region)) {
				return city;
			}
            return region+city;
        }
        return null;
    }

    /**
     * @param urlStr   ����ĵ�ַ
     * @param content  ����Ĳ��� ��ʽΪ��name=xxx&pwd=xxx
     * @param encoding ��������������롣��GBK,UTF-8��
     * @return
     */
    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection(); // �½�����ʵ��
            connection.setConnectTimeout(2000); // �������ӳ�ʱʱ�䣬��λ����
            connection.setReadTimeout(2000); // ���ö�ȡ���ݳ�ʱʱ�䣬��λ����
            connection.setDoOutput(true); // �Ƿ������� true|false
            connection.setDoInput(true); // �Ƿ��������true|false
            connection.setRequestMethod("POST"); // �ύ����POST|GET
            connection.setUseCaches(false); // �Ƿ񻺴�true|false
            connection.connect(); // �����Ӷ˿�
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());// ����������Զ˷�����д����
            out.writeBytes(content); // д����,Ҳ�����ύ��ı� name=xxx&pwd=xxx
            out.flush(); // ˢ��
            out.close(); // �ر������
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));// ���Զ�д�����ݶԶ˷�������������
                                                                                                                        // ,��BufferedReader������ȡ
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect(); // �ر�����
            }
        }
        return null;
    }

    /**
     * unicode ת���� ����
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException("Malformed  encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }
/**
 * ��ȡ��ǰIP��ַ
 * @return
 * @throws IOException
 */
	public static String getIP() throws IOException {
		InetAddress add = InetAddress.getLocalHost();
		String ip = add.getHostAddress();
		return ip;
	}
}
