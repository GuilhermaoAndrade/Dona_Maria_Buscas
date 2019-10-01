package br.com.etecia.dona_maria_buscas;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    EditText txtEmail, txtNome, txtSenha,txtConfirmSenha;
    Button btnRegistro;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_layout);
        getSupportActionBar().setTitle("Cadastrado");

        txtNome = (EditText)findViewById(R.id.txtNome);
        txtEmail = (EditText)findViewById(R.id.txtNome);
        txtSenha = (EditText)findViewById(R.id.txtSenha);
        txtConfirmSenha = (EditText)findViewById(R.id.txtConfirmSenha);
        btnRegistro = (Button)findViewById(R.id.btnRegistro);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);


        firebaseAuth = FirebaseAuth.getInstance();

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = txtNome.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                String senha = txtSenha.getText().toString().trim();
                String confirmsenha = txtConfirmSenha.getText().toString().trim();

                if (TextUtils.isEmpty(nome)){
                    Toast.makeText(CadastroActivity.this, "Por favor digite um nome", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(CadastroActivity.this, "Por favor digite um email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(senha)){
                    Toast.makeText(CadastroActivity.this, "Por favor digite uma senha", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmsenha)){
                    Toast.makeText(CadastroActivity.this, "Por favor confirme a senha", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (senha.length()<6){
                    Toast.makeText(CadastroActivity.this, "Senha muito pequena", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);


                if (senha.equals(confirmsenha)){

                    firebaseAuth.createUserWithEmailAndPassword(email, senha)
                            .addOnCompleteListener(CadastroActivity.this, new  OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {


                                        startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                                        Toast.makeText(CadastroActivity.this, "Registrado com sucesso", Toast.LENGTH_SHORT).show();


                                    } else {

                                        Toast.makeText(CadastroActivity.this, "Falha no Registro", Toast.LENGTH_SHORT).show();


                                    }

                                    // ...
                                }
                            });




                }

            }
        });


    }
}

