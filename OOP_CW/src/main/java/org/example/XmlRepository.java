package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class XmlRepository {
    public void saveProducts(ArrayList<Product> products){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("cart");
            document.appendChild(root);

            for (Product product : products) {
                Element productElement = document.createElement("product");

                Element id = document.createElement("id");
                id.appendChild(document.createTextNode(String.valueOf(product.getId())));
                productElement.appendChild(id);

                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(product.getName()));
                productElement.appendChild(name);

                Element price = document.createElement("price");
                price.appendChild(document.createTextNode(String.valueOf(product.getPrice())));
                productElement.appendChild(price);

                root.appendChild(productElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            File file = new File("products.xml");
            if(!file.exists()) {
                System.out.println("File doesn't exist yet.");
            }
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(source, streamResult);

            System.out.println("Successfully Created XML File.");
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public ArrayList<Product> loadProducts(){
        ArrayList<Product> products = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File("products.xml"));
            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            System.out.println("Root Element: " + root.getNodeName());

            NodeList productList = document.getElementsByTagName("product");
            for (int i = 0; i < productList.getLength(); i++){
                Node productNode = productList.item(i);
                if (productNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element productElement = (Element) productNode;

                    int id = Integer.valueOf(productElement.getElementsByTagName("id").item(0).getTextContent());
                    String name = productElement.getElementsByTagName("name").item(0).getTextContent();
                    double price = Double.valueOf(productElement.getElementsByTagName("price").item(0).getTextContent());
                    System.out.println("Product ID: " + id);
                    System.out.println("name: " + name);
                    System.out.println("price: " + price);
                    System.out.println();

                    products.add(new Product(id, name, price));
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return  products;
    }
}