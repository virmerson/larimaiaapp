package br.ucdb.larimaiaapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.List;
import br.ucdb.larimaiaapp.R;
import br.ucdb.larimaiaapp.api.ApiWeb;
import br.ucdb.larimaiaapp.model.Produto;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by MarceloPC on 30/09/2015.
 */
public class ActivityConsultaProduto extends AppCompatActivity {
    @Bind(R.id.lista_consulta)
    ListView listaProduto;

    @Bind(R.id.btn_lista_voltar)
    Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consulta_produto);

        ButterKnife.bind(this);

        ApiWeb.getRotas().listarProduto(new Callback<List<Produto>>() {
            @Override
            public void success(List<Produto> produtos, Response response) {
                //Carrengando os itens na Lista
                ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(ActivityConsultaProduto.this, android.R.layout.simple_list_item_1, produtos);
                listaProduto.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    public void voltar(View view) {
        Intent it = new Intent(this, ActivityProduto.class);
        startActivity(it);
    }


}

