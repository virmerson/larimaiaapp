package ucdb.larimaia.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ucdb.larimaia.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_cadastros)
    public void cadastro(){
        Intent irParaOpcao =  new Intent(this, ActivityCadastros.class);
        startActivity(irParaOpcao);
    }
    @OnClick(R.id.btn_consultar_pedido)
    public void consultarPedido(){

    }
    @OnClick(R.id.btn_novo_pedido)
    public void novoPedido(){

    }
}
