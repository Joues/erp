package com.yihang.serp.utils;

import com.yihang.serp.model.*;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class POIUtils {

    public static ResponseEntity<byte[]> PurOrder2Excel(List<PurOrder> list) {
        //1. 创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 创建文档摘要
        workbook.createInformationProperties();
        //3. 获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory("采购订单信息");
        //文档管理员
        docInfo.setManager("yihang");
        //设置公司信息
        docInfo.setCompany("www.yihang.ml");
        //4. 获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //文档标题
        summInfo.setTitle("采购订单表");
        //文档作者
        summInfo.setAuthor("周逸航");
        // 文档备注
        summInfo.setComments("本文档由 yihang 提供");
        //5. 创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        HSSFSheet sheet = workbook.createSheet("采购订单表");
        //设置列的宽度
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 5 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 10 * 256);
        sheet.setColumnWidth(7, 10 * 256);
        sheet.setColumnWidth(8, 16 * 256);
        sheet.setColumnWidth(9, 12 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        //6. 创建标题行
        HSSFRow r0 = sheet.createRow(0);
        HSSFCell c0 = r0.createCell(0);
        c0.setCellValue("订单号");
        c0.setCellStyle(headerStyle);
        HSSFCell c1 = r0.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("物料名");
        HSSFCell c2 = r0.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("规格");
        HSSFCell c3 = r0.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("类别");
        HSSFCell c4 = r0.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("供应商");
        HSSFCell c5 = r0.createCell(5);
        c5.setCellStyle(headerStyle);
        c5.setCellValue("仓库");
        HSSFCell c6 = r0.createCell(6);
        c6.setCellStyle(headerStyle);
        c6.setCellValue("价格");
        HSSFCell c7 = r0.createCell(7);
        c7.setCellStyle(headerStyle);
        c7.setCellValue("数量");
        HSSFCell c8 = r0.createCell(8);
        c8.setCellStyle(headerStyle);
        c8.setCellValue("单位");
        HSSFCell c9 = r0.createCell(9);
        c9.setCellStyle(headerStyle);
        c9.setCellValue("下单时间");
        HSSFCell c10 = r0.createCell(10);
        c10.setCellStyle(headerStyle);
        c10.setCellValue("订单状态");
        for (int i = 0; i < list.size(); i++) {
            PurOrder pur = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(pur.getOid());
            row.createCell(1).setCellValue(pur.getWname());
            row.createCell(2).setCellValue(pur.getGuige());
            row.createCell(3).setCellValue(pur.getTypes().getTypeName());
            row.createCell(4).setCellValue(pur.getPurSuppliers().getSupplierName());
            row.createCell(5).setCellValue(pur.getStores().getStoreName());
            row.createCell(6).setCellValue(pur.getPrice());
            row.createCell(7).setCellValue(pur.getNum());
            row.createCell(8).setCellValue(pur.getUnit());
            HSSFCell cell9 = row.createCell(9);
            cell9.setCellStyle(dateCellStyle);
            cell9.setCellValue(pur.getDate());
            row.createCell(10).setCellValue(pur.getStates().getStateName());
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String("采购订单表.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    /**
     * Excel 解析成 员工数据集合
     *
     * @param file
     * @param allTypes
     * @param allPurSuppliers
     * @param allStates
     * @param allStores
     * @return
     */
    public static List<PurOrder> excel2PurOrder(MultipartFile file, List<PurSupplier> allPurSuppliers, List<Type> allTypes, List<Store> allStores, List<State> allStates) {
        List<PurOrder> list = new ArrayList<>();
        PurOrder purOrder = null;
        try {
            //1. 创建一个 workbook 对象
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            //2. 获取 workbook 中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //3. 获取表单
                HSSFSheet sheet = workbook.getSheetAt(i);
                //4. 获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    //5. 跳过标题行
                    if (j == 0) {
                        continue;//跳过标题行
                    }
                    //6. 获取行
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    //7. 获取列数
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    purOrder = new PurOrder();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellType()) {
                            case STRING:
                                String cellValue = cell.getStringCellValue();
                                switch (k) {
                                    case 1:
                                        purOrder.setOid(cellValue);
                                        break;
                                    case 2:
                                        purOrder.setWname(cellValue);
                                        break;
                                    case 3:
                                        purOrder.setGuige(cellValue);
                                        break;
                                    case 4:
                                        int typeIndex = allTypes.indexOf(new Type(cellValue));
                                        purOrder.setType(allTypes.get(typeIndex).getId());
                                        break;
                                    case 5:
                                        int supplierIndex = allPurSuppliers.indexOf(new PurSupplier(cellValue));
                                        purOrder.setSupplier(allPurSuppliers.get(supplierIndex).getId());
                                        break;
                                    case 6:
                                        int storeIndex = allStores.indexOf(new Store(cellValue));
                                        purOrder.setStore(allStores.get(storeIndex).getId());
                                        break;
                                    case 7:
                                        purOrder.setPrice(cellValue);
                                        break;
                                    case 8:
                                        purOrder.setNum(cellValue);
                                        break;
                                    case 9:
                                        purOrder.setUnit(cellValue);
                                        break;
                                    case 11:
                                        int stateIndex = allStates.indexOf(new State(cellValue));
                                        purOrder.setState(allStates.get(stateIndex).getId());
                                        break;
                                }
                                break;
                            default: {
                                switch (k) {
                                    case 10:
                                        purOrder.setDate(cell.getDateCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    list.add(purOrder);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
