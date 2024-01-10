package atu.testng.reports.excel;

import atu.testng.reports.chart.PieChart;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.jfree.chart.JFreeChart;

public class ExcelReports$ExcelChart {
	private static byte[] writeChartToByteStream(JFreeChart paramJFreeChart) {
		BufferedImage localBufferedImage = paramJFreeChart.createBufferedImage(450, 450);
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		try {
			ImageIO.write(localBufferedImage, "png", localByteArrayOutputStream);
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}
		byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
		return arrayOfByte;
	}

	public static byte[] writeChartToExcel(int paramInt1, int paramInt2, int paramInt3) {
		return writeChartToByteStream(PieChart.generate2DPieChart(paramInt1, paramInt2, paramInt3));
	}
}