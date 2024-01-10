package commonMethods;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

	
public class TestNgXml {
	public static String testExecutionFile=System.getProperty("user.dir") +Utils.getDataFromTestConfig("TestExeFile");
	public void testNgXmlSuite() {
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		List<XmlClass> classes = new ArrayList<XmlClass>();
		List<Class> listenerClasses = new ArrayList<Class>();
		XmlSuite suite = new XmlSuite();
		suite.setName("ProgramSuite");		XmlTest test = new XmlTest(suite);
		test.setName("ProgramTest");
		List<String> classNames = extractExcelContentByColumnIndex(2);
		List<String> testCases = extractExcelContentByColumnIndex(3);
		//for (int i = 0; i < classNames.size(); i++) 
		{
			XmlClass clss1 = new XmlClass("commonMethods.Testcases");
			classes.add(clss1);
			/*clss1.setIncludedMethods(Arrays.asList(new XmlInclude[] 
					{ 
							new XmlInclude(testCases.get(i)) 
					}));*/
		}
		//listenerClasses.add(ListenerTest.class);
		test.setXmlClasses(classes);
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		//tng.setListenerClasses(listenerClasses);
		tng.run();
	}
	
	static HashMap<String, String> values ;
	public static HashMap<String,String> getdatafromExecution() {
		values = new HashMap<String, String>();
		List<String> TestcaseID = extractExcelContentByColumnIndex(4);
		List<String> Environment = extractExcelContentByColumnIndex(6);
		for (int k=0;k<TestcaseID.size();k++) {
			values.put(TestcaseID.get(k), Environment.get(k));
		}
		return values;
	}
	
	public static void main(String args[]) throws MalformedURLException {
	/*	{
			  System.setProperty("atu.reporter.config","D:\\connaizen\\TDD\\atu.properties");
		}*/
		
		execution();
		/*
		TestNG runner=new TestNG();

		// Create a list of String 
		List<String> suitefiles=new ArrayList<String>();

		// Add xml file which you have to execute
		suitefiles.add("D:\\connaizen\\TDD\\config\\testng.xml");

		// now set xml file for execution
		runner.setTestSuites(suitefiles);

		// finally execute the runner using run method
		runner.run();*/
	}
	public static ArrayList<String> extractExcelContentByColumnIndex(int columnIndex) {
		ArrayList<String> columndata = null;
		try {
			File f = new File(testExecutionFile);
			FileInputStream ios = new FileInputStream(f);
			XSSFWorkbook workbook = new XSSFWorkbook(ios);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			columndata = new ArrayList<String>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (row.getRowNum() > 0) { 
						if (cell.getColumnIndex() == columnIndex) {
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								columndata.add(cell.getNumericCellValue() + "");
								break;
							case Cell.CELL_TYPE_STRING:
								columndata.add(cell.getStringCellValue());
								break;
							}
						}
					}
				}
			}
			ios.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columndata;
	}
	public static void execution() {
		 try {
	         DocumentBuilderFactory dbFactory =
	         DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = 
	            dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         // root element
	         Element rootElement = doc.createElement("suite");
	         doc.appendChild(rootElement);
	         
	         Attr suiteattr1 = doc.createAttribute("configfailurepolicy");
	         suiteattr1.setValue("continue");
	         rootElement.setAttributeNode(suiteattr1);
	         
	         Attr suiteattr2 = doc.createAttribute("name");
	         suiteattr2.setValue("Suite");
	         rootElement.setAttributeNode(suiteattr2);
	         
	         Attr suiteattr3 = doc.createAttribute("parallel");
	         suiteattr3.setValue("none");
	         rootElement.setAttributeNode(suiteattr3);
	         
	         Element test = doc.createElement("test");
	         rootElement.appendChild(test);
	         
	         Attr testattr1 = doc.createAttribute("name");
	         testattr1.setValue("Test");
	         test.setAttributeNode(testattr1);
	         
	         Attr testattr2 = doc.createAttribute("preserve-order");
	         testattr2.setValue("true");
	         test.setAttributeNode(testattr2);
	         
	         Element classes = doc.createElement("classes");
	         test.appendChild(classes);
	         
	         Element class1 = doc.createElement("class");
	         classes.appendChild(class1);
	         
	         Attr classattr1 = doc.createAttribute("name");
	         classattr1.setValue("commonMethods.Testcases");
	         class1.setAttributeNode(classattr1);
	         
	         Element methods = doc.createElement("methods");
	         test.appendChild(methods);
	         
	     	List<String> testCases = extractExcelContentByColumnIndex(4);  	
	     	List<String> flag = extractExcelContentByColumnIndex(5);
	    	
	         for(int i=1;i<=testCases.size();i++)
	         {
	        	 String testcasename=testCases.get(i-1);
	        	 String flag1=flag.get(i-1);
	        	 if(flag1.equals("Yes"))
	        	 {
	         Element include = doc.createElement("include");
	         methods.appendChild(include);
	         
	         Attr includeattri= doc.createAttribute("name");
	         includeattri.setValue(testcasename);
	         include.setAttributeNode(includeattri);
	         
	         Attr priority= doc.createAttribute("priority");
	         String integer = Integer.toString(i);
	         priority.setValue(integer);
	         include.setAttributeNode(priority);
	        	 }
	         }
	         
	         Element listeners = doc.createElement("listeners");
	         rootElement.appendChild(listeners);
	         
	         String a[]={"atu.testng.reports.listeners.ATUReportsListener","atu.testng.reports.listeners.ConfigurationListener","atu.testng.reports.listeners.MethodListener"};
	         for(int i=0;i<a.length;i++)
	         {
	        	 Element listener = doc.createElement("listener");
		         listeners.appendChild(listener);
		         listener.setAttribute("class-name", a[i]);
	         }
	         	         
	         // write the content into xml file
	         TransformerFactory transformerFactory =TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         StreamResult result =new StreamResult(new File(System.getProperty("user.dir")+"\\testng.xml"));
	         transformer.transform(source, result);
	         // Output to console for testing
	         StreamResult consoleResult =
	         new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
}