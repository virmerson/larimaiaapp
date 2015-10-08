package br.ucdb.larimaiaapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import br.ucdb.larimaiaapp.R;
import br.ucdb.larimaiaapp.api.ApiWeb;
import br.ucdb.larimaiaapp.model.Produto;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ActivityProduto extends AppCompatActivity {

    @Bind(R.id.et_id)
    EditText etId;
    @Bind(R.id.et_descricao)
    EditText etDescricao;
    @Bind(R.id.et_valor)
    EditText etValor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_salvar)
    public void Salvar(){

        Produto produto = new Produto();


        produto.setDescricao(etDescricao.getText().toString());
        produto.setValor(Double.parseDouble(etValor.getText().toString()));

        ApiWeb.getRotas().salvarProduto(produto, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Toast.makeText(ActivityProduto.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActivityProduto.this, "Falha ao salvar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn_editar)
    public void Editar(){

        Long id = Long.parseLong(etId.getText().toString());

        ApiWeb.getRotas().editarProduto(id, new Callback<Produto>() {
            @Override
            public void success(Produto produto, Response response) {
                etId.setText(produto.getId().toString());
                etDescricao.setText(produto.getDescricao().toString());
                etValor.setText(produto.getValor().toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActivityProduto.this, "Falha ao editar", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick(R.id.btn_excluir)
    public void Excluir(){

        Long id = Long.parseLong(etId.getText().toString());

        ApiWeb.getRotas().excluirProduto(id, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Toast.makeText(ActivityProduto.this, "Ecluido com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActivityProduto.this, "Falha ao excluir", Toast.LENGTH_SHORT).show();
            }
        });

    }




}
