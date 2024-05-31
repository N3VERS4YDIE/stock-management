package com.stock.management.web.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public byte[] generateReport(String sql) throws JRException {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        if (rows.isEmpty()) {
            throw new JRException("The query returned no results.");
        }

        Map<String, Object> firstRow = rows.get(0);
        JasperDesign jasperDesign = createJasperDesign(firstRow);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rows);

        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private JasperDesign createJasperDesign(Map<String, Object> sampleRow) throws JRException {
        InputStream inputStream = this.getClass().getResourceAsStream("/report-template.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);

        int x = 0;
        int columnWidth = jasperDesign.getColumnWidth() / sampleRow.size();

        if (jasperDesign.getColumnHeader() == null) {
            JRDesignBand columnHeader = new JRDesignBand();
            columnHeader.setHeight(20);
            jasperDesign.setColumnHeader(columnHeader);
        }

        for (Map.Entry<String, Object> entry : sampleRow.entrySet()) {
            String fieldName = entry.getKey();
            Class<?> fieldClass = entry.getValue().getClass();

            JRDesignField field = new JRDesignField();
            field.setName(fieldName);
            field.setValueClass(fieldClass);
            jasperDesign.addField(field);

            JRDesignTextField textField = new JRDesignTextField();
            textField.setX(x);
            textField.setWidth(columnWidth);
            textField.setHeight(20);
            textField.setExpression(new JRDesignExpression("$F{" + fieldName + "}"));
            ((JRDesignBand) jasperDesign.getDetailSection().getBands()[0]).addElement(textField);

            JRDesignStaticText staticText = new JRDesignStaticText();
            staticText.setX(x);
            staticText.setWidth(columnWidth);
            staticText.setHeight(20);
            staticText.setText(fieldName);
            ((JRDesignBand) jasperDesign.getColumnHeader()).addElement(staticText);

            x += columnWidth;
        }

        return jasperDesign;
    }
}
