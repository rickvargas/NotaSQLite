package com.example.notasqlite;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExibeNotaActivity extends AppCompatActivity {
	TextView textViewId;
	TextView textViewBody;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exibe_evento);
	
		int id=getIntent().getExtras().getInt("id");
		
		textViewId = findViewById(R.id.editTextViewId);
		textViewBody = findViewById(R.id.editTextViewBody);

		NotaController notaController = new NotaController();
		textViewId.setText(notaController.NOTAS.get(id).getId());
		textViewBody.setText(notaController.NOTAS.get(id).getBody());
	
	}
}