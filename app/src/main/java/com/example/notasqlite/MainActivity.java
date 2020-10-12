package com.example.notasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// FAB
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
	RecyclerView recyclerView;
	NotaController notaController;
	SQLiteDatabase meuDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		meuDB = openOrCreateDatabase("notadb", MODE_PRIVATE, null);
		meuDB.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT, body VARCHAR);");
	
		recyclerView = findViewById(R.id.recylerView);
		recyclerView.setHasFixedSize(true);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		
		Cursor dataSelect = meuDB.rawQuery("SELECT * FROM notas", null);
		dataSelect.moveToFirst();
		
		// forcing clear the array...
		notaController = new NotaController();
		notaController.NOTAS.clear();
		for(int i = 0; i < dataSelect.getCount(); i++){
			// column 0 = id, column 1 = body
			notaController.NOTAS.add(new Nota(dataSelect.getString(0), dataSelect.getString(1)));
			dataSelect.moveToNext();
		}
		System.out.println(" getCount() ended : " + dataSelect.getCount());
		
		NotaAdapterRecyclerView notaAdapterRecyclerView =
				new NotaAdapterRecyclerView(
						getApplicationContext(),
						R.layout.list_notas,
						notaController.NOTAS
				);
		recyclerView.setAdapter(notaAdapterRecyclerView);

		final AlertDialog.Builder builderAlert = new AlertDialog.Builder(this);
		FloatingActionButton fabButton = findViewById(R.id.fabButton);
		fabButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				builderAlert.setTitle("Digite o conteudo da sua nova nota: ");
				final EditText inputBody = new EditText(MainActivity.this);
				builderAlert.setView(inputBody);

				builderAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String body = inputBody.getText().toString();
						addNota(body);
					}
				});
				builderAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				builderAlert.show();
				System.out.println("Showing");
			}
		});
	}
	
	public void addNota(String body){
		ContentValues notaValue = new ContentValues();
		notaValue.put("body", body);
		long addedID = meuDB.insert("notas",null, notaValue);

		if(addedID < 0){
			System.out.println("Falha ao adicionar entrada no banco...");
		}
		else{
			Nota minhaNota = new Nota(Long.toString(addedID), body);
			notaController.NOTAS.add(minhaNota);
		}
	}
}
