package com.example.notasqlite;

import java.util.ArrayList;
import java.util.HashMap;

public class NotaController {
	public ArrayList<Nota> NOTAS = new ArrayList<>();
	public HashMap<String, Nota> nota_map = new HashMap<>();
	
	public void atualizarNotas() {
		nota_map.clear();
		for (Nota NOTA : NOTAS) {
			nota_map.put(String.valueOf(NOTA.getId()), NOTA);
		}
	}
}