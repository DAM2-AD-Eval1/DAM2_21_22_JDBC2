package com.dam.DAM2_21_22_JDBC2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public class Principal {
	
	static ArrayList<Empleado> listaEmp = new ArrayList<Empleado>();
	static ArrayList<Departamento> listaDepto = new ArrayList<Departamento>();

	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException, ClassNotFoundException, SQLException {
		
		//Lectura Fichero
		Path rutaFichero = Paths.get("D:\\PRUEBAS\\empresa.csv");
		BufferedReader br = Files.newBufferedReader(rutaFichero);
		Stream<String> lineas = br.lines();
		
		lineas.forEach(l -> {
			String[] campos;
			
			campos = l.split(" ");
			
			if (campos[2].equals("1")){
				listaEmp.add(new Empleado(Integer.parseInt(campos[0]), campos[1]));
			}else {
				listaDepto.add(new Departamento(Integer.parseInt(campos[0]), campos[1]));
			}
		});
		
		//Generamos el XML
		Utilidades.generarXML(listaEmp, listaDepto);
		
		//Insertar en BBDD
		Utilidades.insertarBBDD(listaEmp, listaDepto);
		
	}

}
