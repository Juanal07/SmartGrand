package Controller;

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

public class GsonPersona {
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


}
