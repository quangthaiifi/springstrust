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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class Readexcel {
    List<Model> models;

    List<Pa> pas;

    public void addList(List<Model> models, Model model) {
        boolean check = false;

        for (int i = 0; i < models.size(); i++) {
            String str = models.get(i).getDateTime().toString();
            String str1 = model.getDateTime().toString();

            if (str.equals(str1)) {
                check = true;
            }
        }

        if (!check) {
            models.add(model);
        }
    }

    public void readEXCEL(int id) {
        models = new ArrayList<>();
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(new File("C:\\Users\\nqthai\\Downloads\\fresher\\fresher\\data.xls"));
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
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
                    addList(models, model1);
                    addList(models, model2);
                    addList(models, model3);
                    addList(models, model4);
                    addList(models, model5);
                    addList(models, model6);
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @GetMapping(value = "/{id}")
    public List<Pa> getAllModel(@PathVariable int id) throws ParseException {// Đọc một file XSL.

        pas = new ArrayList<Pa>();
        readEXCEL(id);
        int m = 0;
        for (int i = 1; i <= 12; i++)
            for (int j = 1; j <= 31; j++) {
                double d = 0;
                double s = 0;
                for (int k = 0; k < models.size(); k++)
                    if (models.get(k).getDateTime().getDayOfMonth() == j && models.get(k).getDateTime().getMonthValue() == i) {
                        m = k;
                        d++;
                        s += models.get(k).getPa();
                    }
                if (s != 0) {

                    Pa pa = new Pa();

                    pa.setDateTime(models.get(m).getDateTime().toLocalDate().toString());
                    pa.setPaValue(s / 6);

                    pas.add(pa);
                }
            }
        System.out.println(pas);
        return pas;

    }

    @GetMapping(value = "/{sheet1}/{day}/{month}/{years}")
    public List<Paofhour> getListPaofhours(@PathVariable int sheet1, @PathVariable int day, @PathVariable int month, @PathVariable int years) {
        // System.out.println(day + "/" + month);
        List<Paofhour> paofhours = new ArrayList<Paofhour>();
        readEXCEL(sheet1);
        //System.out.println(models.size());
        for (int i = 0; i <= 23; i++) {
            for (int j = 0; j <= 60; j = j + 10) {
                double s = 0;
                double d = 0;
                for (int k = 0; k < models.size(); k++)
                    if (models.get(k).getDateTime().getMonthValue() == month && models.get(k).getDateTime().getDayOfMonth() == day && models.get(k).getDateTime().getHour() == i && models.get(k).getDateTime().getMinute() == j) {
                        d++;
                        Paofhour paofhour = new Paofhour();
                        System.out.println(models.get(k));
                        paofhour.setPsValue(models.get(k).getPsp());
                        paofhour.setPaValues(models.get(k).getPa());
                        paofhour.setHours(models.get(k).getDateTime());
                        paofhours.add(paofhour);

                        //  ps=models.get(k).getPsp();


                    }
//                    if(s!=0)
//                    {
//                        Paofhour paofhour = new Paofhour();
//                        paofhour.setHours(i);
//                        paofhour.setPaValues(s/d);
//                        paofhour.setPsValue(ps);
//                        paofhours.add(paofhour);
//                    }
            }


        }
        return paofhours;
    }
}


