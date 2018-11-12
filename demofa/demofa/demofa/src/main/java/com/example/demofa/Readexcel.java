package com.example.demofa;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class Readexcel {
    Map<LocalDate, List<Model>> listMap = new HashMap<LocalDate, List<Model>>();
   // Boolean first = false;
    List<Pa> pas;

    public void addList(Map<LocalDate, List<Model>> listMap, Model model) {
//        //buoc dau
//        if (!first) {
//
//            List<Model> modelList = new ArrayList<Model>();
//            modelList.add(model);
//            listMap.put(model.getDateTime().toLocalDate(), modelList);
//            first = true;
//        }

        boolean checkvalue = false;

        LocalDate luuKey = model.getDateTime().toLocalDate();
        //listMap.con
        /*for (LocalDate key : listMap.keySet()) {
            String str = key.toString();
            String str1 = model.getDateTime().toLocalDate().toString();

            if (str.equals(str1)) {
                checkkey = true;
                luuKey = key;
                break;

            }
        }*/
        List<Model> modelList = listMap.get(luuKey);
        if (modelList != null) {

            for (int i = 0; i < modelList.size(); i++) {
                String strValue = modelList.get(i).getDateTime().toString();
                String strDatatime = model.getDateTime().toString();
                if (strValue.equals(strDatatime)) {
                    checkvalue = true;
                    break;
                }
            }
            if (!checkvalue) {
                modelList.add(model);
                listMap.put(luuKey, modelList);
            }
        }

     else {
            modelList = new ArrayList<Model>();
            modelList.add(model);
            listMap.put(luuKey, modelList);
        }
 }
    @GetMapping(value = "/check/{id}")
    public Map<LocalDate, List<Model>> readEXCEL(@PathVariable int id) {
        listMap = new HashMap<>();
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(new File("C:\\Users\\nqthai\\Downloads\\fresher\\fresher\\data.xls"));
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            if(id < 0  )
            {
            	return null;
            }
            HSSFSheet sheet = workbook.getSheetAt(id);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row = rowIterator.next();
            while (rowIterator.hasNext()) {

                row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();
                Model model1 = new Model();
                Model model2 = new Model();
                Model model3 = new Model();
                Model model4 = new Model();
                Model model5 = new Model();
                Model model6 = new Model();

                Pa pa = new Pa();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String[] date1String = cell.getStringCellValue().split("\\+");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime date1 = LocalDateTime.parse(date1String[0], formatter);
                    model1.setDateTime(date1);

                    cell = cellIterator.next();
                    String[] date2String = cell.getStringCellValue().split("\\+");
                    LocalDateTime date2 = LocalDateTime.parse(date2String[0], formatter);
                    model2.setDateTime(date2);
                    cell = cellIterator.next();
                    String[] date3String = cell.getStringCellValue().split("\\+");
                    LocalDateTime date3 = LocalDateTime.parse(date3String[0], formatter);
                    model3.setDateTime(date3);
                    cell = cellIterator.next();
                    String[] date4String = cell.getStringCellValue().split("\\+");
                    LocalDateTime date4 = LocalDateTime.parse(date4String[0], formatter);
                    model4.setDateTime(date4);
                    cell = cellIterator.next();
                    String[] date5String = cell.getStringCellValue().split("\\+");
                    LocalDateTime date5 = LocalDateTime.parse(date5String[0], formatter);
                    model5.setDateTime(date5);
                    cell = cellIterator.next();
                    String[] date6String = cell.getStringCellValue().split("\\+");
                    LocalDateTime date6 = LocalDateTime.parse(date6String[0], formatter);
                    model6.setDateTime(date6);

                    cell = cellIterator.next();
                    String[] pa1string1 = cell.getStringCellValue().split("k");
                    double pa1int1 = Double.parseDouble(pa1string1[0]);
                    model1.setPa(pa1int1);
                    cell = cellIterator.next();
                    String[] pa1string2 = cell.getStringCellValue().split("k");
                    double pa1int2 = Double.parseDouble(pa1string2[0]);
                    model2.setPa(pa1int2);
                    cell = cellIterator.next();
                    String[] pa1string3 = cell.getStringCellValue().split("k");
                    double pa1int3 = Double.parseDouble(pa1string3[0]);
                    model3.setPa(pa1int3);
                    cell = cellIterator.next();
                    String[] pa1string4 = cell.getStringCellValue().split("k");
                    double pa1int4 = Double.parseDouble(pa1string4[0]);
                    model4.setPa(pa1int4);
                    cell = cellIterator.next();
                    String[] pa1string5 = cell.getStringCellValue().split("k");
                    double pa1int5 = Integer.parseInt(pa1string5[0]);
                    model5.setPa(pa1int5);
                    cell = cellIterator.next();
                    String[] pa1string6 = cell.getStringCellValue().split("k");
                    double pa1int6 = Double.parseDouble(pa1string6[0]);
                    model6.setPa(pa1int6);

                    cell = cellIterator.next();
                    String[] pa1string = cell.getStringCellValue().split("k");
                    double psp = Double.parseDouble(pa1string[0]);
                    model1.setPsp(psp);
                    model2.setPsp(psp);
                    model3.setPsp(psp);
                    model4.setPsp(psp);
                    model5.setPsp(psp);
                    model6.setPsp(psp);

                    addList(listMap, model1);
                    addList(listMap, model2);
                    addList(listMap, model3);
                    addList(listMap, model4);
                    addList(listMap, model5);
                    addList(listMap, model6);

                }
            }


        } catch (IOException e) {

            e.printStackTrace();
        }

        return listMap;
    }


    @GetMapping(value = "/{id}")
    public List<Pa> getAllModel(@PathVariable int id)  {// Đọc một file XSL.
        pas = new ArrayList<Pa>();
        readEXCEL(id);
        int m = 0;
        LocalDate luukey = null;

        for (LocalDate key : listMap.keySet()) {
            Double s = 0.0;
            for (int k = 0; k < listMap.get(key).size(); k++) {
                m = k;
                luukey = key;
                s += listMap.get(key).get(k).getPa();
            }

            if (s != 0) {
                Pa pa = new Pa();

                pa.setDateTime(listMap.get(luukey).get(m).getDateTime().toLocalDate().toString());
                pa.setPaValue(s / 6);

                pas.add(pa);
            }

        }
        List<Pa> paList = new ArrayList<Pa>();
        int j = 0;
        for (int i = pas.size() - 1; i >= 0; i--) {
            paList.add(pas.get(i));
        }
        return paList;
    }
    @GetMapping(value = "/{id}/{startDay}/{endDay}")
    public  List<Pa> getPaInRanger(@PathVariable int id, @PathVariable String startDay, @PathVariable String endDay) throws ParseException {
        List<Pa> paAll= this.getAllModel(id);
        List<Pa> paInRanger = new ArrayList<>();
        for(int i=0;i<paAll.size();i++){
            String strDayPa = paAll.get(i).getDateTime().toString();
            if(strDayPa.compareTo(startDay)>=0 && strDayPa.compareTo(endDay)<=0)
                paInRanger.add(paAll.get(i));
        }
    return  paInRanger;
    }

    @GetMapping(value = "/{sheet1}/{day}/{month}/{years}")
    public List<Paofhour> getListPaofhours(@PathVariable int sheet1, @PathVariable int day, @PathVariable int month, @PathVariable int years) {
        String strday = Integer.toString(years) + "-" + Integer.toString(month) + "-" + Integer.toString(day);
        List<Paofhour> paofhours = new ArrayList<Paofhour>();
        readEXCEL(sheet1);
         for (LocalDate key : listMap.keySet())
            for (int k = 0; k < listMap.get(key).size(); k++) {
                if (strday.equals(listMap.get(key).get(k).getDateTime().toLocalDate().toString())) {
                    Paofhour paofhour = new Paofhour();

                    paofhour.setPsValue(listMap.get(key).get(k).getPsp());
                    paofhour.setPaValues(listMap.get(key).get(k).getPa());
                    paofhour.setHours(listMap.get(key).get(k).getDateTime());

                    paofhours.add(paofhour);
                }
            }
        return paofhours;
    }
}


