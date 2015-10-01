package br.ucdb.larimaiaapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


import br.ucdb.larimaiaapp.R;

public class ActivityCliente extends AppCompatActivity {


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
    }

    @OnClick(R.id.btn_salvar_cliente)
    public void salvar(){
       /*  Produto.Cliente c = new Produto.Cliente();
        c.setNome(txtNome.getText().toString());
        c.setEmail(txtEmail.getText().toString());
        c.setTelefone(txtTelefone.getText().toString());


        Toast.makeText(this, c.toString(), Toast.LENGTH_SHORT).show();
*/

    }

    @OnClick(R.id.btn_Listar_cliente)
    public void listar(){

    }

}

/* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro_cliente, menu);
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
    }*/