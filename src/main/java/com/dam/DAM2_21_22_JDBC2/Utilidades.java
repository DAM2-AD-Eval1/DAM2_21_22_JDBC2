package com.dam.DAM2_21_22_JDBC2;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
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
	
	public static void insertarBBDD(ArrayList<Empleado> listaEmp, ArrayList<Departamento> listaDepto) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/empresa", "root", "1234.Abcd");
		
		PreparedStatement psEmp = conexion.prepareStatement("INSERT INTO empleados VALUES(?, ?)");
		for (int i=0; i<listaEmp.size(); i++) {
			
			psEmp.setInt(1, listaEmp.get(i).getCodigo());
			psEmp.setString(2, listaEmp.get(i).getNombre());
			psEmp.executeUpdate();
			
		}
		
		PreparedStatement psDepto = conexion.prepareStatement("INSERT INTO departamentos VALUES(?, ?)");
		for (int i=0; i<listaDepto.size(); i++) {
			
			psDepto.setInt(1, listaDepto.get(i).getCodigo());
			psDepto.setString(2, listaDepto.get(i).getNombre());
			psDepto.executeUpdate();
			
		}
		
		psEmp.close();
		psDepto.close();
		conexion.close();
		
	}

}
