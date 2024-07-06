package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.exception.FileTypeException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/*
批量导入和导出用户
*/

public class ScanExcel {
    private final String XLSX = ".xlsx";
    private final String XLS=".xls";

    //读取Excel文件（支持.xls和.xlsx）
    public List<User> readExcel(File file) throws Exception{
        int res = checkFile(file);
        if (res == 0) {
//            System.out.println("File not found");
            throw new FileNotFoundException("File not found");
        }else if (res == 1) {//类型为-XLSX
            return readXLSX(file);
        }else if (res == 2) {//类型为-XLS
            return readXLS(file);
        }else{
            throw new FileTypeException("暂不支持读取该文件格式");
//            System.out.println("暂不支持读取该文件格式");
        }
//        return new ArrayList<>();
    }

    //写入Excel文件（支持.xls和.xlsx）
//    public void writeExcel(File file,String pathname,String s) throws Exception{
//        int res = checkFile(file);
//        if (res == 0) {
//            System.out.println("File not found");
//        }else if (res == 1 || res == 2) {//类型为-XLSX或-XLS
//            String[] strs = pathname.split("\\.");
//            String Path=strs[0]+".json";
//            FileOutputStream fos= new FileOutputStream(Path);
//            OutputStreamWriter os= new OutputStreamWriter(fos);
//            BufferedWriter w= new BufferedWriter(os);
//            w.write(s);
//            w.close();
//        }else{
//            System.out.println("暂不支持写入该文件格式");
//        }
//    }


    //判断File文件的类型
    public int checkFile(File file){
        if (file==null) {
            return 0;
        }
        String fileName = file.getName();
        if (fileName.endsWith(XLSX)) {
            return 1;
        }
        if (fileName.endsWith(XLS)) {
            return 2;
        }
        return 3;
    }

    //读取XLSX文件
    public List<User> readXLSX(File file) throws InvalidFormatException, IOException {
        Workbook book = new XSSFWorkbook(file);
        return read(book);
    }

    //读取XLS文件
    public List<User> readXLS(File file) throws FileNotFoundException, IOException{
        POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));
        Workbook book = new HSSFWorkbook(poifsFileSystem);
        return read(book);
    }

    //解析数据
    public List<User> read(Workbook book) throws IOException{
        int numSheets = book.getNumberOfSheets();
        List<User> users = new ArrayList<>();
        for (int sheetIndex = 0; sheetIndex < numSheets; sheetIndex++) {
            Sheet sheet = book.getSheetAt(sheetIndex);
            int rowStart = sheet.getFirstRowNum();//首行下标
            int rowEnd = sheet.getLastRowNum();//尾行下标
            //如果首行与尾行相同，表明只有一行，直接返回空数组
            if (rowStart == rowEnd) {
                //book.close();
                continue;
            }
            //获取key行JSON对象键
            Map<Integer, String> keyMap = new HashMap<Integer, String>();
            int numCell = -1;//“学号”单元格所在列
            int pwdCell = -1;
            int nameCell = -1;
            int numRow = 0;//“学号”单元格所在行
            for(int i = rowStart; i <= rowEnd ; i++) {
                //找出“学号”单元格所在行
                Row eachRow = sheet.getRow(i);
                if (eachRow != null){
                    int cellStart = eachRow.getFirstCellNum();
                    int cellEnd = eachRow.getLastCellNum();
                    for (int j = cellStart; j < cellEnd; j++) {
                        String tempVal = getValue(eachRow.getCell(j), rowStart, j, book, false);
                        if(tempVal != null){
                            if (tempVal.contains("学号")) {
                                //找出“学号”表头所在行
                                numRow = i;
//                                System.out.println("numRow:"+ numRow);
                            }
                        }
                    }
                }
            }
            Row firstRow = sheet.getRow(numRow);//获取表头
            int firstRowStart = firstRow.getRowNum();
            int cellStart = firstRow.getFirstCellNum();
            int cellEnd = firstRow.getLastCellNum();
            for (int j = cellStart; j < cellEnd; j++) {
                String tempVal = getValue(firstRow.getCell(j), rowStart, j, book, true);
                if(tempVal !=null){
                    //判断是否需要获取该列
                    if (tempVal.contains("学号")) {
                        keyMap.put(j, "学号");
                        numCell = j;
                    } else if (tempVal.contains("姓名") || tempVal.contains("名字") ) {
                        keyMap.put(j, "姓名");
                        nameCell = j;
                    } else if (tempVal.contains("密码")) {
                        keyMap.put(j, "密码");
                        pwdCell = j;
                    }
                }
            }
            //获取每行JSON对象的值
            if(numCell >=0 && nameCell >=0 && pwdCell >=0){
                for(int i = firstRowStart +1; i <= rowEnd ; i++) {
                    Row eachRow = sheet.getRow(i);
                    StringBuffer sb = new StringBuffer();
                    User user = new User();
                    for (int k = cellStart; k < cellEnd; k++) {
                        if (eachRow != null && keyMap.get(k)!=null) {
                            //判断是否需要获取该行
                            if(eachRow.getCell(numCell)!=null && eachRow.getCell(k)!=null ){
                                eachRow.getCell(k).setCellType(CellType.STRING);
                                String val = getValue(eachRow.getCell(k), i, k, book, false);
                                //在excel单元格为"#N/A"时设置默认值
                                if(Objects.equals(val, "#N/A")){
                                    val = "0.00";
                                }
                                if(getValue(eachRow.getCell(numCell), i, numCell, book, false)!=null){
                                    sb.append(val);		// 所有数据添加到里面，用于判断该行是否为空

                                    if(keyMap.get(k).equals("学号")){
                                        user.setUserId(val);
                                    } else if (keyMap.get(k).equals("姓名")) {
                                        user.setUserName(val);
                                    } else if (keyMap.get(k).equals("密码")) {
                                        //pwd的md5加密
//                                        String salt = UUID.randomUUID().toString().toUpperCase();
//                                        Md5 md5 = new Md5();
//                                        String currPwd = md5.MD5handler(val, salt);
//                                        user.setPassword(currPwd);
                                        //在插入数据库时会进行md5加密，此时无需md5加密
                                        user.setPassword(val);
                                    }
                                }
                            }
                        }

                    }
                    if (!sb.toString().isEmpty()) {
                        users.add(user);
                    }
                }
            }

        }
//         返回文件读取到的数据
        return users;
    }

    //获取每个单元格的数据
    public String getValue(Cell cell, int rowNum, int index, Workbook book, boolean isKey) throws IOException{

        // 空白或空
        if (cell == null || cell.getCellType()== CellType.BLANK ) {
//            if (isKey) {
////                book.close();
//                throw new NullPointerException(String.format("the key on row %s index %s is null ", ++rowNum,++index));
//            }else{
//                return null;
//            }
            return null;
        }

        // 0.数字
        if (cell.getCellType() == CellType.NUMERIC) {
//            if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                Date date = cell.getDateCellValue();
//                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                return df.format(date);
//            }
            String val = cell.getNumericCellValue()+"";
            val = val.toUpperCase();
            if (val.contains("E")) {
                val = val.split("E")[0].replace(".", "");
            }
            return val;
        }

        // 1.String
        if (cell.getCellType() == CellType.STRING) {
            String val = cell.getStringCellValue();
            if (val == null || val.trim().isEmpty()) {
//                if (book != null) {
//                    book.close();
//                }
                return null;
            }
            return val.trim();
        }

        // 2.公式
//        if (cell.getCellType() == CellType.FORMULA) {
//            return cell.getStringCellValue();
//        }
//
//        // 3.Boolean
//        if (cell.getCellType() == CellType.BOOLEAN) {
//            return cell.getBooleanCellValue()+"";
//        }

        // 4.错误 CELL_TYPE_ERROR
        return "";
    }
}
