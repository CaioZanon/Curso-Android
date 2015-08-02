package com.example.administrador.teste.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.teste.R;
import com.example.administrador.teste.model.entities.Client;
import com.example.administrador.teste.model.entities.ClientAddress;
import com.example.administrador.teste.model.service.CepService;
import com.example.administrador.teste.util.FormHelper;


/**
 * Created by Administrador on 21/07/2015.
 */
public class ClientPersistentActivity extends AppCompatActivity{

    public static String CLIENT_PARAM = "CLIENT_PARAM";
    private Client client;

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextPhone;
    private EditText editTextCEp;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private EditText editTextEstado;
    private EditText editTextTipoLogradouro;
    private EditText editTextLogradouro;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        bindFields();

        // pega o client que foi serializado no EDIT.
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            client = (Client) extras.getParcelable(CLIENT_PARAM);
            if(client == null){
                throw new IllegalArgumentException();
            }
            bindForm();
        }
    }

    private void bindFields() {
        editTextName = (EditText) findViewById(R.id.editTextename);
        editTextName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_edittext_client, 0);
        editTextName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextName.getRight() - editTextName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        final Intent goToSOContacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        goToSOContacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                        startActivityForResult(goToSOContacts, 999);
                    }
                }
                return false;
            }


        });





        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextCEp = (EditText) findViewById(R.id.cep);
        editTextCEp.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_search_black_24dp, 0);
        editTextCEp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextCEp.getRight() - editTextCEp.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        new getAddressByCep().execute(editTextCEp.getText().toString());
                    }
                }
                return false;
            }


        });




        editTextEstado = (EditText) findViewById(R.id.estado);
        editTextCidade = (EditText) findViewById(R.id.cidade);
        editTextBairro = (EditText) findViewById(R.id.bairro);
        editTextLogradouro = (EditText) findViewById(R.id.logradouro);
        editTextTipoLogradouro = (EditText) findViewById(R.id.tipoLogradouro);
    }


    /**
     * @see <a href="http://developer.android.com/training/basics/intents/result.html">Getting a Result from an Activity</a>
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    final Uri contactUri = data.getData();
                    final String[] projection = {
                            ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    };
                    final Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    editTextName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME)));
                    editTextPhone.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

                    cursor.close();
                } catch (Exception e) {
                    Log.d("TAG", "Unexpected error");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {             // CLIQUE SIMPLES DO MENU.
        getMenuInflater().inflate(R.menu.menu_client_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(FormHelper.requiredValidate(ClientPersistentActivity.this, editTextName, editTextAge, editTextPhone , editTextCEp , editTextEstado , editTextCidade , editTextBairro , editTextLogradouro, editTextTipoLogradouro)){

            if(item.getItemId() == R.id.menu_save){
                Client client = bindClient();
            }

            client.save();
            Toast.makeText(ClientPersistentActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    private Client bindClient(){                // RECUPERA O CLIENTE DA TELA.
        if(client == null) {
            client = new Client();
        }

        client.setName(editTextName.getText().toString());
        client.setAge(Integer.valueOf(editTextAge.getText().toString()));
        client.setPhone(editTextPhone.getText().toString());
        client.setBairro(editTextBairro.getText().toString());
        client.setCidade(editTextCidade.getText().toString());
        client.setEstado(editTextEstado.getText().toString());
        client.setTipoDeLogradouro(editTextTipoLogradouro.getText().toString());
        client.setLogradouro(editTextLogradouro.getText().toString());
        client.setCep(editTextCEp.getText().toString());



        return client;
    }

    private void bindForm(){                // RECUPERA O CLIENTE PARA EDITAR.
        editTextName.setText(client.getName());
        editTextAge.setText(client.getAge().toString());
        editTextPhone.setText(client.getPhone());
        editTextBairro.setText(client.getBairro());
        editTextLogradouro.setText(client.getLogradouro());
        editTextTipoLogradouro.setText(client.getTipoDeLogradouro());
        editTextEstado.setText(client.getEstado());
        editTextCidade.setText(client.getCidade());
        editTextCEp.setText(client.getCep());
    }

    private class  getAddressByCep extends AsyncTask<String,Void,ClientAddress>{

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(ClientPersistentActivity.this);
            progressDialog.setMessage(getString(R.string.Loading));
            progressDialog.show();
        }

        @Override
        protected ClientAddress doInBackground(String... params) {
            return CepService.getAddressBy(params[0]);
        }

        @Override
        protected void onPostExecute(ClientAddress clientAddress) {
            if(clientAddress != null) {
                editTextCEp.setText(clientAddress.getCep().toString());
                editTextTipoLogradouro.setText(clientAddress.getTipoDeLogradouro().toString());
                editTextCidade.setText(clientAddress.getCidade().toString());
                editTextEstado.setText(clientAddress.getEstado().toString());
                editTextLogradouro.setText(clientAddress.getLogradouro().toString());
                editTextBairro.setText(clientAddress.getBairro().toString());

            }else
                Toast.makeText(ClientPersistentActivity.this, R.string.CEPNotFound, Toast.LENGTH_SHORT).show();


            progressDialog.dismiss();
        }
    }



}
