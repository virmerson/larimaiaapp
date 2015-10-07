package br.ucdb.larimaiaapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.ucdb.larimaiaapp.R;
import br.ucdb.larimaiaapp.api.ApiWeb;
import br.ucdb.larimaiaapp.model.Cliente;
import br.ucdb.larimaiaapp.model.Produto;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActivityConsultaProduto extends AppCompatActivity {

    @Bind(R.id.lista)
    ListView listaProduto;

    @Bind(R.id.btn_lista_voltar)
    Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_produto);

        ButterKnife.bind(this);

        ApiWeb.getRotas().listaProdutos(new Callback<List<Produto>>() {
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

    public void voltar(View view){
        Intent it = new Intent(this,ActivityProduto.class);
        startActivity(it);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_consulta_produto, menu);
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
