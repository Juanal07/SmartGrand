package Controller;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Model.Medico;
import Model.Persona;
import Model.Tickets;

public class GsonGeneral {
	public static List<Persona> desserializarJsonAArray() {	
        String jsonInString = "usuarios.json";
        List<Persona> listaPersonas = null;
        try (Reader reader = new FileReader(jsonInString)) {
        	Gson gson = new Gson();
        	Type tipoListaPersonas = new TypeToken<List<Persona>>(){}.getType();
        	List<Persona> personas = gson.fromJson(reader, tipoListaPersonas);
        	
        	listaPersonas = personas;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return listaPersonas;
	}

	public static List<Tickets> desserializarJsonAArrayTicket() {	
        String jsonInString = "jsonTickets.json";
        List<Tickets> listaTickets = null;
        try (Reader reader = new FileReader(jsonInString)) {
        	Gson gson = new Gson();
        	Type tipoListaPersonas = new TypeToken<List<Tickets>>(){}.getType();
        	List<Tickets> tickets = gson.fromJson(reader, tipoListaPersonas);
        	
        	listaTickets = tickets;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return listaTickets;
	}
	
	public static List<Medico> desserializarJsonAArrayMedico() {	
        String jsonInString = "Medicos.json";
        List<Medico> listaMedicos = null;
        try (Reader reader = new FileReader(jsonInString)) {
        	Gson gson = new Gson();
        	Type tipoListaPersonas = new TypeToken<List<Medico>>(){}.getType();
        	List<Medico> medicos = gson.fromJson(reader, tipoListaPersonas);
        	
        	listaMedicos = medicos;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return listaMedicos;
	}

	public static void EscribirJson(String representacionBonita, String ruta) {
		try{
			BufferedWriter bw; //Escribimos la info en el archivo json
			bw = new BufferedWriter(new FileWriter(ruta));
		    bw.write(representacionBonita);
		    bw.close();				 
		} catch (IOException ioe){
		     ioe.printStackTrace();
		  }
	}
	
	public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
}
