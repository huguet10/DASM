package com.miw.dasm.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.miw.dasm.R;
import com.miw.dasm.connection.ClientConnectionAPI;
import com.miw.dasm.connection.ClientConnectionRequest;
import com.miw.dasm.connection.ClientConnectionResponse;
import com.miw.dasm.connection.TypeRequest;
import com.miw.dasm.model.HandlerPersona;

public class MainActivity extends Activity {

	public MainActivity getContext() {
		return this;
	}

	private final OnClickListener eventButton = new OnClickListener() {
		@Override
		public void onClick(View view) {
			String dni = ((EditText) findViewById(R.id.textDniSearch))
					.getText().toString();
			if (view.getId() != R.id.buttonSearch && dni.isEmpty()) {
				Toast.makeText(getBaseContext(), "Debe introducir un DNI",
						Toast.LENGTH_SHORT).show();
			} else {
				ClientConnectionResponse response = new ClientConnectionAPI(
						getContext(), TypeRequest.GET)
						.execute(new ClientConnectionRequest((dni)));
				launchView(response.getHandlerPersona(), view.getId(), dni);
			}
		}

		private void launchView(HandlerPersona handlerPersona, int idButton,
				String dni) {
			switch (idButton) {
			case R.id.buttonAdd:
				if (handlerPersona.contains(dni)) {
					Toast.makeText(getBaseContext(), "La persona ya existe",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getBaseContext(), "XXXXXXXXXXXXXXXX",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.buttonDelete:
				if (handlerPersona.contains(dni)) {
					Toast.makeText(getBaseContext(), "XXXXXXXXXXXXXXXX",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getBaseContext(), "La persona no existe",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.buttonSearch:
				if (handlerPersona.contains(dni)) {
					Toast.makeText(getBaseContext(), "Se muestra una persona",
							Toast.LENGTH_SHORT).show();
				} else if (dni.isEmpty()) {
					Toast.makeText(getBaseContext(),
							"Se muestran todas las personas",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getBaseContext(), "La persona no existe",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.buttonEdit:
				if (handlerPersona.contains(dni)) {
					Toast.makeText(getBaseContext(), "XXXXXXXXXXXXXXXX",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getBaseContext(), "La persona no existe",
							Toast.LENGTH_SHORT).show();
				}
				break;
			}
		}
	};

	public void showDialog() {
		pDialog = ProgressDialog.show(MainActivity.this,
				getString(R.string.information),
				getString(R.string.progress_title), false, true);
	}

	public void closeDialog() {
		pDialog.dismiss();
	}
	
	private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.findViewById(R.id.buttonAdd).setOnClickListener(this.eventButton);
		this.findViewById(R.id.buttonSearch).setOnClickListener(
				this.eventButton);
		this.findViewById(R.id.buttonEdit).setOnClickListener(this.eventButton);
		this.findViewById(R.id.buttonDelete).setOnClickListener(
				this.eventButton);
	}
}
