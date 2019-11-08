package Controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import Model.Persona;

public class GsonPersona {
	public static void desserializarJsonAArray() {
		
		Gson gson = new Gson();
        String jsonInString = "usuarios.json";
        try (Reader reader = new FileReader(jsonInString)) {	
        	Type tipoListaPersonas = new TypeToken<List<Persona>>(){}.getType();
        	List<Persona> personas = gson.fromJson(reader, tipoListaPersonas);
        	for (int i = 0; i < personas.size(); i++) {
				System.out.println("nombre: " + personas.get(i).getNombre() + " -> tipo usuario: " + personas.get(i).getTipoUsuario());
			}
            
        } catch (IOException e) {
            e.printStackTrace();
        }
      
	}
}
