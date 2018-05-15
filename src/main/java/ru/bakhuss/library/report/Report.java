package ru.bakhuss.library.report;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import ru.bakhuss.library.view.BookView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import static net.sf.dynamicreports.report.builder.DynamicReports.export;

public class Report {

    public void build(Collection<BookView> books) {
        JasperReportBuilder report = DynamicReports.report();
        report
                .columns(
                        Columns.column("Book id", "id", DataTypes.stringType()),
                        Columns.column("Name", "name", DataTypes.stringType())
                )
                .title(
                        Components.text("Books")
                                .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                )
                .pageFooter(Components.pageXofY())
                .setDataSource(books);
        JasperPdfExporterBuilder pdf = null;
        try {
            pdf = export.pdfExporter(new FileOutputStream("books.pdf"));
            pdf.setCharacterEncoding("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            report.toPdf(pdf);
        } catch (DRException e) {
            e.printStackTrace();
        }
    }

}
