package com.dam.DAM2_21_22_JDBC2;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Utilidades {
	
	public static void generarXML(ArrayList<Empleado> listaEmp, ArrayList<Departamento> listaDepto) throws ParserConfigurationException, TransformerException {
		
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = factoria.newDocumentBuilder();
		Document documento = db.newDocument();
		
		Element empresa = documento.createElement("empresa");
		documento.appendChild(empresa);
		
		Element empleados = documento.createElement("empleados");
		empresa.appendChild(empleados);
		
		for (int i = 0; i<listaEmp.size(); i++) {
			Element empleado = documento.createElement("empleado");
			empleados.appendChild(empleado);
			
			Element codigo = documento.createElement("codigo");
			codigo.setTextContent(String.valueOf(listaEmp.get(i).getCodigo()));
			empleado.appendChild(codigo);
			
			Element nombre = documento.createElement("nombre");
			nombre.setTextContent(listaEmp.get(i).getNombre());
			empleado.appendChild(nombre);
		}
		
		Element departamentos = documento.createElement("departamentos");
		empresa.appendChild(departamentos);
		
		for (int i = 0; i<listaDepto.size(); i++) {
			Element departamento = documento.createElement("departamento");
			departamentos.appendChild(departamento);
			
			Element codigo = documento.createElement("codigo");
			codigo.setTextContent(String.valueOf(listaDepto.get(i).getCodigo()));
			departamento.appendChild(codigo);
			
			Element nombre = documento.createElement("nombre");
			nombre.setTextContent(listaDepto.get(i).getNombre());
			departamento.appendChild(nombre);
		}
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		
		DOMSource dom = new DOMSource(documento);
		StreamResult sr = new StreamResult(new File("D:\\PRUEBAS\\empresa.xml"));
		
		transformer.transform(dom, sr);
		
	}
	
	public static void insertarBBDD(ArrayList<Empleado> listaEmp, ArrayList<Departamento> listaDepto) {
		
	}

}
