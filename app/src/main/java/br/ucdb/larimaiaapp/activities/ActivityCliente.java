package br.ucdb.larimaiaapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import br.ucdb.larimaiaapp.api.ApiWeb;
import br.ucdb.larimaiaapp.model.Cliente;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


import br.ucdb.larimaiaapp.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActivityCliente extends AppCompatActivity {

    static boolean valida = false;
    static Cliente cli ;
    @Bind(R.id.txt_nome)
    EditText txtNome;

    @Bind(R.id.txt_email)
    EditText txtEmail;


    @Bind(R.id.txt_telefone)
    EditText txtTelefone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Cliente cli = (Cliente) intent.getSerializableExtra("Cliente");
        if(cli!=null){
            TelaEditar(cli);
        }
    }

    @OnClick(R.id.btn_salvar_cliente)
    public void salvar() {
        if(valida==false) {
            Cliente c = new Cliente();
            c.setNome(txtNome.getText().toString());
            c.setEmail(txtEmail.getText().toString());
            c.setTelefone(txtTelefone.getText().toString());

            ApiWeb.getRotas().salvarCliente(c, new Callback<Response>() {
                @Override
                public void success(Response response, Response response2) {
                    Toast.makeText(ActivityCliente.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(ActivityCliente.this, "Falha ao salvar", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            editar();
        }

    }

    @OnClick(R.id.btn_Listar_cliente)
    public void listar() {
        Intent irParaTelaListar = new Intent(ActivityCliente.this, ActivityConsultaCliente.class);
        startActivityForResult(irParaTelaListar, 1);
    }

    public void editar(){
        Cliente c = cli;
        c.setNome(txtNome.getText().toString());
        c.setEmail(txtEmail.getText().toString());
        c.setTelefone(txtTelefone.getText().toString());

        ApiWeb.getRotas().salvarCliente(c, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Toast.makeText(ActivityCliente.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActivityCliente.this, "Falha ao salvar", Toast.LENGTH_SHORT).show();
            }
        });
        valida=false;
    }

    public void TelaEditar(Cliente c){
        cli = c;
        valida = true;
        txtNome.setText(c.getNome());
        txtEmail.setText(c.getEmail());
        txtTelefone.setText(c.getTelefone());
    }
}
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_cadastro_cliente, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }