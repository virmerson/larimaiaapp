package br.ucdb.larimaiaapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.ucdb.larimaiaapp.R;
import br.ucdb.larimaiaapp.api.ApiWeb;
import br.ucdb.larimaiaapp.model.Cerimonial;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActivityConsultaCerimonial extends AppCompatActivity {

    @Bind(R.id.lista_consulta)
    ListView lista;

    ArrayAdapter<Cerimonial> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cerimonial);
        setTitle("Consulta Cerimonial");
        ButterKnife.bind(this);

        listar();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Cerimonial cerimonial = (Cerimonial) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(ActivityConsultaCerimonial.this, ActivityCerimonial.class);
                intent.putExtra("Cerimonial",cerimonial);
                startActivity(intent);
            }
        });
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Cerimonial cerimonial = (Cerimonial) parent.getItemAtPosition(position);
                AlertDialog alert = new AlertDialog.Builder(ActivityConsultaCerimonial.this)
                        .setTitle("Excluir")
                        .setMessage("Deseja realmente excluir?")
                        .setNegativeButton("Não", null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, final int i) {
                                //startLoading();
                                //Chamando API para remover
                                ApiWeb.getRotas().excluirCerimonial(cerimonial.getId(), new Callback<Response>() {
                                    @Override
                                    public void success(Response response, Response response2) {
                                        listar();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        Toast.makeText(ActivityConsultaCerimonial.this, "Erro ao conectar com o servidor", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).show();
                return true;
            }

        });


    }

    public void listar(){
        ApiWeb.getRotas().listaCerimonial(new Callback<List<Cerimonial>>() {
            @Override
            public void success(List<Cerimonial> cerimonial, Response response) {
                //Carrengando no ListView
                adapter = new ArrayAdapter<>(ActivityConsultaCerimonial.this, android.R.layout.simple_list_item_1, cerimonial);
                lista.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }

        });
    }

    @OnClick(R.id.btn_lista_voltar)
    public void voltar(){
        Intent it = new Intent(this,ActivityCadastros.class);
        startActivity(it);
    }
    //Botão para ir a tela de cadastro deste obj
    @OnClick(R.id.btn_cadastrar)
    public void cadastrar(){
        Intent irParaOpcao =  new Intent(this, ActivityCerimonial.class);
        startActivity(irParaOpcao);
    }

}
