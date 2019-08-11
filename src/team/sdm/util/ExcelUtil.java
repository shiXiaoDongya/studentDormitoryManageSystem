package team.sdm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;


public class ExcelUtil {
	private ExcelUtil() {
	};

	/*
	 * headWatchHead��ͷ list�����б�
	 */
	public static HSSFWorkbook fillExcelData(List<String> head, List listData) {

		try {

			HSSFWorkbook wb = new HSSFWorkbook();
			// ������
			HSSFSheet sheet = wb.createSheet();
			// ������һ�У���ͷ��
			HSSFRow HSSFhead = sheet.createRow(0);
			// ������Ԫ��
			for (int c = 0; c < head.size(); c++) {
				HSSFCell cell = HSSFhead.createCell(c);
				cell.setCellValue(new HSSFRichTextString(head.get(c)));
			}

			// ��ȡ����������
			String[] fieldNames = ClassUtil.getFiledName(listData.get(0));

			// �ӵڶ��п�ʼ������
			for (int r = 1; r <= listData.size(); r++) {
				HSSFRow row = sheet.createRow(r);
				Object obj = listData.get(r - 1);
				// ������Ԫ��
				for (int c = 0; c < fieldNames.length; c++) {
					HSSFCell cell = row.createCell(c);
					String fieldName = fieldNames[c];

					// ������������ȡ����ֵ
					Object value = ClassUtil.getFieldValueByName(fieldName, obj);
					if (value != null) {
						cell.setCellValue(value.toString()); // ������������ݸ�ʽ����
					} else {
						cell.setCellValue("");
					}
				}
			}

			return wb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/*
	 * �Զ����ɱ�ͷ
	 */
	public static HSSFWorkbook fillExcelData(List listData) {
		// ��ȡ������
		String[] fieldNames = ClassUtil.getFiledName(listData.get(0));
		List<String> head = new ArrayList<String>();
		for (String n : fieldNames) {
			head.add(n);
		}
		return fillExcelData(head, listData);
	}

	/*
	 * Ĭ�ϱ���
	 */
	public static boolean writeExcel(Workbook wb, String path) {
		try {
			File newFile = new File(path);
			newFile.createNewFile();
			System.out.println(" �����ļ��ɹ��� " + path);
			OutputStream out = new FileOutputStream(path);
			wb.write(out); // д�� File
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * ��response����
	 */
	public static boolean writeExcel(HttpServletResponse response, Workbook wb, String defaultName) {
		defaultName = defaultName + ".xls";
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(defaultName.getBytes("utf-8"), "iso8859-1"));
			response.setContentType("application/ynd.ms-excel;charset=UTF-8");
			OutputStream out = response.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

}
