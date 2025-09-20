// 代码生成时间: 2025-09-20 12:05:21
package com.example.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@ManagedBean(name = "converterBean")
@ViewScoped
public class DocumentConverter {

    // Method to convert an XML file to a Word document
    public void convertXMLToWord(String xmlFilePath, String wordFilePath) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFilePath);
            doc.getDocumentElement().normalize();

            XWPFDocument xDoc = new XWPFDocument();
            XWPFParagraph p;
            NodeList nodeList = doc.getElementsByTagName("*");
            for (int i = 0; i < nodeList.getLength(); i++) {
                p = xDoc.createParagraph();
                p.createRun().setText(nodeList.item(i).getTextContent());
            }

            try (OutputStream out = Files.newOutputStream(Paths.get(wordFilePath),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                xDoc.write(out);
            }
            System.out.println("Conversion from XML to Word completed!");
        } catch (Exception e) {
            System.err.println("Error during XML to Word conversion: " + e.getMessage());
        }
    }

    // Method to convert a Word document to an XML file
    public void convertWordToXML(String wordFilePath, String xmlFilePath) {
        try {
            InputStream in = Files.newInputStream(Paths.get(wordFilePath));
            XWPFDocument xDoc = new XWPFDocument(in);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            for (XWPFParagraph para : xDoc.getParagraphs()) {
                doc.appendChild(doc.createElement("para"))
                        .appendChild(doc.createTextNode(para.getText()));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new StringWriter());
            transformer.transform(source, result);

            String xmlString = result.getWriter().toString();
            Files.write(Paths.get(xmlFilePath), xmlString.getBytes());
            System.out.println("Conversion from Word to XML completed!");
        } catch (Exception e) {
            System.err.println("Error during Word to XML conversion: " + e.getMessage());
        }
    }
}
