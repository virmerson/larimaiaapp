package br.ucdb.larimaiaapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import br.ucdb.larimaiaapp.R;
import br.ucdb.larimaiaapp.api.ApiWeb;
import br.ucdb.larimaiaapp.model.TipoEvento;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by williamluciodonascimento on 30/09/15.
 */
public class ActivityTipoEvento extends AppCompatActivity {


    @Bind(R.id.etDescricao)
    EditText txtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipoevento);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnSalvar)
    public void salvarEvento(){
        TipoEvento te = new TipoEvento();
        te.setDescricao(txtDescricao.getText().toString());
        ApiWeb.getRotas().salvarTipoEvento(te, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Toast.makeText(ActivityTipoEvento.this, "Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActivityTipoEvento.this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(this,te.toString(), Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.btnEditar)
    public void Editar(){

        Long id = Long.parseLong(txtDescricao.getText().toString());

        ApiWeb.getRotas().editarTipoEvento(id, new Callback<TipoEvento>() {
            @Override
            public void success(TipoEvento tipoevento, Response response) {
                txtDescricao.setText(tipoevento.getDescricao().toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActivityTipoEvento.this, "Falha ao editar", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick(R.id.btnExcluir)
    public void Excluir(){

        Long id = Long.parseLong(txtDescricao.getText().toString());

        ApiWeb.getRotas().excluirTipoEvento(id, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Toast.makeText(ActivityTipoEvento.this, "Ecluido com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActivityTipoEvento.this, "Falha ao excluir", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
