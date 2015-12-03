package br.ucdb.larimaiaapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ucdb.larimaiaapp.R;
import br.ucdb.larimaiaapp.api.ApiWeb;
import br.ucdb.larimaiaapp.model.Cerimonial;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActivityCerimonial extends AppCompatActivity {

    @Bind(R.id.txt_nome)
    EditText txt_nome;

    @Bind(R.id.btn_salvar_cliente)
    Button salvar;

    @Bind(R.id.btn_Listar_cliente)
    Button listar;

    @Bind(R.id.btn_lista_voltar)
    Button voltar;

    public  Cerimonial cerimonial;
    public static Cerimonial aux;
    static boolean valida = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerimonial);
        setTitle("Cadastro de Cerimonial");
        ButterKnife.bind(this);

        Intent intent = getIntent();
        aux = (Cerimonial) intent.getSerializableExtra("Cerimonial");
        if(aux!=null){
            TelaEditar(aux);
        }
    }

    @OnClick(R.id.btn_salvar_cliente)
    public void salvar(){
        if(valida==false){
            Cerimonial c = new Cerimonial();
            c.setDescricao(txt_nome.getText().toString());

            ApiWeb.getRotas().salvarCerimonial(c, new Callback<Response>() {
                @Override
                public void success(Response response, Response response2) {
                    Toast.makeText(ActivityCerimonial.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                    txt_nome.setText(null);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(ActivityCerimonial.this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Editar();
        }
    }

    public void Editar(){
        cerimonial.setDescricao(txt_nome.getText().toString());
        ApiWeb.getRotas().salvarCerimonial(cerimonial, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                Toast.makeText(ActivityCerimonial.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                txt_nome.setText(null);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActivityCerimonial.this, "Falha ao Salvar", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @OnClick(R.id.btn_Listar_cliente)
    public void listar() {
        Intent irParaTelaListar = new Intent(this, ActivityConsultaCerimonial.class);
        startActivityForResult(irParaTelaListar, 1);
    }

    @OnClick(R.id.btn_lista_voltar)
    public void voltar(){
        Intent it = new Intent(this,ActivityCadastros.class);
        startActivity(it);
    }

    public void TelaEditar(Cerimonial c){
        cerimonial = aux;
        valida = true;

        txt_nome.setText(aux.getDescricao());
    }

}
