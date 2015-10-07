package br.ucdb.larimaiaapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.ucdb.larimaiaapp.R;
import br.ucdb.larimaiaapp.api.ApiWeb;
import br.ucdb.larimaiaapp.model.Cliente;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActivityConsultaCliente extends AppCompatActivity {

    @Bind(R.id.lista)
    ListView listaCliente;

    @Bind(R.id.btn_lista_voltar)
    Button voltar;

    List<Cliente> clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_lista);

        ButterKnife.bind(this);

        listar();


        listaCliente.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Cliente cli = (Cliente) adapterView.getItemAtPosition(i);
                new AlertDialog.Builder(ActivityConsultaCliente.this)
                        .setTitle("Excluir Cliente")
                        .setMessage("Deseja realmente excluir este cliente?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, final int i) {
                                //startLoading();
                                //Chamando API para remover
                                ApiWeb.getRotas().excluirCliente(cli.getId(), new Callback<Response>() {
                                    @Override
                                    public void success(Response response, Response response2) {
                                        listar();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        Toast.makeText(ActivityConsultaCliente.this, "Erro ao conectar com o servidor", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                        });
                return false;
            }


        });

        listaCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Cliente cli = (Cliente) adapterView.getItemAtPosition(position);
                ActivityCliente a = new ActivityCliente() ;
                a.TelaEditar(cli);
                //Intent intent = new Intent(ActivityConsultaCliente.this, ActivityCliente.class);
                //intent.putExtra("Cliente",cli);
                //startActivity(intent);

                //Chamando nova activity passando um Objeto no Bundle para ser editado no form
                //startActivity(new Intent(ActivityConsultaCliente.this, ActivityCliente.class).putExtra(ActivityCliente.EDIT_KEY_COURSE, adapterView.getItem(position)));
            }
        });
    }

    public void voltar(View view){
        Intent it = new Intent(this,ActivityCliente.class);
        startActivity(it);
    }

    public void listar(){
        ApiWeb.getRotas().listaClientes(new Callback<List<Cliente>>() {
            @Override
            public void success(List<Cliente> clientes, Response response) {
                //Carrengando no ListView
                ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(ActivityConsultaCliente.this, android.R.layout.simple_list_item_1, clientes);
                listaCliente.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consulta_cliente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
