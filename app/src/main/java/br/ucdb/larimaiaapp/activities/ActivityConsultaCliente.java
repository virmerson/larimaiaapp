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
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActivityConsultaCliente extends AppCompatActivity {

    @Bind(R.id.lista_consulta)
    ListView listaCliente;

    List<Cliente> clientes;

    ArrayAdapter<Cliente> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cliente);

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
                Intent intent = new Intent(ActivityConsultaCliente.this, ActivityCliente.class);
                intent.putExtra("Cliente", cli);
                startActivity(intent);

                //Chamando nova activity passando um Objeto no Bundle para ser editado no form
                //startActivity(new Intent(ActivityConsultaCliente.this, ActivityCliente.class).putExtra(ActivityCliente.EDIT_KEY_COURSE, adapterView.getItem(position)));
            }
        });

        listaCliente.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Cliente cli = (Cliente) parent.getItemAtPosition(position);
                AlertDialog alert = new AlertDialog.Builder(ActivityConsultaCliente.this)
                        .setTitle("Excluir")
                        .setMessage("Deseja realmente excluir?")
                        .setNegativeButton("Não", null)
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
                        }).show();
                return true;
            }

        });
    }

    //Botão para voltar a tela anterior, tela de cadastros
    @OnClick(R.id.btn_lista_voltar)
    public void voltar(){
        Intent it = new Intent(this,ActivityCadastros.class);
        startActivity(it);
    }
    //Botão para ir a tela de cadastro deste obj
    @OnClick(R.id.btn_cadastrar)
    public void cadastrar(){
        Intent irParaOpcao =  new Intent(this, ActivityCliente.class);
        startActivity(irParaOpcao);
    }

    public void listar(){
        ApiWeb.getRotas().listaClientes(new Callback<List<Cliente>>() {
            @Override
            public void success(List<Cliente> clientes, Response response) {
                //Carrengando no ListView
                adapter = new ArrayAdapter<>(ActivityConsultaCliente.this, android.R.layout.simple_list_item_1, clientes);
                listaCliente.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }

        });
    }



}
