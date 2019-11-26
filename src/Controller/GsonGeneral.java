package Controller;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

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
        	Type tipoListaPersonas = new TypeToken<List<Persona>>(){}.getType();
        	List<Tickets> tickets = gson.fromJson(reader, tipoListaPersonas);
        	
        	listaTickets = tickets;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return listaTickets;
	}

	public static void EscribirJson(String representacionBonita) {
		try{
			BufferedWriter bw; //Escribimos la info en el archivo json
			bw = new BufferedWriter(new FileWriter("usuarios.json"));
		    bw.write(representacionBonita);
		    bw.close();				 
		} catch (IOException ioe){
		     ioe.printStackTrace();
		  }
	}
}
