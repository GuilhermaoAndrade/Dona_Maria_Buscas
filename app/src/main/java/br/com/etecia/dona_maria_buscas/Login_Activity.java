package br.com.etecia.dona_maria_buscas;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {

    EditText txtUsuario, txtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        txtUsuario = (EditText) findViewById(R.id.edtUsuario);
        txtSenha = (EditText)findViewById(R.id.edtSenha);
    }


    public void sairApp(View view) {
        finish();
    }

    public void entrarApp(View view) {

        String usuario = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();

        if (usuario.equals("Guilherme") && senha.equals("1234")) {

            Toast.makeText(getApplicationContext(),"Bem vindo ao sistema",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(),"Usuário ou senha inválidos",Toast.LENGTH_SHORT).show();
        }
    }
}
