package com.example.appfirabasefirestore

//Por: Luisa Santos Silva - 3°DS AMS

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appfirabasefirestore.ui.theme.AppFirabaseFirestoreTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.material3.MaterialTheme.colorScheme as colorScheme1

class MainActivity : ComponentActivity() {
    // Cria uma instância do Firebase Firestore
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Define o tema da aplicação e chama o composable 'App' passando o Firestore
            AppFirabaseFirestoreTheme {
                App(db)
            }
        }
    }

    // Função composable
    @Composable
    fun App(db: FirebaseFirestore) {
        // Variáveis mutáveis que armazenam os valores inseridos nos campos de texto
        var nome by remember {
            mutableStateOf("")  // Armazena o valor do campo 'nome'
        }
        var telefone by remember {
            mutableStateOf("")  // Armazena o valor do campo 'telefone'
        }

        // Organiza os elementos em uma coluna vertical
        Column(
            Modifier
                .fillMaxWidth()  // Define a largura máxima para a coluna
        ) {
            // Linha (vazia, apenas para espaçamento)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)  // Define um padding de 20 dp
            ) {}

            // Linha com o título centralizado
            Row(
                Modifier
                    .fillMaxWidth(),
                Arrangement.Center  // Alinha o conteúdo ao centro da linha
            ) {
                Text(text = "App Firebase Firestore")  // Título do aplicativo
            }

            // Linha (vazia, apenas para espaçamento)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)  // Define um padding de 20 dp
            ) {}

            // Primeira linha do formulário: campo de entrada para o nome
            Row(
                Modifier
                    .fillMaxWidth()  // Define a largura máxima para a linha
            ) {
                // Coluna para o texto "Nome:"
                Column(
                    Modifier
                        .fillMaxWidth(0.3f)
                ) {
                    Text(text = "Nome:")  // Rótulo do campo de nome
                }
                // Coluna para o campo de entrada do nome
                Column {
                    TextField(
                        value = nome,  // Valor atual do campo 'nome'
                        onValueChange = { nome = it }  // Atualiza o valor do nome quando o usuário digita
                    )
                }
            }

            // Segunda linha do formulário: campo de entrada para o telefone
            Row(
                Modifier
                    .fillMaxWidth()  // Define a largura máxima para a linha
            ) {
                // Coluna para o texto "Telefone:"
                Column(
                    Modifier
                        .fillMaxWidth(0.3f)  // Atribui 30% da largura total
                ) {
                    Text(text = "Telefone:")  // Rótulo do campo de telefone
                }
                // Coluna para o campo de entrada do telefone
                Column {
                    TextField(
                        value = telefone,  // Valor atual do campo 'telefone'
                        onValueChange = { telefone = it }  // Atualiza o valor do telefone quando o usuário digita
                    )
                }
            }

            // Linha (vazia, apenas para espaçamento)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)  // Define um padding de 20 dp
            ) {}

            // Botão "Cadastrar" centralizado
            Row(
                Modifier
                    .fillMaxWidth(),
                Arrangement.Center  // Alinha o botão ao centro
            ) {
                // Botão de cadastro
                Button(onClick = {
                    // Cria um hashMap com os dados do nome e telefone
                    val city = hashMapOf(
                        "nome" to nome,  // Chave 'nome' com valor inserido no campo de nome
                        "telefone" to telefone  // Chave 'telefone' com valor inserido no campo de telefone
                    )

                    // Adiciona os dados ao Firestore na coleção "Clientes" e documento "PrimeiroCliente"
                    db.collection("Clientes").document("PrimeiroCliente")
                        .set(city)  // Grava os dados no Firestore
                        .addOnSuccessListener {
                            // Se a gravação for bem-sucedida, loga a mensagem de sucesso
                            Log.d(
                                ContentValues.TAG,
                                "DocumentSnapshot successfully written"
                            )
                        }
                        .addOnFailureListener { e ->
                            // Se ocorrer um erro, loga a mensagem de erro
                            Log.w(
                                ContentValues.TAG,
                                "Error writing document",
                                e
                            )
                        }
                }) {
                    Text(text = "Cadastrar")  // Texto exibido no botão
                }
            }
        }
    }
}
